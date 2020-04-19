package com.jobnew.farm.data;

/**
 * Created by wc on 2017/8/16.
 * Function：
 * desc：
 */

public class KindMyBean {

    /**
     * id : 50
     * name : 车厘子
     * price : 0.03
     * farm : {"id":8,"name":"正大农场","longitude":"104.06137695451396","latitude":"30.735622100763015","distance":3744570.7469325163}
     */

    private int id;
    private String unitName;
    private String name;
    private double price;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * id : 8
     * name : 正大农场
     * longitude : 104.06137695451396
     * latitude : 30.735622100763015
     * distance : 3744570.7469325163
     */

    private FarmEntity farm;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFarm(FarmEntity farm) {
        this.farm = farm;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public FarmEntity getFarm() {
        return farm;
    }

    public static class FarmEntity {
        private int id;
        private String name;
        private String longitude;
        private String latitude;
        private double distance;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public double getDistance() {
            return distance;
        }
    }
}
