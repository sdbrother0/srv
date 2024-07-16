package com.example.demo.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "test1")
public class Test1Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;
    @Column(name = "test")
    private String test;
    @Column(name = "field1")
    private Integer field1;
    @Column(name = "field2")
    private String field2;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="test2_id")
    private Test2Entity test2;

    @Column
    private LocalDateTime created;

    @Formula(value="(select count(*) FROM test3 WHERE test3.test1_id = id)")
    private Long myCount3;

    @Formula(value="(select count(*) FROM test4 WHERE test4.test1_id = id)")
    private Long myCount4;

}
