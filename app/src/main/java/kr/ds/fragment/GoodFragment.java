package kr.ds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kr.com.biligo.GoodViewActivity;
import kr.com.biligo.R;
import kr.ds.adapter.GoodAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.GoodData;
import kr.ds.handler.GoodHandler;

/**
 * Created by Administrator on 2016-05-15.
 */
public class GoodFragment extends Fragment implements View.OnClickListener{
    private ArrayList<GoodHandler> mData;
    private Context mContext;
    private View mView;
    private GoodData mGoodData;
    private ListView mListView;
    private GoodAdapter mGoodAdapter;
    private ProgressBar mProgressBar;
    private String mCode = "001000000";
    private LinearLayout mLinearLayoutTab1;
    private LinearLayout mLinearLayoutTab2;
    private LinearLayout mLinearLayoutTab3;
    private LinearLayout mLinearLayoutTab4;
    private LinearLayout mLinearLayoutTab5;
    private LinearLayout mLinearLayoutTab6;

    public GoodFragment() {
    }


    public static GoodFragment newInstance() {
        GoodFragment fragment = new GoodFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.good_fragment, null);


        (mLinearLayoutTab1 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab1)).setOnClickListener(this);
        (mLinearLayoutTab2 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab2)).setOnClickListener(this);
        (mLinearLayoutTab3 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab3)).setOnClickListener(this);
        (mLinearLayoutTab4 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab4)).setOnClickListener(this);
        (mLinearLayoutTab5 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab5)).setOnClickListener(this);
        (mLinearLayoutTab6 = (LinearLayout) mView.findViewById(R.id.linearLayout_tab6)).setOnClickListener(this);


        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (ListView)mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TEST","onItemClick");
                Intent NextIntent = new Intent(mContext, GoodViewActivity.class);
                NextIntent.putExtra("data", mData.get(position));
                startActivity(NextIntent);

            }
        });


        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        mGoodData = new GoodData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mGoodData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<GoodHandler>) arrayList;
                            mGoodAdapter = new GoodAdapter(mContext, mData);
                            mListView.setAdapter(mGoodAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).setParam("?code="+mCode).getView();


    }

    public void getData(String code){

        mProgressBar.setVisibility(View.VISIBLE);
        mGoodData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<GoodHandler>) arrayList;
                            mGoodAdapter = new GoodAdapter(mContext, mData);
                            mListView.setAdapter(mGoodAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).setParam("?code="+mCode).getView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLayout_tab1:
                getData("001000000");
                break;
            case R.id.linearLayout_tab2:
                getData("002000000");
                break;
            case R.id.linearLayout_tab3:
                getData("003000000");
                break;
            case R.id.linearLayout_tab4:
                getData("004000000");
                break;
            case R.id.linearLayout_tab5:
                getData("005000000");
                break;
            case R.id.linearLayout_tab6:
                getData("006000000");
                break;


        }

    }
}
