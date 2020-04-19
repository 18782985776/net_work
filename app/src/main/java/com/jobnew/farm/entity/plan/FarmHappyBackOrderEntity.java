package com.jobnew.farm.entity.plan;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/5.
 * title:
 * note:
 */

public class FarmHappyBackOrderEntity {


    /**
     * id : 196
     * createDate : null
     * modifyDate : null
     * logisticsNo : null
     * sn : 20170805010000344471845
     * orderName : null
     * farmId : 13
     * type : agritmnt
     * status : pendingPayment
     * price : 0.01
     * fee : 0
     * freight : 0
     * tax : 0
     * promotionDiscount : 0
     * couponDiscount : 0
     * offsetAmount : 0
     * amount : 0
     * amountPaid : 0
     * refundAmount : null
     * rewardPoint : 0
     * exchangePoint : 0
     * weight : 0
     * quantity : 1
     * shippedQuantity : 0
     * returnedQuantity : 0
     * consignee : null
     * consigneeInfo : null
     * areaName : null
     * address : null
     * zipCode : null
     * phone : null
     * memo : null
     * expire : null
     * isUseCouponCode : false
     * isExchangePoint : false
     * isAllocatedStock : null
     * paymentMethodName : null
     * paymentMethodType : null
     * shippingMethodName : null
     * completeDate : null
     * area : null
     * paymentMethodId : null
     * shippingMethodId : null
     * oriMember : 46
     * nowMember : null
     * farmManagerId : null
     * couponCode : null
     * validDate : null
     * programTotalPrice : null
     * isShipping : null
     * farm : null
     * deniedReason : null
     * farmManager : null
     * cycleTime : null
     * deliveryCorpId : null
     * deliveryCorp : null
     * orderItems : [{"id":1278,"createDate":null,"modifyDate":null,"sn":null,"name":"星巴克01","cname":null,"type":"MAJOR","price":0.01,"weight":0,"thumbnail":null,"quantity":1,"shippedQuantity":0,"returnedQuantity":0,"productId":362,"orderId":196,"order":null,"commentState":null,"rate":0,"productCategoryId":null,"planCount":null,"planInterval":null,"planDuration":null,"orderTempId":null,"freight":null,"interval":null,"duration":null,"productUnit":null,"countItemModels":null,"executeDate":null,"delivery":true}]
     * allocatedStock : null
     */

    private int id;
    private Object createDate;
    private Object modifyDate;
    private Object logisticsNo;
    private String sn;
    private Object orderName;
    private int farmId;
    private String type;
    private String status;
    private double price;
    private int fee;
    private int freight;
    private int tax;
    private int promotionDiscount;
    private int couponDiscount;
    private int offsetAmount;
    private int amount;
    private int amountPaid;
    private Object refundAmount;
    private int rewardPoint;
    private int exchangePoint;
    private int weight;
    private int quantity;
    private int shippedQuantity;
    private int returnedQuantity;
    private Object consignee;
    private Object consigneeInfo;
    private Object areaName;
    private Object address;
    private Object zipCode;
    private Object phone;
    private Object memo;
    private Object expire;
    private boolean isUseCouponCode;
    private boolean isExchangePoint;
    private Object isAllocatedStock;
    private Object paymentMethodName;
    private Object paymentMethodType;
    private Object shippingMethodName;
    private Object completeDate;
    private Object area;
    private Object paymentMethodId;
    private Object shippingMethodId;
    private int oriMember;
    private Object nowMember;
    private Object farmManagerId;
    private Object couponCode;
    private Object validDate;
    private Object programTotalPrice;
    private Object isShipping;
    private Object farm;
    private Object deniedReason;
    private Object farmManager;
    private Object cycleTime;
    private Object deliveryCorpId;
    private Object deliveryCorp;
    private Object allocatedStock;
    private List<OrderItemsBean> orderItems;

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

    public Object getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(Object logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Object getOrderName() {
        return orderName;
    }

    public void setOrderName(Object orderName) {
        this.orderName = orderName;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(int promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    public int getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(int couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public int getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(int offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Object getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Object refundAmount) {
        this.refundAmount = refundAmount;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public int getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(int exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(int shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public int getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(int returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public Object getConsignee() {
        return consignee;
    }

    public void setConsignee(Object consignee) {
        this.consignee = consignee;
    }

    public Object getConsigneeInfo() {
        return consigneeInfo;
    }

    public void setConsigneeInfo(Object consigneeInfo) {
        this.consigneeInfo = consigneeInfo;
    }

    public Object getAreaName() {
        return areaName;
    }

    public void setAreaName(Object areaName) {
        this.areaName = areaName;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getZipCode() {
        return zipCode;
    }

    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public Object getExpire() {
        return expire;
    }

    public void setExpire(Object expire) {
        this.expire = expire;
    }

    public boolean isIsUseCouponCode() {
        return isUseCouponCode;
    }

    public void setIsUseCouponCode(boolean isUseCouponCode) {
        this.isUseCouponCode = isUseCouponCode;
    }

    public boolean isIsExchangePoint() {
        return isExchangePoint;
    }

    public void setIsExchangePoint(boolean isExchangePoint) {
        this.isExchangePoint = isExchangePoint;
    }

    public Object getIsAllocatedStock() {
        return isAllocatedStock;
    }

    public void setIsAllocatedStock(Object isAllocatedStock) {
        this.isAllocatedStock = isAllocatedStock;
    }

    public Object getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(Object paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public Object getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(Object paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public Object getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(Object shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    public Object getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Object completeDate) {
        this.completeDate = completeDate;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public Object getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Object paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Object getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Object shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public int getOriMember() {
        return oriMember;
    }

    public void setOriMember(int oriMember) {
        this.oriMember = oriMember;
    }

    public Object getNowMember() {
        return nowMember;
    }

    public void setNowMember(Object nowMember) {
        this.nowMember = nowMember;
    }

    public Object getFarmManagerId() {
        return farmManagerId;
    }

    public void setFarmManagerId(Object farmManagerId) {
        this.farmManagerId = farmManagerId;
    }

    public Object getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Object couponCode) {
        this.couponCode = couponCode;
    }

    public Object getValidDate() {
        return validDate;
    }

    public void setValidDate(Object validDate) {
        this.validDate = validDate;
    }

    public Object getProgramTotalPrice() {
        return programTotalPrice;
    }

    public void setProgramTotalPrice(Object programTotalPrice) {
        this.programTotalPrice = programTotalPrice;
    }

    public Object getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Object isShipping) {
        this.isShipping = isShipping;
    }

    public Object getFarm() {
        return farm;
    }

    public void setFarm(Object farm) {
        this.farm = farm;
    }

    public Object getDeniedReason() {
        return deniedReason;
    }

    public void setDeniedReason(Object deniedReason) {
        this.deniedReason = deniedReason;
    }

    public Object getFarmManager() {
        return farmManager;
    }

    public void setFarmManager(Object farmManager) {
        this.farmManager = farmManager;
    }

    public Object getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Object cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Object getDeliveryCorpId() {
        return deliveryCorpId;
    }

    public void setDeliveryCorpId(Object deliveryCorpId) {
        this.deliveryCorpId = deliveryCorpId;
    }

    public Object getDeliveryCorp() {
        return deliveryCorp;
    }

    public void setDeliveryCorp(Object deliveryCorp) {
        this.deliveryCorp = deliveryCorp;
    }

    public Object getAllocatedStock() {
        return allocatedStock;
    }

    public void setAllocatedStock(Object allocatedStock) {
        this.allocatedStock = allocatedStock;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemsBean {
        /**
         * id : 1278
         * createDate : null
         * modifyDate : null
         * sn : null
         * name : 星巴克01
         * cname : null
         * type : MAJOR
         * price : 0.01
         * weight : 0
         * thumbnail : null
         * quantity : 1
         * shippedQuantity : 0
         * returnedQuantity : 0
         * productId : 362
         * orderId : 196
         * order : null
         * commentState : null
         * rate : 0.0
         * productCategoryId : null
         * planCount : null
         * planInterval : null
         * planDuration : null
         * orderTempId : null
         * freight : null
         * interval : null
         * duration : null
         * productUnit : null
         * countItemModels : null
         * executeDate : null
         * delivery : true
         */

        private int id;
        private Object createDate;
        private Object modifyDate;
        private Object sn;
        private String name;
        private Object cname;
        private String type;
        private double price;
        private int weight;
        private Object thumbnail;
        private int quantity;
        private int shippedQuantity;
        private int returnedQuantity;
        private int productId;
        private int orderId;
        private Object order;
        private Object commentState;
        private double rate;
        private Object productCategoryId;
        private Object planCount;
        private Object planInterval;
        private Object planDuration;
        private Object orderTempId;
        private Object freight;
        private Object interval;
        private Object duration;
        private Object productUnit;
        private Object countItemModels;
        private Object executeDate;
        private boolean delivery;

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

        public Object getSn() {
            return sn;
        }

        public void setSn(Object sn) {
            this.sn = sn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCname() {
            return cname;
        }

        public void setCname(Object cname) {
            this.cname = cname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Object getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Object thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getShippedQuantity() {
            return shippedQuantity;
        }

        public void setShippedQuantity(int shippedQuantity) {
            this.shippedQuantity = shippedQuantity;
        }

        public int getReturnedQuantity() {
            return returnedQuantity;
        }

        public void setReturnedQuantity(int returnedQuantity) {
            this.returnedQuantity = returnedQuantity;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public Object getCommentState() {
            return commentState;
        }

        public void setCommentState(Object commentState) {
            this.commentState = commentState;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public Object getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(Object productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public Object getPlanCount() {
            return planCount;
        }

        public void setPlanCount(Object planCount) {
            this.planCount = planCount;
        }

        public Object getPlanInterval() {
            return planInterval;
        }

        public void setPlanInterval(Object planInterval) {
            this.planInterval = planInterval;
        }

        public Object getPlanDuration() {
            return planDuration;
        }

        public void setPlanDuration(Object planDuration) {
            this.planDuration = planDuration;
        }

        public Object getOrderTempId() {
            return orderTempId;
        }

        public void setOrderTempId(Object orderTempId) {
            this.orderTempId = orderTempId;
        }

        public Object getFreight() {
            return freight;
        }

        public void setFreight(Object freight) {
            this.freight = freight;
        }

        public Object getInterval() {
            return interval;
        }

        public void setInterval(Object interval) {
            this.interval = interval;
        }

        public Object getDuration() {
            return duration;
        }

        public void setDuration(Object duration) {
            this.duration = duration;
        }

        public Object getProductUnit() {
            return productUnit;
        }

        public void setProductUnit(Object productUnit) {
            this.productUnit = productUnit;
        }

        public Object getCountItemModels() {
            return countItemModels;
        }

        public void setCountItemModels(Object countItemModels) {
            this.countItemModels = countItemModels;
        }

        public Object getExecuteDate() {
            return executeDate;
        }

        public void setExecuteDate(Object executeDate) {
            this.executeDate = executeDate;
        }

        public boolean isDelivery() {
            return delivery;
        }

        public void setDelivery(boolean delivery) {
            this.delivery = delivery;
        }
    }
}
