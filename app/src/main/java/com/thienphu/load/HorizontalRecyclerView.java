package com.thienphu.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorizontalRecyclerView extends RecyclerView.Adapter<HorizontalRecyclerView.ViewHolder> {

    private Context context;
    private List<Uri> list;

    public HorizontalRecyclerView(Context context) {
       this.context=context;
    }
     public void setData(List<Uri> list){
        this.list=list;
        notifyDataSetChanged();
     }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Uri uri=list.get(position);
       if (uri==null){
           return;
       }
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
            if(bitmap !=null){
                holder.imgViewIcon.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            list.size();
        }
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.image_view);
        }
    }
}
