
package com.statt.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.statt.activity.AbstractActivity;
import com.statt.activity.MainActivity;
import com.statt.util.ActionBarUtil;
import com.statt.yimiaotree.R;

public class ScanGalleryActivity extends AbstractActivity implements OnClickListener {

    private ImageView mAddPhoto, mNextAddPhoto;
    private Button mUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_gallery_layout);
        initActionBar();
        initView();
        setListener();
    }

    private void setListener() {
        mAddPhoto.setOnClickListener(this);
        mNextAddPhoto.setOnClickListener(this);
        mUpload.setOnClickListener(this);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.scan_gallery, View.VISIBLE);
    }

    private void initView() {
        mAddPhoto = (ImageView) findViewById(R.id.add_photo);
        mNextAddPhoto = (ImageView) findViewById(R.id.next_add_photo);
        mUpload = (Button) findViewById(R.id.upload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mNextAddPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_photo:
                showChoosePhoto(ScanGalleryActivity.this, (ImageView) v);
                break;
            case R.id.next_add_photo:

                break;
            case R.id.upload:
                finish();
                break;
            default:
                break;
        }
    }
}
