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

public class Catagory_Adapter extends FirebaseRecyclerAdapter<model,Catagory_Adapter.myprodctviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Catagory_Adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myprodctviewholder holder, int position, @NonNull model model) {
        holder.cat.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),Medicine_Catagory.class);
                in.putExtra("catagory",model.name);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public myprodctviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catagoryline,parent,false);
        return new myprodctviewholder(view);
    }

    public class myprodctviewholder extends RecyclerView.ViewHolder {
        TextView cat;
        public myprodctviewholder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.catcatcat);
        }
    }
}
