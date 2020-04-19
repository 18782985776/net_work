package com.jobnew.farm.entity.ShoppingCar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufengqiao on 2017/8/18.
 * function：
 * desc：
 */

public class ShoppingProductEntity implements Parcelable {

    /**
     * quantity : 10
     * product : 85
     * farmId : 8
     * productName : 测试集市产品
     * productImg : http://192.168.9.200/images/image/bff4979ac4b94684a53792b2cf51674b.jpg
     * productPrice : 100
     * productCname : 测试集市产品
     */

    public int quantity;
    public int productId;
    public int farmId;
    public String productName;
    public String productImg;
    public double productPrice;
    public String productCname;
    public String farmName;
    public String farmLogo;
    public boolean isSelect;
    public boolean isEditStatus;
    public int tempQuantity;
    public List<ShoppingProductEntity> list;
    public ShippingMethodEntity shippingMethodEntity;

    public ShoppingProductEntity(){}


    protected ShoppingProductEntity(Parcel in) {
        quantity = in.readInt();
        productId = in.readInt();
        farmId = in.readInt();
        productName = in.readString();
        productImg = in.readString();
        productPrice = in.readDouble();
        productCname = in.readString();
        farmName = in.readString();
        farmLogo = in.readString();
        isSelect = in.readByte() != 0;
        isEditStatus = in.readByte() != 0;
        tempQuantity = in.readInt();
        list = in.createTypedArrayList(ShoppingProductEntity.CREATOR);
        shippingMethodEntity = in.readParcelable(ShippingMethodEntity.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeInt(productId);
        dest.writeInt(farmId);
        dest.writeString(productName);
        dest.writeString(productImg);
        dest.writeDouble(productPrice);
        dest.writeString(productCname);
        dest.writeString(farmName);
        dest.writeString(farmLogo);
        dest.writeByte((byte) (isSelect ? 1 : 0));
        dest.writeByte((byte) (isEditStatus ? 1 : 0));
        dest.writeInt(tempQuantity);
        dest.writeTypedList(list);
        dest.writeParcelable(shippingMethodEntity, flags);
    }

    public static final Creator<ShoppingProductEntity> CREATOR = new Creator<ShoppingProductEntity>() {
        @Override
        public ShoppingProductEntity createFromParcel(Parcel in) {
            return new ShoppingProductEntity(in);
        }

        @Override
        public ShoppingProductEntity[] newArray(int size) {
            return new ShoppingProductEntity[size];
        }
    };

    public void add(ShoppingProductEntity entity) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(entity);
    }

    public void clear() {
        if (list != null) {
            list.clear();
        }
    }
    public void setSelectAll(boolean isSelect){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).isSelect = isSelect;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
