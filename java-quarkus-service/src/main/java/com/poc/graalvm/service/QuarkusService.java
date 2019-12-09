package com.poc.graalvm.service;

import javax.enterprise.context.ApplicationScoped;

import com.poc.graal.obj.Comment;

@ApplicationScoped
public class QuarkusService {

    public Comment saveComment(String name) {
        return new Comment();
    }

}
