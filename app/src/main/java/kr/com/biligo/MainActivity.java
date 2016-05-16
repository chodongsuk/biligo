package kr.com.biligo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
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

import java.util.HashMap;

import kr.ds.fragment.BaseFragment;
import kr.ds.fragment.Menu1Fragment;
import kr.ds.fragment.Menu2Fragment;
import kr.ds.fragment.Menu3Fragment;
import kr.ds.fragment.Menu4Fragment;
import kr.ds.permission.Permission;
import kr.ds.utils.DsObjectUtils;

/**
 * Created by Administrator on 2016-04-26.
 */
public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;

    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private Fragment mFragment = null;

    private Permission cPermission;
    private boolean isPermission = false;

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

            cPermission = new Permission(MainActivity.this);
            cPermission.setCallback(new Permission.PermissionListener() {
                @Override
                public void onSuccess() {
                    SetTell("011-1111-1111");
                }

                @Override
                public void onCancle() {

                }



                @Override
                public void requestPermissions(String[] type) {
                    ActivityCompat.requestPermissions(MainActivity.this, type, 0);
                }
            }).isCall();



        }else if(position == 2){
            mFragment = Menu3Fragment.newInstance();
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

    private void SetTell(final String tell){
        if (!DsObjectUtils.getInstance(getApplicationContext()).isEmpty(tell)) {
            AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
            alt_bld.setMessage("전화 연결 하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("전화걸기",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try{
                                        Intent NextIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tell));
                                        NextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(NextIntent);
                                    }catch (Exception e) {
                                        // TODO: handle exception
                                        Toast.makeText(getApplicationContext(), "계속 문제가 발생시 관리자에게 문의해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                    .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'NO' Button
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = alt_bld.create();
            alert.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        isPermission = true;
        if(cPermission != null) {
            cPermission.setRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(isPermission && cPermission != null){
            cPermission.onRequestPermissionsResult(cPermission.getRequestCode(), cPermission.getPermissions(), cPermission.getGrantResults());
            isPermission = false;
        }
    }
}