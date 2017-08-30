package com.bemetson.paivajarjestys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    EditText description, location;
    Button mon_button, tue_button, wed_button, thu_button, fri_button;
    List<Button> buttonList = new ArrayList<Button>();

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

        mon_button = (Button) findViewById(R.id.monButton);
        tue_button = (Button) findViewById(R.id.tueButton);
        wed_button = (Button) findViewById(R.id.wedButton);
        thu_button = (Button) findViewById(R.id.thuButton);
        fri_button = (Button) findViewById(R.id.friButton);
        buttonList.add(mon_button);
        buttonList.add(tue_button);
        buttonList.add(wed_button);
        buttonList.add(thu_button);
        buttonList.add(fri_button);
        addButtonOnClicks();

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
                    setButtonBolding();
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

    private void setButtonBolding() {
        switch (day) {
            case Calendar.MONDAY:
                mon_button.setTypeface(Typeface.DEFAULT_BOLD);
                mon_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                //mon_button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
                //mon_button.getBackground().setColorFilter(0xFF94c0d3, PorterDuff.Mode.MULTIPLY);
                //tue_button.getBackground().setColorFilter(0xFF679db5, PorterDuff.Mode.MULTIPLY);
                break;
            case Calendar.TUESDAY:
                tue_button.setTypeface(Typeface.DEFAULT_BOLD);
                tue_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                break;
            case Calendar.WEDNESDAY:
                wed_button.setTypeface(Typeface.DEFAULT_BOLD);
                wed_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                break;
            case Calendar.THURSDAY:
                thu_button.setTypeface(Typeface.DEFAULT_BOLD);
                thu_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                break;
            case Calendar.FRIDAY:
                fri_button.setTypeface(Typeface.DEFAULT_BOLD);
                fri_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
    }

    private void addButtonOnClicks() {
        for (Button b : buttonList) {
            final Button button = b;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetBoldingOnButtons();
                    button.setTypeface(Typeface.DEFAULT_BOLD);
                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
            });
        }
    }

    private void resetBoldingOnButtons() {
        for (Button b : buttonList) {
            b.setTypeface(Typeface.DEFAULT);
            b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }
    }
}

