package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.TopUpDetial;
import com.jobnew.farm.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wc on 2017/6/19.
 * Function：
 * desc：
 */

public class DetailAdapter extends BaseQuickAdapter<TopUpDetial,BaseViewHolder> {
    public DetailAdapter(int resourceId, List<TopUpDetial> arrayList){
        super(resourceId,arrayList);
    }
    @Override
    protected void convert(BaseViewHolder helper, TopUpDetial item) {
        helper.setText(R.id.txt_detail,item.getTitle());
        helper.setText(R.id.txt_balance,"余额："+item.getBalance()+"个");
        if (item.getType().equals("recharge_balance")){
            helper.setText(R.id.txt_type,"余额充值");
        }else if (item.getType().equals("withdrawal_balance")) {
            helper.setText(R.id.txt_type,"余额提现");
        }else if (item.getType().equals("recharge_coin")) {
            helper.setText(R.id.txt_type,"充值网农比");
        }else if (item.getType().equals("cost_coin")) {
            helper.setText(R.id.txt_type,"消费网农币");
        }
        long createDate = item.getCreateDate();
        String s = DateUtils.DateToString(createDate + "", "yyyy-MM-dd");
        String s1 = DateUtils.DateToString(createDate + "", "HH:mm");
        helper.setText(R.id.txt_time_one,s1);
        helper.setText(R.id.txt_time_two,s);

    }
}
