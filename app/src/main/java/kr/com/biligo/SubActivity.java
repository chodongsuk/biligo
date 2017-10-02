package kr.com.biligo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kr.ds.fragment.BounceAreaFragment;
import kr.ds.fragment.Event2Fragment;
import kr.ds.fragment.Event3Fragment;
import kr.ds.fragment.EventFragment;
import kr.ds.fragment.GoodFragment;
import kr.ds.fragment.Menu1Fragment;
import kr.ds.fragment.ShopFragment;
import kr.ds.fragment.AreaFragment;

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
                mToolbar.setTitle("주간 렌탈");
            }else if(mType == Menu1Fragment.TypeShop) {
                mToolbar.setTitle("모바일 쇼핑몰");
            }else if(mType == Menu1Fragment.TypeEvent) {
                mToolbar.setTitle("기타 렌탈");
            }else if(mType == Menu1Fragment.TypeEvent2) {
                mToolbar.setTitle("이벤트 용품");
            }else if(mType == Menu1Fragment.TypeTeacher) {
                mToolbar.setTitle("강사 빌리고");
            }else if(mType == Menu1Fragment.TypeBounce) {
                mToolbar.setTitle("바운스 빌리고");
            }else if(mType == Menu1Fragment.TypeBrandZone) {
                mToolbar.setTitle("월간 렌탈");
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
            case Menu1Fragment.TypeEvent2:
                mFragment = Event2Fragment.newInstance();
                break;
            case Menu1Fragment.TypeTeacher:
                mFragment = AreaFragment.newInstance();
                break;
            case Menu1Fragment.TypeBounce:
                mFragment = BounceAreaFragment.newInstance();
                break;
            case Menu1Fragment.TypeBrandZone:
                mFragment = Event3Fragment.newInstance();
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