// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.MyFarm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlantingPlanDetailsActivity_ViewBinding implements Unbinder {
  private PlantingPlanDetailsActivity target;

  @UiThread
  public PlantingPlanDetailsActivity_ViewBinding(PlantingPlanDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlantingPlanDetailsActivity_ViewBinding(PlantingPlanDetailsActivity target, View source) {
    this.target = target;

    target.tvMajorName = Utils.findRequiredViewAsType(source, R.id.tv_major_name, "field 'tvMajorName'", TextView.class);
    target.tvBreedNum = Utils.findRequiredViewAsType(source, R.id.tv_breed_num, "field 'tvBreedNum'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.llPlantInfo = Utils.findRequiredViewAsType(source, R.id.ll_plant_info, "field 'llPlantInfo'", LinearLayout.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvCropCycle = Utils.findRequiredViewAsType(source, R.id.tv_crop_cycle, "field 'tvCropCycle'", TextView.class);
    target.stvLandNameCard = Utils.findRequiredViewAsType(source, R.id.stv_land_name_card, "field 'stvLandNameCard'", SuperTextView.class);
    target.tvSelectSeed = Utils.findRequiredViewAsType(source, R.id.tv_select_seed, "field 'tvSelectSeed'", TextView.class);
    target.stvSelectSeed = Utils.findRequiredViewAsType(source, R.id.stv_select_seed, "field 'stvSelectSeed'", SuperTextView.class);
    target.tvPlantingScheme = Utils.findRequiredViewAsType(source, R.id.tv_planting_scheme, "field 'tvPlantingScheme'", TextView.class);
    target.stvPlantingScheme = Utils.findRequiredViewAsType(source, R.id.stv_planting_scheme, "field 'stvPlantingScheme'", SuperTextView.class);
    target.stvExecutionSteward = Utils.findRequiredViewAsType(source, R.id.stv_execution_steward, "field 'stvExecutionSteward'", SuperTextView.class);
    target.stvMonitor = Utils.findRequiredViewAsType(source, R.id.stv_monitor, "field 'stvMonitor'", SuperTextView.class);
    target.tvSignboard = Utils.findRequiredViewAsType(source, R.id.tv_signboard, "field 'tvSignboard'", TextView.class);
    target.stvSignboard = Utils.findRequiredViewAsType(source, R.id.stv_signboard, "field 'stvSignboard'", SuperTextView.class);
    target.tvProductProcessing = Utils.findRequiredViewAsType(source, R.id.tv_product_processing, "field 'tvProductProcessing'", TextView.class);
    target.StvProductProcessing = Utils.findRequiredViewAsType(source, R.id.Stv_product_processing, "field 'StvProductProcessing'", SuperTextView.class);
    target.tvInsurance = Utils.findRequiredViewAsType(source, R.id.tv_insurance, "field 'tvInsurance'", TextView.class);
    target.stvInsurance = Utils.findRequiredViewAsType(source, R.id.stv_insurance, "field 'stvInsurance'", SuperTextView.class);
    target.tvDistributioPrice = Utils.findRequiredViewAsType(source, R.id.tv_distributio_price, "field 'tvDistributioPrice'", TextView.class);
    target.stvDistribution = Utils.findRequiredViewAsType(source, R.id.stv_distribution, "field 'stvDistribution'", SuperTextView.class);
    target.tlAddress = Utils.findRequiredViewAsType(source, R.id.tl_address, "field 'tlAddress'", RelativeLayout.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvIntro = Utils.findRequiredViewAsType(source, R.id.tv_intro, "field 'tvIntro'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlantingPlanDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMajorName = null;
    target.tvBreedNum = null;
    target.tvPrice = null;
    target.tvUnitPrice = null;
    target.llPlantInfo = null;
    target.tvArea = null;
    target.tvCropCycle = null;
    target.stvLandNameCard = null;
    target.tvSelectSeed = null;
    target.stvSelectSeed = null;
    target.tvPlantingScheme = null;
    target.stvPlantingScheme = null;
    target.stvExecutionSteward = null;
    target.stvMonitor = null;
    target.tvSignboard = null;
    target.stvSignboard = null;
    target.tvProductProcessing = null;
    target.StvProductProcessing = null;
    target.tvInsurance = null;
    target.stvInsurance = null;
    target.tvDistributioPrice = null;
    target.stvDistribution = null;
    target.tlAddress = null;
    target.name = null;
    target.tvName = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.tvIntro = null;
    target.tvTotalPrice = null;
  }
}
