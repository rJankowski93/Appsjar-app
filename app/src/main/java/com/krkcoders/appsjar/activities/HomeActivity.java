package com.krkcoders.appsjar.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.krkcoders.appsjar.R;
import com.krkcoders.appsjar.adapters.HomeViewPagerAdapter;
import com.krkcoders.appsjar.fragments.ChatFragment;
import com.krkcoders.appsjar.fragments.AppListFragment;
import com.krkcoders.appsjar.models.App;

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
        adapter.addFragment(new AppListFragment(), "Games");
        adapter.addFragment(new ChatFragment(), "Chat");
        viewPager.setAdapter(adapter);
    }


    private void setData(){
        App app1 = new App();
        app1.setName("Gra3");
        app1.setImage(R.drawable.app_image_1);
        app1.setId(3);
        app1.setAppVersion("app v1");
        app1.setYoutubeId("v=92GHdmnDiFE");
        app1.setRating(2.0F);


        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(app1);
        realm.commitTransaction();


        final RealmResults<App> games = realm.where(App.class).findAll();
        games.size();
    }



}
