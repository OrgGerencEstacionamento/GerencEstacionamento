package io.github.pi.gerencestacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pi.gerencestacionamento.model.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
    
}
