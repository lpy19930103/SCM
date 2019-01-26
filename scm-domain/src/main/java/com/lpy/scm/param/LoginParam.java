package com.lpy.scm.param;

/**
 * @author lpy
 * @date 2019/1/26 10:36
 */
public class LoginParam extends HeaderParam {
    private String userName;
    private String userPass;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
