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
@Table(value = "student")
public class UserInfo extends BaseEntity {
    private long id;
    private String name;
    private String age;
    private String sex;
    private String grade;

    @Id(value = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(value = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Column(value = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column("grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
