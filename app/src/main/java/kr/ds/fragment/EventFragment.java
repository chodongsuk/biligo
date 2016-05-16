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
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kr.com.biligo.EventViewActivity;
import kr.com.biligo.GoodViewActivity;
import kr.com.biligo.R;
import kr.com.biligo.WebActivity;
import kr.ds.adapter.EventAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.EventData;
import kr.ds.handler.EventHandler;

/**
 * Created by Administrator on 2016-05-15.
 */
public class EventFragment extends Fragment{
    private ArrayList<EventHandler> mData;
    private Context mContext;
    private View mView;
    private EventData mEventData;
    private ListView mListView;
    private EventAdapter mEventAdapter;
    private ProgressBar mProgressBar;

    public EventFragment() {
    }


    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
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

        mView = inflater.inflate(R.layout.sub_fragment, null);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (ListView)mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.i("TEST","onItemClick");
                Intent NextIntent = new Intent(mContext, EventViewActivity.class);
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
        mEventData = new EventData(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mEventData.clear().setCallBack(
                new BaseResultListener() {

                    @Override
                    public <T> void OnComplete(String result) {

                    }

                    @Override
                    public <T> void OnComplete(ArrayList<T> arrayList) {
                        mProgressBar.setVisibility(View.GONE);
                        if(arrayList != null){
                            mData = (ArrayList<EventHandler>) arrayList;
                            mEventAdapter = new EventAdapter(mContext, mData);
                            mListView.setAdapter(mEventAdapter);
                        }

                    }

                    @Override
                    public void OnError(String str) {
                        mProgressBar.setVisibility(View.GONE);

                    }
                }).getView();


    }
}
