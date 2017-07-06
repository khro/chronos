package site.chronos.entity.page;

import site.chronos.entity.page.core.AbstractPageForm;

public class CommentPage extends AbstractPageForm<CommentPage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String questionId;
	 
	 private String commentId;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	

}
