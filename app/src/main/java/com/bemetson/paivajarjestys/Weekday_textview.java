package com.bemetson.paivajarjestys;

import android.app.Activity;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Henri on 29.8.2017.
 */

public class Weekday_textview extends AppCompatTextView {

    /*
        Values inside 'schedule' will follow the following format:
        H:DESCRIPTION;
        H will determine to which time category schedule will be inserted
        DESCRIPTION will include both the description of activity and location
     */

    String description, location;
    Activity activity;
    LinearLayout linearLayout;
    View myview;
    Context context;

    public Weekday_textview(Context context, String description, LinearLayout linearLayout) {
        super(context);
        activity = (Activity) context;
        this.description = description;
        this.linearLayout = linearLayout;
        this.context = context;
        //this.location = location;
        this.setText(description);

        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove();

                return true;
            }
        });
    }

    public void text_apply(String d) {
        this.setText(d);
    }

    private void remove() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        myview = inflater.inflate(R.layout.dialog_weekday_textview, null);
        builder.setView(myview)
                .setTitle(R.string.dialog_delete_entry);
        TextView desc_text = (TextView) myview.findViewById(R.id.weekday_textview_dialog_description);
        desc_text.setText(description);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Button delete_button = (Button) myview.findViewById(R.id.weekday_textview_dialog_ok_button);
        Button cancel_button = (Button) myview.findViewById(R.id.weekday_textview_dialog_cancel_button);

        delete_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(Weekday_textview.this);
                dialog.dismiss();
            }
        });

        cancel_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
