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

public class ShowReview extends FirebaseRecyclerAdapter<MyReviewClass,ShowReview.myreviewviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ShowReview(@NonNull FirebaseRecyclerOptions<MyReviewClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myreviewviewholder holder, int position, @NonNull MyReviewClass model) {
        holder.name.setText(model.getReviewername());
        holder.review.setText(model.getRatings());
        holder.post.setText(model.getReview());
        Glide.with(holder.img.getContext()).load(model.getReviewerurl()).into(holder.img);

    }

    @NonNull
    @Override
    public myreviewviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews,parent,false);
        return new myreviewviewholder(view);
    }

    public class myreviewviewholder extends RecyclerView.ViewHolder {
        TextView name,post,cat,review;
        ImageView img;

        public myreviewviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView79);
            post=(TextView)itemView.findViewById(R.id.textView99);
            review = itemView.findViewById(R.id.textView182);
            img =(ImageView)itemView.findViewById(R.id.imageView56);
        }
    }
}
