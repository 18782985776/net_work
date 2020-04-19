package com.jobnew.farm.entity.base;

/**
 * Created by wangwenlang on 2017/6/14.
 * title:模拟帮助说明实体类
 * note:
 */

public class HelpEntity {

    /**
     * id : 2
     * title : 我的个人中心
     * categoryId : null
     * content : 我是王文朗,我喂自己一袋盐
     * createId : null
     */

    private int id;
    private String title;
    private Object categoryId;
    private String content;
    private Object createId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCreateId() {
        return createId;
    }

    public void setCreateId(Object createId) {
        this.createId = createId;
    }
}
