
package com.statt.activity.appoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

public class AppointInfo extends Activity implements OnClickListener {

    private Button mAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appoint_info_layout);
        initActionBar();
        mAppoint = (Button) findViewById(R.id.btn_appoint);
        mAppoint.setOnClickListener(this);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.appoint_info_title, View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_appoint:
                Intent intent = new Intent();
                intent.setClass(AppointInfo.this, AppointConfirm.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
