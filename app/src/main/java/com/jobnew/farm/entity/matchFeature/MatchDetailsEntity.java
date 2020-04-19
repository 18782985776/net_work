package com.jobnew.farm.entity.matchFeature;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/22.
 * title:
 * note:
 */

public class MatchDetailsEntity {


    /**
     * id : 3
     * createDate : null
     * modifyDate : null
     * name : 我们来比赛吧！
     * farmId : null
     * address : 中和镇
     * areaId : 510100
     * signUpStartDate : 1502461808000
     * signUpEndDate : 1504189810000
     * startDate : 1504276216000
     * endDate : 1506090621000
     * number : 20
     * phone : 18628228047
     * awardsDesc : 奖励二千万
     * details : 动心不动心
     * longitude : 100000
     * latitude : 100000
     * area : {"id":510100,"name":"成都市","parentId":null,"levelType":null,"zipCode":null,"mergerName":"四川省,成都市"}
     * images : [{"url":"http://abc.2008php.com/2012_Website_appreciate/2012-08-02/20120802231101.jpg","matchId":null,"sort":null}]
     * farm : {"id":null,"createDate":null,"modifyDate":null,"userId":null,"name":"正大农场","address":null,"introduce":null,"detail":null,"auditState":null,"state":null,"auditTime":null,"auditOpinion":null,"saleCount":null,"commentCount":null,"province":null,"city":null,"area":null,"longitude":null,"latitude":null,"grade":null,"phone":null,"businessScope":null,"mainBusiness":null,"landStock":null,"distance":null,"img":null,"del":null}
     * img : null
     * joinNumber : 1
     * distance : null
     * sn : null
     */

    private int id;
    private Object createDate;
    private Object modifyDate;
    private String name;
    private Object farmId;
    private String address;
    private int areaId;
    private long signUpStartDate;
    private long signUpEndDate;
    private long startDate;
    private long endDate;
    private int number;
    private String phone;
    private String awardsDesc;
    private String details;
    private String longitude;
    private String latitude;
    private AreaBean area;
    private FarmBean farm;
    private Object img;
    private int joinNumber;
    private Object distance;
    private Object sn;
    private List<ImagesBean> images;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFarmId() {
        return farmId;
    }

    public void setFarmId(Object farmId) {
        this.farmId = farmId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public long getSignUpStartDate() {
        return signUpStartDate;
    }

    public void setSignUpStartDate(long signUpStartDate) {
        this.signUpStartDate = signUpStartDate;
    }

    public long getSignUpEndDate() {
        return signUpEndDate;
    }

    public void setSignUpEndDate(long signUpEndDate) {
        this.signUpEndDate = signUpEndDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAwardsDesc() {
        return awardsDesc;
    }

    public void setAwardsDesc(String awardsDesc) {
        this.awardsDesc = awardsDesc;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public FarmBean getFarm() {
        return farm;
    }

    public void setFarm(FarmBean farm) {
        this.farm = farm;
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public int getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(int joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public Object getSn() {
        return sn;
    }

    public void setSn(Object sn) {
        this.sn = sn;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class AreaBean {
        /**
         * id : 510100
         * name : 成都市
         * parentId : null
         * levelType : null
         * zipCode : null
         * mergerName : 四川省,成都市
         */

        private int id;
        private String name;
        private Object parentId;
        private Object levelType;
        private Object zipCode;
        private String mergerName;

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

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getLevelType() {
            return levelType;
        }

        public void setLevelType(Object levelType) {
            this.levelType = levelType;
        }

        public Object getZipCode() {
            return zipCode;
        }

        public void setZipCode(Object zipCode) {
            this.zipCode = zipCode;
        }

        public String getMergerName() {
            return mergerName;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }
    }

    public static class FarmBean {
        /**
         * id : null
         * createDate : null
         * modifyDate : null
         * userId : null
         * name : 正大农场
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
         * img : null
         * del : null
         */

        private Object id;
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
        private Object distance;
        private Object img;
        private Object del;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }
    }

    public static class ImagesBean {
        /**
         * url : http://abc.2008php.com/2012_Website_appreciate/2012-08-02/20120802231101.jpg
         * matchId : null
         * sort : null
         */

        private String url;
        private Object matchId;
        private Object sort;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getMatchId() {
            return matchId;
        }

        public void setMatchId(Object matchId) {
            this.matchId = matchId;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }
    }
}
