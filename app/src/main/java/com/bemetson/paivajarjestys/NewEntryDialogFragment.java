package com.bemetson.paivajarjestys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Henri on 24.8.2017.
 */

public class NewEntryDialogFragment extends DialogFragment {

    List<RadioButton> container = new ArrayList<RadioButton>();
    List<RadioButton> container_time = new ArrayList<RadioButton>();

    String hour, desc, loc, day;
    EditText description, location;
    Button cancel, add;
    int id, calendar_date, day_c;
    View myview;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        myview = inflater.inflate(R.layout.dialog, null);

        calendar_date = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        // Initializing radio buttons
        initialize_radiobuttos();

        description = (EditText) myview.findViewById(R.id.description_edittext);
        description.setInputType(InputType.TYPE_CLASS_TEXT);
        location = (EditText) myview.findViewById(R.id.location_edittext);
        location.setInputType(InputType.TYPE_CLASS_TEXT);


        cancel = (Button) myview.findViewById(R.id.dialog_cancel);
        add = (Button) myview.findViewById(R.id.dialog_ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!description.getText().toString().isEmpty() && !location.getText().toString().isEmpty() &&
                        !(day == null || hour == null)) {
                    desc = description.getText().toString();
                    loc = location.getText().toString();
                    if (calendar_date == day_c) {
                        ((MainActivity) getActivity()).addElement((desc + "\n\n" + loc), id);
                    }

                    dismiss();
                } else {
                    Toast toast = Toast.makeText(myview.getContext(), R.string.dialog_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        builder.setView(myview)
                .setTitle(R.string.add_item);

        // Create the AlertDialog object and return it
        return builder.create();

    }

    private void initialize_radiobuttos() {
        RadioButton r_mon = (RadioButton) myview.findViewById(R.id.radio_monday);
        RadioButton r_tue = (RadioButton) myview.findViewById(R.id.radio_tuesday);
        RadioButton r_wed = (RadioButton) myview.findViewById(R.id.radio_wednesday);
        RadioButton r_thu = (RadioButton) myview.findViewById(R.id.radio_thursday);
        RadioButton r_fri = (RadioButton) myview.findViewById(R.id.radio_friday);
        container.add(r_mon);
        container.add(r_tue);
        container.add(r_wed);
        container.add(r_thu);
        container.add(r_fri);

        for(RadioButton item : container) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = ((RadioButton) v).isChecked();

                    // Check which radio button was clicked
                    switch(v.getId()) {
                        case R.id.radio_monday:
                            if (checked)
                                day = "monday";
                            day_c = Calendar.MONDAY;
                            break;
                        case R.id.radio_tuesday:
                            if (checked)
                                day = "tuesday";
                            day_c = Calendar.TUESDAY;
                            break;
                        case R.id.radio_wednesday:
                            if (checked)
                                day = "wednesday";
                            day_c = Calendar.WEDNESDAY;
                            break;
                        case R.id.radio_thursday:
                            if (checked)
                                day = "thursday";
                            day_c = Calendar.THURSDAY;
                            break;
                        case R.id.radio_friday:
                            if (checked)
                                day = "friday";
                            day_c = Calendar.FRIDAY;
                            break;

                    }

                }
            });
        }

        RadioButton r_8 = (RadioButton) myview.findViewById(R.id.radio_8);
        RadioButton r_10 = (RadioButton) myview.findViewById(R.id.radio_10);
        RadioButton r_12 = (RadioButton) myview.findViewById(R.id.radio_12);
        RadioButton r_14 = (RadioButton) myview.findViewById(R.id.radio_14);
        RadioButton r_16 = (RadioButton) myview.findViewById(R.id.radio_16);
        container_time.add(r_8);
        container_time.add(r_10);
        container_time.add(r_12);
        container_time.add(r_14);
        container_time.add(r_16);

        for(RadioButton item : container_time) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = ((RadioButton) v).isChecked();

                    // Check which radio button was clicked
                    switch(v.getId()) {
                        case R.id.radio_8:
                            if (checked)
                                hour = "8";
                            id = R.id.weekday_8;
                            break;
                        case R.id.radio_10:
                            if (checked)
                                hour = "10";
                            id = R.id.weekday_10;
                            break;
                        case R.id.radio_12:
                            if (checked)
                                hour = "12";
                            id = R.id.weekday_12;
                            break;
                        case R.id.radio_14:
                            if (checked)
                                hour = "14";
                            id = R.id.weekday_14;
                            break;
                        case R.id.radio_16:
                            if (checked)
                                hour = "16";
                            id = R.id.weekday_16;
                            break;

                    }

                }
            });
        }

    }
}
