package com.example.noblegeorge.whatsuppclone.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.noblegeorge.whatsuppclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {



    ListView chatList;
    EditText chatEdit;
    ArrayList <String>chatContents =new ArrayList<>();
    String whatsappUser="";
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent =getIntent();
        final String user = intent.getStringExtra("username");
        setTitle(user);
        whatsappUser=user;

         chatList =(ListView)findViewById(R.id.chatListView);
         chatEdit =(EditText)findViewById(R.id.chatEditText);
        Button sendButton =(Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,chatContents);
        chatList.setAdapter(adapter);


        ParseQuery<ParseObject> parseQuery1 =new ParseQuery<ParseObject>("Message");
        parseQuery1.whereEqualTo("recipient",ParseUser.getCurrentUser().getUsername());
        parseQuery1.whereEqualTo("sender",whatsappUser);


        ParseQuery<ParseObject>parseQuery2 =new ParseQuery<ParseObject>("Message");
        parseQuery2.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        parseQuery2.whereEqualTo("recipient",whatsappUser);

        List<ParseQuery<ParseObject>>queries =new ArrayList<>();
        queries.add(parseQuery1);
        queries.add(parseQuery2);


        ParseQuery<ParseObject>parseQuery =ParseQuery.or(queries);
        parseQuery.orderByAscending("createdAt");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e==null)
                {
                    if (objects.size()>0)
                    {
                        for (ParseObject chat:objects)
                        {
                            String message =chat.getString("message");
                            if (!chat.getString("sender").matches(ParseUser.getCurrentUser().getUsername()))
                            {
                                message ="                                                                 "+message;
                            }
                           chatContents.add(message);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

            }
        });







        /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Send a message to "+user);
        final EditText messageEditText =new EditText(this);
        builder.setView(messageEditText);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.i("save",tweetEditText.getText().toString());

                ParseObject message = new ParseObject("Message");
                message.put("sender", ParseUser.getCurrentUser().getUsername());
                message.put("recipient", user);
                message.put("message", messageEditText.getText().toString());
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "successfully send", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        builder.show();*/
    }



    @Override
    public void onClick(View v) {
        final String messageContents =chatEdit.getText().toString();

        ParseObject message = new ParseObject("Message");
        message.put("sender", ParseUser.getCurrentUser().getUsername());
        message.put("recipient", whatsappUser);
        message.put("message", messageContents);
        chatEdit.setText("");

        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                {

                    chatContents.add(messageContents);
                    adapter.notifyDataSetChanged();
                    //Log.i("save",chatEdit.getText().toString());
                }
            }
        });


    }
}
