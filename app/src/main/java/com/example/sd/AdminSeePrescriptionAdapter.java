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

public class AdminSeePrescriptionAdapter extends FirebaseRecyclerAdapter<Member,AdminSeePrescriptionAdapter.myprescriptionviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminSeePrescriptionAdapter(@NonNull FirebaseRecyclerOptions<Member> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myprescriptionviewholder holder, int position, @NonNull Member model) {
        holder.name.setText(model.getFullName());
        holder.phone.setText(model.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),PrescriptionDetail.class);
                in.putExtra("name",model.FullName);
                in.putExtra("address",model.Address);
                in.putExtra("phn",model.Phone);
                in.putExtra("url",model.url);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public myprescriptionviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_prescription,parent,false);
        return new myprescriptionviewholder(view);
    }

    public class myprescriptionviewholder extends RecyclerView.ViewHolder {
        TextView name,phone,cat;
        public myprescriptionviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView218);
            phone=(TextView)itemView.findViewById(R.id.textView220);
        }
    }
}
