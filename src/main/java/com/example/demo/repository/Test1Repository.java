package com.example.demo.repository;

import com.example.demo.entity.Test1Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Test1Repository extends JpaRepository<Test1Entity, UUID>, JpaSpecificationExecutor<Test1Entity> {

    @Override
    //Solved n+1 queries
    @EntityGraph(attributePaths = {"id", "field1", "field2", "created", "test2.id", "test2.val"})
    Page<Test1Entity> findAll(Specification<Test1Entity> spec, Pageable pageable);

}
