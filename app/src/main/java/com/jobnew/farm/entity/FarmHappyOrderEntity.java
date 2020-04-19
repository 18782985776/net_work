package com.jobnew.farm.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.jobnew.farm.entity.plan.FarmHappyOrderItemEntity;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/8/4.
 * title:
 * note:
 */

public class FarmHappyOrderEntity implements Parcelable{
    String userAddressId;
    long validDate;//订单生效时间
    String type;//general-普通集市订单，activity-活动订单,agritmnt-农家乐
    List<FarmHappyOrderItemEntity> itemModels;
    Integer farmId;
    public FarmHappyOrderEntity(){

    }

    public FarmHappyOrderEntity(Parcel in) {
        userAddressId = in.readString();
        validDate = in.readLong();
        type = in.readString();
        farmId=in.readInt();
    }

    public static final Creator<FarmHappyOrderEntity> CREATOR = new Creator<FarmHappyOrderEntity>() {
        @Override
        public FarmHappyOrderEntity createFromParcel(Parcel in) {
            return new FarmHappyOrderEntity(in);
        }

        @Override
        public FarmHappyOrderEntity[] newArray(int size) {
            return new FarmHappyOrderEntity[size];
        }
    };

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(String userAddressId) {
        this.userAddressId = userAddressId;
    }

    public long getValidDate() {
        return validDate;
    }

    public void setValidDate(long validDate) {
        this.validDate = validDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FarmHappyOrderItemEntity> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<FarmHappyOrderItemEntity> itemModels) {
        this.itemModels = itemModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(userAddressId);
        dest.writeLong(validDate);
        dest.writeString(type);
        dest.writeInt(farmId);
    }
}
