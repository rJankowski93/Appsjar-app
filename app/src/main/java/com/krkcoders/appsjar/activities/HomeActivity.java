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
import com.krkcoders.appsjar.service.AppService;

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
        new AppService().execute("http://192.168.43.156:8080/api/app/all");

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
        App app = new App();
        app.setName("Gra3");
        //app1.setImage(R.drawable.app_image_1);
        app.setId("3");
        app.setAppVersion("app v1");
        app.setYoutubeId("v=92GHdmnDiFE");
        app.setRating(2.0D);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(app);
        realm.commitTransaction();
        final RealmResults<App> games = realm.where(App.class).findAll();
        games.size();
    }



}
