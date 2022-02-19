package com.omdeep.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Context context;
    RadioGroup radioGroup;
    RadioButton male, female;
    EditText eth_text;
    EditText etw_text;
    Button  clear , submit;
    TextView  res2;
    Spinner spinner;

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
        spinner = findViewById(R.id.spinner_item);

        res2 = findViewById(R.id.res2);
        clear = findViewById(R.id.clearBtn);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
        clear.setVisibility(View.INVISIBLE);
        res2.setVisibility(View.INVISIBLE);

        List<String> list=new ArrayList<String>();
        list.add("Select Units");
        list.add("MKS");
        list.add("FPS");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (v.getId())
        {
            case R.id.submit:
                submit.setVisibility(View.GONE);
                clear.setVisibility(View.VISIBLE);
                res2.setVisibility(View.VISIBLE);
                String str1 =  eth_text.getText().toString();
                String str2 = etw_text.getText().toString();


                // condition

                if(TextUtils.isEmpty(str1)){
                    res2.setVisibility(View.INVISIBLE);
                    clear.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    eth_text.setError("Please enter your height");
                    eth_text.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(str2)){
                    res2.setVisibility(View.INVISIBLE);
                    clear.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    etw_text.setError("Please enter your weight");
                    etw_text.requestFocus();
                    return;
                }

                if(radioGroup.getCheckedRadioButtonId()==-1){
                    clear.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                return;}



                float height = Float.parseFloat(str1)/100;
                float weight = Float.parseFloat(str2);
                float bmiValue = calculateBMI(weight, height);
                String bmiInterpretation = interpretBMI(bmiValue);
                res2.setText(String.valueOf(" Your BMI value is "+bmiValue + "-" + bmiInterpretation));
                break;

            case R.id.clearBtn:
                clear.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                eth_text.setText(null);
                etw_text.setText(null);
                res2.setText(null);
                radioGroup.clearCheck();

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
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/reference/android/content/Intent"));
                startActivity(intent);
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
