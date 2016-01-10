package com.mentobile.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 11/11/2015.
 */

public class GetDataUsingWService extends AsyncTask<String, String, JSONObject> {

    private Activity activity;
    private ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    private ProgressDialog progressDialog;
    private String url;
    private WebService webService;
    private GetWebServiceData getWebServiceData;
    private int serviceCounter;
    private String message = "Preparing...";
    private boolean isShow;

    public interface GetWebServiceData {
        void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter);
    }

    public GetDataUsingWService(Activity activity, String url, int serviceCounter, ArrayList<NameValuePair> nameValuePairs, boolean isShow, GetWebServiceData getWebServiceData) {
        this.activity = activity;
        this.nameValuePairs = nameValuePairs;
        this.url = url;
        this.serviceCounter = serviceCounter;
        this.isShow = isShow;
        this.getWebServiceData = getWebServiceData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isShow) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        webService = new WebService();
        JSONObject json = webService.makeHttpRequest(url, nameValuePairs);
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        if (isShow)
            progressDialog.hide();
        getWebServiceData.getWebServiceData_JSON(result, serviceCounter);
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
