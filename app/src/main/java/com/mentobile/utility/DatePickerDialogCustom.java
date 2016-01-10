package com.mentobile.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;


import com.mentobile.homzz.Application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerDialogCustom extends DialogFragment implements DialogInterface.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Activity activity;
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mmddyyyy", Locale.ENGLISH);

    GetPickerDailogDate getPickerDailogDate;

    public interface GetPickerDailogDate {
        void GetDate(String date);
    }

    public DatePickerDialogCustom(Activity activity, GetPickerDailogDate getPickerDailogDate) {
        this.activity = activity;
        this.getPickerDailogDate = getPickerDailogDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the date picker
        final Calendar maxCal = Calendar.getInstance();
        maxCal.set(1997, 11, 31);
        int year = maxCal.get(Calendar.YEAR);
        int month = maxCal.get(Calendar.MONTH);
        int day = maxCal.get(Calendar.DAY_OF_MONTH);

        final Calendar minCal = Calendar.getInstance();
        minCal.set(1924, 11, 31);

        DatePickerDialog dpd = new DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        //Get the DatePicker instance from DatePickerDialogCustom
        DatePicker dp = dpd.getDatePicker();

        //Set the maximum date to select from DatePickerDialogCustom
        dp.setMinDate(minCal.getTimeInMillis());
        dp.setMaxDate(maxCal.getTimeInMillis());

        return dpd; //Return the DatePickerDialogCustom in app window
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
//        Log.d("Date Set", ":::::On Click");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        String strDataTime = simpleDateFormat.format(newDate.getTime());
        getPickerDailogDate.GetDate(strDataTime);
    }
}
