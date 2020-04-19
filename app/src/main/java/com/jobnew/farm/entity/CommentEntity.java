package com.jobnew.farm.entity;

/**
 * Created by wufengqiao on 2017/6/8.
 * function：
 * desc：
 */

public class CommentEntity {

    /**
     * id : 1
     * content : 评论了个什么鬼
     * grade : 5
     * userId : 1
     * images : ["1.png","2.png"]
     * reply : {"content":"店主回复"}
     * user : {"id":1,"avatar":"kkkkkkk.png","name":"澎湃"}
     */

    public int id;
    public String content;
    public int grade;
    public int userId;
    public String images;
    public ReplyBean reply;
    public UserBean user;

    public static class ReplyBean {
        /**
         * content : 店主回复
         */
        public String content;
    }

    public static class UserBean {
        /**
         * id : 1
         * avatar : kkkkkkk.png
         * name : 澎湃
         */

        public int id;
        public String avatar;
        public String name;
    }
}
