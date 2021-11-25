package com.example.karama.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karama.R;
import com.example.karama.model.room.DataOrder;

import java.util.List;

public class ReceptByPhoneAdapter extends RecyclerView.Adapter<ReceptByPhoneAdapter.ViewHolder> {
    Context mContext;
    List<DataOrder> orderList;
    LayoutInflater layoutInflater;

    public ReceptByPhoneAdapter(Context mContext, List<DataOrder> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_one_recept, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataOrder recep= orderList.get(position);
        holder.bookingId.setText("BookingID :\n"+recep.getBookingId());
        holder.roomId.setText("RoomID :\n"+recep.getRoomId());
        holder.orderId.setText("OrderID :\n"+recep.getOrderId());
        holder.time_start.setText("Time Start :\n"+recep.getStartTime());
        holder.statusCode.setText("Status :\n"+recep.getStatusCodeName());
        holder.checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checin
                Toast.makeText(mContext, "Checkin-BookingID:"+recep.getBookingId(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingId,roomId,orderId,time_start,statusCode, checkin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.bookingId);
            roomId = itemView.findViewById(R.id.roomId);
            orderId = itemView.findViewById(R.id.orderId);
            time_start = itemView.findViewById(R.id.time_start);
            statusCode = itemView.findViewById(R.id.statusCode);
            checkin = itemView.findViewById(R.id.checkin);


        }
    }
}
