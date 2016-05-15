package kr.ds.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import kr.ds.asynctask.DsAsyncTask;
import kr.ds.asynctask.DsAsyncTaskCallback;
import kr.ds.config.Config;
import kr.ds.handler.MainHandler;
import kr.ds.httpclient.DsHttpClient;
import kr.ds.utils.DsObjectUtils;

/**
 * Created by Administrator on 2016-05-15.
 */
public class MainData extends BaseData {
    private Context mContext;
    private String URL = Config.URL + Config.URL_XML + Config.MAIN;
    private String PARAM = "";

    private MainHandler mMainHandler;
    private ArrayList<MainHandler> mData;

    private BaseResultListener mResultListener;

    public MainData(Context context) {
        mContext = context;
    }

    @Override
    public BaseData clear() {
        if (mData != null) {
            mData = null;
        }
        mData = new ArrayList<MainHandler>();
        if (mMainHandler != null) {
            mMainHandler = null;
        }
        mMainHandler = new MainHandler();
        return this;
    }

    @Override
    public BaseData setUrl(String url) {
        if (DsObjectUtils.getInstance(mContext).isEmpty(URL)) {
            URL = url;
        }
        return this;
    }

    @Override
    public BaseData setParam(String param) {
        PARAM = param;
        return this;
    }

    @Override
    public BaseData getView() {
        new DsAsyncTask<String>().setCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {

                String content = new DsHttpClient().HttpGet(URL + PARAM, "euc-kr");
                JSONObject jsonObject = new JSONObject(content);
                JSONObject summeryjsonObject = jsonObject.getJSONObject("summery");
                String result = summeryjsonObject.getString("result");
//                Log.i("TEST", jsonObject + "");
                if (result.matches("success")) {
                    JSONArray jsonArray1 = jsonObject.getJSONArray("list");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        mData.add(new MainHandler());
                        if (mData.size() > 0) {
                            mMainHandler = mData.get(mData.size() - 1);
                            mMainHandler.setMd_image(jsonArray1.getJSONObject(i).getString("md_image"));

                        }
                    }
                }
                return result;
            }
        }).setCallback(new DsAsyncTaskCallback<String>() {
            @Override
            public void onPreExecute() {
            }

            @Override
            public void onPostExecute(String result) {
                if (result.matches("success") && mData.size() > 0) {
                    if (mResultListener != null) {
                        mResultListener.OnComplete(mData);
                    }
                } else {
                    if (mResultListener != null) {
                        mResultListener.OnError("result_error");
                    }
                }
            }

            @Override
            public void onCancelled() {
            }

            @Override
            public void Exception(Exception e) {
                if (mResultListener != null) {
                    mResultListener.OnError(e.getMessage() + "");
                }
            }
        }).execute();
        return this;
    }

    @Override
    public <T> BaseData getViewPost(T post) {
        return this;
    }

    @Override
    public <T> BaseData setCallBack(T resultListener) {
        mResultListener = (BaseResultListener) resultListener;
        return this;
    }
}