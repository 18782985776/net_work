package com.jobnew.farm.entity.plan;

import android.os.CancellationSignal;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wufengqiao on 2017/6/29.
 * function：
 * desc：
 */

public class OrderItemEntity implements Parcelable {

    /**
     * sn : T0000001
     * name : 500平优质土地
     * type : MAJOR
     * price : 1101
     * quantity : 5
     * productId : 1
     * rate : 100
     */

    public String sn;
    public String name;
    public String cname;
    public String type;
    public double price;
    public int quantity = 1;
    public int productId;
    public int rate;
    public int itemType;
    public String thumbnail;

    public OrderItemEntity() {
    }

    protected OrderItemEntity(Parcel in) {
        sn = in.readString();
        name = in.readString();
        cname = in.readString();
        type = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        productId = in.readInt();
        rate = in.readInt();
        itemType = in.readInt();
        thumbnail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cname);
        dest.writeString(sn);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeDouble(price);
        dest.writeInt(quantity);
        dest.writeInt(productId);
        dest.writeInt(rate);
        dest.writeInt(itemType);
        dest.writeString(thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderItemEntity> CREATOR = new Creator<OrderItemEntity>() {
        @Override
        public OrderItemEntity createFromParcel(Parcel in) {
            return new OrderItemEntity(in);
        }

        @Override
        public OrderItemEntity[] newArray(int size) {
            return new OrderItemEntity[size];
        }
    };
}
