package kr.com.biligo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import kr.ds.fragment.EventFragment;
import kr.ds.fragment.GoodFragment;
import kr.ds.fragment.Menu1Fragment;
import kr.ds.fragment.Menu2Fragment;
import kr.ds.fragment.Menu3Fragment;
import kr.ds.fragment.Menu4Fragment;
import kr.ds.fragment.ShopFragment;
import kr.ds.utils.DsObjectUtils;

/**
 * Created by Administrator on 2016-04-26.
 */
public class SubActivity extends AppCompatActivity {
    private int mType = Menu1Fragment.TypeGood;
    private Toolbar mToolbar;
    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private Fragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getIntent().getIntExtra("type", 0);

        setContentView(R.layout.activity_sub);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if(mType == Menu1Fragment.TypeGood) {
                mToolbar.setTitle("체육용품 렌탈");
            }else if(mType == Menu1Fragment.TypeShop) {
                mToolbar.setTitle("모바일 쇼핑몰");
            }else if(mType == Menu1Fragment.TypeEvent) {
                mToolbar.setTitle("기타 렌탈");
            }
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        getFragment(mType);

    }
    public void getFragment(int type){
        switch (type){
            case Menu1Fragment.TypeGood:
                mFragment = GoodFragment.newInstance();
                break;
            case Menu1Fragment.TypeShop:
                mFragment = ShopFragment.newInstance();
                break;
            case Menu1Fragment.TypeEvent:
                mFragment = EventFragment.newInstance();
                break;
        }


        mFm = getSupportFragmentManager();
        mFt = mFm.beginTransaction();
        mFt.replace(R.id.content_frame, mFragment);
        mFt.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}