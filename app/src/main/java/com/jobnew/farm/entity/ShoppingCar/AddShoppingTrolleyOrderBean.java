package com.jobnew.farm.entity.ShoppingCar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufengqiao on 2017/8/22.
 * function：
 * desc：
 */

public class AddShoppingTrolleyOrderBean {
    public int userAddressId;
    public String type;
    public List<ShoppingCarProductBean> itemModels;

    public void add(ShoppingCarProductBean bean) {
        if (itemModels == null) {
            itemModels = new ArrayList<>();
        }
        itemModels.add(bean);
    }
}
