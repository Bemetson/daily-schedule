package com.bemetson.paivajarjestys;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

/**
 * Created by Henri on 29.8.2017.
 */

public class Weekday_textview extends AppCompatTextView {

    String description, location;
    public Weekday_textview(Context context, String description, String location) {
        super(context);
        this.description = description;
        this.location = location;
        this.setText(description + "\n\n" + location);

    }

    public void text_apply(String d) {
        this.setText(d);
    }

}
