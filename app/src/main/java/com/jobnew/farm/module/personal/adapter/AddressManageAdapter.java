package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.data.AddressBean;

import java.util.List;

/**
 * Created by wc on 2017/5/26.
 * Function：
 * desc：
 */

public class AddressManageAdapter extends BaseQuickAdapter<AddressBean,BaseViewHolder>{

    public AddressManageAdapter(int layoutResId, List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.txt_receipt,"收货人 : "+item.getContactName());
        helper.setText(R.id.txt_address_phone,item.getPhone());
//        helper.setText(R.id.txt_code,"邮编："+item.getZipCode());
        String mergerName = item.getArea().getMergerName();
        String[] split = mergerName.split(",");
        helper.setText(R.id.txt_address,split[0]+" "+split[1]+" "+item.getArea().getName()+" "+item.getAddress());
        helper.addOnClickListener(R.id.txt_is_default);
        helper.addOnClickListener(R.id.del_address);
        helper.addOnClickListener(R.id.edit_address);
        if (item.isIsDefault()){
            helper.setImageResource(R.id.img_is_default,R.mipmap.ic_cb_check);
        }else{
            helper.setImageResource(R.id.img_is_default,R.mipmap.ic_cb_nocheck);
        }
    }
}
