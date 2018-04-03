package com.example.noblegeorge.whatsuppclone.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.noblegeorge.whatsuppclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ArrayList<String> userList =new ArrayList<>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

       ListView usersListView = (ListView) findViewById(R.id.usersListView);
        userList.add("qwertyu");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        usersListView.setAdapter(adapter);
        userList.clear();
        displayUsers();

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(UserListActivity.this,ChatActivity.class);
                intent.putExtra("username",userList.get(position));
                startActivity(intent);


            }
        });


    }

    public void displayUsers()
    {



        ParseQuery<ParseUser> query =ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if (e==null)
                {
                    if (objects.size()>0)
                    {
                        for (ParseUser user:objects)
                        {
                            userList.add(user.getUsername());
                        }

                        adapter.notifyDataSetChanged();
                    }


                }
                else
                {
                    Log.i("error",e.getMessage().toString());
                }
            }



        });


    }
}
