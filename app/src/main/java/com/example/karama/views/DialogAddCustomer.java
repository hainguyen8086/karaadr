package com.example.karama.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.karama.R;
import com.example.karama.model.Customer;

public class DialogAddCustomer extends Dialog {
    Activity activity;
    ImageView view_colse;
    EditText member_fname,mem_lname,mem_gender,mem_adress,mem_phone, mem_email;
    TextView tv_add,tv_update;
    String typeUser;
    Customer customer;
    public DialogAddCustomer(@NonNull Activity context,String type) {
        super(context);
        this.activity = context;
        this.typeUser = type;
    }

    public DialogAddCustomer(@NonNull Activity activity, String typeUser, Customer customer) {
        super(activity);
        this.activity = activity;
        this.typeUser = typeUser;
        this.customer = customer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_customer);

        initView();
        view_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check input and call api add
            }
        });
        //tv_update -chua xu ly


    }

    private void initView() {
        member_fname = findViewById(R.id.member_fname);
        mem_lname = findViewById(R.id.mem_lname);
        mem_gender = findViewById(R.id.mem_gender);
        mem_adress = findViewById(R.id.mem_adress);
        mem_phone = findViewById(R.id.mem_phone);
        mem_email = findViewById(R.id.mem_email);
        tv_add = findViewById(R.id.tv_add);
        tv_update = findViewById(R.id.tv_update);
        view_colse = findViewById(R.id.view_colse);
        if (typeUser.equals("UPDATE")) {
            member_fname.setText(customer.getFirstName());
            mem_lname.setText(customer.getLastName());
            mem_gender.setText(customer.getGender());
            mem_adress.setText(customer.getAddress1());
            mem_phone.setText(customer.getPhoneNumber());
            mem_email.setText(customer.getEmail());
            tv_update.setVisibility(View.VISIBLE);
        }
        if (typeUser.equals("ADD")) {
            tv_add.setVisibility(View.VISIBLE);
        }
    }
}
