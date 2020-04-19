package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wufengqiao on 2017/6/29.
 * function：
 * desc：
 */

public class OrderEntity implements Parcelable {

    /**
     * id : 10
     * sn : 20170628174510808063761
     * type : plant
     * status : pendingPayment
     * price : 5715
     * quantity : 116
     * orderItems : []
     */

    public int id;
    public String sn;
    public String type;
    public String status;
    public double price;
    public int quantity;
    public double freight; //运费
    public double programTotalPrice; //方案价格
    public String areaName;
    public String address;
    public double amount;
    public long cycleTime;
    public List<OrderItemEntity> orderItems;

    public OrderEntity(){

    }

    protected OrderEntity(Parcel in) {
        id = in.readInt();
        sn = in.readString();
        type = in.readString();
        status = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        freight = in.readDouble();
        programTotalPrice = in.readDouble();
        areaName = in.readString();
        address =in.readString();
        amount = in.readDouble();
        orderItems = in.createTypedArrayList(OrderItemEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sn);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeDouble(price);
        dest.writeInt(quantity);
        dest.writeDouble(freight);
        dest.writeDouble(programTotalPrice);
        dest.writeString(areaName);
        dest.writeString(address);
        dest.writeDouble(amount);
        dest.writeTypedList(orderItems);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel in) {
            return new OrderEntity(in);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}
