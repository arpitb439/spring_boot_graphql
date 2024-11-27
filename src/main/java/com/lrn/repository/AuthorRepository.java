package com.lrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrn.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}