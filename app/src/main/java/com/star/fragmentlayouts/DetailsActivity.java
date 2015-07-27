package com.star.fragmentlayouts;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {

            finish();
            return;

        }

        if (savedInstanceState == null) {

            DetailsFragment detailsFragment = new DetailsFragment();

            detailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(
                    android.R.id.content, detailsFragment).commit();

        }
    }
}
