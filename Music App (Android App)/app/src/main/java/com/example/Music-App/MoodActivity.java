package com.example.youtbe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MoodActivity extends AppCompatActivity{
    EditText mood;
    Button entry;
    TextView playlist;
    public static final  String EXTRA_MOOD="com.example.lock_unlock.MainActivity2.EXTRA_MOOD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        mood = (EditText) findViewById(R.id.mood);
        entry = (Button) findViewById(R.id.button);
        playlist = (TextView) findViewById(R.id.textView4);
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),song_player.class);
                intent.putExtra(EXTRA_MOOD,mood.getText().toString());
                startActivity(intent);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),choose.class);
                startActivity(intent);
            }
        });

    }


}
