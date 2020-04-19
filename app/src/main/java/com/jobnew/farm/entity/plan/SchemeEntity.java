package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/12.
 * function：
 * desc：
 */

public class SchemeEntity implements Cloneable, Parcelable {


    /**
     * artProductId : 9
     * isCount : false
     * count : 1
     * countTitle : null
     * isDuration : false
     * duration : 1
     * durationTitle : null
     * isInterval : false
     * interval : 1
     * intervalTitle : null
     * farmArtId : 3
     * farmArtName : 生物肥
     * unitName : ㎡
     * price : 10
     * isDefault : false
     * ftype : LIST
     * ctype : 1
     * intro : null
     * categoryId : 14
     * categoryName : 施肥
     * unionTask : false
     * unionTitle : null
     */

    public int artProductId;
    public boolean isCount;
    public int count;
    public String countTitle;
    public boolean isDuration;
    public int duration = 1;
    public String durationTitle;
    public boolean isInterval;
    public int interval = 1;
    public String intervalTitle;
    public int farmArtId;
    public String farmArtName;
    public String unitName;
    public double price;
    public String ftype;
    public int ctype;
    public String intro;
    public int categoryId;
    public String categoryName;
    public boolean unionTask;
    public String unionTitle;
    public boolean checked;
    public String date;
    public List<SchemeEntity> entities;
    public List<CountItemEntity> countItemModels;

    public SchemeEntity() {
    }


    protected SchemeEntity(Parcel in) {
        artProductId = in.readInt();
        isCount = in.readByte() != 0;
        count = in.readInt();
        countTitle = in.readString();
        isDuration = in.readByte() != 0;
        duration = in.readInt();
        durationTitle = in.readString();
        isInterval = in.readByte() != 0;
        interval = in.readInt();
        intervalTitle = in.readString();
        farmArtId = in.readInt();
        farmArtName = in.readString();
        unitName = in.readString();
        price = in.readDouble();
        checked = in.readByte() != 0;
        ftype = in.readString();
        ctype = in.readInt();
        intro = in.readString();
        categoryId = in.readInt();
        categoryName = in.readString();
        unionTask = in.readByte() != 0;
        unionTitle = in.readString();
        date = in.readString();
        entities = in.createTypedArrayList(SchemeEntity.CREATOR);
        countItemModels = in.createTypedArrayList(CountItemEntity.CREATOR);
    }

    public static final Creator<SchemeEntity> CREATOR = new Creator<SchemeEntity>() {
        @Override
        public SchemeEntity createFromParcel(Parcel in) {
            return new SchemeEntity(in);
        }

        @Override
        public SchemeEntity[] newArray(int size) {
            return new SchemeEntity[size];
        }
    };

    public void add(SchemeEntity entity) {
        if (entities == null) {
            entities = new ArrayList<>();
        }
        if (entity != this) {
            entities.add(entity);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(artProductId);
        dest.writeByte((byte) (isCount ? 1 : 0));
        dest.writeInt(count);
        dest.writeString(countTitle);
        dest.writeByte((byte) (isDuration ? 1 : 0));
        dest.writeInt(duration);
        dest.writeString(durationTitle);
        dest.writeByte((byte) (isInterval ? 1 : 0));
        dest.writeInt(interval);
        dest.writeString(intervalTitle);
        dest.writeInt(farmArtId);
        dest.writeString(farmArtName);
        dest.writeString(unitName);
        dest.writeDouble(price);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeString(ftype);
        dest.writeInt(ctype);
        dest.writeString(intro);
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
        dest.writeByte((byte) (unionTask ? 1 : 0));
        dest.writeString(unionTitle);
        dest.writeString(date);
        dest.writeTypedList(entities);
        dest.writeTypedList(countItemModels);
    }
}
