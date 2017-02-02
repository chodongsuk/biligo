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

import kr.com.biligo.Event2ViewActivity;
import kr.com.biligo.EventViewActivity;
import kr.com.biligo.R;
import kr.ds.adapter.EventAdapter;
import kr.ds.data.BaseResultListener;
import kr.ds.data.Event2Data;
import kr.ds.data.EventData;
import kr.ds.handler.EventHandler;

/**
 * Created by Administrator on 2016-05-15.
 */
public class Event2Fragment extends Fragment{
    private ArrayList<EventHandler> mData;
    private Context mContext;
    private View mView;
    private Event2Data mEvent2Data;
    private ListView mListView;
    private EventAdapter mEventAdapter;
    private ProgressBar mProgressBar;

    public Event2Fragment() {
    }


    public static Event2Fragment newInstance() {
        Event2Fragment fragment = new Event2Fragment();
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
                Intent NextIntent = new Intent(mContext, Event2ViewActivity.class);
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
        mEvent2Data = new Event2Data(mContext);
        mProgressBar.setVisibility(View.VISIBLE);
        mEvent2Data.clear().setCallBack(
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
