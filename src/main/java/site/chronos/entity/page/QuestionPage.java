package site.chronos.entity.page;

import site.chronos.entity.page.core.AbstractPageForm;

public class QuestionPage extends AbstractPageForm<QuestionPage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
