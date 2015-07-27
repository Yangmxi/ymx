
package com.statt.activity.discovery;

import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class BBSActivity extends Activity {

    private ImageButton mDetail;
    private ImageButton mPublishPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_layout);
        initView();
        initActionBar();
        mDetail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BBSActivity.this, BbsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.bbs, View.VISIBLE);
        mPublishPost = au.getCustomButton(this);
        mPublishPost.setVisibility(View.VISIBLE);
        mPublishPost.setBackgroundResource(R.drawable.publish_post_selector);
        mPublishPost.setOnClickListener(publishPostListener);
    }

    private void initView() {
        mDetail = (ImageButton) findViewById(R.id.detail);
    }

    OnClickListener publishPostListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BBSActivity.this, PublishActivity.class);
            startActivity(intent);
        }
    };

}
