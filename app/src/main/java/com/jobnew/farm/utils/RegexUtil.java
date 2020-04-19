package com.jobnew.farm.utils;

import java.util.regex.Pattern;

/**
 * Created by wancheng on 2017/3/13.
 * title：
 * note：一些正则表达式
 */

public class RegexUtil {
    private static final String MOBILE = "^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$"; // 手机号正则表达式
    private static final String NICKNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$"; // 调度姓名，至少一个汉字、字母或数字，可以使用下划线，但不能以下划线开头
    private static final String DISPATCHER_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$"; // 调度密码，6-10位的数字和字母组合，不能纯数字或字母
    private static final String CODE ="^[1-9][0-9]{5}$";

    /**
     * 判断是否是手机号
     *
     * @param tel
     * @return
     */
    public static boolean isTelNum(String tel) {
        boolean res = false;
        Pattern pattern = Pattern.compile(MOBILE);
        res = pattern.matcher(tel).matches();
        return res;
    }
    /**
     * 判断是否是手机号
     *
     * @param code
     * @return
     */
    public static boolean isCode(String code) {
        return Pattern.compile(CODE).matcher(code).matches();
    }
    /**
     * 判断是否符合昵称要求
     * @param nickname
     * @return
     */
    public static boolean isNickName(String nickname){
        boolean res = false;
        Pattern pattern = Pattern.compile(NICKNAME);
        res = pattern.matcher(nickname).matches();
        return res;
    }

    /**
     * 判断是否符合密码要求
     * @return
     */
    public static boolean isDispatcherPwd(String password){
        boolean res = false;
        Pattern pattern = Pattern.compile(DISPATCHER_PASSWORD);
        res = pattern.matcher(password).matches();
        return res;
    }
}
