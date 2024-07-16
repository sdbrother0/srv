package com.example.demo.repository;

import com.example.demo.entity.Test2Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface Test2Repository extends JpaRepository<Test2Entity, UUID>, JpaSpecificationExecutor<Test2Entity> {
    //@Query("select t from Test2Entity as t")
    //Page<Test2Entity> findAll(Specification<Test2Entity> specification, Pageable pageable);
}
