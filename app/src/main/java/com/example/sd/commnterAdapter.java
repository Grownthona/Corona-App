package com.example.sd;

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

public class commnterAdapter extends FirebaseRecyclerAdapter<PostClass,commnterAdapter.mycommentviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public commnterAdapter(@NonNull FirebaseRecyclerOptions<PostClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mycommentviewholder holder, int position, @NonNull PostClass model) {
        holder.name.setText(model.getCommentername());
        holder.comment.setText(model.getComment());
        Glide.with(holder.img.getContext()).load(model.getCommenterurl()).into(holder.img);
    }

    @NonNull
    @Override
    public mycommentviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment,parent,false);
        return new mycommentviewholder(view);
    }

    public class mycommentviewholder extends RecyclerView.ViewHolder {

        TextView name,comment,cat;
        ImageView img;
        public mycommentviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView159);
            comment = itemView.findViewById(R.id.textView160);
            img = itemView.findViewById(R.id.imageView556);
        }
    }
}
