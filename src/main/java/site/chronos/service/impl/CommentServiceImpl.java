package site.chronos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import site.chronos.constant.Result;
import site.chronos.entity.Comment;
import site.chronos.entity.page.CommentPage;
import site.chronos.mapper.CommentMapper;
import site.chronos.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private CommentMapper CommentMapper;

	@Override
	public Result selectByQuestion(String questionId) {
		CommentPage commentPage = new CommentPage();
		commentPage.setQuestionId(questionId);
		List<Comment> selectByQuestionId = CommentMapper.selectByQuestionId(commentPage.enablePaging());
		PageInfo<Comment> pageInfo = new PageInfo<>(selectByQuestionId);
		return new Result(pageInfo);
	}

	@Override
	public Result selectByComment(String commentId) {
		CommentPage commentPage = new CommentPage();
		commentPage.setCommentId(commentId);
		List<Comment> selectByQuestionId = CommentMapper.selectByCommentId(commentPage.enablePaging());
		PageInfo<Comment> pageInfo = new PageInfo<>(selectByQuestionId);
		return new Result(pageInfo);
	}
}
