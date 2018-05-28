package com.example.numafia.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtPasswd, edtConfirmPasswd;
    private Button btnSubmit;
    private TextView txvCreate;
    private Context mContext;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        initViews();
        initListenner();
    }

    public void initViews() {
        edtEmail = findViewById(R.id.login_edt_email);
        edtPasswd = findViewById(R.id.login_edt_passwd);
        btnSubmit = findViewById(R.id.login_btn_submit);
        txvCreate = findViewById(R.id.login_txv_createacc);
    }

    public void initListenner() {

        if (!edtEmail.getText().toString().equals("") && !edtPasswd.getText().toString().equals("")) {
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(buttonClick);
                    Intent i = new Intent(mContext, TwoActivity.class);
                    i.putExtra("login_email", edtEmail.getText().toString());
                    i.putExtra("login_passwd", edtPasswd.getText().toString());
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
            });
        } else {
            Toast.makeText(mContext, "Incorrect Username or Password ...", Toast.LENGTH_SHORT).show();
        }

        txvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View mView = getLayoutInflater().inflate(R.layout.dialog_create_account, null);

                edtEmail = mView.findViewById(R.id.dialog_edt_email);
                edtPasswd = mView.findViewById(R.id.dialog_edt_passwd);
                edtConfirmPasswd = mView.findViewById(R.id.dialog_edt_confirmpasswd);
                btnSubmit = mView.findViewById(R.id.dialog_btn_submit);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!edtEmail.getText().toString().equals("") && !edtPasswd.getText().toString().equals("") && !edtConfirmPasswd.getText().toString().equals("")) {
                            if (edtPasswd.equals(edtConfirmPasswd)) {
                                Intent i = new Intent(mContext, MainActivity.class);
                                i.putExtra("login_email", edtEmail.getText().toString());
                                i.putExtra("login_passwd", edtPasswd.getText().toString());
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                            } else {
                                Toast.makeText(mContext, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(mContext, "Incorrect Username or Password ...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}