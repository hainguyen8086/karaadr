package com.example.karama.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.karama.BookingRoom;
import com.example.karama.R;
import com.example.karama.helper.UHelper;
import com.example.karama.model.room.DataRoom;

public class DialogAddRoom extends Dialog implements View.OnClickListener {
    ImageView view_close;
    EditText room_name;
    RadioButton rdbtn_vip,rdbtn_normal;
    TextView add_room;
    Activity activity;
    String typeuse;
    DataRoom roomGet;
    public DialogAddRoom(@NonNull Activity context,String typeuse) {
        super(context);
        this.activity = context;
        this.typeuse = typeuse;
    }
    public DialogAddRoom(@NonNull Activity context,String typeuse,DataRoom room) {
        super(context);
        this.activity = context;
        this.typeuse = typeuse;
        this.roomGet = room;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_room);
        initView();
        view_close.setOnClickListener(this);
        add_room.setOnClickListener(this);
    }

    private void initView() {
        view_close = findViewById(R.id.view_close);
        room_name = findViewById(R.id.room_name);
        rdbtn_normal = findViewById(R.id.rdbtn_normal);
        rdbtn_vip = findViewById(R.id.rdbtn_vip);
        add_room = findViewById(R.id.add_room);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_room:
                //call api add room
                checkValid();
                //reload room
                break;
            case R.id.view_close:
                dismiss();
                break;
        }
    }

    private void checkValid() {
        String name = UHelper.onTextStr(room_name);
        if (UHelper.onValidate(name)) {
            Toast.makeText(activity, "Vui lòng nhập tên phòng ", Toast.LENGTH_SHORT).show();
            room_name.requestFocus();
        } else if (!rdbtn_vip.isChecked() && !rdbtn_normal.isChecked()) {
            Toast.makeText(activity, "Vui lòng chọn loại phòng", Toast.LENGTH_SHORT).show();
        } else {
            if (BookingRoom.getInstance() != null) {
                if (rdbtn_vip.isChecked()) {
                    BookingRoom.getInstance().addRoom(name,"VIP");
                    dismiss();
                }
                if (rdbtn_normal.isChecked()) {
                    BookingRoom.getInstance().addRoom(name,"NORMAL");
                    dismiss();
                }
            }
        }

    }
}
