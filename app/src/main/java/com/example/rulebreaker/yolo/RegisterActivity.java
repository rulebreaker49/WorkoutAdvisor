package com.example.rulebreaker.yolo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.registerpassword)
    EditText registerpassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    public void register() {
        String Email = email.getText().toString();
        final String RegisterPassword = registerpassword.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email cannot be Empty!");
            return;
        }

        if (TextUtils.isEmpty(RegisterPassword)) {
            Toast.makeText(this,"Password cannot be Empty",Toast.LENGTH_LONG).show();
            return;
        }
        else if(registerpassword.length()<8){
            Toast.makeText(this,"Password should have minimum 8 characters!",Toast.LENGTH_LONG).show();
            registerpassword.setText("");
            registerpassword.requestFocus();
            return;

        }

        mAuth.createUserWithEmailAndPassword(Email, RegisterPassword)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("CreateUser", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            if (RegisterPassword.length() < 8)
                                Toast.makeText(RegisterActivity.this,"Password should have minimum 8 characters!",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(RegisterActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        } else {

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
    }


    @OnClick(R.id.loginpage)
    public void loginpage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
