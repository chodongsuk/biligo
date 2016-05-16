package kr.ds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.com.biligo.WebActivity;
import kr.ds.adapter.BoardAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.BoardData;
import kr.ds.handler.BoardHandler;

/**
 * Created by Administrator on 2016-04-27.
 */
public class Menu3Fragment extends BaseFragment {
    private ArrayList<BoardHandler> mData;
    private Context mContext;
    private View mView;
    private BoardData mBoardData;
    private ListView mListView;
    private BoardAdapter mBoardAdapter;
    private ProgressBar mProgressBar;

    public static Menu3Fragment newInstance() {
        Menu3Fragment fragment = new Menu3Fragment();
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
        mView = inflater.inflate(R.layout.sub_fragment, null);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (ListView)mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (mData.get(position).getLayout() == false) {
                    mData.get(position).setLayout(true);
                } else {
                    mData.get(position).setLayout(false);
                }
                mBoardAdapter.notifyDataSetChanged();

            }
        });
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBoardData = new BoardData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mBoardData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(java.util.ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<BoardHandler>) arrayList;
                            mBoardAdapter = new BoardAdapter(mContext, mData);
                            mListView.setAdapter(mBoardAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).getView();
    }
}
