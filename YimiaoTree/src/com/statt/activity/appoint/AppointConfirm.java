
package com.statt.activity.appoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

public class AppointConfirm extends Activity implements OnClickListener {

    private Button mCancle, mOk;
    private View mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appoint_confirm_layout);
        initActionBar();
        initView();
        mCancle.setOnClickListener(this);
        mOk.setOnClickListener(this);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.appoint_info_title, View.VISIBLE);
    }

    private void initView() {
        mCancle = (Button) findViewById(R.id.cancle);
        mOk = (Button) findViewById(R.id.ok);
        mContent = findViewById(R.id.confirm_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                mContent.setBackgroundResource(R.drawable.appoint_completed);
                finish();
                break;
            case R.id.cancle:
                finish();

        }
    }
}
