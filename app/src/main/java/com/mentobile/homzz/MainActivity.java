package com.mentobile.homzz;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mentobile.utility.DBHandler;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, ActionBar.TabListener {

    private String TAG = "MainActivity";

    //Navigation Drawer layout
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ArrayList<NvItems> arrayList;
    private SMenuAdapter sMenuAdapter;
    private ListView listView;
    private DBHandler dbHandler;

    private TextView tvNVEmail;
    private TextView tvNVName;

    FragmentManager manager;
    Fragment fragmentTrends;
    Fragment fragmentSearch;

    @Override
    protected void onStart() {
        super.onStart();
        setProfile();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit this application?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        fragmentTrends = new TrendFragment();
        fragmentSearch = new SearchFragment();

        dbHandler = new DBHandler(getApplicationContext(), 1);
        mTitle = mDrawerTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        listView = (ListView) findViewById(R.id.main_lv_nvdrawer);
        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.nv_header, null, false);
        tvNVEmail = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pemail);
        tvNVName = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pname);
        listView.addHeaderView(viewGroup, null, false);

        arrayList = new ArrayList<NvItems>();
        String nv_array[] = getResources().getStringArray(R.array.prompt_nv_drawer);
        for (int i = 0; i < nv_array.length; i++) {
            NvItems items = new NvItems(R.mipmap.ic_launcher, nv_array[i], null, false);
            arrayList.add(items);
        }
        sMenuAdapter = new SMenuAdapter(getApplicationContext(),
                R.layout.nv_drawer_row, arrayList);
        listView.setAdapter(sMenuAdapter);
        listView.setOnItemClickListener(this);

        String[] tab_name = getResources().getStringArray(R.array.action_tab_name);
        ActionBar actionBar = getActionBar();
        setCustomActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0; i < 2; i++) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(tab_name[i]);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }
    }

    private void setCustomActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        RelativeLayout actionBarLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.action_bar_layout_mainpage, null);

        Spinner spinnerCity = (Spinner) actionBarLayout.findViewById(R.id.main_spinner_city);
        String[] category1 = getResources().getStringArray(R.array.spinner_city);
        ArrayAdapter<String> arrayAdapter_City = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category1);
        spinnerCity.setAdapter(arrayAdapter_City);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.LEFT);
        ImageButton drawerImageView = (ImageButton) actionBarLayout.findViewById(R.id.action_bar_imgbtn);
        drawerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aplha);
                view.startAnimation(animation);
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        actionBar.setCustomView(actionBarLayout, params);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    private void setProfile() {
        String email = Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "email");
        if (email != null && email.length() > 5) {
            NvItems items = (NvItems) arrayList.get(5);
            items.setTitle(getString(R.string.prompt_logout));
            sMenuAdapter.notifyDataSetChanged();
            Cursor cursor = dbHandler.getPRofileDataFromDB(email);
            while (cursor.moveToNext()) {
                Profile.getProfile().setFirstName(cursor.getString(0));
                Profile.getProfile().setLastName(cursor.getString(1));
                Profile.getProfile().setFullName(cursor.getString(2));
                Profile.getProfile().setEmailID(cursor.getString(3));
                Profile.getProfile().setMobile(cursor.getString(4));
                Profile.getProfile().setCityName(cursor.getString(5));
                Profile.getProfile().setLocation(cursor.getString(6));
                Profile.getProfile().setCompanyName(cursor.getString(7));
                Profile.getProfile().setHouseNo(cursor.getString(8));
                Profile.getProfile().setApartmentName(cursor.getString(9));
                Profile.getProfile().setPostalCode(cursor.getString(10));
                Profile.getProfile().setOtherAddress(cursor.getString(11));
                Profile.getProfile().setDelIns(cursor.getString(12));
            }
        } else {
            Profile.getProfile().setEmailID(getString(R.string.prompt_nv_email));
            Profile.getProfile().setFullName(getString(R.string.prompt_nv_name));
        }
        tvNVEmail.setText(Profile.getProfile().getEmailID());
        tvNVName.setText(Profile.getProfile().getFullName());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.main_lv_nvdrawer &&
                (Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "email") != null)) {

            switch (position) {
                case 0:// header
                    break;
                case 1://my Profile

                    break;
                case 2:// order

                    break;
                case 3://address

                    break;
                case 4://offers

                    break;
                case 5://contact us

                    break;
                case 6:
                    if (Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "email") != null) {
                        Application.clearSharedPreferenceFile(this, Application.SP_LOGIN_LOGOUT);
                        NvItems items = (NvItems) arrayList.get(position - 1);
                        items.setTitle(getString(R.string.prompt_login));
                        sMenuAdapter.notifyDataSetChanged();
                        tvNVEmail.setText(getString(R.string.prompt_nv_email));
                        tvNVName.setText(getString(R.string.prompt_nv_name));
                        Profile.emptyProfile();
                        switch (LoginActivity.LOGIN_TYPE) {
                            case 1://Simple Login
                                break;
                            case 2://Google
                                LoginActivity.loginActivity.googlePlusLogout();
                                break;
                            case 3://Facebook
                                LoginActivity.loginActivity.facebokLogout();
                                break;
                        }

                    }
                    break;
            }
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        switch (tab.getPosition()) {
            case 0:
                ft.replace(android.R.id.content, new TrendFragment());
                break;
            case 1:
                ft.replace(android.R.id.content, new SearchFragment());
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
