package com.example.demo.controller;

import com.example.demo.dto.meta.MetaData;
import com.example.demo.entity.Test1Entity;
import com.example.demo.repository.Test1Repository;
import com.example.demo.repository.Test2Repository;
import com.example.demo.dto.Table1Dto;
import com.example.demo.specification.SimpleLikeSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.example.demo.mapper.MapperService.test1Mapper;

@RestController
@RequiredArgsConstructor
public class Test1Controller {
    private final Test1Repository test1Repository;
    private final Test2Repository test2Repository;

    private final ObjectMapper objectMapper;

    @GetMapping("/meta/test1")
    public Object getMetaData() throws JsonProcessingException {

        return objectMapper.readValue( """
                {
                    "url" : "http://localhost:8090/test1",
                    "name": "test1",
                    "key": "id",
                    "showSelect": false,
                    "showAction": true,
                    "showLoader": true,
                    "fields": [
                        {
                            "name": "id",
                            "label": "ИД (UUID)",
                            "type": {
                                "name": "test"
                            },
                            "hidden": false
                        },
                        {
                            "name": "created",
                            "label": "Дата",
                            "type": {
                                "name": "date",
                                "format": "yyyy-MM-dd HH:mm:ss"
                            },
                            "editable": true
                        },
                        {
                            "name": "test",
                            "label": "Тест",
                            "type": {
                                "name": "text"
                            },
                            "editable": true
                        },
                        {
                            "name": "test2",
                            "label": "Вал",
                            "type": {
                                "name": "lookup",
                                "metaUrl": "http://localhost:8090/meta/test2",
                                "foreignKey": "test2_id",
                                "keyFieldName": "id",
                                "valFieldName": "val"
                            },
                            "editable": true
                        },
                        {
                            "name": "myCount3",
                            "label": "Количество тест 3",
                            "type": {
                                "name": "text"
                            },
                            "editable": false
                        },
                        {
                            "name": "myCount4",
                            "label": "Количество тест 4",
                            "type": {
                                "name": "text"
                            },
                            "editable": false
                        }
                    ],
                    "details": [
                          {
                            "label": "Test 3 table",
                            "metaUrl" : "http://localhost:8090/meta/test3"
                          },
                          {
                            "label": "Test 4 table",
                            "metaUrl" : "http://localhost:8090/meta/test4"
                          }
                    ]
                }
            """, MetaData.class);

        /*

        return MetaData.builder()
            .url("http://localhost:8090/test1")
            .keyFieldName("id")
            .showSelect(true)
            .showLoader(true)
            .showAction(true)
            .fields(List.of(
                Field.builder().name("id").label("ИД (UUID)").hidden(true)
                    .type(FieldType.builder().name("text").build()).build(),
                Field.builder().name("created").label("Дата").hidden(false)
                    .type(FieldType.builder().name("date").format("yyyy-MM-dd HH:mm:ss").build()).build(),
                Field.builder().name("test2").label("Вал").hidden(false)
                    .type(FieldType.builder().name("lookup")
                        .metaUrl("http://localhost:8090/meta/test2")
                        .foreignKey("test2_id")
                        .keyFieldName("id")
                        .valFieldName("val")
                    .build()).build(),
                Field.builder().name("test").label("Тест").hidden(false)
                    .type(FieldType.builder().name("text").build()).build())
            )
            .details(List.of(Detail
                .builder()
                    .label("Test 3 table")
                    .metaUrl("http://localhost:8090/meta/test3")
                    .masterObjectName("test1")
                    .masterFieldKey("id")
                .build())).build();

         */
    }

    @GetMapping("/test1")
    public Page<Test1Entity> findAll(Pageable pageable,
                                     @RequestParam(name = "search", required = false) List<String> search) {
        Specification<Test1Entity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return test1Repository.findAll(simpleLikeSpecification, pageable);
    }

    @DeleteMapping("/test1")
    public Test1Entity delete(@RequestParam UUID id) {
        Test1Entity test1Entity = test1Repository.findById(id).orElseThrow();
        test1Repository.deleteById(id);
        return test1Entity;
    }

    @PostMapping("/test1")
    public Test1Entity save(@RequestBody Table1Dto table1Dto) {
        Test1Entity test1Entity = test1Mapper.map(table1Dto);
        return test1Repository.saveAndFlush(test1Entity);
    }


}
