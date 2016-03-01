package com.zju.yibao.bean;

/**
 * Created by Atlas on 16/3/1.
 */
public class MyInformation {
    /**
     * studentId : 1
     * nickName : 123
     * email : 123@qq.com
     * headPortraits : null
     * studentAge : 18
     * realName : 小张
     * gender : 男
     * preference : 小提琴
     */

    private int studentId;
    private String nickName;
    private String email;
    private Object headPortraits;
    private int studentAge;
    private String realName;
    private String gender;
    private String preference;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeadPortraits(Object headPortraits) {
        this.headPortraits = headPortraits;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public Object getHeadPortraits() {
        return headPortraits;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public String getRealName() {
        return realName;
    }

    public String getGender() {
        return gender;
    }

    public String getPreference() {
        return preference;
    }
}
