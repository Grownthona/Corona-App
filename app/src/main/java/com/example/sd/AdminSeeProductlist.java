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

public class AdminSeeProductlist extends FirebaseRecyclerAdapter<model,AdminSeeProductlist.myproductlistviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminSeeProductlist(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myproductlistviewholder holder, int position, @NonNull model model) {
        holder.name.setText(model.getId());
        holder.productname.setText(model.getName());
        holder.productprice.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),Admin_Addproduct.class);
                in.putExtra("about",model.description);
                in.putExtra("medname",model.name);
                in.putExtra("criteria",model.catagory);
                in.putExtra("cost",model.price);
                in.putExtra("pid",model.id);
                in.putExtra("url",model.url);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public myproductlistviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminproductlistshow,parent,false);
        return new myproductlistviewholder(view);
    }

    public class myproductlistviewholder extends RecyclerView.ViewHolder {
        TextView name,productname,productprice;
        public myproductlistviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView144);
            productname = itemView.findViewById(R.id.textView146);
            productprice = itemView.findViewById(R.id.textView148);
        }
    }
}
