package com.example.karama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karama.data.SharedPrefManager;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.Config;
import com.example.karama.helper.UIHelper;
import com.example.karama.model.ResToken;
import com.example.karama.model.ResUser;
import com.example.karama.services.APIServices;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txt_username, txt_pass;
    Button btn_login, btn_signin;
    public Context mContext =this;
    public static Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPrefManager.init(mContext);
        checkConnect();
        checkFirstInstall();
        checkTokenInvalid();
        initView();
        initClick();
    }

    private void checkTokenInvalid() {
        if (!SharedPrefManager.getRefreshToken().equals("NON")) {
            if (!SharedPrefManager.getAccessToken().equals("NON")) {

            }
        }
    }

    private void checkFirstInstall() {
        if (!SharedPrefManager.getFirstInstall()) {
            Toast.makeText(this, "First install App", Toast.LENGTH_SHORT).show();
            Log.e("==share:", "First install App");
            SharedPrefManager.setPrefFirstInstall(true);
        }
    }

    private void checkConnect() {
        if (isNetwork(getApplicationContext())){

            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getApplicationContext(), "Internet Is Not Connected", Toast.LENGTH_SHORT).show();
        }
    }

    private void initClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_username.getText().toString().equals("") || txt_pass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "VUI LÒNG NHẬP USER-PASSWORD", Toast.LENGTH_SHORT).show();
                } else {
                    String username = txt_username.getText().toString();
                    String password = txt_pass.getText().toString();
                    getToken(username, password);


                }
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExam("00096");
            }
        });
    }

    private void checkExam(String employ) {
        APIServices.checkExam(mContext, new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                String res = (String) response.body();
                Log.e("==resExam:", res);
            }

            @Override
            public void Error(String error) {
                Log.e("==resExam:", error);
            }
        },employ);
    }
    public boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private void getToken(String username, String password) {
        loadingDialog.show();
        APIServices.getAccessRefreshToken(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                loadingDialog.cancel();
                ResToken resToken = (ResToken) response.body();
                if (resToken != null) {
                    Log.e("==acToken:", resToken.getData().getAccessToken());
                    Log.e("==rfToken:", resToken.getData().getRefreshToken());
                }
            }

            @Override
            public void Error(String error) {
                loadingDialog.cancel();
                UIHelper.showAlertDialog(mContext,"ERROR",error,R.drawable.ic_error);
            }
        },username,password);
    }

    private void initView() {
        Log.e("==url", Config.URL);
        loadingDialog = UIHelper.setShowProgressBar(mContext);
        txt_username = findViewById(R.id.txt_username);
        txt_pass = findViewById(R.id.txt_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signin = findViewById(R.id.btn_signin);
    }
}