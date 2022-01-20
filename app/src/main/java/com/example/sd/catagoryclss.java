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

public class catagoryclss extends FirebaseRecyclerAdapter<model,catagoryclss.myviewholdercat> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public catagoryclss(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholdercat holder, int position, @NonNull model model) {

        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice().toString()+"Tk");
        Glide.with(holder.img.getContext()).load(model.getUrl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),Detail.class);
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
    public myviewholdercat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.showall,parent,false);
        return new myviewholdercat(view);
    }

    public class myviewholdercat extends RecyclerView.ViewHolder {
        TextView name,price,cat;
        ImageView img;
        public myviewholdercat(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView49);
            price=(TextView)itemView.findViewById(R.id.textView51);
            img =(ImageView)itemView.findViewById(R.id.imageView26);
        }
    }
}
