package com.example.noblegeorge.whatsuppclone.Model;

import android.app.Application;
import android.service.autofill.SaveCallback;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

import java.text.ParseException;

/**
 * Created by noblegeorge on 2018-04-02.
 */

public class ParseConnection extends Application {




    public void onCreate() {
        super.onCreate();


        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("1796777bec6df1500872d448fe3e0dc588315c53")
                .clientKey("1b14125b540c553c86c13ffbac8dca5a3072e131")
                .server("http://18.220.229.56:80/parse/")
                .build()
        );

        /*ParseObject sampleObject =new ParseObject("Sample");

        sampleObject.put("user","noble");

        sampleObject.saveInBackground(new com.parse.SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e==null)
                {

                }
            }
        });*/


        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }



}
