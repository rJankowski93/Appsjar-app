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
import com.krkcoders.appsjar.models.Game;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class GameListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView gameRecycler = (RecyclerView)inflater.inflate(R.layout.game_list_fragment,
                container, false);

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Game> games = realm.where(Game.class).findAll();


        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(games);
        gameRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        gameRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), AppDetails.class);
                intent.putExtra("gameId",games.get(position).getId());
                getActivity().startActivity(intent);
            }
        });
        return gameRecycler;
    }
}
