package com.jobnew.farm.entity.myfarm;

import android.support.design.widget.TabLayout;

import com.jobnew.farm.entity.plan.CommitItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wufengqiao on 2017/7/18.
 * function：
 * desc：
 */

public class CommitNewTaskEntity {
    public int orderId;
    public List<CommitItemEntity> taskItemModels;
    public void add(CommitItemEntity entity){
        if(taskItemModels == null){
            taskItemModels = new ArrayList<>();
        }
        taskItemModels.add(entity);
    }
}
