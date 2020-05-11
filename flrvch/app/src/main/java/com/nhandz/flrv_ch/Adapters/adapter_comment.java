package com.nhandz.flrv_ch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.nhandz.flrv_ch.DT.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nhandz.flrv_ch.MainActivity;
import com.nhandz.flrv_ch.R;

import java.util.ArrayList;

public class adapter_comment extends RecyclerView.Adapter<adapter_comment.ViewHolder> {

    ArrayList<comments> data_comments;
    Context context;

    public adapter_comment(ArrayList<comments> data_comments, Context context) {
        this.data_comments = data_comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemview=layoutInflater.inflate(R.layout.comment_item,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUrl url=new GlideUrl(MainActivity.serverImg+""+data_comments.get(position).getAvt(),
                new LazyHeaders.Builder()
                        .addHeader("Cookie",MainActivity.cookies)
                        .build()
        );
        Glide.
                with(context.getApplicationContext())
                .load(url)
                .timeout(30000)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.thongbao)
                .into(holder.imgAvt);
        holder.txtname.setText(data_comments.get(position).getName());
        holder.txtcontent.setText(data_comments.get(position).getContent());
    }

    public void UpdateData(ArrayList<comments> list){
        this.data_comments.clear();
        this.data_comments.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data_comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        de.hdodenhof.circleimageview.CircleImageView imgAvt;
        TextView txtname;
        TextView txtcontent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvt=itemView.findViewById(R.id.cmt_avt);
            txtname=itemView.findViewById(R.id.cmtitem_name);
            txtcontent=itemView.findViewById(R.id.cmtitem_content);
        }
    }

}
