package site.chronos.entity;

import java.io.Serializable;
import java.util.Date;

public class QuestionRecording implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String questionId;

    private String createTime;

    private String createBy;

    private String questionFunc;

    private String questionFuncAbout;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }


    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getQuestionFunc() {
        return questionFunc;
    }

    public void setQuestionFunc(String questionFunc) {
        this.questionFunc = questionFunc == null ? null : questionFunc.trim();
    }

    public String getQuestionFuncAbout() {
        return questionFuncAbout;
    }

    public void setQuestionFuncAbout(String questionFuncAbout) {
        this.questionFuncAbout = questionFuncAbout == null ? null : questionFuncAbout.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}