package com.example.noblegeorge.whatsuppclone.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noblegeorge.whatsuppclone.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userNameEditText,passwordEditText,emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        userNameEditText =(EditText)findViewById(R.id.usenamEditText1);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText1);
        emailEditText =(EditText)findViewById(R.id.emailEditText);
        Button signupButton =(Button)findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.signupButton)
        {
            signUp();
        }

    }

    public void signUp()
    {

        String username =userNameEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String email =emailEditText.getText().toString();

        //Log.i("pass",username+" "+password);
        ParseUser parseUser =new ParseUser();
        parseUser.setUsername(username);
        parseUser.setPassword(password);
        parseUser.setEmail(email);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                {
                    Toast.makeText(SignupActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    String error_message=e.getMessage();
                    if (error_message.toLowerCase().matches("java"))
                    {
                        error_message =error_message.substring(error_message.indexOf(" "));
                    }

                    Toast.makeText(SignupActivity.this,error_message,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
