package site.chronos.service;

import site.chronos.constant.Result;

public interface CommentService {
	
	
	/**
	 * 根据问题查询评论
	 * @param commentPage
	 * @return
	 */
	public Result selectByQuestion(String questionId);
	
	/**
	 * 根据评论查询评论
	 * @param commentPage
	 * @return
	 */
	public Result selectByComment(String commentId);

}
