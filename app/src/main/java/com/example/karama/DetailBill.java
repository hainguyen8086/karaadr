package com.example.karama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karama.adapter.ItemOrderAdapter;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.IInterfaceModel;
import com.example.karama.helper.UIHelper;
import com.example.karama.model.order.ProductInBill;
import com.example.karama.model.order.ResBill;
import com.example.karama.model.room.ResOrder;
import com.example.karama.services.OrderServices;
import com.example.karama.services.RumServices;
import com.example.karama.views.DialogDiscount;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DetailBill extends AppCompatActivity implements View.OnClickListener {
    //recept
    TextView bookingId,roomId,orderId,time_start, statusCode;
    //event
    TextView discount,destroy_bill,order, pay;
    //bill
    TextView billId,time_used,discount_percent, percent_money,bill_sdt;
    RecyclerView rcv_order;
    String BOOKINGID="";
    Context mContext = this;
    List<ProductInBill> productInBillList;
    ItemOrderAdapter itemOrderAdapter;
    private static DetailBill instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bill);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                BOOKINGID= null;
            } else {
                BOOKINGID= extras.getString("BOOKINGID");
            }
        } else {
            BOOKINGID= (String) savedInstanceState.getSerializable("BOOKINGID");
        }

        initView();
        initClick();
        loadRecept(BOOKINGID);
        loadBill(BOOKINGID);

    }

    private void loadBill(String bookingid) {
        OrderServices.getBillByBookingID(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                ResBill resBill = (ResBill) response.body();
                if (resBill != null) {
                    if (resBill.getStatus().equals("200")) {
                        billId.setText(resBill.getData().getId());
                        discount_percent.setText(resBill.getData().getDiscountPercent());
                        percent_money.setText(resBill.getData().getDiscountMoney());
                        bill_sdt.setText(resBill.getData().getGuestPhoneNumber());
                        productInBillList.clear();
                        if (resBill.getData().getProducts().size() >= 1) {
                            for (int i = 0; i < resBill.getData().getProducts().size(); i++) {
                                ProductInBill item = resBill.getData().getProducts().get(i);
                                productInBillList.add(item);
                            }
                            itemOrderAdapter = new ItemOrderAdapter(mContext, productInBillList);
                            rcv_order.setAdapter(itemOrderAdapter);
                            itemOrderAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void Error(String error) {

            }
        },bookingid);
    }

    private void loadRecept(String bookingid) {
        RumServices.getOrderByBookingID(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                ResOrder resOrder = (ResOrder) response.body();
                if (resOrder != null) {
                    if (resOrder.getStatus().equals("200")) {
                        bookingId.setText(resOrder.getData().getBookingId());
                        roomId.setText(resOrder.getData().getRoomId());
                        orderId.setText(resOrder.getData().getOrderId());
                        time_start.setText(resOrder.getData().getStartTime());
                        statusCode.setText(resOrder.getData().getStatusCodeName());
                    } else if (resOrder.getStatus().equals("403")){
                        Log.e("==403", resOrder.getMessage());
                        Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DetailBill.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    } else{
                        UIHelper.showAlertDialog(mContext,resOrder.getStatus(),resOrder.getMessage(),R.drawable.troll_64);
                        Toast.makeText(mContext, resOrder.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("==err", resOrder.getStatus() + "-" + resOrder.getMessage());
                    }
                }
            }

            @Override
            public void Error(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                Log.e("==Error", error);
            }
        },bookingid);
    }

    private void initClick() {
        order.setOnClickListener(this);
        discount.setOnClickListener(this);
    }

    public static DetailBill getInstance() {
        if (instance == null) {
            instance = new DetailBill();
        }
        return instance;
    }
    private void initView() {
        instance = this;
        productInBillList = new ArrayList<>();
        bookingId = findViewById(R.id.bookingId);
        roomId = findViewById(R.id.roomId);
        orderId = findViewById(R.id.orderId);
        time_start = findViewById(R.id.time_start);
        statusCode = findViewById(R.id.statusCode);
        discount = findViewById(R.id.discount);
        destroy_bill = findViewById(R.id.destroy_bill);
        order = findViewById(R.id.order);
        pay = findViewById(R.id.pay);
        billId = findViewById(R.id.billId);
        time_used = findViewById(R.id.time_used);
        discount_percent = findViewById(R.id.discount_percent);
        bill_sdt = findViewById(R.id.bill_sdt);
        percent_money = findViewById(R.id.percent_money);
        rcv_order = findViewById(R.id.rcv_order);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rcv_order.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order:
                
                break;
            case R.id.discount:
                discount();
                break;
            case R.id.destroy_bill:
                
                break;
            case R.id.pay:
                
                break;
        }
    }

    private void discount() {
        DialogDiscount dldiscount = new DialogDiscount(DetailBill.this, BOOKINGID);
        dldiscount.setCanceledOnTouchOutside(false);
        dldiscount.show();
    }
    public void f5() {
        loadBill(BOOKINGID);
    }
}