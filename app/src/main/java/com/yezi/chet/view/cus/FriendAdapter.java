package com.yezi.chet.view.cus;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yezi.chet.R;
import com.yezi.chet.tools.PictureTool;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.view.MessageActivity;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<Friend> list;

    public FriendAdapter(List<Friend> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_friend_info,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend_info = list.get(position);
        if(friend_info.getHead_picture()!=null)
            holder.head.setImageBitmap(PictureTool.ByteBecameBitmap(friend_info.getHead_picture()));
        if(friend_info.getName()!=null)
            holder.name.setText(friend_info.getName());
        if(friend_info.getInfo()!=null)
            holder.info.setText(friend_info.getInfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        LinearLayout linearLayout;
        ImageView head;
        TextView name;
        TextView info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            init(view);
        }

        public void init(final View view){
            linearLayout = view.findViewById(R.id.recyc_friend_layout);
            head = view.findViewById(R.id.recyc_friend_head);
            name = view.findViewById(R.id.recyc_friend_name);
            info = view.findViewById(R.id.recyc_friend_info);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    intent.putExtra("target_account",name.getText().toString().trim());
                    view.getContext().startActivity(intent);
                }
            });
        }

    }
}
