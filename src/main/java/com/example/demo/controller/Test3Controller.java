package com.example.demo.controller;

import com.example.demo.dto.meta.MetaData;
import com.example.demo.entity.Test1Entity;
import com.example.demo.repository.Test1Repository;
import com.example.demo.entity.Test3Entity;
import com.example.demo.repository.Test3Repository;
import com.example.demo.dto.Table3Dto;
import com.example.demo.specification.SimpleLikeSpecification;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.example.demo.mapper.MapperService.test1Mapper;
import static com.example.demo.mapper.MapperService.test3Mapper;

@RestController
@RequiredArgsConstructor
public class Test3Controller {
    private final Test1Repository test1Repository;
    private final Test3Repository test3Repository;

    private final ObjectMapper objectMapper;

    private final EntityManager entityManager;

    @GetMapping("/meta/test3")
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
                                "name": "text"
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

    @GetMapping("/test3")
    public Page<Test3Entity> findAllTest3(Pageable pageable,
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
    @DeleteMapping("/test3")
    public Test3Entity delete(@RequestParam UUID id) {
        Test3Entity test3Entity = test3Repository.findById(id).orElseThrow();
        test3Repository.deleteById(id);
        Test1Entity test1Entity = test1Repository.findById(test3Entity.getTest1().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(test1Entity);
        return test3Entity;
    }

    @PostMapping("/test3")
    @Transactional
    public Test3Entity save3(@RequestBody Table3Dto table3Dto) {

        Test1Entity test1Entity = test1Mapper.map(table3Dto.getTest1());
        test1Entity = test1Repository.save(test1Entity);

        Test3Entity test3Entity = test3Mapper.map(table3Dto);
        test3Entity.setTest1(test1Entity);
        test3Repository.save(test3Entity);

        entityManager.flush();
        entityManager.refresh(test1Entity);

        return test3Entity;
    }

}
