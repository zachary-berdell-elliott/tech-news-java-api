package com.technews.controller;

import com.technews.model.Post;
import com.technews.repository.PostRepository;
import com.technews.repository.UserRepository;
import com.technews.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository repository;

    @Autowired
    VoteRepository VoteRepository;

    @Autowired
    UserRepository UserRepository;

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        List<Post> postList = repository.findAll();
        for (Post p : postList) {
            p.setVoteCount(VoteRepository.countVotesByPostId(p.getId()));
        }

        return postList;
    }

    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable Integer id) {
        Post returnPost = repository.getOne(id);
        returnPost.setVoteCount(VoteRepository.countVotesByPostId(returnPost.getId()));
        
        return returnPost;
    }
}
