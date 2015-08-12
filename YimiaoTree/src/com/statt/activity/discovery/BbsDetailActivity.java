
package com.statt.activity.discovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.statt.adapter.PostAdapter;
import com.statt.adapter.PostDetailAdapter;
import com.statt.json.RequestTask;
import com.statt.json.model.PostDetail;
import com.statt.json.model.ReplyPost;
import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.serializable.Reply;
import com.statt.util.ActionBarUtil;
import com.statt.util.Address;
import com.statt.util.DefineUtil;
import com.statt.util.LogUtil;
import com.statt.widget.XListView;
import com.statt.widget.XListView.IXListViewListener;
import com.statt.yimiaotree.R;

public class BbsDetailActivity extends Activity implements IXListViewListener {

    private static final String TAG = "BbsDetailActivity";
    private static final String TOPIC_ID = "topicId";
    private static final String LOGIN_ID = "getFromSharePreference";
    private XListView mPostDetail;
    private PostDetailAdapter mAdapter;
    private Post mPost;
    private ArrayList<Reply> mListReply;
    private Handler mHandler;
    private String mId;

    static Uri[] uri = new Uri[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentExtras();
        setContentView(R.layout.bbs_detail_layout);
        initViews();
        initActionBar();

        getPostDetail();

        mPostDetail.setPullLoadEnable(true);
        mAdapter = new PostDetailAdapter(this, mPost, mListReply, LOGIN_ID);
        mPostDetail.setAdapter(mAdapter);

        mPostDetail.setXListViewListener(this);
    }

    private void getPostDetail() {
        JSONObject param = new JSONObject();
        Parent parent;
        boolean isTop;
        Reply reply;
        ArrayList<String> imagePath = new ArrayList<String>();
        try {
            param.put(TOPIC_ID, String.valueOf(mId));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestTask rt = new RequestTask(param, Address.GET_POST_DETAIL);

        Map<String, Object> temp = null;
        try {
            temp = rt.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        PostDetail object = JSON.parseObject(temp.get("obj").toString(), PostDetail.class);
        String id = object.getId();
        String title = object.getTitle();
        String publishDate = object.getPublishDate();
        String replyCount = object.getReplyCount();
        String content = object.getContent();
        String viewCount = object.getViewCount();
        String userName = object.getUserName();
        String city = object.getCity();
        String userUrl = object.getUserUrl();
        String state = object.getState();
        if (state.equals("1")) {
            isTop = true;
        } else {
            isTop = false;
        }
        for (int count = 0; count < object.getImageVos().size(); count++) {
            imagePath.add(object.getImageVos().get(count).getUrl());
        }
        parent = new Parent(userName, "", city, userUrl);
        mPost = new Post(isTop, title, content, parent, publishDate, viewCount, replyCount, imagePath, id);
        List<ReplyPost> list = object.getReplyVos();
        for (int i = 0; i < list.size(); i++) {
            ReplyPost replyPost = list.get(i);
            parent = new Parent(replyPost.getUserName(), replyPost.getLoc(), replyPost.getUrl());
            reply = new Reply(parent, replyPost.getReDate(), replyPost.getContent());
            mListReply.add(reply);
        }
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(DefineUtil.KEY_SELECT_POST);
        if (mId == null) {
            LogUtil.Log(TAG, "Get Post id from intent bundle is null");
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
                mListReply = new ArrayList<Reply>();
                getPostDetail();
                mAdapter = new PostDetailAdapter(BbsDetailActivity.this, mPost, mListReply, LOGIN_ID);
                mPostDetail.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO get more detail post and update list
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
