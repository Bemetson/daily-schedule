package com.bemetson.paivajarjestys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Henri on 2.9.2017.
 */

public class Weekday_textview_dialog extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_delete_entry);


        // Create the AlertDialog object and return it
        return builder.create();
    }

}
