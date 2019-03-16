package com.example.dewioktaviani.firsttraining;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAddres, etId;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private Button btnSubmit, btnView;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etNAme);
        etAddres = (EditText) findViewById(R.id.etAddress);
        etId = (EditText)findViewById(R.id.etid);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnView = (Button) findViewById(R.id.btnView);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgGender.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rbGender = (RadioButton) findViewById(selectedId);
                if (etName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill name field", Toast.LENGTH_SHORT).show();
                }else if (etAddres.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill address field", Toast.LENGTH_SHORT).show();
                }else if (selectedId==-1){
                    Toast.makeText(getApplicationContext(), "Please choose gender", Toast.LENGTH_SHORT).show();
                }else {
                    People people = new People();
//                    Intent intent = new Intent(getApplicationContext(), ViewBiodataActivity.class);
//                    intent.putExtra(people.Property_ID, etId.getText().toString());
//                    intent.putExtra(people.Property_Name, etName.getText().toString());
//                    intent.putExtra(people.Property_Address, etAddres.getText().toString());
//                    intent.putExtra(people.Property_Gender, rbGender.getText().toString());
//                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);

                    people.setIntId(Integer.parseInt(etId.getText().toString()));
                    people.setTxtName(etName.getText().toString());
                    people.setTxtAddress(etAddres.getText().toString());
                    people.setTxtGender(rbGender.getText().toString());
                    new PeopleBL().SaveData(people);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(getApplicationContext(), String.valueOf(selectedId), Toast.LENGTH_SHORT).show();

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgGender.getCheckedRadioButtonId();

                // find the radiobutton by returned id
//                rbGender = (RadioButton) findViewById(selectedId);
//                String data = "Nama : " + etName.getText().toString() + "\n" +
//                        "Gender : " + rbGender.getText().toString() + "\n" +
//                        "Address : " + etAddres.getText().toString();
//                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, ListdataActivity.class));
                finish();
            }
        });
    }

    //saat on resume mapping version API
    @Override
    protected void onResume() {
        super.onResume();

        int hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission(Splash.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE);
//        int hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(Splash.this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        int hasCameraPermission = ContextCompat.checkSelfPermission(Splash.this,
//                Manifest.permission.CAMERA);
//        int hasPhonePermission = ContextCompat.checkSelfPermission(Splash.this,
//                Manifest.permission.READ_PHONE_STATE);

        if (Build.VERSION.SDK_INT >= 23){
            if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                checkPermission();
            }
//            else if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED){
//                checkPermission();
//            } else if (hasAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED){
//                checkPermission();
//            } else if (hasCameraPermission != PackageManager.PERMISSION_GRANTED){
//                checkPermission();
//            } else if (hasPhonePermission != PackageManager.PERMISSION_GRANTED){
//                checkPermission();
//            }  else {
//                StartAnimations();
//                checkStatusMenu();
//            }

            if (hasWriteExternalStoragePermission == PackageManager.PERMISSION_GRANTED){
                new Database().createFolderApp();
            }
        }


    }

    private boolean checkPermission() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("You need to allow access. . .");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        && !ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
//                        Manifest.permission.CAMERA)
//                        &&!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this,
//                        Manifest.permission.READ_PHONE_STATE)
                        ){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE
//                                    , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE
                            },
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();

                }
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
//                        REQUEST_CODE_ASK_PERMISSIONS);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }
}
