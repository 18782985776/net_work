package com.jobnew.farm.entity.exclusive;

import android.os.Parcel;
import android.os.Parcelable;

import com.jobnew.farm.entity.FarmEntity;

/**
 * Created by wufengqiao on 2017/7/31.
 * function：
 * desc：
 */

public class ExclusiveLandEntity implements Parcelable{

    /**
     * id : 36
     * name : 神秘1号
     * pImg : http://192.168.9.200/images/image/c10540ac6c5b4acd97354cde2a11a886.jpg
     * price : 0.01
     * unitName : null
     * stock : 2000
     * farm : {"address":"为名路12号","province":"四川省","city":"成都市","area":"郫县"}
     * distance : 1.1652507601319721E7
     * fitValue : {"value":"种植"}
     */

    public int id;
    public String name;
    public String pImg;
    public double price;
    public int stock;
    public FarmEntity farm;
    public double distance;
    public String categoryName;
    public String categoryIcon;

    public ExclusiveLandEntity(){}
    protected ExclusiveLandEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pImg = in.readString();
        price = in.readDouble();
        stock = in.readInt();
        distance = in.readDouble();
        categoryName = in.readString();
        categoryIcon = in.readString();
    }

    public static final Creator<ExclusiveLandEntity> CREATOR = new Creator<ExclusiveLandEntity>() {
        @Override
        public ExclusiveLandEntity createFromParcel(Parcel in) {
            return new ExclusiveLandEntity(in);
        }

        @Override
        public ExclusiveLandEntity[] newArray(int size) {
            return new ExclusiveLandEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pImg);
        dest.writeDouble(price);
        dest.writeInt(stock);
        dest.writeDouble(distance);
        dest.writeString(categoryName);
        dest.writeString(categoryIcon);
    }
}
