package com.example.android_buoi2_btvn;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvRegister;
    private EditText edtPassword, edtUserName;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tvRegister);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUserName);
        btnLogin = findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                gotoRegister.launch(intentRegister);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        });
    }

    ActivityResultLauncher<Intent> gotoRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                edtUserName.setText(intent.getStringExtra("UserName"));
                edtPassword.setText(intent.getStringExtra("UserPassword"));
                Log.d(TAG, "onActivityResult: RESULT_OK" + intent.getStringExtra("UserName"));
                Log.d(TAG, "onActivityResult: RESULT_OK" + intent.getStringExtra("Password"));
            }
            if (result.getResultCode() == RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: RESULT_CANCELED");
                Toast.makeText(LoginActivity.this, "Back Login", Toast.LENGTH_SHORT).show();
            }
        }
    });
}