package com.jobnew.farm.entity.base;


import com.jobnew.farm.data.KindMyBean;
import com.jobnew.farm.data.interfaces.Nullable;
import com.jobnew.farm.entity.bazaar.BazaarSmall;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李波 on 2017/3/31.
 * Function: 单实体模型容器
 * Desc:
 */
public class BaseEntity<T> implements Nullable,Serializable{

    public int code;
    public String msg;
    public String url; // 当前请求路径
    public boolean success;

    public T data;


    @Override
    public boolean isNull() {
        if (data == null) {
            return true;
        } else if (data.getClass()
                == Nullable.class) {
            return ((Nullable) data).isNull();
        }
        return false;
    }

}
