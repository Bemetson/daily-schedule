package com.bemetson.paivajarjestys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);

    ScrollView scrollview;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Logic pending
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.setTitle(setDate(day));

        /* Here we inflate our /layout/weekday_view.xml into MainActivity's scrollview
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout weekday_view = (LinearLayout) inflater.inflate(R.layout.weekday_view, null, false);
        scrollview = (ScrollView) findViewById(R.id.main_scrollview);
        scrollview.addView(weekday_view);*/

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Weekday_view_fragment fragment = new Weekday_view_fragment();
        fragmentTransaction.add(R.id.main_scrollview, fragment);
        fragmentTransaction.commit();
    }



    private int setDate(int date) {
        Log.e("TAG", Integer.toString(date));
        switch(date) {
            case Calendar.MONDAY:
                return R.string.monday;
            case Calendar.TUESDAY:
                return R.string.tuesday;
            case Calendar.WEDNESDAY:
                return R.string.wednesday;
            case Calendar.THURSDAY:
                return R.string.thursday;
            case Calendar.FRIDAY:
                return R.string.friday;
        }
        return R.string.error_date;
    }

}

