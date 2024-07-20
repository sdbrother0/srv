package srv.controller;

import srv.dto.meta.MetaData;
import srv.entity.Test1Entity;
import srv.repository.Test1Repository;
import srv.entity.Test3Entity;
import srv.repository.Test3Repository;
import srv.dto.Table3Dto;
import srv.specification.SimpleLikeSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srv.mapper.MapperService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Test3Controller {

    private final Test1Repository test1Repository;
    private final Test3Repository test3Repository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    private static final String DATA_URL = "/test3";

    @GetMapping("/meta" + DATA_URL)
    public Object getMetaData3() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/test3",
                    "name": "test3",
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
                                "name": "text"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

    @GetMapping(DATA_URL)
    public Page<Test3Entity> findAll(Pageable pageable,
                                     @RequestParam(value = "masterId", required = false) UUID masterId,
                                     @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(masterId)) {
            return new PageImpl<>(Collections.emptyList());
        }
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<Test3Entity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null, Map.of("test1.id", masterId));
        return test3Repository.findAll(simpleLikeSpecification, pageable);
    }

    @Transactional
    @DeleteMapping(DATA_URL)
    public Test3Entity delete(@RequestParam UUID id) {
        Test3Entity test3Entity = test3Repository.findById(id).orElseThrow();
        test3Repository.deleteById(id);
        Test1Entity test1Entity = test1Repository.findById(test3Entity.getTest1().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(test1Entity);
        return test3Entity;
    }

    @PostMapping(DATA_URL)
    @Transactional
    public Test3Entity save(@RequestBody Table3Dto table3Dto) {

        Test1Entity test1Entity = MapperService.test1Mapper.map(table3Dto.getTest1());
        test1Entity = test1Repository.save(test1Entity);

        Test3Entity test3Entity = MapperService.test3Mapper.map(table3Dto);
        test3Entity.setTest1(test1Entity);
        test3Repository.save(test3Entity);

        entityManager.flush();
        entityManager.refresh(test1Entity);

        return test3Entity;
    }

}