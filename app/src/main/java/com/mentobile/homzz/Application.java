package com.mentobile.homzz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.mentobile.utility.GetDataUsingWService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Deepak Sharma on 8/2/2015.
 */
public class Application {

    public final static String URL_SIGNUP = "http://www.mentobile.com/homzz/signup.php";
    public final static String URL_TRENDING = "http://cashbackhomes.in/webservice/trending.php";
    public final static String URL_IMAGES = "http://www.mentobile.com/homzz/images/";

    public final static String URL_SEARCH_ANY = "http://cashbackhomes.in/webservice/get_project_location_developer.php";
    public final static String URL_SEARCH_LOCATION = "http://cashbackhomes.in/webservice/get_locality.php";

    public static final String DATE_PATTERN1 = "dd.MM.yy";
    public static final String DATE_PATTERN2 = "yyyyMMdd";
    public static final String DATE_PATTERN3 = "dd/MM/yyyy";
    public static final String DATE_PATTERN4 = "dd-MM-yyyy";
    public static final String DATE_PATTERN5 = "MM/dd/yyyy";
    public static final String DATE_PATTERN6 = "ddMMyyyy";
    public static final String DATE_PATTERN7 = "yyyy-MM-dd";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String TIME_PATTERN4 = "HH:mm:ss";//16:56:45
    public static final String TIME_PATTERN2 = "HH:mm:ss a";//16:46:56 AM
    public static final String TIME_PATTERN3 = "HH:mm:ss:S aa";//16:34:56:67678 PM
    public static final String TIME_PATTERN1 = "HHmmss";


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void startAsyncTaskInParallel(GetDataUsingWService task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }

}
