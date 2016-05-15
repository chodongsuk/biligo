package kr.com.biligo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import kr.ds.fragment.BaseFragment;
import kr.ds.fragment.Menu1Fragment;
import kr.ds.fragment.Menu2Fragment;
import kr.ds.fragment.Menu3Fragment;
import kr.ds.fragment.Menu4Fragment;

/**
 * Created by Administrator on 2016-04-26.
 */
public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;

    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private Fragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.useFixedMode();//정렬
        mBottomBar.setActiveTabColor("#3F51B5");

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.menu1){
                    selectItem(0);
                }else if(menuItemId == R.id.menu2){
                    selectItem(1);
                }else if(menuItemId == R.id.menu3){
                    selectItem(2);
                }else if(menuItemId == R.id.menu4){
                    selectItem(3);
                }
                //Toast.makeText(getApplicationContext(), getMessage(menuItemId, true), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

                if(menuItemId == R.id.menu1){
                    selectItem(0);
                }else if(menuItemId == R.id.menu2){
                    selectItem(1);
                }else if(menuItemId == R.id.menu3){
                    selectItem(2);
                }else if(menuItemId == R.id.menu4){
                    selectItem(3);
                }
                //Toast.makeText(getApplicationContext(), getMessage(menuItemId, true), Toast.LENGTH_SHORT).show();
            }
        });
        //selectItem(0);
        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.

    }

    private void selectItem(int position){
        if(position == 0){
            mFragment = Menu1Fragment.newInstance();
        }else if(position == 1){
            mFragment = Menu2Fragment.newInstance();
        }else if(position == 2){
            mFragment = Menu3Fragment.newInstance();
        }else if(position == 3){
            mFragment = Menu4Fragment.newInstance();
        }
        setFragment(mFragment);


    }

    private void setFragment(Fragment fragment){
        mFm = getSupportFragmentManager();
        mFt = mFm.beginTransaction();
        mFt.replace(R.id.content_frame, fragment);
        mFt.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }
}