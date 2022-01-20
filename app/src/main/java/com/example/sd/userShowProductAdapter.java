package com.example.sd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class userShowProductAdapter extends FirebaseRecyclerAdapter<products,userShowProductAdapter.myuserproductviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public userShowProductAdapter(@NonNull FirebaseRecyclerOptions<products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myuserproductviewholder holder, int position, @NonNull products model) {
        holder.name.setText(model.getUsername());
        holder.productname.setText(model.getProduct());
        holder.productdate.setText(model.getPrice());
    }

    @NonNull
    @Override
    public myuserproductviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usershowproduct,parent,false);
        return new myuserproductviewholder(view);
    }

    public class myuserproductviewholder extends RecyclerView.ViewHolder {
        TextView name,productname,productdate;
        public myuserproductviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView144);
            productname = itemView.findViewById(R.id.textView146);
            productdate = itemView.findViewById(R.id.textView148);
        }
    }
}

