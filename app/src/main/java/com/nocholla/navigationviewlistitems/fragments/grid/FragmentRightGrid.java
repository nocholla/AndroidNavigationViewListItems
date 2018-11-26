package com.nocholla.navigationviewlistitems.fragments.grid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nocholla.navigationviewlistitems.R;


public class FragmentRightGrid extends Fragment {

    public FragmentRightGrid() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_right_grid, container);

        return retView;
    }

}


