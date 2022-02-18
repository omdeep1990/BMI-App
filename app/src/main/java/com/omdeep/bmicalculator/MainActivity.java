package com.omdeep.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Context context;
    RadioGroup radioGroup;
    RadioButton male, female;
    EditText eth_text;
    EditText etw_text;
    Button  clear , submit;
    TextView  res2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        radioGroup = findViewById(R.id.group);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        eth_text = findViewById(R.id.hieght);
        etw_text = findViewById(R.id.wight);
        submit = findViewById(R.id.submit);

        res2 = findViewById(R.id.res2);
        clear = findViewById(R.id.clearBtn);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId())
        {
            case R.id.submit:
                String str1 =  eth_text.getText().toString();
                String str2 = etw_text.getText().toString();


                // condition

                if(TextUtils.isEmpty(str1)){
                    eth_text.setError("Please enter your height");
                    eth_text.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(str2)){
                    etw_text.setError("Please enter your weight");
                    etw_text.requestFocus();
                    return;
                }



                float height = Float.parseFloat(str1)/100;
                float weight = Float.parseFloat(str2);
                float bmiValue = calculateBMI(weight, height);
                String bmiInterpretation = interpretBMI(bmiValue);
                res2.setText(String.valueOf(" Your BMI value is "+bmiValue + "-" + bmiInterpretation));
                break;

            case R.id.clearBtn:
                eth_text.setText(null);
                etw_text.setText(null);
                res2.setText(null);


        }
    }

    //Calculate BMI
    private float calculateBMI (float weight, float height) {
        return (float) (weight / (height * height));
    }



    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "You are Severely underweight";
        } else if (bmiValue < 18.5) {

            return "You are Underweight";
        } else if (bmiValue < 25) {

            return "You are Normal";
        } else if (bmiValue < 30) {

            return "You are Overweight";
        } else {
            return "You are Obese";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(MainActivity.this, IntentActivity1.class));
                Toast.makeText(this, "BMI Selected", Toast.LENGTH_SHORT);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Tips for Fitness selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                startActivity(new Intent(MainActivity.this, BalancedDiet.class));
                Toast.makeText(this, "Balanced Diet selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                startActivity(new Intent(MainActivity.this, Exercise.class));
                Toast.makeText(this, "Exercise selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                startActivity(new Intent(MainActivity.this, GoSocial.class));
                Toast.makeText(this, "Go social selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem4:
                startActivity(new Intent(MainActivity.this, Positivity.class));
                Toast.makeText(this, "Positivity selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem5:
                startActivity(new Intent(MainActivity.this, Challenge.class));
                Toast.makeText(this, "Challenge selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                startActivity(new Intent(MainActivity.this, IntentActivity2.class));
                Toast.makeText(this, "About Us selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                startActivity(new Intent(MainActivity.this, BMIChart1.class));
                Toast.makeText(this, "BMI Chart selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item5:
                startActivity(new Intent(MainActivity.this, Developer.class));
                Toast.makeText(this, "About Developer selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item7:
                startActivity(new Intent(MainActivity.this, ContactUs.class));
                Toast.makeText(this, "Contact Us selected", Toast.LENGTH_SHORT).show();
            case R.id.item6:
                Toast.makeText(this, "Exit selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onBackPressed(){

        myAlert(MainActivity.this);
    }

    public void myAlert(Context mContext){
        new AlertDialog.Builder(mContext)
                .setIcon(R.drawable.ic_exit)
                .setTitle("Exit?")
                .setMessage("Do you want to exit my app?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();



    }
}
