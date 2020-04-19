package com.jobnew.farm.entity.bazaar;

import java.util.List;

/**
 * Created by wc on 2017/7/31.
 * Function：
 * desc：
 */

public class BazaarBig {

    /**
     * id : 4
     * createDate : null
     * modifyDate : null
     * name : 蔬菜
     * parentId : null
     * children : [{"id":29,"createDate":null,"modifyDate":null,"name":"白菜","parentId":null,"children":null,"type":null,"showType":0},{"id":30,"createDate":null,"modifyDate":null,"name":"萝卜","parentId":null,"children":null,"type":null,"showType":0},{"id":31,"createDate":null,"modifyDate":null,"name":"黄瓜","parentId":null,"children":null,"type":null,"showType":0},{"id":32,"createDate":null,"modifyDate":null,"name":"茄子","parentId":null,"children":null,"type":null,"showType":0},{"id":33,"createDate":null,"modifyDate":null,"name":"土豆","parentId":null,"children":null,"type":null,"showType":0}]
     * type : null
     * showType : 3
     */

    private int id;
    private Object createDate;
    private Object modifyDate;
    private String name;
    private Object parentId;
    private Object type;
    private int showType;
    /**
     * id : 29
     * createDate : null
     * modifyDate : null
     * name : 白菜
     * parentId : null
     * children : null
     * type : null
     * showType : 0
     */

    private List<ChildrenEntity> children;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public void setChildren(List<ChildrenEntity> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public String getName() {
        return name;
    }

    public Object getParentId() {
        return parentId;
    }

    public Object getType() {
        return type;
    }

    public int getShowType() {
        return showType;
    }

    public List<ChildrenEntity> getChildren() {
        return children;
    }

    public static class ChildrenEntity {
        private int id;
        private Object createDate;
        private Object modifyDate;
        private String name;
        private Object parentId;
        private Object children;
        private Object type;
        private int showType;

        public void setId(int id) {
            this.id = id;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public void setShowType(int showType) {
            this.showType = showType;
        }

        public int getId() {
            return id;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public String getName() {
            return name;
        }

        public Object getParentId() {
            return parentId;
        }

        public Object getChildren() {
            return children;
        }

        public Object getType() {
            return type;
        }

        public int getShowType() {
            return showType;
        }
    }
}
