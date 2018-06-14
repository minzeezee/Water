package com.example.minzee.water;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SetGoal extends AppCompatActivity {

    LottieAnimationView lottie;
    SharedPreferences sharedPreferences;
    Button btn_setgoal;
    EditText edit_goal;
    String orignin_intake;
    TextView tv_goal;
    int new_intakeGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        btn_setgoal = findViewById(R.id.btn_setgoal);
        edit_goal=findViewById(R.id.edit_goal);
        tv_goal = findViewById(R.id.tv_goal);


        sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        String intake = sharedPreferences.getString("intake_goal","0");
        tv_goal.setText(intake);


        lottie = findViewById(R.id.lottie);
        lottie.playAnimation();
        lottie.setSpeed(2);

        btn_setgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                orignin_intake = edit_goal.getText().toString();
                //new_intakeGoal = Integer.parseInt(orignin_intake);
                editor.putString("intake_goal",orignin_intake);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });




    }
}
