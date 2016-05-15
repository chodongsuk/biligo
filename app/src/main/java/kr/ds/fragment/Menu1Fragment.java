package kr.ds.fragment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.com.biligo.SubActivity;
import kr.ds.data.BaseResultListener;
import kr.ds.data.MainData;
import kr.ds.handler.MainHandler;
import kr.ds.widget.ContentViewPager;

/**
 * Created by Administrator on 2016-04-27.
 */
public class Menu1Fragment extends BaseFragment implements View.OnClickListener{
    private Context mContext;
    private View mView;
    private LinearLayout mLinearLayoutGood, mLinearLayoutShop, mLinearLayoutEvent;
    public final static int TypeGood = 1;
    public final static int TypeShop = 2;
    public final static int TypeEvent = 3;
    private ContentViewPager mContentViewPager;
    private MainData mMainData;


    public static Menu1Fragment newInstance() {
        Menu1Fragment fragment = new Menu1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext = getActivity();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.i("TEST","onCreateView");
        mView = inflater.inflate(R.layout.menu1_fragment, null);
        (mLinearLayoutGood = (LinearLayout) mView.findViewById(R.id.linearLayout_good)).setOnClickListener(this);
        (mLinearLayoutShop = (LinearLayout) mView.findViewById(R.id.linearLayout_shop)).setOnClickListener(this);
        (mLinearLayoutEvent = (LinearLayout) mView.findViewById(R.id.linearLayout_event)).setOnClickListener(this);

        mContentViewPager = (ContentViewPager) mView.findViewById(R.id.viewpager);
        mMainData = new MainData(mContext);
        mMainData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete() {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        Log.i("TEST","onComplete");
                        if(arrayList != null){
                            ArrayList<MainHandler> mData = (ArrayList<MainHandler>) arrayList;
                            String[] mDatas = new String[mData.size()];
                            for(int i=0; i<mData.size(); i++){
                                mDatas[i] = mData.get(i).getMd_image();
                            }
                            mContentViewPager.setVisibility(View.VISIBLE);
                            mContentViewPager.setView(mDatas);
                        }else{
                            mContentViewPager.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void OnError(String str) {

                    }
                }).getView();



        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        Intent NextIntent;
        switch (v.getId()){
            case R.id.linearLayout_good:
                NextIntent = new Intent(mContext, SubActivity.class);
                NextIntent.putExtra("type", TypeGood);
                startActivity(NextIntent);
                break;
            case R.id.linearLayout_shop:
                NextIntent = new Intent(mContext, SubActivity.class);
                NextIntent.putExtra("type", TypeShop);
                startActivity(NextIntent);
                break;
            case R.id.linearLayout_event:
                NextIntent = new Intent(mContext, SubActivity.class);
                NextIntent.putExtra("type", TypeEvent);
                startActivity(NextIntent);
                break;
        }

    }
}
