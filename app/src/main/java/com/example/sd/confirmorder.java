package com.example.sd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class confirmorder extends FirebaseRecyclerAdapter<addCart,confirmorder.mylastviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public confirmorder(@NonNull FirebaseRecyclerOptions<addCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mylastviewholder holder, int position, @NonNull addCart model) {
        holder.product.setText(model.getProduct());
        holder.quantity.setText(model.getQuantity());
    }

    @NonNull
    @Override
    public mylastviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.confirm,parent,false);
        return new mylastviewholder(view);
    }

    public class mylastviewholder extends RecyclerView.ViewHolder {
        TextView quantity,product;
        public mylastviewholder(@NonNull View itemView) {
            super(itemView);
            quantity = (TextView)itemView.findViewById(R.id.textView74);
            product = (TextView)itemView.findViewById(R.id.textView76);
        }
    }
}
