package com.krkcoders.appsjar.service;

import android.os.AsyncTask;

import com.krkcoders.appsjar.models.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.realm.Realm;


public class AppService extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            copyServerApps(builder.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    private void copyServerApps(String listApps) throws JSONException {
        JSONArray apps = new JSONArray(listApps);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 0; i < apps.length(); i++) {
            JSONObject jsonObject = apps.getJSONObject(i);
            App app = new App();
            app.setName(jsonObject.getString("name"));
            app.setId(jsonObject.getString("id"));
            app.setAppVersion(jsonObject.getString("version"));
            app.setYoutubeId(jsonObject.getString("youTubeId"));
            app.setRating(jsonObject.getDouble("rating"));
            app.setImg(jsonObject.getString("img"));
            realm.copyToRealmOrUpdate(app);
        }
        realm.commitTransaction();
    }

    @Override
    protected void onPostExecute(String temp) {
    }
}