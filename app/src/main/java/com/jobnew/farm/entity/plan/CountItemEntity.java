package com.jobnew.farm.entity.plan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wufengqiao on 2017/7/18.
 * function：
 * desc：
 */

public class CountItemEntity implements Parcelable{
    public String title;
    public long executeDate;

    public CountItemEntity() {
    }

    public CountItemEntity(String title, long executeDate) {
        this.title = title;
        this.executeDate = executeDate;
    }

    protected CountItemEntity(Parcel in) {
        title = in.readString();
        executeDate = in.readLong();
    }

    public static final Creator<CountItemEntity> CREATOR = new Creator<CountItemEntity>() {
        @Override
        public CountItemEntity createFromParcel(Parcel in) {
            return new CountItemEntity(in);
        }

        @Override
        public CountItemEntity[] newArray(int size) {
            return new CountItemEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeLong(executeDate);
    }
}
