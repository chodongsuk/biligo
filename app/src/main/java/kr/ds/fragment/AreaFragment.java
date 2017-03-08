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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.com.biligo.TeacherActivity;
import kr.com.biligo.WebActivity2;
import kr.ds.adapter.AreaAdapter;
import kr.ds.config.Config;
import kr.ds.data.BaseResultListener;
import kr.ds.data.AreaData;
import kr.ds.handler.AreaHandler;

/**
 * Created by Administrator on 2017-03-07.
 */
public class AreaFragment extends Fragment implements View.OnClickListener{

    private ArrayList<AreaHandler> mData;
    private Context mContext;
    private View mView;
    private AreaData mAreaData;
    private GridView mGridView;
    private AreaAdapter mAreaAdapter;
    private ProgressBar mProgressBar;
    private ImageView mImageViewBanner;


    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();
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

        mView = inflater.inflate(R.layout.area_fragment, null);
        (mImageViewBanner = (ImageView)mView.findViewById(R.id.imageView_banner)).setOnClickListener(this);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mGridView = (GridView) mView.findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TEST","onItemClick");
                Intent NextIntent = new Intent(mContext, TeacherActivity.class);
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
        mAreaData = new AreaData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mAreaData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<AreaHandler>) arrayList;
                            mAreaAdapter = new AreaAdapter(mContext, mData);
                            mGridView.setAdapter(mAreaAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).setParam("").getView();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_banner:
                Intent NextIntent = new Intent(mContext, WebActivity2.class);
                NextIntent.putExtra("title", "강사 빌리고 이용안내?");
                NextIntent.putExtra("url", "http://blog.naver.com/kiminhan4909/220948992883");
                startActivity(NextIntent);
                break;
        }
    }
}
