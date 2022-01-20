package com.example.sd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class searchadapter extends FirebaseRecyclerAdapter<model,searchadapter.mysearchviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public searchadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mysearchviewholder holder, int position, @NonNull model model) {
        holder.name.setText(model.getName());
    }

    @NonNull
    @Override
    public mysearchviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchproductlayout,parent,false);
        return new mysearchviewholder(view);
    }

    public class mysearchviewholder extends RecyclerView.ViewHolder {
        TextView name;
        public mysearchviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView69);
        }
    }
}
