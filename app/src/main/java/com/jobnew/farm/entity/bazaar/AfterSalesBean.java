package com.jobnew.farm.entity.bazaar;

import java.math.BigDecimal;

/**
 * Created by wc on 2017/8/23.
 * Function：
 * desc：
 */

public class AfterSalesBean {

    /**
     * id : 17
     * createDate : 1503384906000
     * refundAmount : 0.01
     * returnStatus : pending
     * order : {"logisticsNo":null,"sn":"20170808064612071209215","freight":null,"amount":0.38,"farm":{"name":"王二娃柴火鸡","img":"http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg"}}
     * orderItem : {"id":1917,"name":"茶楼1","cname":null,"price":0.01,"thumbnail":null,"quantity":1,"productId":233}
     */

    private int id;
    private long createDate;
    private double refundAmount;
    private String returnStatus;
    /**
     * logisticsNo : null
     * sn : 20170808064612071209215
     * freight : null
     * amount : 0.38
     * farm : {"name":"王二娃柴火鸡","img":"http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg"}
     */

    private OrderEntity order;
    /**
     * id : 1917
     * name : 茶楼1
     * cname : null
     * price : 0.01
     * thumbnail : null
     * quantity : 1
     * productId : 233
     */

    private OrderItemEntity orderItem;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public void setOrderItem(OrderItemEntity orderItem) {
        this.orderItem = orderItem;
    }

    public int getId() {
        return id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public OrderItemEntity getOrderItem() {
        return orderItem;
    }

    public static class OrderEntity {
        private String logisticsNo;
        private String sn;
        private double freight;
        private double amount;
        /**
         * name : 王二娃柴火鸡
         * img : http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg
         */

        private FarmEntity farm;

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setFarm(FarmEntity farm) {
            this.farm = farm;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public String getSn() {
            return sn;
        }

        public double getFreight() {
            return freight;
        }

        public double getAmount() {
            return amount;
        }

        public FarmEntity getFarm() {
            return farm;
        }

        public static class FarmEntity {
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            private int id;
            private String name;
            private String img;

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public String getImg() {
                return img;
            }
        }
    }

    public static class OrderItemEntity {
        private int id;
        private String name;
        private String cname;
        private double price;
        private String thumbnail;
        private int quantity;
        private int productId;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCname() {
            return cname;
        }

        public double getPrice() {
            return price;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getProductId() {
            return productId;
        }
    }
}
