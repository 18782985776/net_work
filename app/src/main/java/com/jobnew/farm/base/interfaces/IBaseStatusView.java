package com.jobnew.farm.base.interfaces;

/**
 * Created by wufengqiao on 2017/3/14 17:35.
 * Function:
 * Desc:
 */
public interface IBaseStatusView extends ILoading {
	void noNet();//没有网络的回调

	void empty();//无数据的回调

	void loading();//正在加载的回调

	void error(String msg);//加载数据错误的回调

	void content();//显示数据的回调

//	/**
//	 * 登陆s失效     * @param msg
//	 */
//	void tgtInvalid(String msg);

}
