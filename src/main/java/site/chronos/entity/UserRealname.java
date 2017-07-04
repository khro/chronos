package site.chronos.entity;

import java.io.Serializable;
import java.util.Date;

public class UserRealname  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String userId;

    private String idcard;

    private String name;

    private Integer isReal;

    private String realChannel;

    private String realNameTime;

    private Integer isDel;

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