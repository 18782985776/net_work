package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangwenlang on 2017/8/4.
 * title:
 * note:
 */

public class FarmHappyOrderItemEntity implements Parcelable {
  Integer productId;
   Integer quantity;
    public FarmHappyOrderItemEntity(){

    }
    protected FarmHappyOrderItemEntity(Parcel in) {
        productId= in.readInt();
        quantity=in.readInt();
    }

    public static final Creator<FarmHappyOrderItemEntity> CREATOR = new Creator<FarmHappyOrderItemEntity>() {
        @Override
        public FarmHappyOrderItemEntity createFromParcel(Parcel in) {
            return new FarmHappyOrderItemEntity(in);
        }

        @Override
        public FarmHappyOrderItemEntity[] newArray(int size) {
            return new FarmHappyOrderItemEntity[size];
        }
    };

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeInt(quantity);
    }
}
