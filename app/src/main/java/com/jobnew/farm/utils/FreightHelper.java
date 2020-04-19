package com.jobnew.farm.utils;

import android.util.Log;

import com.jobnew.farm.entity.ShoppingCar.ShippingMethodEntity;

/**
 * Created by wufengqiao on 2017/8/28.
 * function：
 * desc：
 */

public class FreightHelper {

    public static double shippingFreight(ShippingMethodEntity entity, double weight) {
        if (entity ==  null){
            return 0;
        }
        if (weight <= entity.freeWeight) {
            return 0;
        }
        if (weight > entity.freeWeight && weight <= entity.firstWeight) {
            return entity.defaultFirstPrice;
        }

        return entity.defaultFirstPrice + entity.defaultContinuePrice * (weight - entity.firstWeight);
    }
}
