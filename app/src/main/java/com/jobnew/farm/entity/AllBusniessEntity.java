package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/6/23.
 * title:
 * note:
 */

public class AllBusniessEntity {

    private List<BusinessBean> business;

    public List<BusinessBean> getBusiness() {
        return business;
    }

    public void setBusiness(List<BusinessBean> business) {
        this.business = business;
    }

    public static class BusinessBean {
        /**
         * id : 1
         * name : 种植
         * parentId : 0
         * icon : http://222.88.94.204:5080/images/icon/4.png
         */

        private int id;
        private String name;
        private int parentId;
        private String icon;
        public boolean isSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
