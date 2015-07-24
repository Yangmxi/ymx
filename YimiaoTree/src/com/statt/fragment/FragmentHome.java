
package com.statt.fragment;

import com.statt.activity.MainActivity;
import com.statt.widget.Branch;
import com.statt.widget.Floor;
import com.statt.widget.VacTree;
import com.statt.widget.VacTree.OnGrowListener;
import com.statt.yimiaotree.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentHome extends Fragment {

    private VacTree mVacTree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    public void addFloor() {
        mVacTree.addBranch();
    }

    private OnClickListener choosePhotoListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MainActivity ma = (MainActivity) getActivity();
            ma.showChoosePhoto(ma, (ImageView) v);
        }
    };
}
