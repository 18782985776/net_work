package com.jobnew.farm.entity.bazaar;

import java.util.List;

/**
 * Created by wc on 2017/8/16.
 * Function：
 * desc：
 */

public class OrderBazaarBean {


    /**
     * id : 147
     * createDate : 1501467972000
     * sn : 20170725100310521192864
     * farmId : 8
     * status : pendingPayment
     * amount : 100.0
     * freight : 10.0
     * farm : {"name":"正大农场","img":"http://192.168.9.200/images/image/a598a9936a914336a7214e1d89faec53.jpg"}
     * orderItems : [{"id":999,"name":"测试集市产品","cname":"一句话描述","price":100,"thumbnail":"http://192.168.9.200/images/image/b967d7bdcf324ff1aab00dde0a30397a.jpg","quantity":1,"productId":85,"commentState":"pendingEvaluation","refund":{"id":1,"refundAmount":100,"returnInfo":"无条件","returnType":"refund","returnStatus":"canceled"}}]
     */

    private int id;
    private long createDate;
    private String sn;
    private int farmId;
    private String status;
    private double amount;
    private double freight;
    /**
     * name : 正大农场
     * img : http://192.168.9.200/images/image/a598a9936a914336a7214e1d89faec53.jpg
     */

    private FarmEntity farm;
    /**
     * id : 999
     * name : 测试集市产品
     * cname : 一句话描述
     * price : 100.0
     * thumbnail : http://192.168.9.200/images/image/b967d7bdcf324ff1aab00dde0a30397a.jpg
     * quantity : 1
     * productId : 85
     * commentState : pendingEvaluation
     * refund : {"id":1,"refundAmount":100,"returnInfo":"无条件","returnType":"refund","returnStatus":"canceled"}
     */

    private List<OrderItemsEntity> orderItems;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public void setFarm(FarmEntity farm) {
        this.farm = farm;
    }

    public void setOrderItems(List<OrderItemsEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getSn() {
        return sn;
    }

    public int getFarmId() {
        return farmId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public double getFreight() {
        return freight;
    }

    public FarmEntity getFarm() {
        return farm;
    }

    public List<OrderItemsEntity> getOrderItems() {
        return orderItems;
    }

    public static class FarmEntity {
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

    public static class OrderItemsEntity {
        private int id;
        private String name;
        private String cname;
        private double price;
        private String thumbnail;
        private int quantity;
        private int productId;
        private String commentState;
        private boolean canRefund;

        public boolean isCanRefund() {
            return canRefund;
        }

        public void setCanRefund(boolean canRefund) {
            this.canRefund = canRefund;
        }

        /**
         * id : 1
         * refundAmount : 100.0
         * returnInfo : 无条件
         * returnType : refund
         * returnStatus : canceled
         */

        private RefundEntity refund;

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

        public void setCommentState(String commentState) {
            this.commentState = commentState;
        }

        public void setRefund(RefundEntity refund) {
            this.refund = refund;
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

        public String getCommentState() {
            return commentState;
        }

        public RefundEntity getRefund() {
            return refund;
        }

        public static class RefundEntity {
            private int id;
            private double refundAmount;
            private String returnInfo;
            private String returnType;
            private String returnStatus;

            public void setId(int id) {
                this.id = id;
            }

            public void setRefundAmount(double refundAmount) {
                this.refundAmount = refundAmount;
            }

            public void setReturnInfo(String returnInfo) {
                this.returnInfo = returnInfo;
            }

            public void setReturnType(String returnType) {
                this.returnType = returnType;
            }

            public void setReturnStatus(String returnStatus) {
                this.returnStatus = returnStatus;
            }

            public int getId() {
                return id;
            }

            public double getRefundAmount() {
                return refundAmount;
            }

            public String getReturnInfo() {
                return returnInfo;
            }

            public String getReturnType() {
                return returnType;
            }

            public String getReturnStatus() {
                return returnStatus;
            }
        }
    }
}
