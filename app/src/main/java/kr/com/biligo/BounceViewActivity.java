package kr.com.biligo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ds.data.BaseResultListener;
import kr.ds.data.BounceSaveData;
import kr.ds.data.TeacherSaveData;
import kr.ds.handler.TeacherHandler;
import kr.ds.utils.DsObjectUtils;
import kr.ds.utils.ScreenUtils;

/**
 * Created by Administrator on 2017-03-08.
 */
public class BounceViewActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private TeacherHandler mSavedata;
    private ImageView mImageView;
    private TextView mTextViewName;
    private TextView mTextViewType;
    private TextView mTextViewTitle;
    private TextView mTextViewContent;


    private LinearLayout mLinearLayoutSubImage;

    private final ImageLoader imageDownloader = ImageLoader.getInstance();

    private EditText mEditTextName;
    private EditText mEditTextHp;
    private EditText mEditTextAddress;
    private EditText mEditTextContent;
    private Button mButton;
    private CheckBox mCheckBox;
    private ImageView mImageViewBanner;
    private LinearLayout mLinearLayoutType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedata = (TeacherHandler) getIntent().getParcelableExtra("data");
        setContentView(R.layout.activity_bounce_view);

        mLinearLayoutType = (LinearLayout)findViewById(R.id.linearLayout_type);
        (mImageViewBanner = (ImageView)findViewById(R.id.imageView_banner)).setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("바운스 빌리고");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mTextViewName = (TextView) findViewById(R.id.textView_name);
        mTextViewTitle = (TextView) findViewById(R.id.textView_title);
        mTextViewType = (TextView) findViewById(R.id.textView_type);
        mLinearLayoutSubImage = (LinearLayout) findViewById(R.id.linearLayout_sub_images);
        mTextViewContent = (TextView) findViewById(R.id.textView_content);

        (mCheckBox = (CheckBox) findViewById(R.id.checkbox)).setOnClickListener(this);

        mEditTextName = (EditText)findViewById(R.id.ediText_name);
        mEditTextHp = (EditText)findViewById(R.id.ediText_hp);
        mEditTextAddress = (EditText)findViewById(R.id.ediText_address);
        mEditTextContent = (EditText)findViewById(R.id.ediText_content);

        (mButton = (Button)findViewById(R.id.button)).setOnClickListener(this);


        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getTd_image())){
            imageDownloader.displayImage(mSavedata.getTd_image(), mImageView, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                    mImageView.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    mImageView.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                    // TODO Auto-generated method stub
                    mImageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                    mImageView.setVisibility(View.GONE);
                }
            });
        }else{
            mImageView.setVisibility(View.GONE);
        }


        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getTd_name())){
            mTextViewName.setText(mSavedata.getTd_name());
        }else{
            mTextViewName.setText("");
        }
        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getTd_title())){
            mTextViewTitle.setText(mSavedata.getTd_title());
        }else{
            mTextViewTitle.setText("");
        }
        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getTd_type())){
            //mTextViewType.setText(mSavedata.getTd_type());
            ImageView imageView[] = new ImageView[Integer.parseInt(mSavedata.getTd_type())];
            for(int i=0; i<Integer.parseInt(mSavedata.getTd_type()); i++) {
                imageView[i] = new ImageView(getApplicationContext());
                imageView[i].setImageResource(R.mipmap.type);
                mLinearLayoutType.addView(imageView[i]);
            }
        }else{
            //mTextViewType.setText("");
        }



        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getSub_images())){
            //mContetViewPager.setVisibility(View.VISIBLE);
            mLinearLayoutSubImage.setVisibility(View.VISIBLE);
            String[] mDatas = new String[(mSavedata.getSub_images().split(",").length)];
            final ImageView imageView[] = new ImageView[mDatas.length];
            for(int i=0; i<mSavedata.getSub_images().split(",").length; i++){
                mDatas[i] = mSavedata.getSub_images().split(",")[i];
                imageView[i] = new ImageView(getApplicationContext());
                imageView[i].setPadding(ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 10),ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 5),ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 10),ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 5));
                imageView[i].setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 117), ScreenUtils.getInstacne().getPixelFromDPI(getApplicationContext(), 103)));
                mLinearLayoutSubImage.addView(imageView[i]);
                imageDownloader.displayImage(mDatas[i], imageView[i]);
            }
        }else{
            mLinearLayoutSubImage.setVisibility(View.GONE);
        }

        if(!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mSavedata.getTd_content())){
            mTextViewContent.setText(Html.fromHtml(mSavedata.getTd_content()));
        }else{
            mTextViewContent.setText("");
        }

    }


    public Boolean Check() {
        if(!mCheckBox.isChecked()){
            Toast.makeText(getApplicationContext(),"약관 동의 체크 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextName.getText().toString())) {
            Toast.makeText(getApplicationContext(),"이름 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
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
        if(DsObjectUtils.getInstance(getApplicationContext()).isEmpty(mEditTextContent.getText().toString())) {
            Toast.makeText(getApplicationContext(),"예약 / 날짜 / 시간 / 메모 를 입력 해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
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
                    mHashMap.put("td_uid", mSavedata.getTd_uid());
                    mHashMap.put("tfd_name", mEditTextName.getText().toString());
                    mHashMap.put("tfd_content", mEditTextContent.getText().toString());
                    mHashMap.put("tfd_address", mEditTextAddress.getText().toString());
                    mHashMap.put("tfd_tell", mEditTextHp.getText().toString());
                    if (mHashMap != null) {
                        new BounceSaveData(getApplicationContext()).clear().setCallBack(new BaseResultListener() {
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
            case R.id.imageView_banner:
                Intent NextIntent = new Intent(getApplicationContext(), WebActivity2.class);
                NextIntent.putExtra("title", "에어바운스 빌리고 신청 방법");
                NextIntent.putExtra("url", "http://blog.naver.com/kiminhan4909/221072363921");
                startActivity(NextIntent);
                break;
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
}
