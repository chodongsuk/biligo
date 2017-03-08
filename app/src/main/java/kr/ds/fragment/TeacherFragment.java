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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.com.biligo.TeacherViewActivity;
import kr.com.biligo.WebActivity2;
import kr.ds.adapter.TeacherAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.TeacherData;
import kr.ds.handler.AreaHandler;
import kr.ds.handler.GoodHandler;
import kr.ds.handler.TeacherHandler;

/**
 * Created by Administrator on 2016-05-15.
 */
public class TeacherFragment extends Fragment implements  View.OnClickListener{
    private ArrayList<TeacherHandler> mData;
    private Context mContext;
    private View mView;
    private TeacherData mTeacherData;
    private ListView mListView;
    private TeacherAdapter mTeacherAdapter;
    private ProgressBar mProgressBar;
    private AreaHandler mSavedata;
    private ImageView mImageViewBanner;

    public TeacherFragment() {
    }

    public static TeacherFragment newInstance(AreaHandler data) {
        TeacherFragment fragment = new TeacherFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
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

        mView = inflater.inflate(R.layout.teacher_fragment, null);
        (mImageViewBanner = (ImageView)mView.findViewById(R.id.imageView_banner)).setOnClickListener(this);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (ListView)mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TEST","onItemClick");
                Intent NextIntent = new Intent(mContext, TeacherViewActivity.class);
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
        if(getArguments() != null){
            mSavedata = getArguments().getParcelable("data");
        }
        mTeacherData = new TeacherData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mTeacherData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<TeacherHandler>) arrayList;
                            mTeacherAdapter = new TeacherAdapter(mContext, mData);
                            mListView.setAdapter(mTeacherAdapter);
                        }
                    }

                    @Override
                    public void OnError(String str) {
                        Toast.makeText(mContext, "강사가 존재 하지 않습니다.",Toast.LENGTH_SHORT).show();
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).setParam("?code="+mSavedata.getCode()).getView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_banner:
                Intent NextIntent = new Intent(mContext, WebActivity2.class);
                NextIntent.putExtra("title", "강사 빌리고 출강 상품");
                NextIntent.putExtra("url", "http://blog.naver.com/kiminhan4909/220948994977");
                startActivity(NextIntent);
                break;
        }
    }


}
