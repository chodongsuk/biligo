package kr.com.biligo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ds.data.BaseResultListener;
import kr.ds.data.EventSaveData;
import kr.ds.handler.EventHandler;
import kr.ds.utils.DsObjectUtils;
import kr.ds.widget.ContentViewPager;

/**
 * Created by Administrator on 2016-05-16.
 */
public class EventViewActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private ScrollView mScrollView;
    private ContentViewPager mContetContentViewPager;
    private EventHandler mSavedata;
    private EditText mEditTextName;
    private EditText mEditTextHp;
    private EditText mEditTextAddress;
    private EditText mEditTextStartDate;
    private EditText mEditTextEndDate;
    private Button mButton;
    private CheckBox mCheckBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedata = (EventHandler) getIntent().getParcelableExtra("data");

        setContentView(R.layout.activity_view);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getEd_name())){
                mToolbar.setTitle(mSavedata.getEd_name());
            }else{
                mToolbar.setTitle(getResources().getString(R.string.app_name));
            }

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mContetContentViewPager = (ContentViewPager)findViewById(R.id.viewpager);
        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getSub_images())){
            mContetContentViewPager.setVisibility(View.VISIBLE);
            String[] mDatas = new String[(mSavedata.getSub_images().split(",").length)];
            for(int i=0; i<mSavedata.getSub_images().split(",").length; i++){
                mDatas[i] = mSavedata.getSub_images().split(",")[i];
            }
            mContetContentViewPager.setView(mDatas);
        }else{
            mContetContentViewPager.setVisibility(View.GONE);
        }
        (mCheckBox = (CheckBox) findViewById(R.id.checkbox)).setOnClickListener(this);

        mEditTextName = (EditText)findViewById(R.id.ediText_name);
        mEditTextHp = (EditText)findViewById(R.id.ediText_hp);
        mEditTextAddress = (EditText)findViewById(R.id.ediText_address);
        mEditTextStartDate = (EditText)findViewById(R.id.ediText_start_date);
        mEditTextEndDate = (EditText)findViewById(R.id.ediText_end_date);

        (mButton = (Button)findViewById(R.id.button)).setOnClickListener(this);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public Boolean Check() {
        if(!mCheckBox.isChecked()){
            Toast.makeText(getApplicationContext(),"약관 동의 체크 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextName.getText().toString())) {
            Toast.makeText(getApplicationContext(),"주문자를 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextHp.getText().toString())) {
            Toast.makeText(getApplicationContext(),"핸드폰을 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextAddress.getText().toString())) {
            Toast.makeText(getApplicationContext(),"주소을 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextStartDate.getText().toString())) {
            Toast.makeText(getApplicationContext(),"대여일을 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextEndDate.getText().toString())) {
            Toast.makeText(getApplicationContext(),"반납일을 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                if(Check()){

                    HashMap<String, String> mHashMap = new HashMap<String, String>();
                    mHashMap.put("ed_uid", mSavedata.getEd_uid());
                    mHashMap.put("efr_title", "");
                    mHashMap.put("efr_name", mEditTextName.getText().toString());
                    mHashMap.put("efr_start_date", mEditTextStartDate.getText().toString());
                    mHashMap.put("efr_end_date", mEditTextEndDate.getText().toString());
                    mHashMap.put("efr_address", mEditTextAddress.getText().toString());
                    mHashMap.put("efr_tell", mEditTextHp.getText().toString());

                    if (mHashMap != null) {
                        new EventSaveData(getApplicationContext()).clear().setCallBack(new BaseResultListener() {


                            @Override
                            public <T> void OnComplete(String result) {
                                if(result.matches("success")){
                                    finish();
                                }
                            }

                            @Override
                            public <T> void OnComplete(ArrayList<T> arrayList) {

                            }

                            @Override
                            public void OnError(String str) {
                                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                            }
                        }).getViewPost(mHashMap);
                    }

                }
                break;
        }
    }
}
