
package com.statt.fragment;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.statt.activity.MainActivity;
import com.statt.activity.home.ScanGalleryActivity;
import com.statt.adapter.DialogListViewAdapter;
import com.statt.widget.Branch;
import com.statt.widget.Floor;
import com.statt.widget.RoundImageView;
import com.statt.widget.VacTree;
import com.statt.widget.VacTree.OnGrowListener;
import com.statt.yimiaotree.R;

public class FragmentHome extends Fragment {

    private static final String TAG = "FragmentHome";
    private VacTree mVacTree;
    private RoundImageView mBabyAvatar;
    private Button mVacBook, mBabyManager;
    private Drawable mBabyAvatarDrawable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActionBar();
        initView();
        setListener();
        initVacTree();
    }

    private void initVacTree() {
        mVacTree = (VacTree) getView().findViewById(R.id.layout_tree_trunk);
        mVacTree.setOnGrowListener(new OnGrowListener() {

            @Override
            public void onGrowTree() {

            }

            @Override
            public void onAddBranch(Floor lastFloor) {
                Branch left = lastFloor.getBranchLeft();
                Branch right = lastFloor.getBranchRight();
                left.getTreeGallery().getCoverPic().setOnClickListener(choosePhotoListener);
                right.getTreeGallery().getCoverPic().setOnClickListener(choosePhotoListener);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initActionBar();
        if (mBabyAvatarDrawable != null) {
            mBabyAvatar.setImageDrawable(mBabyAvatarDrawable);
        }

        initView();
        setListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBabyAvatar == null) {
            mBabyAvatarDrawable = mBabyAvatar.getDrawable();
        }
    }

    private void setListener() {
        mBabyAvatar.setOnClickListener(actionBarListener);
        mVacBook.setOnClickListener(actionBarListener);
        mBabyManager.setOnClickListener(actionBarListener);
    }

    private void initView() {
        View v = getActivity().getActionBar().getCustomView();
        mVacBook = (Button) v.findViewById(R.id.vac_book);
        mBabyAvatar = (RoundImageView) v.findViewById(R.id.baby_avatar);
        mBabyManager = (Button) v.findViewById(R.id.baby_manager);
    }

    public void addFloor() {
        mVacTree.addBranch();
    }

    private void initActionBar() {
        ActionBar ab = getActivity().getActionBar();
        ab.setCustomView(R.layout.custom_action_bar_home);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    private OnClickListener choosePhotoListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ScanGalleryActivity.class);
            startActivity(intent);
        }
    };

    private void showBabyManagerDialog(Context c, String[] array, String currentName) {

        View content = LayoutInflater.from(c).inflate(R.layout.dialog_layout, null);
        ListView list = (ListView) content.findViewById(R.id.dialog_list);
        Button addBaby = (Button) content.findViewById(R.id.dialog_btn);
        addBaby.setVisibility(View.VISIBLE);
        final DialogListViewAdapter adapter = new DialogListViewAdapter(c, array, currentName);
        list.setAdapter(adapter);

        Dialog alertDialog = new AlertDialog.Builder(c)
                .setView(content)
                .create();
        alertDialog.show();

    }

    private void showVacBookInfo() {
        // TODO intent to another activity

    }

    private OnClickListener actionBarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.baby_avatar:
                    MainActivity ma = (MainActivity) getActivity();
                    ma.showChoosePhoto(ma, mBabyAvatar);
                    break;
                case R.id.baby_manager:
                    final String[] baby = getResources().getStringArray(R.array.children);
                    showBabyManagerDialog(getActivity(), baby, mBabyManager.getText().toString());
                    break;
                case R.id.vac_book:
                    showVacBookInfo();
                    break;
                default:
                    Log.w(TAG, "this is unidentifiable ID!");
                    break;
            }
        }
    };

}
