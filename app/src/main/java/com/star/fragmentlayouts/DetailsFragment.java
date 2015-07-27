package com.star.fragmentlayouts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment{

    public static DetailsFragment newInstance(int index) {

        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle args = new Bundle();

        args.putInt("index", index);

        detailsFragment.setArguments(args);

        return detailsFragment;
    }

    public int getShownIndex() {

        return getArguments().getInt("index", 0);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ScrollView scrollView = new ScrollView(getActivity());

        TextView textView = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                getActivity().getResources().getDisplayMetrics());

        textView.setPadding(padding, padding, padding, padding);

        textView.setText(SuperHeroInfo.HISTORY[getShownIndex()]);

        scrollView.addView(textView);

        return scrollView;
    }
}
