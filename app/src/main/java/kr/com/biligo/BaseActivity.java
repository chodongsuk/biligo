package kr.com.biligo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016-04-26.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
    }
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        overridePendingTransition(0,0);
    }
}
