
package com.statt.activity.discovery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

public class PublishActivity extends Activity {

    private Button mPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_layout);
        initView();
        initActionBar();
        mPublish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mPublish = (Button) findViewById(R.id.post_publish);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.publish_post, View.VISIBLE);
    }
}
