package com.yezi.chet.view.cus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.tools.PictureTool;

import java.util.List;

public class AddFriendsAdapter extends RecyclerView.Adapter<AddFriendsAdapter.ViewHolder> {

    List<Friend> list;

    public AddFriendsAdapter(List<Friend> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_add_firends_info,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Friend friend = list.get(position);
        if(friend.getHead_picture()!=null)
            holder.head.setImageBitmap(PictureTool.ByteBecameBitmap(friend.getHead_picture()));
        holder.name.setText(friend.getName());
        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendInfo sendInfo = new SendInfo(friend.getName(),ApplicationData.getData(Permission.ADD_FRIEND));
                sendInfo.setSender_account(ApplicationData.getData().getUser().getAccount());
                CommunityBoot.getBoot().senderData(sendInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView head;
        TextView name;
        Button request;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            init(view);
        }

        public void init(View view){
            head = view.findViewById(R.id.add_friends_request_head);
            name = view.findViewById(R.id.add_friends_request_name);
            request = view.findViewById(R.id.add_friends_btn);
        }
    }

}
