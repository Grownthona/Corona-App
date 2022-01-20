package com.example.sd;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCheckAppoinmentAdapter extends FirebaseRecyclerAdapter<Doctorappoinment,AdminCheckAppoinmentAdapter.myadminappoinmentviewholder> {

    DatabaseReference reff;
    duplicate d;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminCheckAppoinmentAdapter(@NonNull FirebaseRecyclerOptions<Doctorappoinment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myadminappoinmentviewholder holder, int position, @NonNull Doctorappoinment model) {

        holder.docname.setText(model.getDoctorname());
        holder.price.setText(model.getFees().toString()+"Tk");
        holder.patientname.setText(model.getPatientname());
        holder.pphone.setText(model.getPatientsphone());
        holder.pmail.setText(model.getPatientemail());
        holder.appoinmentdate.setText(model.getAppoinmentdate());

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),Appoinment_Details.class);
                in.putExtra("docname",model.doctorname);
                in.putExtra("pname",model.patientname);
                in.putExtra("pmail",model.patientemail);
                in.putExtra("pphone",model.patientsphone);
                in.putExtra("pfees",model.fees);
                in.putExtra("pdate",model.appoinmentdate);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public myadminappoinmentviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_seedoctorappoinment_list,parent,false);
        return new myadminappoinmentviewholder(view);
    }

    public class myadminappoinmentviewholder extends RecyclerView.ViewHolder {
        TextView docname,price,patientname,appoinmentdate,patientuid,pphone,pmail;
        ImageView approve,disapprove;
        public myadminappoinmentviewholder(@NonNull View itemView) {
            super(itemView);
            patientname=(TextView)itemView.findViewById(R.id.textView204);
            docname=(TextView)itemView.findViewById(R.id.textView210);
            appoinmentdate = (TextView) itemView.findViewById(R.id.textView213);
            pphone = itemView.findViewById(R.id.textView215);
            pmail = itemView.findViewById(R.id.textView217);
            price = itemView.findViewById(R.id.textView206);
            approve =(ImageView)itemView.findViewById(R.id.imageView62);
            disapprove = itemView.findViewById(R.id.imageView66);
        }
    }
}
