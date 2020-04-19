package com.jobnew.farm.data.repository;

import com.jobnew.farm.data.service.ExclusiveLandService;
import com.jobnew.farm.entity.ExclusiveLandDetailsEntity;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;

/**
 * Created by wufengqiao on 2017/8/14.
 * function：
 * desc：
 */

public class ExclusiveLandRepository extends BaseRepository<ExclusiveLandService> {

    private static ExclusiveLandRepository sInstance;

    @Override
    public Class<ExclusiveLandService> bindService() {
        return ExclusiveLandService.class;
    }

    public static ExclusiveLandRepository getIns() {
        if (sInstance == null) {
            synchronized (ExclusiveLandRepository.class) {
                if (sInstance == null) {
                    sInstance = new ExclusiveLandRepository();

                }
            }
        }
        return sInstance;
    }

    /**
     * 查询专属土地列表
     *
     * @param map
     * @return
     */
    public Observable<List<ExclusiveLandEntity>> getExclusiveLand(Map<String, String> map) {
        return sepaCheckNet(getService().getExclusiveLand(map));
    }

    /**
     * 查询专属土地详情
     */
    public Observable<ExclusiveLandDetailsEntity> getExclusiveLandDetails(int id, Map<String, String> map) {
        return sepaCheckNet(getService().getExclusiveLandDetails(id + "", map));
    }

    /**
     * 添加租地订单
     */
    public Observable<OrderEntity> addLeaseOrder(Map<String, String> map){
        return sepaCheckNet(getLoginService().addLeaseOrder(map));
    }
}
