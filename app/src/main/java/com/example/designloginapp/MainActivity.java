package com.example.designloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView title, subTitle, forgetPass, noAcc;
    Button loginBtn;
    EditText email, pass;
    CheckBox remember;

    BubbleEmitterView bubbleEmitter;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.gradientTop));
        getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity.this, R.color.gradientBot));

        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subtitle);

        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        forgetPass = findViewById(R.id.forgotpass);
        loginBtn = findViewById(R.id.buttonlogin);
        remember = findViewById(R.id.remember);
        noAcc = findViewById(R.id.noacc);




        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_translate);
        Animation slideUpEmail = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in);
        Animation slideUpPass = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in_2);
        Animation slideUpForget = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in_3);
        Animation slideUpBtn = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in_4);

        title.startAnimation(titleAnim);
        subTitle.startAnimation(titleAnim);

        email.startAnimation(slideUpEmail);
        pass.startAnimation(slideUpPass);
        forgetPass.startAnimation(slideUpForget);
        remember.startAnimation(slideUpForget);
        loginBtn.startAnimation(slideUpBtn);
        noAcc.startAnimation(slideUpBtn);


        bubbleEmitter = findViewById(R.id.bubbleEmitter);
        bubbleEmitter.canExplode(false);
        bubbleEmitter.setColors(getResources().getColor(R.color.gradientTop),getResources().getColor(R.color.gradientTop),getResources().getColor(R.color.gradientTop));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                emitBubbles();
            }
        }, 2500);



        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlert);
                alertdialog.setTitle("Recover your account by email");

                alertdialog.setView(R.layout.recuperar_cuenta);



                alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, "Recovery email sent", Toast.LENGTH_SHORT).show();

                    }
                });

                alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertdialog.show();
            }
        });


    }

    private void emitBubbles() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = new Random().nextInt(61)+ 20;
                bubbleEmitter.emitBubble(size);
                emitBubbles();
            }
        }, new Random().nextInt(401) +100);
    }

    public void logged(View view){
        String emailTxt = email.getText().toString();
        String passTxt = pass.getText().toString();

        if(emailTxt.isEmpty()){
            email.setError("Please, complete this field");
            email.requestFocus();
        }else if(passTxt.isEmpty()){
            pass.setError("Please, complete this field");
            pass.requestFocus();
        }else{
            Toast.makeText(MainActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
        }

    }

}