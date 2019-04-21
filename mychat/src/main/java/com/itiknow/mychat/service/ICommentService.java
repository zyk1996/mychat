package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Comment;

import java.util.List;

public interface ICommentService {
    Integer insert(Comment comment);
    Comment selectById(String id);
    List<Comment> selectCommitsBySpace(Long spaceId);
}
