// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.MyFarm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.marno.easystatelibrary.EasyStatusView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandInformationActivity_ViewBinding implements Unbinder {
  private LandInformationActivity target;

  private View view2131297214;

  @UiThread
  public LandInformationActivity_ViewBinding(LandInformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LandInformationActivity_ViewBinding(final LandInformationActivity target, View source) {
    this.target = target;

    View view;
    target.stvRecordNumber = Utils.findRequiredViewAsType(source, R.id.stv_record_number, "field 'stvRecordNumber'", SuperTextView.class);
    target.stvLandNumber = Utils.findRequiredViewAsType(source, R.id.stv_land_number, "field 'stvLandNumber'", SuperTextView.class);
    target.stvLandArea = Utils.findRequiredViewAsType(source, R.id.stv_land_area, "field 'stvLandArea'", SuperTextView.class);
    target.stvLandUnitPrice = Utils.findRequiredViewAsType(source, R.id.stv_land_unit_price, "field 'stvLandUnitPrice'", SuperTextView.class);
    target.stvRentalPeriod = Utils.findRequiredViewAsType(source, R.id.stv_rental_period, "field 'stvRentalPeriod'", SuperTextView.class);
    target.stvLandAffiliation = Utils.findRequiredViewAsType(source, R.id.stv_land_affiliation, "field 'stvLandAffiliation'", SuperTextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.stvLandAddress = Utils.findRequiredViewAsType(source, R.id.stv_land_address, "field 'stvLandAddress'", SuperTextView.class);
    target.stvLandValidDate = Utils.findRequiredViewAsType(source, R.id.stv_land_valid_date, "field 'stvLandValidDate'", SuperTextView.class);
    target.stvLandNameCard = Utils.findRequiredViewAsType(source, R.id.stv_land_name_card, "field 'stvLandNameCard'", SuperTextView.class);
    target.stvCrop = Utils.findRequiredViewAsType(source, R.id.stv_crop, "field 'stvCrop'", SuperTextView.class);
    target.stvSteward = Utils.findRequiredViewAsType(source, R.id.stv_steward, "field 'stvSteward'", SuperTextView.class);
    target.tvIntro = Utils.findRequiredViewAsType(source, R.id.tv_intro, "field 'tvIntro'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onClick'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.esvMain = Utils.findRequiredViewAsType(source, R.id.esv_main, "field 'esvMain'", EasyStatusView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LandInformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stvRecordNumber = null;
    target.stvLandNumber = null;
    target.stvLandArea = null;
    target.stvLandUnitPrice = null;
    target.stvRentalPeriod = null;
    target.stvLandAffiliation = null;
    target.tvAddress = null;
    target.stvLandAddress = null;
    target.stvLandValidDate = null;
    target.stvLandNameCard = null;
    target.stvCrop = null;
    target.stvSteward = null;
    target.tvIntro = null;
    target.tvSave = null;
    target.esvMain = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
