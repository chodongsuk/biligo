package kr.ds.data;

import android.content.Context;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.Callable;

import kr.ds.asynctask.DsAsyncTask;
import kr.ds.asynctask.DsAsyncTaskCallback;
import kr.ds.config.Config;
import kr.ds.httpclient.DsHttpClient;
import kr.ds.utils.DsDebugUtils;
import kr.ds.utils.DsObjectUtils;


public class Event3SaveData extends BaseData {
	private DsDebugUtils mDsDebugUtils;
	private Context mContext;
	private BaseResultListener mResultListener;
	private String URL = Config.URL + Config.URL_XML + Config.EVENTSAVE3;
	private String PARAM = "";
	private HashMap<String, String> mDataPost;
	private String mMsg = "";
	public Event3SaveData(Context context){
		mContext = context;
	}
	@Override
	public BaseData clear() {
		return this;
	}

	@Override
	public BaseData setUrl(String url) {
		// TODO Auto-generated method stub
		if(DsObjectUtils.getInstance(mContext).isEmpty(URL)){
			URL = url;
		}
		return this;
	}

	@Override
	public BaseData setParam(String param) {
		// TODO Auto-generated method stub
		PARAM = param;
		return this;
	}

	@Override
	public BaseData getView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public <T> BaseData setCallBack(T resultListener) {
		// TODO Auto-generated method stub
		mResultListener = (BaseResultListener) resultListener;
		return this;
	}

	@Override
	public <T> BaseData getViewPost(final T post) {
		// TODO Auto-generated method stub
		new DsAsyncTask<String>().setCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
            	String content = new DsHttpClient().HttpPost((HashMap<String, String>) post, URL + PARAM,"utf-8");
            	JSONObject jsonObject = new JSONObject(content);
            	JSONObject summeryjsonObject = jsonObject.getJSONObject("summery");
            	String result = summeryjsonObject.getString("result");
            	mMsg = summeryjsonObject.getString("msg");
				return result;
            }
        }).setCallback(new DsAsyncTaskCallback<String>() {
            @Override
            public void onPreExecute() {
            }

            @Override
            public void onPostExecute(String result) {

				if (result.matches("success")) {
					if (mResultListener != null) {
						mResultListener.OnComplete(result);
						mResultListener.OnError(mMsg);

					}
				}else if (result.matches("error") ) {
					if (mResultListener != null) {
						mResultListener.OnError(mMsg);
					}
				} 
            }

            @Override
            public void onCancelled() {
            }

            @Override
            public void Exception(Exception e) {
            	if (mResultListener != null) {
					mResultListener.OnError(e.getMessage()+"");
                }
            }
        }).execute();
		return this;
	}



}
