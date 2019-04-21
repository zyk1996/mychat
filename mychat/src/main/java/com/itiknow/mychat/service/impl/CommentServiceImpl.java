package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Comment;
import com.itiknow.mychat.mapper.CommentMapper;
import com.itiknow.mychat.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public Integer insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public Comment selectById(String id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<Comment> selectCommitsBySpace(Long spaceId) {
        return commentMapper.selectCommitsBySpace(spaceId);
    }
}
