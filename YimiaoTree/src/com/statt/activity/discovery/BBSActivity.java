
package com.statt.activity.discovery;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.statt.adapter.PostAdapter;
import com.statt.serializable.Parent;
import com.statt.serializable.Post;
import com.statt.util.ActionBarUtil;
import com.statt.util.DefineUtil;
import com.statt.widget.XListView;
import com.statt.widget.XListView.IXListViewListener;
import com.statt.yimiaotree.R;

public class BBSActivity extends Activity implements IXListViewListener {

    // How many Top post should show to user
    private static final int TOP_POST_NUM = 3;

    // Get POST_NUM_TIME posts from service every time 
    private static final int POST_NUM_TIMES = 5;

    private static final int TOTAL_POSTS = 30;

    private XListView mListViewPost;
    private ImageButton mPublishPost;
    private Handler mHandler;
    private static ArrayList<Post> mListDate;
    private PostAdapter mAdapter;
    private ArrayList<Post> mCurrentDate = new ArrayList<Post>();
    private static String[] place = {
            "南京", "上海", "苏州", "成都", "武汉"
    };

    static Uri[] uri = new Uri[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbs_layout);
        initView();
        initActionBar();
        preparePostListDate();

        mListViewPost.setPullLoadEnable(true);
        mCurrentDate = getUpdate(0, POST_NUM_TIMES);
        mAdapter = new PostAdapter(this, mCurrentDate);
        mListViewPost.setAdapter(mAdapter);

        mListViewPost.setXListViewListener(this);
        mListViewPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(BBSActivity.this, BbsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DefineUtil.KEY_SELECT_POST, mCurrentDate.get(position - 1));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private static void preparePostListDate() {
        boolean[] topArray = new boolean[TOTAL_POSTS];
        String[] titleArray = new String[TOTAL_POSTS];
        String[] contentArray = new String[TOTAL_POSTS];
        String[] DateArray = new String[TOTAL_POSTS];
        int[] clickArray = new int[TOTAL_POSTS];
        int[] replyArray = new int[TOTAL_POSTS];
        Parent[] parentArray = new Parent[TOTAL_POSTS];
        initTop(topArray);
        initTitle(titleArray);
        initContent(contentArray);
        initParent(parentArray);
        initDate(DateArray);
        initClick(clickArray);
        initReply(replyArray);
        Post post = null;
        for (int i = 0; i < TOTAL_POSTS; i++) {
            if (i % 4 == 0) {
                ArrayList<String> list = new ArrayList<String>();
                if (i == 8) {
                    list.add(uri[0].toString());
                    list.add(uri[1].toString());
                } else if (i == 12) {
                    list.add(uri[0].toString());
                    list.add(uri[1].toString());
                    list.add(uri[0].toString());
                } else {
                    list.add(uri[0].toString());
                }
                post = new Post(topArray[i], titleArray[i],
                        contentArray[i], parentArray[i], DateArray[i],
                        clickArray[i], replyArray[i], list);
            } else {
                post = new Post(topArray[i], titleArray[i],
                        contentArray[i], parentArray[i], DateArray[i],
                        clickArray[i], replyArray[i], null);
            }
            mListDate.add(post);
        }

    }

    private static void initTop(boolean[] topArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            if (i < TOP_POST_NUM) {
                topArray[i] = true;
            } else {
                topArray[i] = false;
            }

        }
    }

    private static void initTitle(String[] titleArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            titleArray[i] = "测试论坛帖子標題_" + i;
        }
    }

    private static void initContent(String[] contentArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            contentArray[i] = "测试内容发帖_" + i;
        }
    }

    private static void initParent(Parent[] parentArray) {

        for (int i = 0; i < TOTAL_POSTS; i++) {
            String name = "家长_" + i;
            String phoneNum = "186****" + (10 + (int) Math.random() * 89);
            parentArray[i] = new Parent(name, phoneNum, place[(int) (Math.random() * 4)], uri[i % 2].toString());
        }
    }

    private static void initDate(String[] dateArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            dateArray[i] = "2015.08.04 15 : " + i * 2;
        }
    }

    private static void initClick(int[] clickArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            clickArray[i] = (int) (Math.random() * 10000);
        }
    }

    private static void initReply(int[] replyArray) {
        for (int i = 0; i < TOTAL_POSTS; i++) {
            replyArray[i] = (int) (Math.random() * 100);
        }
    }

    private ArrayList<Post> getUpdate(int index, int length) {
        ArrayList<Post> list = new ArrayList<Post>();
        for (int i = index; i < length; i++) {
            list.add(mListDate.get(i));
        }
        return list;
    }

    private void loadMoreDate(int length) {
        final int currentSize = mCurrentDate.size();
        int size = 0;
        if (currentSize >= mListDate.size()) {
            return;
        }
        if (currentSize + length > mListDate.size()) {
            size = mListDate.size();
        } else {
            size = currentSize + length;
        }
        for (int i = currentSize; i < size; i++) {
            mCurrentDate.add(mListDate.get(i));
        }
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
        mListDate = new ArrayList<Post>();
        mHandler = new Handler();
        uri[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.icon_child_kimi);
        uri[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.icon_parent_avatar);
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
                mCurrentDate = getUpdate(0, 3 + (int) (Math.random() * 6));
                mAdapter = new PostAdapter(BBSActivity.this, mCurrentDate);
                //mAdapter.notifyDataSetChanged();
                //mAdapter = new ArrayAdapter<String>(XListViewActivity.this, R.layout.list_item, items);
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
                loadMoreDate(POST_NUM_TIMES);
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
