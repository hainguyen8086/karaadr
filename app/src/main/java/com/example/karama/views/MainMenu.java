package com.example.karama.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karama.MainActivity;
import com.example.karama.R;
import com.example.karama.data.SharedPrefManager;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.IInterfaceModel;
import com.example.karama.helper.UHelper;
import com.example.karama.helper.UIHelper;
import com.example.karama.model.ResProfile;
import com.example.karama.services.APIServices;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    public Context mContext =this;
    public static Dialog loadingDialog;
    Button nav,btn_update,btn_logout;
    View view;
    CardView card_filter,card_info,card_update;
    TextView text_info,text_update_info,txt_username,txt_first_name,txt_last_name,txt_gender,txt_email, txt_role;
    TextView update_username,update_first_name,update_last_name, update_gender;
    ProgressDialog dialogUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        initView();
        setClick();
        getProfile();
    }

    private void getProfile() {
        loadingDialog.show();
        APIServices.seeProfile(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                loadingDialog.cancel();
                ResProfile resProfile = (ResProfile) response.body();
                if (resProfile != null) {
                    if (resProfile.getStatus().equals("200")) {
                        //successs
                        txt_username.setText(UHelper.getNullorEmptyV2(resProfile.getData().getUsername()));
                        txt_first_name.setText(UHelper.getNullorEmptyV2(resProfile.getData().getFirstName()));
                        txt_last_name.setText(UHelper.getNullorEmptyV2(resProfile.getData().getLastName()));
                        txt_gender.setText(UHelper.getNullorEmptyV2(resProfile.getData().getGender()));
                        txt_email.setText(UHelper.getNullorEmptyV2(resProfile.getData().getEmail()));
                        txt_role.setText(UHelper.getNullorEmptyV2(resProfile.getData().getRoleCodeName()));

                        update_first_name.setText(UHelper.getNullorEmptyV2(resProfile.getData().getFirstName()));
                        update_last_name.setText(UHelper.getNullorEmptyV2(resProfile.getData().getLastName()));
                        update_gender.setText(UHelper.getNullorEmptyV2(resProfile.getData().getGender()));


                    }
                    if (resProfile.getStatus().equals("401")) {
                        UIHelper.showAlertDialogV3(mContext, "ERROR 401", resProfile.getMessage(), R.drawable.ic_error, new IInterfaceModel.OnBackIInterface() {
                            @Override
                            public void onSuccess() {
                                Intent i = new Intent(MainMenu.this, MainActivity.class);
                                // set the new task and clear flags
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                        });


                    }
                }
            }

            @Override
            public void Error(String error) {
                loadingDialog.cancel();
                UIHelper.showAlertDialog(mContext,"ERROR",error,R.drawable.ic_error);
            }
        });
    }

    private void setClick() {
        nav.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        view.setOnClickListener(this);
        card_info.setOnClickListener(this);
        card_update.setOnClickListener(this);
        text_info.setOnClickListener(this);
        text_update_info.setOnClickListener(this);
    }

    private void initView() {
        loadingDialog = UIHelper.setShowProgressBar(mContext);
        dialogUpdate = new ProgressDialog(mContext);
        dialogUpdate.setMessage("Đang update thông tin");
        nav = findViewById(R.id.nav);
        btn_update = findViewById(R.id.btn_update);
        btn_logout = findViewById(R.id.btn_logout);
        view = findViewById(R.id.view);
        card_filter = findViewById(R.id.card_filter);
        card_filter.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        card_info = findViewById(R.id.card_info);
        card_info.setVisibility(View.GONE);
        card_update = findViewById(R.id.card_update);
        card_update.setVisibility(View.GONE);

        txt_username = findViewById(R.id.txt_username);
        txt_first_name = findViewById(R.id.txt_first_name);
        txt_last_name = findViewById(R.id.txt_last_name);
        txt_gender = findViewById(R.id.txt_gender);
        txt_email = findViewById(R.id.txt_email);
        txt_role = findViewById(R.id.txt_role);
        text_info = findViewById(R.id.text_info);
        text_update_info = findViewById(R.id.text_update_info);
        update_first_name = findViewById(R.id.update_first_name);
        update_gender = findViewById(R.id.update_gender);
        update_last_name = findViewById(R.id.update_last_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav:
                card_filter.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                break;
            case R.id.view:
                card_filter.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                break;
            case R.id.text_info:
                if (card_info.isShown()) {
                    card_info.setVisibility(View.GONE);
                } else {
                    card_info.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.text_update_info:
                if (card_update.isShown()) {
                    card_update.setVisibility(View.GONE);
                } else {
                    card_update.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_update:
                updateInfo();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        SharedPrefManager.setAccessToken("NON");
        SharedPrefManager.setRefreshToken("NON");
        Intent i = new Intent(MainMenu.this, MainActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void updateInfo() {
        String fname = update_first_name.getText().toString();
        String lname = update_last_name.getText().toString();
        String gend = update_gender.getText().toString();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", fname);
            jsonObject.put("lastName", lname);
            jsonObject.put("gender", gend);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = jsonObject.toString();
        dialogUpdate.show();
        APIServices.editProfile(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                dialogUpdate.cancel();
                ResProfile resUpdate = (ResProfile) response.body();

                if (resUpdate != null) {
                    if (resUpdate.getStatus().equals("200")) {
                        UIHelper.showAlertDialogConfirm(mContext, "SUCCESS", resUpdate.getMessage(), R.drawable.ic_success_35, new IInterfaceModel.OnBackIInterface() {
                            @Override
                            public void onSuccess() {
                                getProfile();
                            }
                        });

                    } else {
                        UIHelper.showAlertDialog(mContext,"FAILURE",resUpdate.getMessage(),R.drawable.bad_face_35);
                    }
                }
            }

            @Override
            public void Error(String error) {
                dialogUpdate.cancel();
                UIHelper.showAlertDialog(mContext,"ERROR",error,R.drawable.bad_face_35);
            }
        },req);

    }
}