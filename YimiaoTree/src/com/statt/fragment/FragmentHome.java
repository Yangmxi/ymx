
package com.statt.fragment;

import com.statt.widget.VacTree;
import com.statt.yimiaotree.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    }

    public void addFloor() {
        mVacTree.addBranch();
    }
}

