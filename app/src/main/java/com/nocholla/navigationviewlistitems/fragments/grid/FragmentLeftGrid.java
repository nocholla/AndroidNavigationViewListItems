package com.nocholla.navigationviewlistitems.fragments.grid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.nocholla.navigationviewlistitems.R;

public class FragmentLeftGrid extends Fragment {
//    private RecyclerView recyclerView;
//    private ColoursAdapter adapter;
//    private List<Colour> coloursList;

    public FragmentLeftGrid() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_left_grid, container);

        // Get Fragment Belong Activity
        final FragmentActivity fragmentBelongActivity = getActivity();

        if(retView != null) {

            // RecyclerView
//            recyclerView = retView.findViewById(R.id.recycler_view);
//            coloursList = new ArrayList<>();
//            adapter = new ColoursAdapter(getContext(), coloursList);
//            adapter.notifyDataSetChanged();
//
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.setAdapter(adapter);

            // Color Data
            //imageData();
        }

        return retView;
    }

    private void imageData() {
//        Colour colour = new Colour("Black");
//        coloursList.add(colour);
//
//        colour = new Colour("Red");
//        coloursList.add(colour);
//
//        colour = new Colour("Green");
//        coloursList.add(colour);
//
//        colour = new Colour("Blue");
//        coloursList.add(colour);
//
//        colour = new Colour("Gray");
//        coloursList.add(colour);
//
//        adapter.notifyDataSetChanged();
    }

}

