package kr.com.biligo;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ds.config.Config;
import kr.ds.data.BaseResultListener;
import kr.ds.data.GoodSaveData;
import kr.ds.handler.GoodHandler;
import kr.ds.utils.DsObjectUtils;
import kr.ds.utils.ScreenUtils;
import kr.ds.widget.ContentViewPager;

/**
 * Created by Administrator on 2016-05-16.
 */
public class GoodViewActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private ScrollView mScrollView;
    private ContentViewPager mContetViewPager;
    private GoodHandler mSavedata;
    private EditText mEditTextName;
    private EditText mEditTextHp;
    private EditText mEditTextAddress;
    private EditText mEditTextStartDate;
    private EditText mEditTextEndDate;
    private Button mButton;
    private CheckBox mCheckBox;
    private WebView mWebView;
    private LinearLayout mLinearLayoutImages;
    private final ImageLoader imageDownloader = ImageLoader.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedata = (GoodHandler) getIntent().getParcelableExtra("data");
        setContentView(R.layout.activity_view);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getGd_name())){
                mToolbar.setTitle(mSavedata.getGd_name());
            }else{
                mToolbar.setTitle(getResources().getString(R.string.app_name));
            }

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mLinearLayoutImages = (LinearLayout)findViewById(R.id.linearLayout_images);
        //mContetViewPager = (ContentViewPager)findViewById(R.id.viewpager);
        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getSub_images())){
          //mContetViewPager.setVisibility(View.VISIBLE);
            mLinearLayoutImages.setVisibility(View.VISIBLE);
            String[] mDatas = new String[(mSavedata.getSub_images().split(",").length)];
            final ImageView imageView[] = new ImageView[mDatas.length];
            for(int i=0; i<mSavedata.getSub_images().split(",").length; i++){
                mDatas[i] = mSavedata.getSub_images().split(",")[i];
                imageView[i] = new ImageView(getApplicationContext());
                imageView[i].setLayoutParams(new LinearLayout.LayoutParams(getWidth(), ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 227)));
                mLinearLayoutImages.addView(imageView[i]);
                imageDownloader.displayImage(mDatas[i], imageView[i]);
            }
            //mContetViewPager.setView(mDatas);
        }else{
            mLinearLayoutImages.setVisibility(View.GONE);
            //mContetViewPager.setVisibility(View.GONE);
        }
        (mCheckBox = (CheckBox) findViewById(R.id.checkbox)).setOnClickListener(this);

        mEditTextName = (EditText)findViewById(R.id.ediText_name);
        mEditTextHp = (EditText)findViewById(R.id.ediText_hp);
        mEditTextAddress = (EditText)findViewById(R.id.ediText_address);
        mEditTextStartDate = (EditText)findViewById(R.id.ediText_start_date);
        mEditTextEndDate = (EditText)findViewById(R.id.ediText_end_date);

        (mButton = (Button)findViewById(R.id.button)).setOnClickListener(this);

        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebClient());
        WebSettings set = mWebView.getSettings();
        set.setJavaScriptEnabled(true);
        mWebView.loadUrl(Config.URL + Config.URL_XML + Config.WEB3);

    }

    public int getWidth(){
        Point p = new Point();
        p.x = getResources().getDisplayMetrics().widthPixels;
        return p.x;
    }
    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
                    mHashMap.put("gd_uid", mSavedata.getGd_uid());
                    mHashMap.put("gfr_title", "");
                    mHashMap.put("gfr_name", mEditTextName.getText().toString());
                    mHashMap.put("gfr_start_date", mEditTextStartDate.getText().toString());
                    mHashMap.put("gfr_end_date", mEditTextEndDate.getText().toString());
                    mHashMap.put("gfr_address", mEditTextAddress.getText().toString());
                    mHashMap.put("gfr_tell", mEditTextHp.getText().toString());

                    if (mHashMap != null) {
                        new GoodSaveData(getApplicationContext()).clear().setCallBack(new BaseResultListener() {


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mContetViewPager.finish();
    }
}
