package com.omdeep.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omdeep.bmicalculator.databinding.ActivityContactUsBinding;

public class ContactUs extends AppCompatActivity implements View.OnClickListener {

    ActivityContactUsBinding binding;
    private String[] PERMISSIONS = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.dial:
                Intent intentDial = new Intent(Intent.ACTION_DIAL);
                intentDial.setData(Uri.parse("tel:9650837262"));
                startActivity(intentDial);
                break;
            case R.id.call:

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    doCall();

                }else {
                    ActivityCompat.requestPermissions(ContactUs.this, PERMISSIONS, 1);
                }
                break;
            case R.id.emailus:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String());
                email.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
//need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            //case R.id.suggestion:

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dial.setOnClickListener(this);
        binding.call.setOnClickListener(this);
        binding.emailus.setOnClickListener(this);
    }

    private void doCall() {
        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9650837262"));
        startActivity(intentCall);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // TODO: DO UR JOB
                doCall();

            }else {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btn_showDialog(View view) {

    }
}

