package com.jobnew.farm.utils.addressPicker;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.adapter.ArrayWheelAdapter;
import com.jobnew.farm.entity.address.City;
import com.jobnew.farm.entity.address.County;
import com.jobnew.farm.entity.address.Province;
import com.jobnew.farm.entity.address.dataAddress;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AddressPicker extends RelativeLayout implements OnWheelScrollListener, OnWheelChangedListener {
    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * 所有省的id
     */
    protected String[] mProvinceIds;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 区域id
     */
    protected Map<String, String> mAreaIdDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";
    /**
     * 当前省的id
     */
    protected String mCurrentPrinceId = "";
    /**
     * 当前区的id
     */
    protected String mCurrentAreaId = "";

    private WheelView mWheelViewProvince;
    private WheelView mWheelViewCity;
    private WheelView mWheelViewDistrict;

    private LayoutInflater mInflater = null;

    private View mViewAddressPick;

    private TextView mBtnAddressConfirm,mBtnAddressCancel;
    private ImageView mBtnCancel;

    private OnAddressPickerChangListener mOnAddressPickChangListener;

    private Context mContext;

    public AddressPicker(Context context, boolean isShowTotal) {
        super(context);
        setShowTotalOptions(isShowTotal);
        // TODO Auto-generated constructor stub
        init(context);
        initData();
    }

    public AddressPicker(Context context) {
        this(context, false);
    }

    public void init(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        mViewAddressPick = mInflater.inflate(R.layout.pop_address_pick, null);
        mWheelViewProvince = (WheelView) mViewAddressPick.findViewById(R.id.id_province);
        mWheelViewProvince.setCyclic(true);
//        mWheelViewProvince.setShadowColor(R.drawable.bg_wheel, R.drawable.bg_wheel, R.drawable.bg_wheel);
        mWheelViewCity = (WheelView) mViewAddressPick.findViewById(R.id.id_city);
        mWheelViewCity.setCyclic(true);
//        mWheelViewCity.setShadowColor(R.drawable.bg_wheel, R.drawable.bg_wheel, R.drawable.bg_wheel);
        mWheelViewDistrict = (WheelView) mViewAddressPick.findViewById(R.id.id_district);
        mWheelViewDistrict.setCyclic(true);
//        mWheelViewDistrict.setShadowColor(R.drawable.bg_wheel, R.drawable.bg_wheel, R.drawable.bg_wheel);
        mWheelViewProvince.addChangingListener(this);
        mWheelViewCity.addChangingListener(this);
        mWheelViewDistrict.addChangingListener(this);

        mBtnAddressConfirm = (TextView) mViewAddressPick.findViewById(R.id.address_confirm_btn);
        mBtnAddressConfirm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOnAddressPickChangListener != null) {
                    mOnAddressPickChangListener.onAddressPickerSelect(mCurrentProviceName, mCurrentCityName, mCurrentDistrictName, mCurrentAreaId,mCurrentPrinceId);

                }
            }
        });
        mBtnCancel = (ImageView) mViewAddressPick.findViewById(R.id.img_outside);
        mBtnAddressCancel = (TextView) mViewAddressPick.findViewById(R.id.address_cancel_btn);
        mBtnAddressCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOnAddressPickChangListener != null) {
                    mOnAddressPickChangListener.onAddressPickerCancel();
                }
            }
        });
        mBtnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOnAddressPickChangListener != null) {
                    mOnAddressPickChangListener.onAddressPickerCancel();
                }
            }
        });
        addView(mViewAddressPick);

    }

    public void initData() {
        initProvinceDatas();
        ArrayWheelAdapter<String> awa = new ArrayWheelAdapter<String>(mContext, mProvinceDatas);
        awa.setTextSize(15);
        mWheelViewProvince.setViewAdapter(awa);
        mWheelViewProvince.setCyclic(false);
        mWheelViewProvince.setCurrentItem(0);
        updateCities();
        updateAreas();

    }

    private boolean isShowTotal = false;

    public void setShowTotalOptions(boolean isShowTotal) {
        this.isShowTotal = isShowTotal;
    }
    /**
     * 解析省市区的XML数据
     */
    protected void initProvinceDatas() {
        List<Province> provinceList  = new Gson().fromJson(new dataAddress().data, new TypeToken<List<Province>>() {
        }.getType());
//        AssetManager asset = mContext.getAssets();
//        try {
//            InputStream input = asset.open("province_data.xml");
//            // 创建一个解析xml的工厂对象
//            SAXParserFactory spf = SAXParserFactory.newInstance();
//            // 解析xml
//            SAXParser parser = spf.newSAXParser();
//            XmlParserHandler handler = new XmlParserHandler();
//            parser.parse(input, handler);
//            input.close();
//            // 获取解析出来的数据
//            provinceList = handler.getDataList();
        if (isShowTotal) {
            List<City> citys = new ArrayList<City>();
            List<County> districtLists = new ArrayList<County>();
            districtLists.add(new County("全部", "111"));
            City city = new City("全部", districtLists);
            citys.add(0, city);
            provinceList.add(0, new Province("全部", citys));
        }
        // */ 初始化默认选中的省、市、区
        if (provinceList != null && !provinceList.isEmpty()) {
            mCurrentProviceName = provinceList.get(0).getName();
            mCurrentPrinceId=provinceList.get(0).getId()+"";
            List<City> cityList = provinceList.get(0).getCitys();
            if (cityList != null && !cityList.isEmpty()) {
                mCurrentCityName = cityList.get(0).getName();
                List<County> districtList = cityList.get(0).getCountys();
                mCurrentDistrictName = districtList.get(0).getName();
                mCurrentAreaId = districtList.get(0).getId()+"";
            }
        }
        // */
        mProvinceDatas = new String[provinceList.size()];
        mProvinceIds = new String[provinceList.size()];
        for (int i = 0; i < provinceList.size(); i++) {
            // 遍历所有省的数据
            mProvinceDatas[i] = provinceList.get(i).getName();
            mProvinceIds[i] = provinceList.get(i).getId()+"";
            List<City> cityList = provinceList.get(i).getCitys();
            String[] cityNames = new String[cityList.size()];
            for (int j = 0; j < cityList.size(); j++) {
                // 遍历省下面的所有市的数据
                cityNames[j] = cityList.get(j).getName();
                List<County> districtList = cityList.get(j).getCountys();
                String[] distrinctNameArray = new String[districtList.size()];
                County[] distrinctArray = new County[districtList.size()];
                for (int k = 0; k < districtList.size(); k++) {
                    // 遍历市下面所有区/县的数据
                    County County = new County(districtList.get(k).getName(), districtList.get(k).getId()+"");
                    // 区/县对于的区域id，保存到mAreaIdDatasMap
                    mAreaIdDatasMap.put(cityList.get(j).getName() + districtList.get(k).getName(), districtList.get(k).getId()+"");
                    distrinctArray[k] = County;
                    distrinctNameArray[k] = County.getName();
                }
                // 市-区/县的数据，保存到mDistrictDatasMap
                mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
            }
            // 省-市的数据，保存到mCitisDatasMap
            mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
        }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        } finally {
//
//        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mWheelViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        try {
            ArrayWheelAdapter<String> awa = new ArrayWheelAdapter<String>(mContext, areas);
            awa.setTextSize(15);
            // awa.setTextColor(0xFFFF0000);
            mWheelViewDistrict.setViewAdapter(awa);
            mWheelViewDistrict.setCyclic(false);
            mWheelViewDistrict.setCurrentItem(0);
            mCurrentDistrictName = areas[0];
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mWheelViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        mCurrentPrinceId=mProvinceIds[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        ArrayWheelAdapter<String> awa = new ArrayWheelAdapter<String>(mContext, cities);
        awa.setTextSize(15);
        // awa.setTextColor(0xFFFF0000);
        mWheelViewCity.setViewAdapter(awa);
        mWheelViewCity.setCyclic(false);
        mWheelViewCity.setCurrentItem(0);
        updateAreas();
    }

    public void setListener(OnAddressPickerChangListener listener) {
        mOnAddressPickChangListener = listener;
    }

    public void setIndicatorAndAreaById(String areaId) {

    }

    public void setIndicatorById(String areaId) {
        if (TextUtils.isEmpty(areaId))
            return;
        int provinceIndex = 0;
        int cityIndex = 0;
        int districtIndex = 0;
        String district = "";
        String city = "";
        String province = "";
        for (Entry<String, String> entry : mAreaIdDatasMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (areaId.equals(value)) {
                district = key;
                break;
            }
        }

        if (TextUtils.isEmpty(district))
            return;

        boolean find = false;
        for (Entry<String, String[]> entry : mDistrictDatasMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            int len = value.length;
            for (int i = 0; i < len; i++) {
                String tmpDistrict = value[i];
                if (district.equals(tmpDistrict)) {
                    city = key;
                    find = true;
                    districtIndex = i;
                    break;
                }
            }
            // for(String tmpDistrict:value) {
            // if(district.equals(tmpDistrict)) {
            // city = key;
            // find = true;
            // break;
            // }
            // }
            if (find)
                break;
        }

        if (TextUtils.isEmpty(city))
            return;
        find = false;
        for (Entry<String, String[]> entry : mCitisDatasMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();

            int len = value.length;
            for (int i = 0; i < len; i++) {
                String tmpCity = value[i];
                if (city.equals(tmpCity)) {
                    province = key;
                    find = true;
                    cityIndex = i;
                    break;
                }
            }

            // for(String tmpCity:value) {
            // if(city.equals(tmpCity)) {
            // province = key;
            // find = true;
            // break;
            // }
            // }
            if (find)
                break;
        }
        if (TextUtils.isEmpty(province))
            return;

        int len = mProvinceDatas.length;
        for (int i = 0; i < len; i++) {
            String tmpProvince = mProvinceDatas[i];
            if (province.equals(tmpProvince)) {
                provinceIndex = i;
                break;
            }
        }

        mWheelViewProvince.setCurrentItem(provinceIndex);
        mWheelViewCity.setCurrentItem(cityIndex);
        mWheelViewCity.setCyclic(false);
        mWheelViewDistrict.setCurrentItem(districtIndex);

    }

    public String getAreaById(String areaId) {
        if (TextUtils.isEmpty(areaId))
            return "";
        String area = "";
        String district = "";
        String city = "";
        String province = "";
        for (Entry<String, String> entry : mAreaIdDatasMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (areaId.equals(value)) {
                district = key;
                break;
            }
        }

        if (TextUtils.isEmpty(district))
            return "";

        boolean find = false;
        for (Entry<String, String[]> entry : mDistrictDatasMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            for (String tmpDistrict : value) {
                if (district.equals(tmpDistrict)) {
                    city = key;
                    find = true;
                    break;
                }
            }
            if (find)
                break;
        }

        if (TextUtils.isEmpty(city))
            return "";
        find = false;
        for (Entry<String, String[]> entry : mCitisDatasMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            for (String tmpCity : value) {
                if (city.equals(tmpCity)) {
                    province = key;
                    find = true;
                    break;
                }
            }
            if (find)
                break;
        }

        if (city.equals(province)) {
            area = city + district;
        } else {
            area = province + city + district;
        }

        return area;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
//        Util.Log(mCurrentDistrictName + " " + mCurrentAreaId);
        if (wheel == mWheelViewProvince) {
            updateCities();
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
            mCurrentAreaId = mAreaIdDatasMap.get(mCurrentDistrictName);
        } else if (wheel == mWheelViewCity) {
            updateAreas();
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
            mCurrentAreaId = mAreaIdDatasMap.get(mCurrentDistrictName);
        } else if (wheel == mWheelViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentAreaId = mAreaIdDatasMap.get(mCurrentDistrictName);
        }
        mCurrentAreaId = mAreaIdDatasMap.get(mCurrentCityName + mCurrentDistrictName);
        // Log.e("AreaID", mCurrentAreaId + "   is null?");
        // Log.e("mCurrentCityName", mCurrentCityName + "   is null?");
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        // TODO Auto-generated method stub

    }

    public interface OnAddressPickerChangListener {
        void onAddressPickerSelect(String province, String city, String district, String areaId,String provinceId);

        void onAddressPickerCancel();
    }

}
