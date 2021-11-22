package com.example.karama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karama.MemberActivity;
import com.example.karama.R;
import com.example.karama.model.person.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    Context mContext;
    List<Customer> customerList;
    LayoutInflater layoutInflater;

    public CustomerAdapter(Context mContext, List<Customer> customerList) {
        this.mContext = mContext;
        this.customerList = customerList;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_list_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.tv_id.setText(customer.getId());
        holder.text_name.setText(customer.getFirstName()+" "+customer.getLastName());
        holder.text_sdt.setText(customer.getPhoneNumber());
        if (customer.getGender().equals("MALE")) {
            holder.avt_customer.setImageDrawable(mContext.getDrawable(R.drawable.sing_boy35));
        } else {
            holder.avt_customer.setImageDrawable(mContext.getDrawable(R.drawable.sing_girl35));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog detail customer
                if (MemberActivity.getInstance() != null) {
                    MemberActivity.getInstance().showDetailCustomer(customer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avt_customer;
        TextView tv_id,text_name, text_sdt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avt_customer = itemView.findViewById(R.id.avt_customer);
            tv_id = itemView.findViewById(R.id.tv_id);
            text_name = itemView.findViewById(R.id.text_name);
            text_sdt = itemView.findViewById(R.id.text_sdt);


        }
    }
}
