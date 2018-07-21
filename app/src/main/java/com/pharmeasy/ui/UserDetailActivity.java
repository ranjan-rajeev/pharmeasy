package com.pharmeasy.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pharmeasy.R;
import com.pharmeasy.model.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivUserPic)
    ImageView ivUserPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);

        //region default code
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        //endregion

        if (getIntent().hasExtra("USER_DATA")) {
            userData = getIntent().getParcelableExtra("USER_DATA");
            Glide.with(this).load(userData.getAvatar())
                    .thumbnail(Glide.with(this).load(R.drawable.loading))
                    .fitCenter()
                    .crossFade()
                    .into(ivUserPic);
            getSupportActionBar().setTitle(userData.getFirst_name() + " " + userData.getLast_name());
            //getSupportActionBar().setTit(Color.WHITE);
        }
    }

}
