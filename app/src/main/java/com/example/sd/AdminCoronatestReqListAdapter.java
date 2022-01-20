package com.example.sd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdminCoronatestReqListAdapter extends FirebaseRecyclerAdapter<CoronatestBooking ,AdminCoronatestReqListAdapter.mytestlistviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminCoronatestReqListAdapter(@NonNull FirebaseRecyclerOptions<CoronatestBooking > options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mytestlistviewholder holder, int position, @NonNull CoronatestBooking  model) {

        holder.name.setText(model.getDate());
        holder.productname.setText(model.getFirstname());
        holder.productprice.setText(model.getPrice());

    }

    @NonNull
    @Override
    public mytestlistviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminproductlistshow,parent,false);
        return new mytestlistviewholder(view);
    }

    public class mytestlistviewholder extends RecyclerView.ViewHolder {

        TextView name,productname,productprice;


        public mytestlistviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView144);
            productname = itemView.findViewById(R.id.textView146);
            productprice = itemView.findViewById(R.id.textView148);
        }
    }
}
