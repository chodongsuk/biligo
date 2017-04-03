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
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kr.com.biligo.R;
import kr.com.biligo.WebActivity;
import kr.ds.adapter.ShopAdapter;
import kr.ds.adapter.ShopAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.ShopData;
import kr.ds.data.ShopData;
import kr.ds.handler.ShopHandler;
import kr.ds.handler.ShopHandler;

/**
 * Created by Administrator on 2016-05-15.
 */
public class ShopFragment extends Fragment{
    private ArrayList<ShopHandler> mData;
    private Context mContext;
    private View mView;
    private ShopData mShopData;
    private GridView mListView;
    private ShopAdapter mShopAdapter;
    private ProgressBar mProgressBar;

    public ShopFragment() {
    }


    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
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

        mView = inflater.inflate(R.layout.shop_fragment, null);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (GridView) mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TEST","onItemClick");
                Intent NextIntent = new Intent(mContext, WebActivity.class);
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
        mShopData = new ShopData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mShopData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<ShopHandler>) arrayList;
                            mShopAdapter = new ShopAdapter(mContext, mData);
                            mListView.setAdapter(mShopAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).getView();


    }
}
