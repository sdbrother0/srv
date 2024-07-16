package com.example.demo.controller;

import com.example.demo.dto.Table4Dto;
import com.example.demo.dto.meta.MetaData;
import com.example.demo.entity.Test1Entity;
import com.example.demo.entity.Test4Entity;
import com.example.demo.repository.Test1Repository;
import com.example.demo.repository.Test4Repository;
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
import static com.example.demo.mapper.MapperService.test4Mapper;

@RestController
@RequiredArgsConstructor
public class Test4Controller {

    private final Test1Repository test1Repository;
    private final Test4Repository test4Repository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    private static final String DATA_URL = "/test4";

    @GetMapping("/meta" + DATA_URL)
    public Object getMetaData3() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/test4",
                    "name": "test4",
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
                            "label": "Вал 4",
                            "type": {
                                "name": "text"
                            },
                            "editable": true
                        },
                        {
                            "name": "amount",
                            "label": "Цена",
                            "type": {
                                "name": "number"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

    @GetMapping(DATA_URL)
    public Page<Test4Entity> findAll(Pageable pageable,
                                     @RequestParam(value = "masterId", required = false) UUID masterId,
                                     @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(masterId)) {
            return new PageImpl<>(Collections.emptyList());
        }
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<Test4Entity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null, Map.of("test1.id", masterId));
        return test4Repository.findAll(simpleLikeSpecification, pageable);
    }

    @Transactional
    @DeleteMapping(DATA_URL)
    public Test4Entity delete(@RequestParam UUID id) {
        Test4Entity test4Entity = test4Repository.findById(id).orElseThrow();
        test4Repository.deleteById(id);
        Test1Entity test1Entity = test1Repository.findById(test4Entity.getTest1().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(test1Entity);
        return test4Entity;
    }

    @PostMapping(DATA_URL)
    @Transactional
    public Test4Entity save(@RequestBody Table4Dto table3Dto) {

        Test1Entity test1Entity = test1Mapper.map(table3Dto.getTest1());
        test1Entity = test1Repository.save(test1Entity);

        Test4Entity test4Entity = test4Mapper.map(table3Dto);
        test4Entity.setTest1(test1Entity);
        test4Repository.save(test4Entity);

        entityManager.flush();
        entityManager.refresh(test1Entity);

        return test4Entity;
    }

}
