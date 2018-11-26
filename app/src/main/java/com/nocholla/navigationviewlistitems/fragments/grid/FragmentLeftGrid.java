package com.nocholla.navigationviewlistitems.fragments.grid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.nocholla.navigationviewlistitems.R;
import com.nocholla.navigationviewlistitems.adapter.grid.GalleryGridAdapter;
import com.nocholla.navigationviewlistitems.helper.GalleryGridSpacingItemDecoration;
import com.nocholla.navigationviewlistitems.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentLeftGrid extends Fragment {
    private ArrayList<Image> images;
    private GalleryGridAdapter mAdapter;
    private RecyclerView recyclerView;

    public FragmentLeftGrid() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_left_grid, container);

        if(retView != null) {
            // Recycler View
            recyclerView = retView.findViewById(R.id.recycler_view_grid);

            images = new ArrayList<>();
            mAdapter = new GalleryGridAdapter(getContext(), images);
            mAdapter.notifyDataSetChanged();

            // Grid Layout
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            // Add spacing between photos in gallery
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_layout_margin);
            recyclerView.addItemDecoration(new GalleryGridSpacingItemDecoration(2, spacingInPixels, true, 0));

            // Get Images
            getImages();
        }

        return retView;
    }

    /**
     * Read JSON from Assets
     */
    public String readJSONFromAsset() {
        String json = null;
        try {
            // Open Assets from Activity
            //InputStream is = getAssets().open("gallery.json");

            // Open Assets from Fragment Activity
            InputStream is = getActivity().getAssets().open("gallery.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Begin Gallery
     */
    public void getImages() {

        images.clear();

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray galleryfeedArray = obj.getJSONArray("galleryfeed");

            for (int i = 0; i < galleryfeedArray.length(); i++) {
                JSONObject galleryObj = galleryfeedArray.getJSONObject(i);
                Log.d("DEBUG GALLERY OBJ", String.valueOf(galleryObj));

                String name = galleryObj.getString("name");
                Log.d("DEBUG GALLERY NAME", String.valueOf(name));

                String url = galleryObj.getString("url");
                Log.d("DEBUG GALLERY URL", String.valueOf(url));

                String timestamp = galleryObj.getString("timestamp");
                Log.d("DEBUG GALLERY TSTAMP", String.valueOf(timestamp));

                Image image = new Image();

                image.setName(galleryObj.getString("name"));
                image.setUrl(galleryObj.getString("url"));
                image.setTimestamp(galleryObj.getString("timestamp"));

                images.add(image);
            }

            /**
             * Very important!! Otherwise, we wont see anything being displayed.
             */

            mAdapter.notifyDataSetChanged();//Important!!

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

