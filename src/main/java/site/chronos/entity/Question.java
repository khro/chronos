package site.chronos.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Question", description = "问题信息")
public class Question  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
	private String id;

	@ApiModelProperty("用户ID")
    private String userId;

	@ApiModelProperty("创建时间")
    private String createTime;

	@ApiModelProperty("最后更新时间")
    private Date updateTime;

	@ApiModelProperty("是否置顶")
    private Integer sort;

	@ApiModelProperty("标题")
    private String title;

	@ApiModelProperty("是否删除  0未删除  1已删除")
    private Integer isDel;

	@ApiModelProperty("问题内容")
    private String question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }
}