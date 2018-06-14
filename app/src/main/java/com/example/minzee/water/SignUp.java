package com.example.minzee.water;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

public class SignUp extends AppCompatActivity {

    Button btn_start;
    EditText tv_setgoal;
    SharedPreferences preferences;
    LottieAnimationView mypage_lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_start = findViewById(R.id.btn_start);
        tv_setgoal = findViewById(R.id.tv_setGoal);
        mypage_lottie = findViewById(R.id.lottie);



        preferences = getSharedPreferences("info", Activity.MODE_PRIVATE);
       /* if(preferences.getBoolean("waterintake",false)){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }*/

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean( "waterintake" , true);
                editor.putString("intake_goal",tv_setgoal.getText().toString());
                editor.commit(); //저장하기

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });
    }
}
