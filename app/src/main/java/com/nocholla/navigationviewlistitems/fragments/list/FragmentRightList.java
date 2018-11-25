package com.nocholla.navigationviewlistitems.fragments.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nocholla.navigationviewlistitems.R;

public class FragmentRightList extends Fragment {

    public FragmentRightList() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_right_list, container);

        return retView;
    }

}