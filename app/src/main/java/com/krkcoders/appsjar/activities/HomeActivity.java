package com.krkcoders.appsjar.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.krkcoders.appsjar.R;
import com.krkcoders.appsjar.adapters.HomeViewPagerAdapter;
import com.krkcoders.appsjar.fragments.ChatFragment;
import com.krkcoders.appsjar.fragments.GameListFragment;
import com.krkcoders.appsjar.models.Game;

import io.realm.Realm;
import io.realm.RealmResults;


public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        //setData();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GameListFragment(), "Games");
        adapter.addFragment(new ChatFragment(), "Chat");
        viewPager.setAdapter(adapter);
    }


    private void setData(){
        Game game1 = new Game();
        game1.setName("Gra29");
        game1.setImage(R.drawable.app2);
        game1.setId(9);
        game1.setAppVersion("app v2");
        game1.setYoutubeId("v=92GHdmnDiFE");
        game1.setRating(4.0F);


        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(game1);
        realm.commitTransaction();


        final RealmResults<Game> games = realm.where(Game.class).findAll();
        games.size();
    }



}
