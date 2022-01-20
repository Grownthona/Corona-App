package com.example.sd;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class productlistadapter extends FirebaseRecyclerAdapter<products,productlistadapter.myproductviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public productlistadapter(@NonNull FirebaseRecyclerOptions<products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myproductviewholder holder, int position, @NonNull products model) {

        holder.name.setText(model.getUsername());
        holder.productname.setText(model.getProduct());
        holder.productprice.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),AdminshowProductDetails.class);
                in.putExtra("about",model.Username);
                in.putExtra("medname",model.Product);
                in.putExtra("criteria",model.Catagory);
                in.putExtra("cost",model.Price);
                in.putExtra("userid",model.userid);
                in.putExtra("listunique",model.listid);
                in.putExtra("unamee",model.Username);
                in.putExtra("thikana",model.UserAddress);
                in.putExtra("pid",model.id);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public myproductviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminproductlistshow,parent,false);
        return new myproductviewholder(view);
    }

    public class myproductviewholder extends RecyclerView.ViewHolder {

        TextView name,productname,productprice;

        public myproductviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView144);
            productname = itemView.findViewById(R.id.textView146);
            productprice = itemView.findViewById(R.id.textView148);
        }
    }
}
