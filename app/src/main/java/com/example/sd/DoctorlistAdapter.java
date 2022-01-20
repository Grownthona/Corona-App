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

public class DoctorlistAdapter extends FirebaseRecyclerAdapter<doctors,DoctorlistAdapter.doctorlistviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DoctorlistAdapter(@NonNull FirebaseRecyclerOptions<doctors> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull doctorlistviewholder holder, int position, @NonNull doctors model) {

        holder.name.setText(model.getName());
        holder.des.setText(model.getSpecialist());
        Glide.with(holder.img.getContext()).load(model.getUrl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),DoctorDetails.class);
                in.putExtra("about",model.details);
                in.putExtra("name",model.name);
                in.putExtra("time",model.prefferedtime);
                in.putExtra("sprcial",model.specialist);
                in.putExtra("url",model.url);
                v.getContext().startActivity(in);
            }
        });

    }

    @NonNull
    @Override
    public doctorlistviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorslist,parent,false);
        return new doctorlistviewholder(view);
    }

    public class doctorlistviewholder extends RecyclerView.ViewHolder {
        TextView name,des;
        ImageView img;
        public doctorlistviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.docnamee);
            des=(TextView)itemView.findViewById(R.id.doccatt);
            img = (ImageView)itemView.findViewById(R.id.docpic2);
        }
    }
}
