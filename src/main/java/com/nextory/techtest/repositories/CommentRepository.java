package com.nextory.techtest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextory.techtest.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
