package com.utkarsh.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Model> modelslist;
    Context context;

    public Adapter(List<Model> modelslist,Context context ) {
        this.modelslist = modelslist;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Adapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model current_item=modelslist.get(position);
        String date=current_item.getDate();
        String time=current_item.getTime();
        String product=current_item.getProduct();
        String quantity=current_item.getQuantity();
        String sell_amt=current_item.getSell_amt();
        String purchase_amt=current_item.getPurchase_amt();

        holder.purchaseAmt.setText(purchase_amt);
        holder.date.setText(date);
        holder.time.setText(time);
        holder.product.setText(product);
        holder.quantity.setText(quantity);
        holder.sellAmt.setText(sell_amt);

    }

    @Override
    public int getItemCount() {
        return modelslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date,time,product,quantity,purchaseAmt,sellAmt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            product=itemView.findViewById(R.id.Product);
            quantity=itemView.findViewById(R.id.Quantiy);
            purchaseAmt=itemView.findViewById(R.id.amtPurchase);
            sellAmt=itemView.findViewById(R.id.amtSell);
        }
    }
}
