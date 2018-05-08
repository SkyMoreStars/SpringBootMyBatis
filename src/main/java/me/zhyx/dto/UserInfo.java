package me.zhyx.dto;

import me.zhyx.common.annotation.Column;
import me.zhyx.common.annotation.Id;
import me.zhyx.common.annotation.Table;
import me.zhyx.common.base.entity.BaseEntity;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:38
 */
@Table(value = "USER_INFO")
public class UserInfo extends BaseEntity {
    private long id;
    private String userCode;
    private String userName;
    private String userPwd;
    private String remark;

    @Id(value = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(value = "USER_CODE")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(value = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(value = "USER_PWD")
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Column(value = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
