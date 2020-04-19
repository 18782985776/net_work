package com.jobnew.farm.entity.plan;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.JsonToken;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wufengqiao on 2017/6/29.
 * function：
 * desc：
 */

public class CommitOrderEntity {

    /**
     * name : 小明为爷爷养的老母猪
     * farmManagerId : 1
     * userAddressId : 3
     * itemModels : [{"productId":1,"type":"MAJOR","quantity":5},{"productId":3,"type":"MINOR","quantity":5},{"productId":1,"type":"PLAN","quantity":1},{"productId":3,"type":"PLAN","quantity":5},{"productId":4,"type":"PLAN","quantity":100}]
     */

    public String name;
    public int farmManagerId;
    public int userAddressId;
    public boolean isDefaultTemplate;
    public int farmId;
    public boolean isDelivery;
    public int cycleTime;
    public String type;

    public List<CommitItemEntity> itemModels;

    public void add(@NonNull CommitItemEntity entity) {
        if (itemModels == null) {
            itemModels = new ArrayList<>();
        }
        itemModels.add(entity);
    }

    public void clear() {
        if (itemModels != null) {
            itemModels.clear();
        }
    }

}
