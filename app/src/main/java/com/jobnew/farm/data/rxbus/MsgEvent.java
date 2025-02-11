package com.jobnew.farm.data.rxbus;

/**
 * Created wangcheng  on 2016/11/9.
 * title：eventBus传递消息
 * note:
 */

public class MsgEvent<T> {

    private T data;

    private String mMsg;
    private int type;
    private int request;

    public MsgEvent(T data) {
        this.data = data;
    }

    public MsgEvent(int request, int type, String msg) {
        this.type = type;
        this.mMsg = msg;
        this.request = request;
    }
    public String getMsg(){
        return mMsg;
    }
    public int getType(){
        return type;
    }
    public int getRequest(){ return request; }

    public T getData(){return data;}

}
