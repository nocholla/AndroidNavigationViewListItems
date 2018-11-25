package com.nocholla.navigationviewlistitems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {

    /**
     * @method onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_fragment_list);

        // Back Home Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Back Home
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
