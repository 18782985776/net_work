package com.jobnew.farm.entity.ShoppingCar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wufengqiao on 2017/8/28.
 * function：产品运费计算方式
 * desc：
 */

public class ShippingMethodEntity implements Parcelable{

    /**
     * id : 16
     * freeWeight : 222
     * firstWeight : 0
     * continueWeight : 10
     * defaultFirstPrice : 4
     * defaultContinuePrice : 2
     */

    public int id;
    public int productId;
    public int freeWeight;
    public int firstWeight;
    public int continueWeight;
    public int defaultFirstPrice;
    public int defaultContinuePrice;

    protected ShippingMethodEntity(Parcel in) {
        id = in.readInt();
        productId = in.readInt();
        freeWeight = in.readInt();
        firstWeight = in.readInt();
        continueWeight = in.readInt();
        defaultFirstPrice = in.readInt();
        defaultContinuePrice = in.readInt();
    }

    public static final Creator<ShippingMethodEntity> CREATOR = new Creator<ShippingMethodEntity>() {
        @Override
        public ShippingMethodEntity createFromParcel(Parcel in) {
            return new ShippingMethodEntity(in);
        }

        @Override
        public ShippingMethodEntity[] newArray(int size) {
            return new ShippingMethodEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(productId);
        dest.writeInt(freeWeight);
        dest.writeInt(firstWeight);
        dest.writeInt(continueWeight);
        dest.writeInt(defaultFirstPrice);
        dest.writeInt(defaultContinuePrice);
    }
}
