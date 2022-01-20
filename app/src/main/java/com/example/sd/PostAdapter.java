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

public class PostAdapter extends FirebaseRecyclerAdapter<PostClass,PostAdapter.mypostviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostAdapter(@NonNull FirebaseRecyclerOptions<PostClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mypostviewholder holder, int position, @NonNull PostClass model) {

        holder.username.setText(model.getPostername());
        holder.userpost.setText(model.getPostext());
       // if(model.getCommentnumber().equals("null")) {
           // holder.commentcount.setText("0" +" "+"Comments");
       // }else{
            holder.commentcount.setText(model.getCommentnumber()+" " + "Comments");
        ///}
        Glide.with(holder.img.getContext()).load(model.getUrl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(),PostDetail.class);
                in.putExtra("postid",model.postid);
                in.putExtra("posttxt",model.postext);
                in.putExtra("posternme",model.postername);
                in.putExtra("url",model.url);
                v.getContext().startActivity(in);
            }
        });
    }

    @NonNull
    @Override
    public mypostviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.postshoww,parent,false);
        return new mypostviewholder(view);
    }

    public class mypostviewholder extends RecyclerView.ViewHolder {
        TextView username,userpost,commentcount;
        ImageView img;
        public mypostviewholder(@NonNull View itemView) {
            super(itemView);
            username= itemView.findViewById(R.id.textView79);
            userpost = itemView.findViewById(R.id.textView198);
            img = itemView.findViewById(R.id.imageView556);
            commentcount = itemView.findViewById(R.id.textView196);
        }
    }
}
