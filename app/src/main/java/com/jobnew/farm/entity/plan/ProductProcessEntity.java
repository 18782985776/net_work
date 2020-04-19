package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/6/1.
 * function：
 * desc：
 */

public class ProductProcessEntity implements Parcelable {

    /**
     * id : 6
     * farmArtName : 去泥清洗
     * unitId : null
     * unitName : ㎡
     * price : 10.00
     */

    public int id;
    public String farmArtName;
    public int unitId;
    public String unitName;
    public double price;
    public String intro;
    public boolean isSelect;

    public ProductProcessEntity() {
    }

    private ProductProcessEntity(Parcel in) {
        id = in.readInt();
        farmArtName = in.readString();
        unitId = in.readInt();
        unitName = in.readString();
        price = in.readDouble();
        intro = in.readString();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<ProductProcessEntity> CREATOR = new Creator<ProductProcessEntity>() {
        @Override
        public ProductProcessEntity createFromParcel(Parcel in) {
            return new ProductProcessEntity(in);
        }

        @Override
        public ProductProcessEntity[] newArray(int size) {
            return new ProductProcessEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(farmArtName);
        dest.writeInt(unitId);
        dest.writeString(unitName);
        dest.writeDouble(price);
        dest.writeString(intro);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }
}
