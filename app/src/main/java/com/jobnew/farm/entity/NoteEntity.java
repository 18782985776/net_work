package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/14.
 * title:
 * note:
 * * 活动留言实体类
 */

public class NoteEntity {

    /**
     * reviewCount : 4
     * list : [{"id":2,"createDate":1501694341000,"modifyDate":null,"content":"测试留言1","member":null,"productId":null,"user":{"id":42,"createDate":null,"modifyDate":null,"account":null,"avatar":null,"sex":null,"passwd":null,"phone":"18961389011","paypwd":null,"name":null,"signature":null,"role":null,"loginNum":null,"lastLoginTime":null,"state":null,"remark":null,"points":null,"qqOpenId":null,"wbOpenId":null,"wxOpenId":null,"city":null,"birthday":null},"imgs":"[\"http://www.baidu.com/img/logo.png\",\"img2.png\"]","replyMsg":"这是一个回复测试","imgList":["http://www.baidu.com/img/logo.png","img2.png"]},{"id":3,"createDate":1501694341000,"modifyDate":null,"content":"测试留言2","member":null,"productId":null,"user":{"id":42,"createDate":null,"modifyDate":null,"account":null,"avatar":null,"sex":null,"passwd":null,"phone":"18961389011","paypwd":null,"name":null,"signature":null,"role":null,"loginNum":null,"lastLoginTime":null,"state":null,"remark":null,"points":null,"qqOpenId":null,"wbOpenId":null,"wxOpenId":null,"city":null,"birthday":null},"imgs":"[\"http://www.baidu.com/img/logo.png\",\"img2.png\"]","replyMsg":"这是一个回复测试","imgList":["http://www.baidu.com/img/logo.png","img2.png"]},{"id":4,"createDate":1501694341000,"modifyDate":null,"content":"测试留言3","member":null,"productId":null,"user":{"id":42,"createDate":null,"modifyDate":null,"account":null,"avatar":null,"sex":null,"passwd":null,"phone":"18961389011","paypwd":null,"name":null,"signature":null,"role":null,"loginNum":null,"lastLoginTime":null,"state":null,"remark":null,"points":null,"qqOpenId":null,"wbOpenId":null,"wxOpenId":null,"city":null,"birthday":null},"imgs":"[\"http://www.baidu.com/img/logo.png\",\"img2.png\"]","replyMsg":"这是一个回复测试","imgList":["http://www.baidu.com/img/logo.png","img2.png"]},{"id":5,"createDate":1501694341000,"modifyDate":null,"content":"测试留言4","member":null,"productId":null,"user":{"id":42,"createDate":null,"modifyDate":null,"account":null,"avatar":null,"sex":null,"passwd":null,"phone":"18961389011","paypwd":null,"name":null,"signature":null,"role":null,"loginNum":null,"lastLoginTime":null,"state":null,"remark":null,"points":null,"qqOpenId":null,"wbOpenId":null,"wxOpenId":null,"city":null,"birthday":null},"imgs":"[\"http://www.baidu.com/img/logo.png\",\"img2.png\"]","replyMsg":"这是一个回复测试","imgList":["http://www.baidu.com/img/logo.png","img2.png"]}]
     */

    private int reviewCount;
    private List<ListBean> list;

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2
         * createDate : 1501694341000
         * modifyDate : null
         * content : 测试留言1
         * member : null
         * productId : null
         * user : {"id":42,"createDate":null,"modifyDate":null,"account":null,"avatar":null,"sex":null,"passwd":null,"phone":"18961389011","paypwd":null,"name":null,"signature":null,"role":null,"loginNum":null,"lastLoginTime":null,"state":null,"remark":null,"points":null,"qqOpenId":null,"wbOpenId":null,"wxOpenId":null,"city":null,"birthday":null}
         * imgs : ["http://www.baidu.com/img/logo.png","img2.png"]
         * replyMsg : 这是一个回复测试
         * imgList : ["http://www.baidu.com/img/logo.png","img2.png"]
         */

        private int id;
        private long createDate;
        private Object modifyDate;
        private String content;
        private Object member;
        private Object productId;
        private UserBean user;
        private String imgs;
        private String replyMsg;
        private List<String> imgList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getReplyMsg() {
            return replyMsg;
        }

        public void setReplyMsg(String replyMsg) {
            this.replyMsg = replyMsg;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public static class UserBean {
            /**
             * id : 42
             * createDate : null
             * modifyDate : null
             * account : null
             * avatar : null
             * sex : null
             * passwd : null
             * phone : 18961389011
             * paypwd : null
             * name : null
             * signature : null
             * role : null
             * loginNum : null
             * lastLoginTime : null
             * state : null
             * remark : null
             * points : null
             * qqOpenId : null
             * wbOpenId : null
             * wxOpenId : null
             * city : null
             * birthday : null
             */

            private int id;
            private Object createDate;
            private Object modifyDate;
            private Object account;
            private String avatar;
            private Object sex;
            private Object passwd;
            private String phone;
            private Object paypwd;
            private String name;
            private Object signature;
            private Object role;
            private Object loginNum;
            private Object lastLoginTime;
            private Object state;
            private Object remark;
            private Object points;
            private Object qqOpenId;
            private Object wbOpenId;
            private Object wxOpenId;
            private Object city;
            private Object birthday;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public Object getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(Object modifyDate) {
                this.modifyDate = modifyDate;
            }

            public Object getAccount() {
                return account;
            }

            public void setAccount(Object account) {
                this.account = account;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getPasswd() {
                return passwd;
            }

            public void setPasswd(Object passwd) {
                this.passwd = passwd;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getPaypwd() {
                return paypwd;
            }

            public void setPaypwd(Object paypwd) {
                this.paypwd = paypwd;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
                this.signature = signature;
            }

            public Object getRole() {
                return role;
            }

            public void setRole(Object role) {
                this.role = role;
            }

            public Object getLoginNum() {
                return loginNum;
            }

            public void setLoginNum(Object loginNum) {
                this.loginNum = loginNum;
            }

            public Object getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(Object lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getPoints() {
                return points;
            }

            public void setPoints(Object points) {
                this.points = points;
            }

            public Object getQqOpenId() {
                return qqOpenId;
            }

            public void setQqOpenId(Object qqOpenId) {
                this.qqOpenId = qqOpenId;
            }

            public Object getWbOpenId() {
                return wbOpenId;
            }

            public void setWbOpenId(Object wbOpenId) {
                this.wbOpenId = wbOpenId;
            }

            public Object getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(Object wxOpenId) {
                this.wxOpenId = wxOpenId;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }
        }
    }
}
