package com.nocholla.navigationviewlistitems.fragments.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.nocholla.navigationviewlistitems.R;
import com.nocholla.navigationviewlistitems.adapter.grid.GalleryGridAdapter;
import com.nocholla.navigationviewlistitems.adapter.list.GalleryListAdapter;
import com.nocholla.navigationviewlistitems.model.Image;


public class FragmentLeftList extends Fragment {
    private ArrayList<Image> images;
    private GalleryListAdapter mAdapter;
    private RecyclerView recyclerView;

    public FragmentLeftList() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_left_list, container);

        if(retView != null) {
            // Recycler View
            recyclerView = retView.findViewById(R.id.recycler_view_list);

            images = new ArrayList<>();
            mAdapter = new GalleryListAdapter(getContext(), images);
            mAdapter.notifyDataSetChanged();

            // Linear Layout
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            recyclerView.addOnItemTouchListener(new GalleryListAdapter.RecyclerTouchListener(getContext(), recyclerView, new GalleryListAdapter.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("images", images);
                    bundle.putInt("position", position);

                    //Toast.makeText(getContext(), "Image Clicked", Toast.LENGTH_SHORT).show();

                    FragmentRightList fragmentRightList = new FragmentRightList();
                    fragmentRightList.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    FragmentManager fragmentManager = activity.getSupportFragmentManager();

                    // Get right Fragment Object
                    Fragment rightFragment = fragmentManager.findFragmentById(R.id.fragment_right_list);

                    // Get the View Object in right Fragment
                    final View rightFragmentFullImage = rightFragment.getView().findViewById(R.id.img_fullscreen_list);

                    final Image image = images.get(position);

                    Glide.with(getActivity()).load(image.getUrl())
                            .thumbnail(0.5f)
                            .into((ImageView) rightFragmentFullImage);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

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

