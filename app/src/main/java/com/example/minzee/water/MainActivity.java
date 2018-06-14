package com.example.minzee.water;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    TextView tv_Goal;
    TextView tv_intake;
    TextView tv_percent;

    float final_value;
    int intake =0;
    int settingvalue = 0; //예외처리 . 섭취한 물 - 초기값이 없을 때 0으로 셋팅

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressBar);
        tv_intake = findViewById(R.id.tv_intake);
        tv_Goal = findViewById(R.id.tv_goal);
        tv_percent= findViewById(R.id.tv_percent);

        //TODO sharered preferecne로 앞에서 받은 목표 섭취량 가져온다
        sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        String origin_goal = sharedPreferences.getString("intake_goal","0");
        //String origin_goal_string = origin_goal +"";
        tv_Goal.setText(origin_goal);
        tv_intake.setText(settingvalue+"");


        //TODO progressbar 설정하기
        progressBar.setMax(100);
        progressBar.setProgress(sharedPreferences.getInt("fianl_progress",0));
        tv_percent.setText(sharedPreferences.getString("final_percent","0")+"%");

        //TODO intake 설정하기
        intake = sharedPreferences.getInt("intake_value",0);
        tv_intake.setText(intake+"");




        //TODO 플로팅버튼 설정하는 부분
        FloatingActionButton floating_btn = (FloatingActionButton) findViewById(R.id.floating_btn);
        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intake +=200;
                tv_intake.setText(Integer.toString(intake));
                int goal2 = Integer.parseInt(tv_Goal.getText().toString());
                final_value = ((float)intake / goal2 ) *100;
                String result = String.format("%.0f",final_value);
                tv_percent.setText(result + "%");
                progressBar.setProgress((int)final_value);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("intake_value",intake);
                editor.putInt("fianl_progress",(int)final_value);
                editor.putString("final_percent",result);
                editor.commit();

            }
        });



























        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setgoal) {
            sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
            //tv_Goal.setText(sharedPreferences.getString("intake_goal",""));
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            String origin_goal = tv_Goal.getText().toString();
           // int originGoal_int= Integer.parseInt(origin_goal);
            editor.putString("intake_goal",origin_goal);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(),SetGoal.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {




            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}
