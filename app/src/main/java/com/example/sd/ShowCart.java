package com.example.sd;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShowCart extends FirebaseRecyclerAdapter<addCart,ShowCart.myviewholder> {
    int count = 1;
    int sum = 0;
    Dialog dialog;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ShowCart(@NonNull FirebaseRecyclerOptions<addCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull addCart model) {

        holder.name.setText(model.getProduct());
        holder.catagory.setText(model.getCatagory());
        holder.price.setText(model.getPrice()+" "+"Tk");
        holder.quantity.setText(model.getQuantity());
        Glide.with(holder.productimg.getContext()).load(model.getImg()).into(holder.productimg);
        Picasso.get().load(model.getImg()).into(holder.productimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),Detail.class);
                in.putExtra("medname",model.getProduct());
                in.putExtra("criteria",model.getCatagory());
                in.putExtra("cost",model.getPrice());
                in.putExtra("pid",model.getId());
                in.putExtra("url",model.getImg());
                in.putExtra("message","Update");
                v.getContext().startActivity(in);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addtocart,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView name,price,catagory,quantity;
        ImageView plus,minus,productimg;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textView32);
            price = (TextView)itemView.findViewById(R.id.textView43);
            catagory = (TextView)itemView.findViewById(R.id.textView42);
            quantity = (TextView)itemView.findViewById(R.id.textView44);
            plus = (ImageView)itemView.findViewById(R.id.imageView23);
            minus = (ImageView)itemView.findViewById(R.id.imageView24);
            productimg = (ImageView)itemView.findViewById(R.id.imageView22);
        }
    }
}
