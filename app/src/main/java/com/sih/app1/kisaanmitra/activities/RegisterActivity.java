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
import com.sih.app1.kisaanmitra.model.Authrization.RegisterRequest;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;
import com.sih.app1.kisaanmitra.utils.AppConstants;
import com.sih.app1.kisaanmitra.utils.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etUsername, etEmail, etContactNo, etPassword, etConfirmpassword;
    private String firstName, lastName, username, email, contactNo, password, confirmpassword;
    private Button btnRegister;
    private RegisterRequest registerRequest;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new TinyDB(this);

        if(db.getBoolean(AppConstants.IS_LOGGED_IN)){
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etContactNo = findViewById(R.id.etContactNo);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmpassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        registerRequest = new RegisterRequest();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = etFirstName.getText().toString().trim();
                lastName = etLastName.getText().toString().trim();
                username = etUsername.getText().toString().trim();
                contactNo = etContactNo.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString();
                confirmpassword = etConfirmpassword.getText().toString();
                if(firstName!=null && lastName!=null && username!=null && contactNo!=null && email!=null && password!=null && confirmpassword!=null && password.equals(confirmpassword)){
                    apicall();
                }
                else
                    Toast.makeText(RegisterActivity.this, "All are compulsory field", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void apicall(){
        registerRequest.setFirst_name(firstName);
        registerRequest.setLast_name(lastName);
        registerRequest.setUsername(username);
        registerRequest.setContact(contactNo);
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        registerRequest.setUser_type("FARMER");

        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<GenericResponse> call = apiServices.requestRegister(registerRequest);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if(response.isSuccessful()){
                    GenericResponse genericResponse = response.body();
                    if(genericResponse !=null){
                        if(genericResponse.getSuccess()){
                            db.putString(AppConstants.FIRST_NAME, firstName);
                            db.putString(AppConstants.LAST_NAME, lastName);
                            db.putString(AppConstants.EMAIL, email);
                            db.putString(AppConstants.CONTACT, contactNo);
                            //Intent to Login
                            Toast.makeText(RegisterActivity.this, "Register Successfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Some error occured", Toast.LENGTH_LONG).show();
            }
        });

    }
}
