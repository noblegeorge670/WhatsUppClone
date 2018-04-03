package com.example.noblegeorge.whatsuppclone.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noblegeorge.whatsuppclone.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    EditText userNameEditText;
    EditText passwordEditText;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton =(Button)findViewById(R.id.loginButton);
        userNameEditText =(EditText)findViewById(R.id.usenamEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        TextView signup =(TextView)findViewById(R.id.signTextView);
        signup.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        if (ParseUser.getCurrentUser()!=null)
        {
            Intent intent =new Intent(getApplicationContext(),UserListActivity.class);
            startActivity(intent);
        }


        /*RelativeLayout rLayout = (RelativeLayout) findViewById (R.id.layout);
        Resources res = getResources(); //resource handle
        Drawable drawable = res.getDrawable(R.drawable.whatsapp); //new Image that was added to the res folder

        rLayout.setBackground(drawable);*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.loginButton)


        {
             username = userNameEditText.getText().toString();
             password =passwordEditText.getText().toString();
            login();

        }

        else if (v.getId()==R.id.signTextView)
        {

            Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        }



    }

    public void login()
    {

 Log.i("save",username+" "+password);
        ParseUser.logInInBackground(username, password, new LogInCallback() {


            @Override
            public void done(ParseUser user, ParseException e) {
                if (e==null)

                {
                    Toast.makeText(LoginActivity.this,"Login success",Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(getApplicationContext(),UserListActivity.class);
                    startActivity(intent);
                }
                else
                {

                    String error_message=e.getMessage();
                    if (error_message.toLowerCase().matches("java"))
                    {
                        error_message =error_message.substring(error_message.indexOf(" "));
                    }

                    Toast.makeText(LoginActivity.this,error_message,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
