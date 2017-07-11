package site.chronos.entity.page;

import site.chronos.entity.page.core.AbstractPageForm;

public class QuestionPage extends AbstractPageForm<QuestionPage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String isReview;
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsReview() {
		return isReview;
	}

	public void setIsReview(String isReview) {
		this.isReview = isReview;
	}
	
	
	

}
