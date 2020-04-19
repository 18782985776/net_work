package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wufengqiao on 2017/7/18.
 * function：
 * desc：
 */

public class CommitItemEntity implements Parcelable {
    public int productId;
    public String type;
    public int quantity;
    public int interval = 1;
    public int duration = 1;
    public long executeDate;
    public List<CountItemEntity> countItemModels;

    public CommitItemEntity() {
    }


    protected CommitItemEntity(Parcel in) {
        productId = in.readInt();
        type = in.readString();
        quantity = in.readInt();
        interval = in.readInt();
        duration = in.readInt();
        executeDate = in.readLong();
        countItemModels = in.createTypedArrayList(CountItemEntity.CREATOR);
    }

    public static final Creator<CommitItemEntity> CREATOR = new Creator<CommitItemEntity>() {
        @Override
        public CommitItemEntity createFromParcel(Parcel in) {
            return new CommitItemEntity(in);
        }

        @Override
        public CommitItemEntity[] newArray(int size) {
            return new CommitItemEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(type);
        dest.writeInt(quantity);
        dest.writeInt(interval);
        dest.writeInt(duration);
        dest.writeLong(executeDate);
        dest.writeTypedList(countItemModels);
    }

}
