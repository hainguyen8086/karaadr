package com.example.karama.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karama.Booking;
import com.example.karama.MainActivity;
import com.example.karama.R;
import com.example.karama.adapter.ReceptByPhoneAdapter;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.IInterfaceModel;
import com.example.karama.helper.UIHelper;
import com.example.karama.model.room.DataOrder;
import com.example.karama.model.room.Res13ListOrder;
import com.example.karama.services.KaraServices;
import com.example.karama.services.RumServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DialogReceptByPhone extends Dialog {
    Activity activity;
    DataOrder order;
    RecyclerView recyclerView;
    ReceptByPhoneAdapter receptByPhoneAdapter;
    List<DataOrder> orderList;
    TextView title;
    String sdt;
    ImageView view_close;
    private static DialogReceptByPhone instance;

//    ImageView splash;
    public DialogReceptByPhone(@NonNull Activity context,String phone) {
        super(context);
        this.activity = context;
        this.sdt = phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_recept_by_phone);
        initView();
        tstAnimate();
        view_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        loadAllOrderRecept();

    }

    public void loadAllOrderRecept() {
        RumServices.getListOrderBySDT(new CallbackResponse() {
            @Override
            public void Success(Response<?> response) {
                Res13ListOrder resListOrderRecept = (Res13ListOrder) response.body();
                if (resListOrderRecept != null) {
                    if (resListOrderRecept.getStatus().equals("200")) {
                        //suces
                        orderList.clear();
                        for (int i = 0; i < resListOrderRecept.getData().getData().size(); i++) {
                            DataOrder recep=resListOrderRecept.getData().getData().get(i);
                            orderList.add(recep);

                        }
                        receptByPhoneAdapter = new ReceptByPhoneAdapter(activity.getApplicationContext(), orderList);
                        recyclerView.setAdapter(receptByPhoneAdapter);
                        receptByPhoneAdapter.notifyDataSetChanged();
                    } else if (resListOrderRecept.getStatus().equals("403")){
                        UIHelper.showAlertDialogV3(activity, resListOrderRecept.getStatus(), resListOrderRecept.getMessage(), R.drawable.troll_64, new IInterfaceModel.OnBackIInterface() {
                            @Override
                            public void onSuccess() {
                                Intent i = new Intent(activity, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                activity.startActivity(i);
                            }
                        });
                    } else{
                        UIHelper.showAlertDialog(activity,resListOrderRecept.getStatus(),resListOrderRecept.getMessage(),R.drawable.troll_64);
                    }
                }
            }

            @Override
            public void Error(String error) {

            }
        },sdt);
    }

    public static DialogReceptByPhone getInstance() {
        if (instance == null) {
            instance = new DialogReceptByPhone(instance.activity, getInstance().sdt);
        }
        return instance;
    }

    private void tstAnimate() {
//        RotateAnimation anim = new RotateAnimation(0f, 30f, 5f, 5f);
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE);
//        anim.setDuration(200);
//
//// Start animating the image
//        final ImageView splash =findViewById(R.id.splash);
//        splash.startAnimation(anim);
//
//// Later.. stop the animation
////        splash.setAnimation(null);
    }

    private void initView() {
        instance = this;
        orderList = new ArrayList<>();
        title = findViewById(R.id.title_order);
        recyclerView = findViewById(R.id.rcv_order);
        view_close = findViewById(R.id.view_close);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }
}
