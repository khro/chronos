package site.chronos.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserRealname", description = "用户实名信息")
public class UserRealname  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
	private Integer id;

	@ApiModelProperty("用户ID")
    private String userId;

	@ApiModelProperty("身份证")
    private String idcard;

	@ApiModelProperty("姓名")
    private String name;

	@ApiModelProperty("是否实名")
    private Integer isReal;

	@ApiModelProperty("实名渠道")
    private String realChannel;

	@ApiModelProperty("实名时间")
    private String realNameTime;

	@ApiModelProperty("是否删除 0已删除 1未删除")
    private Integer isDel;

	@ApiModelProperty("最后更新时间")
    private Date updateTime;

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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    public String getRealChannel() {
        return realChannel;
    }

    public void setRealChannel(String realChannel) {
        this.realChannel = realChannel == null ? null : realChannel.trim();
    }

    public String getRealNameTime() {
        return realNameTime;
    }

    public void setRealNameTime(String realNameTime) {
        this.realNameTime = realNameTime == null ? null : realNameTime.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}