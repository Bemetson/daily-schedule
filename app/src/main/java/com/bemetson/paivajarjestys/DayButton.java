package com.bemetson.paivajarjestys;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

/**
 * Created by Henri on 5.8.2017.
 */

public class DayButton extends AppCompatButton{

    String id;

    public DayButton(Context context, String id) {
        super(context);
        this.id = id;
    }

    public void setData(View view) {

    }
}
