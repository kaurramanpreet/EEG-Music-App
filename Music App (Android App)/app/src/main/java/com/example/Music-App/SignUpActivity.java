package com.example.youtbe;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class SignUpActivity extends AppCompatActivity {
    Button signUp;
    EditText username;
    EditText password;
    EditText name;
    EditText confirmPassword;
    databasehandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDb = new databasehandler(this);
        signUp = (Button)findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editTextTextPersonName2);
        username = (EditText)findViewById(R.id.editTextTextPersonName);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        confirmPassword = (EditText)findViewById(R.id.editTextTextPassword2);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    Toast.makeText(SignUpActivity.this,"Password didn't match",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isInserted = myDb.insertuser(username.getText().toString(),name.getText().toString(),password.getText().toString());
                if(isInserted == true)
                {

                    Toast.makeText(SignUpActivity.this,"SignUp Successful",Toast.LENGTH_SHORT).show();
                    System.out.println("done");
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Username already exists",Toast.LENGTH_SHORT).show();
                    System.out.println( "local db issue." );
                }
            }
        });
    }
}