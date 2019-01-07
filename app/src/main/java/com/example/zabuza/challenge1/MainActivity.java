package com.example.zabuza.challenge1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public TextView sayacText;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String INT = "sayacInt";

    private String text;
    private int sayacInt;

    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
           // Log.i("[BroadcastReceiver]", "MyReceiver");

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                //Log.i("[BroadcastReceiver]", "Screen ON");
                loadData();
                updateViews();
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //Log.i("[BroadcastReceiver]", "Screen OFF");
                Artir();
                saveData();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayacText = (TextView) findViewById(R.id.sayac);

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        loadData();
        updateViews();

    }

    public void Artir(){
        sayacInt += 1;
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        sayacText.setText(""+  sayacInt);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

       // editor.putString(TEXT, sayacText.getText().toString());
        editor.putInt(INT, sayacInt);
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        //text = sharedPreferences.getString(TEXT, "");
        sayacInt = sharedPreferences.getInt(INT, 0);
    }

    public void updateViews(){
        sayacText.setText("" + sayacInt);
    }

}
