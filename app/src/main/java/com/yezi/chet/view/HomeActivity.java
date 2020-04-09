package com.yezi.chet.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.yezi.chet.R;
import com.yezi.chet.view.cus.HomeBottom;
import com.yezi.chet.view.cus.HomeTitle;
import com.yezi.chet.view.fragment.FragmentAdapter;
import com.yezi.chet.view.fragment.FragmentFriends;
import com.yezi.chet.view.fragment.FragmentFriendsCircle;
import com.yezi.chet.view.fragment.FragmentPublicMessage;
import com.yezi.chet.view.fragment.FragmentUserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {

    ViewPager viewPager;
    HomeBottom bottom;
    HomeTitle title;
    FragmentFriends friends;
    FragmentPublicMessage publicMessage;
    FragmentFriendsCircle circle;
    FragmentUserInfo userInfo;
    FragmentAdapter fragmentAdapter;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void init(){

        // 碎片
        friends = new FragmentFriends();
        publicMessage = new FragmentPublicMessage();
        circle = new FragmentFriendsCircle();
        userInfo = new FragmentUserInfo();

        fragmentList.add(friends);
        fragmentList.add(publicMessage);
        fragmentList.add(circle);
        fragmentList.add(userInfo);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);

        viewPager = findViewById(R.id.home_viewPager);
        viewPager.setAdapter(fragmentAdapter);

        bottom = findViewById(R.id.home_bottom);
        title = findViewById(R.id.home_title);
        bottom.ClickIndex(1);
        bottom.setOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.main_bottom_1:
                            bottom.ClickIndex(1);
                            viewPager.setCurrentItem(0);
                            break;

                        case R.id.main_bottom_2:
                            bottom.ClickIndex(2);
                            viewPager.setCurrentItem(1);
                            break;

                        case R.id.main_bottom_3:
                            bottom.ClickIndex(3);
                            viewPager.setCurrentItem(2);
                            break;

                        case R.id.main_bottom_4:
                            bottom.ClickIndex(4);
                            viewPager.setCurrentItem(3);
                            break;
                    }
                }
        });
        title.setOpeartionOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(title.getOpeartion());
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (viewPager.getCurrentItem()){
            case 0:
                getMenuInflater().inflate(R.menu.friends_menus,menu);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friends_menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerForContextMenu(title.getOpeartion());
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterForContextMenu(title.getOpeartion());
    }


    public void jj(MenuItem menuItem){
        startActivity(new Intent(getApplicationContext(),AddFriendsActivity.class));
    }


    public void NewFriendsRequest(MenuItem menuItem){
        startActivity(new Intent(getApplicationContext(),NewFriendsRequestActivity.class));
    }


}
