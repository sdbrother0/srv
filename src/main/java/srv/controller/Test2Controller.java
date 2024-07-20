package srv.controller;

import srv.dto.Table2Dto;
import srv.dto.meta.MetaData;
import srv.entity.Test2Entity;
import srv.repository.Test2Repository;
import srv.specification.SimpleLikeSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srv.mapper.MapperService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Test2Controller {

    private final Test2Repository test2Repository;
    private final ObjectMapper objectMapper;

    private static final String DATA_URL = "/test2";

    @GetMapping("/meta" + DATA_URL)
    public Object getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/test2",
                    "name": "test2",
                    "key": "id",
                    "showSelect": true,
                    "showAction": true,
                    "showLoader": true,
                    "fields": [
                        {
                            "name": "id",
                            "label": "ИД (UUID)",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "val",
                            "label": "Вал",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

    @GetMapping(DATA_URL)
    public Page<Test2Entity> findAll(Pageable pageable,
                                     @RequestParam(name = "search", required = false) List<String> search,
                                     @RequestParam(name = "keyValue", required = false) String keyValue) {
        Specification<Test2Entity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(),
            Objects.isNull(keyValue) ? null : Pair.of("id", keyValue));

        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return test2Repository.findAll(simpleLikeSpecification, pageRequest);
    }

    @DeleteMapping(DATA_URL)
    public Test2Entity delete(@RequestParam UUID id) {
        Test2Entity test2Entity = test2Repository.findById(id).orElseThrow();
        test2Repository.deleteById(id);
        return test2Entity;
    }

    @PostMapping(DATA_URL)
    public Test2Entity save(@RequestBody Table2Dto table2Dto) {
        Test2Entity test2Entity = MapperService.test2Mapper.map(table2Dto);
        return test2Repository.saveAndFlush(test2Entity);
    }

}
