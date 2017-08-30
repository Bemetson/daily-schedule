package com.bemetson.paivajarjestys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    EditText description, location;

    ScrollView scrollview;

    // Boolean value used to check whether we should add a view in addElement method or not
    Boolean addV = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        //toolbar.setSubtitleTextColor(Color.WHITE);

        scrollview = (ScrollView) findViewById(R.id.main_scrollview);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.setTitle(setDate(day));

        /* Here we inflate our /layout/weekday_view.xml into MainActivity's scrollview
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout weekday_view = (LinearLayout) inflater.inflate(R.layout.weekday_view, null, false);
        scrollview = (ScrollView) findViewById(R.id.main_scrollview);
        scrollview.addView(weekday_view);*/

        // Here we initialize the basic view of the application
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Weekday_view_fragment fragment = new Weekday_view_fragment();
        if (scrollview.getChildCount() == 0) {
            fragmentTransaction.replace(R.id.main_scrollview, fragment, "weekday_fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            NewEntryDialogFragment dialog = new NewEntryDialogFragment();
            dialog.show(getFragmentManager(), "Juu");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Logic pending

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.addRule(RelativeLayout.ABOVE, R.id.navigation);
            params.setMargins(12, 12, 12, 12);

            LinearLayout weekdays = (LinearLayout) findViewById(R.id.weekdays);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    //scrollview.setLayoutParams(params);
                    weekdays.setVisibility(View.GONE);
                    addV = true;
                    return true;
                case R.id.navigation_dashboard:
                    //params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                    //scrollview.setLayoutParams(params);
                    weekdays.setVisibility(View.VISIBLE);
                    addV = false;
                    return true;
            }
            return false;
        }

    };

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
            case Calendar.SATURDAY | Calendar.SUNDAY:
                return R.string.weekend;

        }
        return R.string.error_date;
    }

    public void addElement(String description, int id) {
        LinearLayout target;
        Weekday_textview wdtext;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;
        wdtext = new Weekday_textview(this, "Missing", "Data");
        wdtext.setBackgroundResource(R.drawable.background_box);
        wdtext.setLayoutParams(params);
        wdtext.setGravity(Gravity.CENTER);
        wdtext.text_apply(description);

        if (addV) {
            switch (id) {
                case R.id.weekday_8:
                    target = (LinearLayout) this.findViewById(R.id.weekday_8);
                    target.addView(wdtext);
                    break;
                case R.id.weekday_10:
                    target = (LinearLayout) this.findViewById(R.id.weekday_10);
                    target.addView(wdtext);
                    break;
                case R.id.weekday_12:
                    target = (LinearLayout) this.findViewById(R.id.weekday_12);
                    target.addView(wdtext);
                    break;
                case R.id.weekday_14:
                    target = (LinearLayout) this.findViewById(R.id.weekday_14);
                    target.addView(wdtext);
                    break;
                case R.id.weekday_16:
                    target = (LinearLayout) this.findViewById(R.id.weekday_16);
                    target.addView(wdtext);
                    break;
            }
        }

    }
}

