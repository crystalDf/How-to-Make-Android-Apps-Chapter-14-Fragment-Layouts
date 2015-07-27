package com.star.fragmentlayouts;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {

    boolean mDuelPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, SuperHeroInfo.NAMES);

        setListAdapter(arrayAdapter);

        View detailsFrame = getActivity().findViewById(R.id.details);

        mDuelPane = ((detailsFrame != null) && (detailsFrame.getVisibility() == View.VISIBLE));

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDuelPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    private void showDetails(int index) {

        mCurCheckPosition = index;

        if (mDuelPane) {
            getListView().setItemChecked(mCurCheckPosition, true);

            DetailsFragment detailsFragment = (DetailsFragment) getActivity().
                    getSupportFragmentManager().findFragmentById(R.id.details);

            if (detailsFragment == null || detailsFragment.getShownIndex() != mCurCheckPosition) {
                detailsFragment = DetailsFragment.newInstance(mCurCheckPosition);

                FragmentTransaction fragmentTransaction = getActivity().
                        getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.details, detailsFragment);

                fragmentTransaction.setCustomAnimations(
                        android.R.anim.fade_in, android.R.anim.fade_out);

//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();
            }
        } else {

            Intent intent = new Intent(getActivity(), DetailsActivity.class);

            intent.putExtra("index", mCurCheckPosition);

            startActivity(intent);
        }
    }
}
