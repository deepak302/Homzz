package com.mentobile.homzz;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.utility.DBHandler;
import com.mentobile.utility.UpdateNotification;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, ActionBar.OnNavigationListener {

    private String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private SMenuAdapter nvAdapter;
    Toolbar toolbar;
    ArrayList<NvItems> arrNVItems = new ArrayList<NvItems>();

    private TextView tvNVEmail;
    private TextView tvNVName;

    private DBHandler dbHandler;


    @Override
    protected void onStart() {
        super.onStart();
        // setProfile();
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
        dbHandler = new DBHandler(getApplicationContext(), 1);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.nv_header, null, false);
        tvNVEmail = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pemail);
        tvNVName = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pname);
        mDrawerList.addHeaderView(viewGroup, null, false);

        ActionBar actionBar = getSupportActionBar();

        String nv_array[] = getResources().getStringArray(R.array.prompt_nv_drawer);
        for (int i = 0; i < nv_array.length; i++) {
            NvItems items = new NvItems(R.mipmap.ic_launcher, nv_array[i], null, false);
            arrNVItems.add(items);
        }
        nvAdapter = new SMenuAdapter(getApplicationContext(), R.layout.nv_drawer_row, arrNVItems);
        mDrawerList.setAdapter(nvAdapter);
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        String items[] = getResources().getStringArray(R.array.spinner_city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items);
        actionBar.setListNavigationCallbacks(adapter, this);

        String tabs[] = getResources().getStringArray(R.array.action_tab_name);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (String tab_name : tabs) {
            tabLayout.addTab(tabLayout.newTab().setText(tab_name));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        MyTabPagerAdapter pagerAdapter = new MyTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), "Tab Selected " + tab.getPosition(), Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setProfile() {
        String email = Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "email");
        if (email != null && email.length() > 5) {
            NvItems items = (arrNVItems).get(5);
            items.setTitle(getString(R.string.prompt_logout));
            nvAdapter.notifyDataSetChanged();
            Cursor cursor = dbHandler.getPRofileDataFromDB(email);
            while (cursor.moveToNext()) {
                Profile.getProfile().setFirstName(cursor.getString(0));
                Profile.getProfile().setLastName(cursor.getString(1));
                Profile.getProfile().setFullName(cursor.getString(2));
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
        Toast.makeText(getApplicationContext(), "Item click " + position, Toast.LENGTH_SHORT).show();
        if (Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "email") != null) {
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
                        NvItems items = arrNVItems.get(position - 1);
                        items.setTitle(getString(R.string.prompt_login));
                        nvAdapter.notifyDataSetChanged();
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

//            mNotificationCount = 10;
//            invalidateOptionsMenu();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_done:
                Log.d(TAG, ":::::Done Clicked");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int mNotificationCount = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable layerDrawable = (LayerDrawable) item.getIcon();

        UpdateNotification.setBadgeCount(this, layerDrawable, mNotificationCount);

//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main, menu);
//
//        MenuItem item = menu.findItem(R.id.action_cart);
//        MenuItemCompat.setActionView(item, R.layout.layout_heart);
//
//        RelativeLayout relativeLayout = (RelativeLayout) MenuItemCompat.getActionView(item);
//
//        TextView textView = (TextView) relativeLayout.findViewById(R.id.gsss);
//        textView.setText("10");

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Toast.makeText(getApplicationContext(), " Spinner Item " + itemId, Toast.LENGTH_SHORT).show();
        return false;
    }
}
