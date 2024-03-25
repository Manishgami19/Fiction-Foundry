package com.nextory.techtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextory.techtest.models.Comment;
import com.nextory.techtest.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(comment -> comments.add(comment));
        return (comments);
    }
}
