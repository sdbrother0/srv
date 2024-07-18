package com.example.demo.controller;

import com.example.demo.dto.meta.MetaData;
import com.example.demo.entity.Test1Entity;
import com.example.demo.repository.Test1Repository;
import com.example.demo.dto.Table1Dto;
import com.example.demo.specification.SimpleLikeSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.example.demo.mapper.MapperService.test1Mapper;

@RestController
@RequiredArgsConstructor
@Log4j2
public class Test1Controller {

    private final Test1Repository test1Repository;
    private final ObjectMapper objectMapper;

    private static final String DATA_URL = "/test1";
    private final DataSource dataSource;

    @GetMapping("/meta" + DATA_URL)
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
                                "name": "string"
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
                                "name": "string"
                            },
                            "editable": true,
                            "validation": {
                                "required": true,
                                "message": "Input please!!!"
                            }
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
                                "name": "number"
                            },
                            "editable": false
                        },
                        {
                            "name": "mySum4",
                            "label": "Сумма тест 4",
                            "type": {
                                "name": "number"
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
    }

    @GetMapping(DATA_URL)
    public Page<Test1Entity> findAll(Pageable pageable,
                                     @RequestParam(name = "search", required = false) List<String> search) {
        Specification<Test1Entity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return test1Repository.findAll(simpleLikeSpecification, pageable);
    }

    @DeleteMapping(DATA_URL)
    public Test1Entity delete(@RequestParam UUID id) {
        Test1Entity test1Entity = test1Repository.findById(id).orElseThrow();
        test1Repository.deleteById(id);
        return test1Entity;
    }

    @PostMapping(DATA_URL)
    public Test1Entity save(@RequestBody Table1Dto table1Dto) {
        Test1Entity test1Entity = test1Mapper.map(table1Dto);
        return test1Repository.saveAndFlush(test1Entity);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> report() throws JRException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        InputStream inputStream = getClass().getResourceAsStream("/reports/employeeReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());
        byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        return ResponseEntity.ok().headers(headers).body(pdf);
    }


}
