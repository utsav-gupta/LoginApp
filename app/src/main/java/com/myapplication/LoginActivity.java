package com.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity  {

    private EditText mEmailView;
    private EditText mPasswordView;
    private boolean emailCorrect;
    private boolean pwdCorrect;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        mEmailView = (EditText) findViewById(R.id.email);
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                 email = mEmailView.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmailView.setError(getString(R.string.error_field_required));
                    emailCorrect=false;

                } else if (!isEmailValid(email)) {
                    mEmailView.setError(getString(R.string.error_invalid_email));
                    emailCorrect=false;
                }else{
                    mEmailView.setError(null);
                    emailCorrect=true;
                }
                if (emailCorrect && pwdCorrect) {
                    mEmailSignInButton.setEnabled(true);

                } else {

                    mEmailSignInButton.setEnabled(false);
                }
            }
        });


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                 password = mPasswordView.getText().toString();
                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    pwdCorrect=false;
                }else{
                    mPasswordView.setError(null);
                    pwdCorrect =true;
                }

                if (emailCorrect && pwdCorrect) {
                    mEmailSignInButton.setEnabled(true);

                } else {

                    mEmailSignInButton.setEnabled(false);
                }



            }
        });


    }
    private void attemptLogin() {

        Intent i = new Intent(this,LoggedActivity.class);
        i.putExtra("NAME", email);
        startActivity(i);

    }
    private boolean isEmailValid(String email) {

        return !email.contains("@") && email.length()>6;
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 6;
    }



}

