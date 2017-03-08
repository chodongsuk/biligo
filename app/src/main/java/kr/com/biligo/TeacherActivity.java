package kr.com.biligo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kr.ds.fragment.GoodFragment;
import kr.ds.fragment.TeacherFragment;
import kr.ds.handler.AreaHandler;
import kr.ds.handler.GoodHandler;

/**
 * Created by Administrator on 2017-03-08.
 */
public class TeacherActivity extends BaseActivity{

    private Toolbar mToolbar;
    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private Fragment mFragment = null;
    private AreaHandler mSavedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedata = (AreaHandler) getIntent().getParcelableExtra("data");

        setContentView(R.layout.activity_teacher);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("강사 빌리고");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragment = TeacherFragment.newInstance(mSavedata);
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
