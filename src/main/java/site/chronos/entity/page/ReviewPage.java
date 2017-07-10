package site.chronos.entity.page;

import site.chronos.entity.page.core.AbstractPageForm;

public class ReviewPage extends AbstractPageForm<ReviewPage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String adminId;
    
    private String userId;
    
    private Integer isDel = 0;
    
    private Integer status;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
