package com.jobnew.farm.entity.FarmHappy;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/10.
 * title:
 * note:
 */

public class FarmHappyOrderSpendEntity {

    /**
     * id : 316
     * createDate : 1502272977000
     * modifyDate : null
     * logisticsNo : null
     * sn : 20170808064612071209206
     * orderName : null
     * farmId : 13
     * type : null
     * status : consum
     * price : null
     * fee : null
     * freight : 0.0
     * tax : null
     * promotionDiscount : null
     * couponDiscount : null
     * offsetAmount : null
     * amount : 0.38
     * amountPaid : null
     * refundAmount : null
     * rewardPoint : null
     * exchangePoint : null
     * weight : null
     * quantity : null
     * shippedQuantity : null
     * returnedQuantity : null
     * consignee : null
     * consigneeInfo : null
     * areaName : null
     * address : null
     * zipCode : null
     * phone : null
     * memo : null
     * expire : null
     * isUseCouponCode : null
     * isExchangePoint : null
     * isAllocatedStock : null
     * paymentMethodName : null
     * paymentMethodType : null
     * shippingMethodName : null
     * completeDate : null
     * area : null
     * paymentMethodId : null
     * shippingMethodId : null
     * oriMember : null
     * nowMember : null
     * farmManagerId : null
     * couponCode : null
     * validDate : null
     * programTotalPrice : null
     * isShipping : null
     * farm : {"id":13,"createDate":null,"modifyDate":null,"userId":null,"name":"王二娃柴火鸡","address":null,"introduce":null,"detail":null,"auditState":null,"state":null,"auditTime":null,"auditOpinion":null,"saleCount":null,"commentCount":null,"province":null,"city":null,"area":null,"longitude":null,"latitude":null,"grade":null,"phone":null,"businessScope":null,"mainBusiness":null,"landStock":null,"distance":null,"img":"http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg","realAddress":"","del":null}
     * deniedReason : null
     * farmManager : null
     * cycleTime : null
     * deliveryCorpId : null
     * deliveryCorp : null
     * orderItems : [{"id":1908,"createDate":null,"modifyDate":null,"sn":null,"name":"星巴克01","cname":null,"type":null,"price":0.01,"weight":null,"thumbnail":null,"quantity":3,"shippedQuantity":null,"returnedQuantity":null,"productId":362,"orderId":null,"order":null,"commentState":null,"rate":1,"productCategoryId":null,"planCount":null,"planInterval":null,"planDuration":null,"orderTempId":null,"freight":null,"delivery":null}]
     * allocatedStock : null
     */

    private int id;
    private long createDate;
    private Object modifyDate;
    private Object logisticsNo;
    private String sn;
    private Object orderName;
    private int farmId;
    private String type;
    private String status;
    private Object price;
    private Object fee;
    private double freight;
    private Object tax;
    private Object promotionDiscount;
    private Object couponDiscount;
    private Object offsetAmount;
    private double amount;
    private Object amountPaid;
    private Object refundAmount;
    private Object rewardPoint;
    private Object exchangePoint;
    private Object weight;
    private Object quantity;
    private Object shippedQuantity;
    private Object returnedQuantity;
    private Object consignee;
    private Object consigneeInfo;
    private Object areaName;
    private Object address;
    private Object zipCode;
    private String phone;
    private Object memo;
    private Object expire;
    private Object isUseCouponCode;
    private Object isExchangePoint;
    private Object isAllocatedStock;
    private Object paymentMethodName;
    private Object paymentMethodType;
    private Object shippingMethodName;
    private Object completeDate;
    private Object area;
    private Object paymentMethodId;
    private Object shippingMethodId;
    private Object oriMember;
    private Object nowMember;
    private Object farmManagerId;
    private Object couponCode;
    private long validDate;
    private Object programTotalPrice;
    private Object isShipping;
    private FarmBean farm;
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

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public Object getTax() {
        return tax;
    }

    public void setTax(Object tax) {
        this.tax = tax;
    }

    public Object getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(Object promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    public Object getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Object couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Object getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(Object offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Object getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Object amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Object getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Object refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Object getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(Object rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public Object getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(Object exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Object getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(Object shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public Object getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(Object returnedQuantity) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public Object getIsUseCouponCode() {
        return isUseCouponCode;
    }

    public void setIsUseCouponCode(Object isUseCouponCode) {
        this.isUseCouponCode = isUseCouponCode;
    }

    public Object getIsExchangePoint() {
        return isExchangePoint;
    }

    public void setIsExchangePoint(Object isExchangePoint) {
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

    public Object getOriMember() {
        return oriMember;
    }

    public void setOriMember(Object oriMember) {
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

    public long getValidDate() {
        return validDate;
    }

    public void setValidDate(long validDate) {
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

    public FarmBean getFarm() {
        return farm;
    }

    public void setFarm(FarmBean farm) {
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





    public static class FarmBean {
        /**
         * id : 13
         * createDate : null
         * modifyDate : null
         * userId : null
         * name : 王二娃柴火鸡
         * address : null
         * introduce : null
         * detail : null
         * auditState : null
         * state : null
         * auditTime : null
         * auditOpinion : null
         * saleCount : null
         * commentCount : null
         * province : null
         * city : null
         * area : null
         * longitude : null
         * latitude : null
         * grade : null
         * phone : null
         * businessScope : null
         * mainBusiness : null
         * landStock : null
         * distance : null
         * img : http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg
         * realAddress :
         * del : null
         */

        private int id;
        private Object createDate;
        private Object modifyDate;
        private Object userId;
        private String name;
        private Object address;
        private Object introduce;
        private Object detail;
        private Object auditState;
        private Object state;
        private Object auditTime;
        private Object auditOpinion;
        private Object saleCount;
        private Object commentCount;
        private Object province;
        private Object city;
        private Object area;
        private Object longitude;
        private Object latitude;
        private Object grade;
        private Object phone;
        private Object businessScope;
        private Object mainBusiness;
        private Object landStock;
        private double distance;
        private String img;
        private String realAddress;
        private Object del;

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

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }

        public Object getAuditState() {
            return auditState;
        }

        public void setAuditState(Object auditState) {
            this.auditState = auditState;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(Object auditTime) {
            this.auditTime = auditTime;
        }

        public Object getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(Object auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public Object getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(Object saleCount) {
            this.saleCount = saleCount;
        }

        public Object getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Object commentCount) {
            this.commentCount = commentCount;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(Object businessScope) {
            this.businessScope = businessScope;
        }

        public Object getMainBusiness() {
            return mainBusiness;
        }

        public void setMainBusiness(Object mainBusiness) {
            this.mainBusiness = mainBusiness;
        }

        public Object getLandStock() {
            return landStock;
        }

        public void setLandStock(Object landStock) {
            this.landStock = landStock;
        }

        public double  getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRealAddress() {
            return realAddress;
        }

        public void setRealAddress(String realAddress) {
            this.realAddress = realAddress;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }
    }

    public static class OrderItemsBean {
        /**
         * id : 1908
         * createDate : null
         * modifyDate : null
         * sn : null
         * name : 星巴克01
         * cname : null
         * type : null
         * price : 0.01
         * weight : null
         * thumbnail : null
         * quantity : 3
         * shippedQuantity : null
         * returnedQuantity : null
         * productId : 362
         * orderId : null
         * order : null
         * commentState : null
         * rate : 1.0
         * productCategoryId : null
         * planCount : null
         * planInterval : null
         * planDuration : null
         * orderTempId : null
         * freight : null
         * delivery : null
         */

        private int id;
        private long createDate;
        private Object modifyDate;
        private Object sn;
        private String name;
        private String cname;
        private String type;
        private double price;
        private Object weight;
        private String thumbnail;
        private int quantity;
        private Object shippedQuantity;
        private Object returnedQuantity;
        private int productId;
        private int orderId;
        private Object order;
        private Object commentState;
        private double rate;
        private int productCategoryId;
        private Object planCount;
        private Object planInterval;
        private Object planDuration;
        private Object orderTempId;
        private Object freight;
        private Object delivery;

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

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
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

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Object getShippedQuantity() {
            return shippedQuantity;
        }

        public void setShippedQuantity(Object shippedQuantity) {
            this.shippedQuantity = shippedQuantity;
        }

        public Object getReturnedQuantity() {
            return returnedQuantity;
        }

        public void setReturnedQuantity(Object returnedQuantity) {
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

        public int getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(int productCategoryId) {
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

        public Object getDelivery() {
            return delivery;
        }

        public void setDelivery(Object delivery) {
            this.delivery = delivery;
        }
    }
}
