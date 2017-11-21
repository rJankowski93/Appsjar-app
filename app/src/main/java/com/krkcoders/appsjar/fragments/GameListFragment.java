package com.krkcoders.appsjar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krkcoders.appsjar.R;
import com.krkcoders.appsjar.activities.AppDetails;
import com.krkcoders.appsjar.adapters.CaptionedImagesAdapter;

public class GameListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView gameRecycler = (RecyclerView)inflater.inflate(R.layout.game_list_fragment,
                container, false);

        String[] gameNames = new String[5];
        for (int i = 0; i < gameNames.length; i++) {
            gameNames[i] = "test";
        }

        int[] gameImages = new int[5];
        for (int i = 0; i < gameImages.length; i++) {
            gameImages[i] = R.drawable.app_logo;
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(gameNames, gameImages);
        gameRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        gameRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), AppDetails.class);
//                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, position);
                getActivity().startActivity(intent);
            }
        });
        return gameRecycler;
    }
}
