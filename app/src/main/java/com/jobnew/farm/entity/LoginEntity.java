package com.jobnew.farm.entity;

/**
 * Created by wc on 2017/5/31.
 * Function：
 * desc：
 */

public class LoginEntity {

    /**
     * token : string
     * user : {"account":"string","avatar":"string","createTime":"2017-05-31T08:50:54.437Z","id":0,"lastLoginTime":"2017-05-31T08:50:54.437Z","loginNum":0,"modifyTime":"2017-05-31T08:50:54.437Z","name":"string","passwd":"string","paypwd":"string","phone":"string","points":0,"qqOpenId":"string","remark":"string","role":"string","sex":"string","signature":"string","state":"string","wbOpenId":"string","wxOpenId":"string"}
     */

    private String token;
    /**
     * account : string
     * avatar : string
     * createTime : 2017-05-31T08:50:54.437Z
     * id : 0
     * lastLoginTime : 2017-05-31T08:50:54.437Z
     * loginNum : 0
     * modifyTime : 2017-05-31T08:50:54.437Z
     * name : string
     * passwd : string
     * paypwd : string
     * phone : string
     * points : 0
     * qqOpenId : string
     * remark : string
     * role : string
     * sex : string
     * signature : string
     * state : string
     * wbOpenId : string
     * wxOpenId : string
     */

    private UserEntity user;

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserEntity getUser() {
        return user;
    }

    public static class UserEntity {
        private String account;
        private String avatar;
        private String createTime;
        private int id;
        private long lastLoginTime;
        private int loginNum;
        private String modifyTime;
        private String name;
        private String passwd;
        private String paypwd;
        private String phone;
        private int points;
        private String qqOpenId;
        private String remark;
        private String role;
        private String sex;
        private String signature;
        private String state;
        private String wbOpenId;
        private String wxOpenId;
        private long birthday;
        private String city;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public void setLoginNum(int loginNum) {
            this.loginNum = loginNum;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public void setPaypwd(String paypwd) {
            this.paypwd = paypwd;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public void setQqOpenId(String qqOpenId) {
            this.qqOpenId = qqOpenId;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setWbOpenId(String wbOpenId) {
            this.wbOpenId = wbOpenId;
        }

        public void setWxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
        }

        public String getAccount() {
            return account;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getCreateTime() {
            return createTime;
        }

        public int getId() {
            return id;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public int getLoginNum() {
            return loginNum;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public String getName() {
            return name;
        }

        public String getPasswd() {
            return passwd;
        }

        public String getPaypwd() {
            return paypwd;
        }

        public String getPhone() {
            return phone;
        }

        public int getPoints() {
            return points;
        }

        public String getQqOpenId() {
            return qqOpenId;
        }

        public String getRemark() {
            return remark;
        }

        public String getRole() {
            return role;
        }

        public String getSex() {
            return sex;
        }

        public String getSignature() {
            return signature;
        }

        public String getState() {
            return state;
        }

        public String getWbOpenId() {
            return wbOpenId;
        }

        public String getWxOpenId() {
            return wxOpenId;
        }
    }

}
