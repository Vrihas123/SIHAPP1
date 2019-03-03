package com.sih.app1.kisaanmitra.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.Authrization.AuthenticationResponse;
import com.sih.app1.kisaanmitra.model.Authrization.GenericResponse;
import com.sih.app1.kisaanmitra.model.Authrization.LoginRequest;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;
import com.sih.app1.kisaanmitra.utils.AppConstants;
import com.sih.app1.kisaanmitra.utils.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private String username, password;
    private Button btnLogin;
    private LoginRequest loginRequest;
    private TinyDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        loginRequest = new LoginRequest();
        db = new TinyDB(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString();

                if(username!=null && password!=null){
                    apicall();
                }
                else
                    Toast.makeText(LoginActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void apicall(){

        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<AuthenticationResponse> call = apiServices.requestLogin(loginRequest);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.isSuccessful()){
                    AuthenticationResponse authenticationResponse = response.body();
                    if(null!=authenticationResponse){
                        db.putString(AppConstants.ACCESS_TOKEN, authenticationResponse.getToken());
                        //Intent to Main Activity
                        db.putBoolean(AppConstants.IS_LOGGED_IN, true);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {

            }
        });
    }
}
