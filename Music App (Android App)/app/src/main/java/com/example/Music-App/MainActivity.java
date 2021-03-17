package com.example.youtbe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    public static final  String EXTRA_STRING="com.example.youtbe.MainActivity.EXTRA_STRING";
    Button signIn;
    EditText username;
    EditText password;
    private static int count=0;
    databasehandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.editTextTextPersonName);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        signIn = (Button)findViewById(R.id.button);
        myDb = new databasehandler(this);

        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean recordExists = myDb.logIn(username.getText().toString(),password.getText().toString());
                if(recordExists)
                {
                    Intent intent = new Intent(getApplicationContext(), MoodActivity.class);
                    intent.putExtra(EXTRA_STRING,username.getText().toString());
                    startActivity(intent);

                    Toast.makeText(MainActivity.this,"Log In Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"You don't have an account! SignUp",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}