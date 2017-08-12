package kr.com.biligo;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016-05-12.
 */
public class BiligoApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        MultiDex.install(this);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .delayBeforeLoading(100).cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(
                defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }


}
