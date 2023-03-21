package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

public class LoginActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Menginisilisasi Variable dengan Form User dan Form Password dari Layout LoginActivity
        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);

        // Menajalankan Method razia() jika tombol Signin dikeyboard di sentuh
        mViewPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                razia();
                return true;
            }
            return false;
        });

        // Menjalankan Method razia() jika merasakan tombol Signin disentuh
        findViewById(R.id.button_signinSignin).setOnClickListener((v) -> {
           razia();
        });

        // ke RegisterActivity jika merasakan tombol Signup disentuh
        findViewById(R.id.button_signupSignin).setOnClickListener((v) -> {
            startActivity(new Intent(getBaseContext(),RegisterActivity.class));
        });
    }

    // ke MainActivity jika data status Login dari Data Preferences bernilai true
    @Override
    protected void onStart(){
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    // Men-Check inputan User dan Password dan Memberikan akses ke MainActivity
    private void razia(){

        // Mereset semua Error dan fokus menjadi default
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        // Mengambil text dari form user dan form password dengan variable baru bertipe String
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        // Jika form user kosong atau tidak memiliki kriteria di Method cekUser() maka, set error
        // di form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field id required");
            fokus = mViewUser;
            cancel = true;
        } else if(!cekUser(user)){
            mViewUser.setError("This is Username not Found");
            fokus = mViewUser;
            cancel = true;
        }

        // Sama syarat percabanganya dengan User seperti di atas. Bedanya ini untuk Form Password
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This Field is Required");
            fokus = mViewPassword;
            cancel = true;
        } else if (!cekPassword(password)){
            mViewPassword.setError("This Password is Incorrect");
            fokus = mViewPassword;
            cancel = true;
        }

        // Jika cancel true, Variable fokus mendapatkan fokus
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    // Menuju ke MainActivity dan set User dan Status sedang Login, di Preferences
    private void masuk(){
        Preferences.setLoggedInUSer(getBaseContext(), mViewUser.getText().toString());
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    //  True jika parameter Password sama dengan data password yang terdaftar dari Preferences
    private boolean cekPassword(String password){
        SharedPreferences sp = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if(sp.getString("pass", "").equals(password)){
            return true;
        }
        return false;
    }

    // True jika parameter User sama dengan data user yang terdaftar dari preferences
    private boolean cekUser(String user){
        SharedPreferences sp = getSharedPreferences("daftar", Context.MODE_PRIVATE);
        if(sp.getString("username", "").equals(user)){
            return true;
        }
        return false;
    }
}