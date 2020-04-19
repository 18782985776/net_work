package com.jobnew.farm.base.interfaces;

import java.util.List;

/**
 * Created by wufengqiao on 2017/5/17 10:08.
 * Function:
 * Desc:
 */


public interface IPermissionsLinstener {
	void permissionSuccess();

	void permissionDenied(List<String> deniedPermissions);

}
