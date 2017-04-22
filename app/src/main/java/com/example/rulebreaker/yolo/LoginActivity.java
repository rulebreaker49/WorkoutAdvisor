package com.example.rulebreaker.yolo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth2;

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.loginbutton)
    public void login()
    {
        String usrnm=username.getText().toString();
        String pswrd=password.getText().toString();

        mAuth2 = FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(usrnm))
        {
            username.setError("Username cannot be Empty!");
            return;
        }
        if(TextUtils.isEmpty(pswrd))
        {
            Toast.makeText(this,"Password cannot be Empty",Toast.LENGTH_LONG).show();
            return;
        }
        else if(pswrd.length()<8){
            Toast.makeText(this,"Invalid Password",Toast.LENGTH_LONG).show();
            password.setText("");
            password.requestFocus();
            return;

    }

        mAuth2.signInWithEmailAndPassword(usrnm,pswrd)
                .addOnCompleteListener(
                        this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                                    password.setText("");
                                    password.requestFocus();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();

                                    username.setText("");
                                    password.setText("");
                                    username.requestFocus();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        }
                );


    }
    @OnClick(R.id.resetbutton)
    public void reset()
    {
        username.setText("");
        password.setText("");
        username.requestFocus();

    }
    @OnClick(R.id.skiplogin)
    public void skipLogin()
    {
        Toast.makeText(this, "Guest Login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.registerpage)
    public void registerpage(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}

