package site.chronos.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Review", description = "审核信息")
public class Review implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty("ID")
	private Integer id;

	@ApiModelProperty("用户ID")
    private String userId;

	@ApiModelProperty("审核类型")
    private String action;

	@ApiModelProperty("申请创建时间")
    private Date createTime;

	@ApiModelProperty("最后更新时间")
    private Date updateTime;

	@ApiModelProperty("是否删除  0未删除  1已删除")
    private Integer isDel;

	@ApiModelProperty("状态 0初始 10通过审核")
    private Integer status;

	@ApiModelProperty("审核人")
    private String adminId;

	@ApiModelProperty("审核问题")
    private String questionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }
}