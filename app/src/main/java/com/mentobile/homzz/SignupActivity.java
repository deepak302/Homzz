package com.mentobile.homzz;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mentobile.utility.DBHandler;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "SignupFragment";
    private EditText edName;
    private EditText edEmail;
    private EditText edMobile;
    private EditText edPassword;
    private EditText edCPassword;
    private EditText edCity;
    private CheckBox chkTC;
    private Button btnCreateAccount;
    private String strEmail;
    CProgressDialog cProgressDialog;
    private WebService webService;
    private ArrayList<NameValuePair> listValue;
    private DBHandler dbHandler;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Sign Up");

        dbHandler = new DBHandler(this, 1);
        edName = (EditText) findViewById(R.id.signup_ed_fname);
        edEmail = (EditText) findViewById(R.id.signup_ed_email);
        edEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                strEmail = edEmail.getText().toString().trim();
                if (strEmail.length() > 1 && !hasFocus) {
                    if (!Application.isValidEmail(strEmail)) {
                        edEmail.setError(getString(R.string.error_email_verify));
                    }
                }
            }
        });
        edMobile = (EditText) findViewById(R.id.signup_ed_mobile);
        edPassword = (EditText) findViewById(R.id.signup_ed_password);
        edCPassword = (EditText) findViewById(R.id.signup_ed_cpassword);
        edCity = (EditText) findViewById(R.id.signup_ed_city);
        chkTC = (CheckBox) findViewById(R.id.signup_chk_tc);
        btnCreateAccount = (Button) findViewById(R.id.signup_btn_createaccount);
        btnCreateAccount.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_btn_createaccount:

                final String name = edName.getText().toString().trim();
                final String mobile = edMobile.getText().toString().trim();
                final String password = edPassword.getText().toString().trim();
                final String cPassword = edCPassword.getText().toString().trim();
                final String city = edCity.getText().toString().trim();
                if (name.length() < 1) {
                    edName.setError(getString(R.string.error_name));
                } else if (mobile.length() < 1) {
                    edMobile.setError(getString(R.string.error_mobile));
                } else if (password.length() < 6) {
                    edPassword.setError(getString(R.string.error_password));
                } else if (!password.equals(cPassword)) {
                    edCPassword.setError(getString(R.string.error_cpassword));
                } else if (city.length() < 1) {
                    edCity.setError(getString(R.string.error_city));
                } else if (!chkTC.isChecked()) {
                    chkTC.setError(getString(R.string.prompt_chk_tc));
                } else {
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            cProgressDialog = new CProgressDialog(SignupActivity.this);
                            cProgressDialog.setMessage("Please wait...");
                            cProgressDialog.show();
                        }

                        @Override
                        protected String doInBackground(String... params) {
                            webService = new WebService();
                            listValue = new ArrayList<NameValuePair>();
                            listValue.add(new BasicNameValuePair("name", name));
                            listValue.add(new BasicNameValuePair("email", strEmail));
                            listValue.add(new BasicNameValuePair("mobile", mobile));
                            listValue.add(new BasicNameValuePair("pass", password));
                            listValue.add(new BasicNameValuePair("cpass", cPassword));
                            listValue.add(new BasicNameValuePair("city", city));
                            listValue.add(new BasicNameValuePair("type", "2"));
                            JSONObject json = webService.makeHttpRequest("signup", listValue);
                            try {
                                String success = json.getString("description");
                                return success;

                            } catch (JSONException e) {
                                Log.d(TAG, ":::::Exception " + e.getMessage());
                            }
                            return "Invalid";
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            super.onPostExecute(result);
                            Log.d(TAG, "::::Result " + result);
                            Toast.makeText(SignupActivity.this, "" + result, Toast.LENGTH_SHORT).show();
                            cProgressDialog.hide();
                            if (result.equalsIgnoreCase("success")) {
                                Log.d(TAG, "::::Email " + strEmail);
                                Application.setDataInSharedPreference(SignupActivity.this, Application.SP_LOGIN_LOGOUT, "email", strEmail);
                                ContentValues values = new ContentValues();
                                values.put("FULLNAME", name);
                                values.put("EMAIL", strEmail);
                                values.put("MOBILE", mobile);
                                values.put("CITY", "");
                                dbHandler.insertData(DBHandler.TABLE_USER_PROFILE, values);
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                SignupActivity.this.finish();
                                startActivity(intent);
                            }
                        }
                    }.execute();
                }
                break;
        }
    }
}
