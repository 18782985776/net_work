// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.RundImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EntertainmentDetailsActivity_ViewBinding implements Unbinder {
  private EntertainmentDetailsActivity target;

  @UiThread
  public EntertainmentDetailsActivity_ViewBinding(EntertainmentDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EntertainmentDetailsActivity_ViewBinding(EntertainmentDetailsActivity target,
      View source) {
    this.target = target;

    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", ConvenientBanner.class);
    target.entertainmentNametv = Utils.findRequiredViewAsType(source, R.id.entertainment_nametv, "field 'entertainmentNametv'", TextView.class);
    target.p = Utils.findRequiredViewAsType(source, R.id.p, "field 'p'", TextView.class);
    target.numTv = Utils.findRequiredViewAsType(source, R.id.num_tv, "field 'numTv'", TextView.class);
    target.dateTv = Utils.findRequiredViewAsType(source, R.id.date_tv, "field 'dateTv'", TextView.class);
    target.textView3 = Utils.findRequiredViewAsType(source, R.id.textView3, "field 'textView3'", TextView.class);
    target.textView4 = Utils.findRequiredViewAsType(source, R.id.textView4, "field 'textView4'", TextView.class);
    target.headImg = Utils.findRequiredViewAsType(source, R.id.head_img, "field 'headImg'", RundImageView.class);
    target.conversationImg = Utils.findRequiredViewAsType(source, R.id.conversation_img, "field 'conversationImg'", ImageView.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.address_tv, "field 'addressTv'", TextView.class);
    target.priceTv = Utils.findRequiredViewAsType(source, R.id.price_tv, "field 'priceTv'", TextView.class);
    target.farmNameTv = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'farmNameTv'", TextView.class);
    target.phoneImg = Utils.findRequiredViewAsType(source, R.id.phone_img, "field 'phoneImg'", ImageView.class);
    target.activityIntroTv = Utils.findRequiredViewAsType(source, R.id.tv_activity_intro, "field 'activityIntroTv'", TextView.class);
    target.addMessageImg = Utils.findRequiredViewAsType(source, R.id.img_add_message, "field 'addMessageImg'", ImageView.class);
    target.tvLeaveMessageNum = Utils.findRequiredViewAsType(source, R.id.tv_leave_message_num, "field 'tvLeaveMessageNum'", TextView.class);
    target.joinBt = Utils.findRequiredViewAsType(source, R.id.bt_join, "field 'joinBt'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EntertainmentDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.banner = null;
    target.entertainmentNametv = null;
    target.p = null;
    target.numTv = null;
    target.dateTv = null;
    target.textView3 = null;
    target.textView4 = null;
    target.headImg = null;
    target.conversationImg = null;
    target.recycler = null;
    target.addressTv = null;
    target.priceTv = null;
    target.farmNameTv = null;
    target.phoneImg = null;
    target.activityIntroTv = null;
    target.addMessageImg = null;
    target.tvLeaveMessageNum = null;
    target.joinBt = null;
  }
}
