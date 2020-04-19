package com.jobnew.farm.data.utils;

import com.jobnew.farm.data.interfaces.Nullable;

/**
 * Created by 李波 on 2017/4/1.
 * Function:
 * Desc:
 */
public class NullUtils {

    /**
     * 检查Nullable的子类是否为空
     *
     * @param nullAble
     * @param <T>
     * @return
     */
    public static <T extends Nullable> boolean isNull(T nullAble) {
        return nullAble == null
                || nullAble.isNull();
    }

    /**
     * 检查Nullable的子类是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNull(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof Nullable) {
            return isNull((Nullable) value);
        }
        return false;
    }
}
