package com.bemetson.paivajarjestys;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Henri on 4.8.2017.
 */

public class Weekday{
    String day;
    String[][] schedule = new String[5][]; //2D array, with 5 arrays of unspecified size

    /*
        Values inside 'schedule' will follow the following format:
        H:COURSE_NAME-ROOM;
        H will determine to which time category schedule will be inserted
        COURSE_NAME is what will be displayed for the user as with
        ROOM which will be displayed to user in which room action takes place
     */


    public Weekday(String day) {
        this.day = day;
        //readData(day)
    }


    private void readData(String filename) {
        //TODO: Read data that is saved in a text file and transform that data into 'schedule'
    }
}
