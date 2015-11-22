package com.example.franciscogutierrez.hackathon;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlashActivity extends ActionBarActivity {

    private static final String TAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        Intent intent = getIntent();
        String deckName = intent.getStringExtra("deckName");

        //http://54.183.149.62/test3.php?d=SimplifiedActivities

        List<FlashCard> arrayList = new ArrayList<FlashCard>();

        try {
            String s = "http://54.183.149.62/test3.php?d=" + deckName;
            String[] url = new String[]{s};

            s = new GetData().execute(url).get();

            JSONObject jObject = new JSONObject(s);
            JSONArray jArray = jObject.getJSONArray("deckContent");

            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    String oneObjectsItem = oneObject.getString("e");
                    String twoObjectsItem = oneObject.getString("c");
                    arrayList.add(new FlashCard(oneObjectsItem, twoObjectsItem));
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }

            TextView tv = (TextView) findViewById(R.id.textView4);
            tv.setText(s);

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }


    }

}