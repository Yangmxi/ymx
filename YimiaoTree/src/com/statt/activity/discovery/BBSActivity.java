
package com.statt.activity.discovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.statt.adapter.PostAdapter;
import com.statt.json.RequestTask;
import com.statt.json.model.GeneralPost;
import com.statt.json.model.TopPost;
import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.util.ActionBarUtil;
import com.statt.util.Address;
import com.statt.util.DefineUtil;
import com.statt.widget.XListView;
import com.statt.widget.XListView.IXListViewListener;
import com.statt.yimiaotree.R;

public class BBSActivity extends Activity implements IXListViewListener {

    private static final String TAG = "BBSActivity";

    private static final String PAGE_INDEX = "pageIndex";
    private static final String TOP_POST = "topicVo";
    private static final String GENERAL_POST = "generalPost";
    private XListView mListViewPost;
    private ImageButton mPublishPost;
    private Handler mHandler;
    private PostAdapter mAdapter;
    private ArrayList<Post> mCurrentDate = new ArrayList<Post>();

    private int mPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_layout);
        initView();
        initActionBar();
        //preparePostListDate();

        getTopPostDate(mCurrentDate);
        getGeneralPostDate(mCurrentDate, mPageIndex);
        mListViewPost.setPullLoadEnable(true);
        //mCurrentDate = getUpdate(0, POST_NUM_TIMES);
        mAdapter = new PostAdapter(this, mCurrentDate);
        mListViewPost.setAdapter(mAdapter);

        mListViewPost.setXListViewListener(this);
        mListViewPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(BBSActivity.this, BbsDetailActivity.class);
                intent.putExtra(DefineUtil.KEY_SELECT_POST, mCurrentDate.get(position - 1).getId());
                /* This is put post object to bundle and send to BBS detail Activity
                Bundle bundle = new Bundle();
                bundle.putSerializable(DefineUtil.KEY_SELECT_POST, mCurrentDate.get(position - 1));
                intent.putExtras(bundle);*/
                startActivity(intent);
            }
        });

    }

    private void getGeneralPostDate(ArrayList<Post> currentDate, int pageIndex) {
        JSONObject param = new JSONObject();
        Parent parent;
        ArrayList<String> imagePath = new ArrayList<String>();
        try {
            param.put(PAGE_INDEX, String.valueOf(pageIndex));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestTask rt = new RequestTask(param, Address.GET_ONE_PAGE_POST);

        Map<String, Object> temp = null;
        try {
            temp = rt.execute().get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> object = JSON.parseObject(temp.get("obj").toString(), Map.class);
        List<GeneralPost> generalPost = JSON.parseArray(object.get(GENERAL_POST).toString(), GeneralPost.class);
        for (int i = 0; i < generalPost.size(); i++) {
            GeneralPost gen = generalPost.get(i);
            String id = gen.getId();
            String title = gen.getTitle();
            String publishDate = gen.getPublishDate();
            String replyCount = gen.getReplyCount();
            String content = gen.getContent();
            String viewCount = gen.getViewCount();
            String userName = gen.getUserName();
            String city = gen.getCity();
            String userUrl = gen.getUserUrl();
            imagePath.clear();
            for (int count = 0; count < gen.getImageVos().size(); count++) {
                imagePath.add(gen.getImageVos().get(count).getUrl());
            }
            parent = new Parent(userName, "", city, userUrl);

            Post post = new Post(false, title, content, parent, publishDate,
                    viewCount, replyCount, imagePath, id);
            currentDate.add(post);
            Log.i(TAG, "date size = " + mCurrentDate.size());
        }

    }

    private void getTopPostDate(ArrayList<Post> currentDate) {
        RequestTask rt = new RequestTask(null, Address.GET_TOP_POST);

        Map<String, Object> temp = null;
        try {
            temp = rt.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map<String, Object> object = JSON.parseObject(temp.get("obj").toString(), Map.class);
        List<TopPost> topPost = JSON.parseArray(object.get(TOP_POST).toString(), TopPost.class);
        for (int i = 0; i < topPost.size(); i++) {
            String id = topPost.get(i).getId();
            String title = topPost.get(i).getTitle();
            Log.i(TAG, "title = " + title);
            Post post = new Post(true, title, id);
            currentDate.add(post);
            Log.i(TAG, "date size = " + mCurrentDate.size());
        }
    }

    private ArrayList<Post> getUpdate() {
        ArrayList<Post> list = new ArrayList<Post>();
        mPageIndex = 1;
        getTopPostDate(list);
        getGeneralPostDate(list, mPageIndex);
        return list;
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
        mListViewPost = (XListView) findViewById(R.id.post_list);
        mHandler = new Handler();
    }

    OnClickListener publishPostListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BBSActivity.this, PublishActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurrentDate = new ArrayList<Post>();
                mCurrentDate = getUpdate();
                mAdapter = new PostAdapter(BBSActivity.this, mCurrentDate);
                mListViewPost.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPageIndex++;
                getGeneralPostDate(mCurrentDate, mPageIndex);
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        mListViewPost.stopRefresh();
        mListViewPost.stopLoadMore();
        mListViewPost.setRefreshTime("刚刚");
    }
}
