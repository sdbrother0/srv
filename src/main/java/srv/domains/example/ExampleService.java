package srv.domains.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.meta.MetaData;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.service.MapperService.exampleMapper;

@RequiredArgsConstructor
@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;
    private final ObjectMapper objectMapper;

    public Page<ExampleDto> findAll(Pageable pageable, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<ExampleEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return exampleRepository.findAll(simpleLikeSpecification, pageable).map(exampleMapper::map);
    }

    @Transactional
    public ExampleDto delete(@RequestParam UUID id) {
        ExampleEntity exampleEntity = exampleRepository.findById(id).orElseThrow();
        exampleRepository.deleteById(id);
        return exampleMapper.map(exampleEntity);
    }

    @Transactional
    public ExampleDto save(@RequestBody ExampleDto exampleDto) {
        ExampleEntity exampleEntity = exampleMapper.map(exampleDto);
        ExampleEntity savedExampleEntity = exampleRepository.save(exampleEntity);
        return exampleMapper.map(savedExampleEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "/example",
                    "name": "example",
                    "key": "id",
                    "fields": [
                        {
                            "name": "id",
                            "label": "Id",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "name",
                            "label": "Example name",
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

}
