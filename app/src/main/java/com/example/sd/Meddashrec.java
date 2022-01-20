package com.example.sd;

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

public class Meddashrec extends FirebaseRecyclerAdapter<meddash,Meddashrec.medecmyviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Meddashrec(@NonNull FirebaseRecyclerOptions<meddash> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull medecmyviewholder holder, int position, @NonNull meddash model) {

        holder.tiitle.setText(model.getTittle());
        Glide.with(holder.img.getContext()).load(model.getImg()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(v.getContext(),Medicine_Catagory.class);
                Intent in2 = new Intent(v.getContext(),Essential_Catagory.class);
                if(model.getId().equals("medicine")){
                    v.getContext().startActivity(in);
                }
                if(model.getId().equals("essential")){
                    v.getContext().startActivity(in2);
                }

            }
        });
    }

    @NonNull
    @Override
    public medecmyviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jhakadash,parent,false);
        return new medecmyviewholder(view);
    }

    public class medecmyviewholder extends RecyclerView.ViewHolder {
        TextView tiitle,course,email;
        ImageView img;
        public medecmyviewholder(@NonNull View itemView) {
            super(itemView);
            tiitle=(TextView)itemView.findViewById(R.id.textView48);
            img =(ImageView)itemView.findViewById(R.id.imageView27);
        }
    }
}
