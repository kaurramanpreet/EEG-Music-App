package com.example.youtbe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class choose extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3;
    private Button submit;
    public static final  String EXTRA_AGE="com.example.lock_unlock.MainActivity2.EXTRA_AGE";
    public static final  String EXTRA_LANG="com.example.lock_unlock.MainActivity2.EXTRA_LANG";
    public static final  String EXTRA_GENRE="com.example.lock_unlock.MainActivity2.EXTRA_GENRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = String.valueOf(spinner1.getSelectedItem());
                String lang = String.valueOf(spinner2.getSelectedItem());
                String genre = String.valueOf(spinner3.getSelectedItem());
                Toast.makeText(getApplicationContext(),
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),list_of_songs.class);
                intent.putExtra(EXTRA_AGE,age.toLowerCase());
                intent.putExtra(EXTRA_LANG,lang.toLowerCase());
                intent.putExtra(EXTRA_GENRE,genre.toLowerCase());
                startActivity(intent);
            }
        });
    }

}