package com.kewldevs.sathish.nie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kewldevs.sathish.nie.Fragments.FormDataStore;
import com.kewldevs.sathish.nie.Fragments.FragsAdditionalSymptoms;
import com.kewldevs.sathish.nie.Fragments.FragsAddress;
import com.kewldevs.sathish.nie.Fragments.FragsAdmissionDate;
import com.kewldevs.sathish.nie.Fragments.FragsDOB;
import com.kewldevs.sathish.nie.Fragments.FragsDept;
import com.kewldevs.sathish.nie.Fragments.FragsDistrict;
import com.kewldevs.sathish.nie.Fragments.FragsEnvSample;
import com.kewldevs.sathish.nie.Fragments.FragsGender;
import com.kewldevs.sathish.nie.Fragments.FragsMobNo;
import com.kewldevs.sathish.nie.Fragments.FragsPatientDetails;
import com.kewldevs.sathish.nie.Fragments.FragsSample;
import com.kewldevs.sathish.nie.Fragments.FragsSummary;
import com.kewldevs.sathish.nie.Fragments.FragsSymptoms;
import com.kewldevs.sathish.nie.Fragments.FragsTaluks;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.Others.SectionsPagerAdapter;
import com.kewldevs.sathish.nie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int currentFrag;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    static FloatingActionButton mFab;
    Random random;
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
            mTabLayout = (TabLayout) findViewById(R.id.tlList);
            mFab = (FloatingActionButton) findViewById(R.id.fab);
            random = new Random();
            setSupportActionBar(mToolBar);
            setupNavigationDrawer();
            setupViewPager();
            thumbsDown();

    }



    private void setupViewPager() {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        for(int i=0; i<FormDataStore.NUMBER_OF_SECTIONS; ++i) FormDataStore.isValidated[i] = false;

        int i=-1;
        addFragment(new FragsAdmissionDate(),"Admission Date"); ++i;
        addFragment(new FragsDept(),"Department"); ++i;
        addFragment(new FragsPatientDetails(),"Patient Details"); ++i;
        addFragment(new FragsDOB(),"Age of Patient"); ++i;
        addFragment(new FragsGender(),"Gender"); ++i;
        addFragment(new FragsMobNo(),"Mobile Number"); ++i;
        addFragment(new FragsDistrict(),"District"); ++i;
        addFragment(new FragsTaluks(),"Taluks"); ++i;
        addFragment(new FragsAddress(),"Address"); ++i;
        addFragment(new FragsSymptoms(),"Symptoms"); ++i;
        FormDataStore.isValidated[i] = true; //No validation needed for Symptoms
        addFragment(new FragsAdditionalSymptoms(),"Additional Symptoms"); ++i;
        FormDataStore.isValidated[i] = true; //No validation needed for Additional Symptoms
        addFragment(new FragsSample(),"Samples"); ++i;
        //addFragment(new FragsTestDate(),"Test Date");
        addFragment(new FragsEnvSample(),"Environment Sample"); ++i;
        FormDataStore.isValidated[i] = true; //No validation needed for Env.Samples
        addFragment(new FragsSummary(),"Summary");

        mSectionsPagerAdapter.addFragmentsAndTitles(mFragmentList,mFragmentTitleList);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOffscreenPageLimit(FormDataStore.NUMBER_OF_SECTIONS+1);

        currentFrag = mViewPager.getCurrentItem();
        setTitle(mFragmentTitleList.get(currentFrag));

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentFrag = position;
                Log.d(Helper.TAG, "Selected: "+currentFrag);
                setTitle(mFragmentTitleList.get(currentFrag));
                if(position<FormDataStore.NUMBER_OF_SECTIONS)  {
                    Log.d(Helper.TAG, "Boolean:"+ FormDataStore.isValidated[position]);
                    if(FormDataStore.isValidated[position]) thumbsUp();
                    else thumbsDown();
                }

                //mFab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255))));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id) {
            case R.id.nav_logout:
                Helper.Logout(this);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setTitle(String s)
    {
        getSupportActionBar().setTitle(s);
    }

    private void setupNavigationDrawer() {
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public static void thumbsUp() {
        mFab.setImageResource(R.mipmap.ic_thumbs_up);
        mFab.setOnClickListener(null);
        Log.d(Helper.TAG, "thumbsUpped");
    }

    public static void thumbsDown() {

        mFab.setImageResource(R.mipmap.ic_thumbs_down);
        mFab.setOnClickListener(null);
        Log.d(Helper.TAG, "thumbsDowned");
    }

    public static void showFormSubmitButton() {
        mFab.setImageResource(R.drawable.ic_done_black_24dp);
        mFab.setOnClickListener(new SubmitForm());
    }

    public static void disableFormSubmitButton() {
        mFab.setImageResource(R.drawable.ic_error_black_48dp);
        mFab.setOnClickListener(null);
    }
}
