package com.example.karama.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.karama.R;
import com.example.karama.model.room.DataRoom;

public class DialogDetailRoom extends Dialog implements View.OnClickListener {
    Activity activity;
    DataRoom room;
    TextView update_room, check_in,cancel_ic,room_id,room_name,room_stt_booking,room_stt_type, tag_type_view,ic_lock,ic_unlock;
    ImageView view_close;
    public DialogDetailRoom(@NonNull Activity context,DataRoom room) {
        super(context);
        this.activity = context;
        this.room = room;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail_room);

        initView();
        update_room.setOnClickListener(this);
        check_in.setOnClickListener(this);
        cancel_ic.setOnClickListener(this);
        view_close.setOnClickListener(this);
        ic_lock.setOnClickListener(this);
        ic_unlock.setOnClickListener(this);

    }

    private void initView() {
        update_room = findViewById(R.id.update_room);
        check_in = findViewById(R.id.check_in);
        cancel_ic = findViewById(R.id.cancel_ic);

        room_id = findViewById(R.id.room_id);
        room_name = findViewById(R.id.room_name);
        room_stt_booking = findViewById(R.id.room_stt_booking);
        room_stt_type = findViewById(R.id.room_stt_type);
        tag_type_view = findViewById(R.id.tag_type_view);
        view_close = findViewById(R.id.view_close);
        ic_lock = findViewById(R.id.ic_lock);
        ic_unlock = findViewById(R.id.ic_unlock);

        room_id.setText(room.getId());
        room_name.setText(room.getName());
        room_stt_booking.setText(room.getRoomBookedStatus());
        room_stt_type.setText(room.getStatusCode());
        if (room.getType().equals("VIP")) {
            tag_type_view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.vip_30, 0, 0);
            tag_type_view.setText("VIP");
        } else {
            tag_type_view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.poor_30, 0, 0);
            tag_type_view.setText("Normal");
        }
        if (room.getStatusCode().equals("ENABLE")) {

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_room:
                //call dialog update
                break;
            case R.id.check_in:
                // load list orderID theo room ID, xong chojn -> check in
                break;
            case R.id.cancel_ic:
                // order ID by room ID
                break;
            case R.id.view_close:
                dismiss();
                break;
            case R.id.ic_lock:
                //call aoi lock
                break;
            case R.id.ic_unlock:
                //call api unclock
                break;
        }
    }
}
