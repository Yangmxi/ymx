
package com.statt.activity.discovery;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.statt.adapter.PostDetailAdapter;
import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.serializable.Reply;
import com.statt.util.ActionBarUtil;
import com.statt.util.DefineUtil;
import com.statt.widget.XListView;
import com.statt.widget.XListView.IXListViewListener;
import com.statt.yimiaotree.R;

public class BbsDetailActivity extends Activity implements IXListViewListener {

    private static final String TAG = "BbsDetailActivity";
    private static final int TOTAL_POSTS = 10;
    private XListView mPostDetail;
    private PostDetailAdapter mAdapter;
    private Post mPost;
    private ArrayList<Reply> mListReply;
    private Handler mHandler;
    private static String[] place = {
            "南京", "上海", "苏州", "成都", "武汉"
    };

    static Uri[] uri = new Uri[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentExtras();
        setContentView(R.layout.bbs_detail_layout);
        initViews();
        initActionBar();

        prepareDate();

        mPostDetail.setPullLoadEnable(true);
        mAdapter = new PostDetailAdapter(this, mPost, mListReply);
        mPostDetail.setAdapter(mAdapter);

        mPostDetail.setXListViewListener(this);
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        mPost = (Post) intent.getSerializableExtra(DefineUtil.KEY_SELECT_POST);
        if (mPost == null) {
            Log.e(TAG, "Get Post from intent bundle is null");
        }
    }

    private void prepareDate() {
        Reply reply;
        Parent[] parent = new Parent[TOTAL_POSTS];
        String[] date = new String[TOTAL_POSTS];
        String[] content = new String[TOTAL_POSTS];

        initContent(content);
        initDate(date);
        initParent(parent);

        for (int i = 0; i < TOTAL_POSTS; i++) {
            reply = new Reply(parent[i], date[i], content[i]);
            mListReply.add(reply);
        }
    }

    private static void initContent(String[] contentArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            contentArray[i] = "测试内容发帖_这个帖子我喜欢_" + i;
        }
    }

    private static void initDate(String[] dateArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            dateArray[i] = "2015.08.04 15 : " + (10 + i * 2);
        }
    }

    private static void initParent(Parent[] parentArray) {

        for (int i = 0; i < TOTAL_POSTS; i++) {
            String name = "回帖家长_" + i;
            String phoneNum = "186****" + (10 + (int) Math.random() * 89);
            parentArray[i] = new Parent(name, phoneNum, place[(int) (Math.random() * 4)], uri[i % 2].toString());
        }
    }

    private void initViews() {
        mListReply = new ArrayList<Reply>();
        mHandler = new Handler();
        mPostDetail = (XListView) findViewById(R.id.post_detail);
        uri[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.icon_child_kimi);
        uri[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.icon_parent_avatar);
    }

    private void initActionBar() {
        ActionBarUtil au = new ActionBarUtil();
        au.initActionBar(this, R.string.detail, View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //mCurrentDate = new ArrayList<Post>();
                //mCurrentDate = getUpdate(0, 3 + (int) (Math.random() * 6));
                //mAdapter = new PostAdapter(BBSActivity.this, mCurrentDate);
                //mAdapter.notifyDataSetChanged();
                //mAdapter = new ArrayAdapter<String>(XListViewActivity.this, R.layout.list_item, items);
                //mListViewPost.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        mPostDetail.stopRefresh();
        mPostDetail.stopLoadMore();
        mPostDetail.setRefreshTime("刚刚");
    }
}
