package com.example.karama.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karama.R;
import com.example.karama.model.product.Products;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context mContext;
    List<Products> productsList;
    LayoutInflater layoutInflater;

    public ItemAdapter(Context mContext, List<Products> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.item_id.setText(products.getId());
        holder.item_name.setText(products.getName());
        holder.item_price.setText(products.getPrice());
        if (products.getStatus().equals("1")) {
            holder.item_lock.setVisibility(View.VISIBLE);
            holder.item_unlock.setVisibility(View.GONE);
            holder.item_name.setTextColor(Color.parseColor("#0707a8"));
        } else {
            holder.item_unlock.setVisibility(View.VISIBLE);
            holder.item_lock.setVisibility(View.GONE);
            holder.item_name.setTextColor(Color.parseColor("#bf1806"));
        }
        holder.item_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call api lock item
            }
        });
        holder.item_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call api unlock
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call detail Item
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_id,item_name,item_price,item_unlock, item_lock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.item_id);
            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            item_unlock = itemView.findViewById(R.id.item_unlock);
            item_unlock.setVisibility(View.GONE);
            item_lock = itemView.findViewById(R.id.item_lock);
            item_lock.setVisibility(View.GONE);
        }
    }
}
