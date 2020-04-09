package com.yezi.chet.view.cus;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.tools.PictureTool;

import java.util.List;

public class NewFriendsRequestAdapter extends RecyclerView.Adapter<NewFriendsRequestAdapter.ViewHolder> {

    List<Friend> list;

    public NewFriendsRequestAdapter(List<Friend> list) {
        this.list = list;
        Log.e(getClass().toString(),""+list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_new_friends_reqyest,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend = list.get(position);
        if(friend.getHead_picture()!=null)
            holder.head.setImageBitmap(PictureTool.ByteBecameBitmap(friend.getHead_picture()));
        if(friend.getName()!=null)
            holder.name.setText(friend.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        ImageView head;
        TextView name;
        Button agree;
        Button disagree;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            init(itemView);
        }

        public void init(View view){
            head = view.findViewById(R.id.new_friends_request_head);
            name = view.findViewById(R.id.new_friends_request_name);
            agree = view.findViewById(R.id.new_friends_request_agree);
            disagree = view.findViewById(R.id.new_friends_request_disagree);


            agree.setOnClickListener(this);
            disagree.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SendInfo sendInfo = null;
            switch (v.getId()){
                case R.id.new_friends_request_agree:
                    sendInfo = new SendInfo(ApplicationData.getData().getUser().getAccount(), ApplicationData.getData(Permission.ADD_FRIEND_AGREE));
                    break;

                case R.id.new_friends_request_disagree:
                    sendInfo = new SendInfo(ApplicationData.getData().getUser().getAccount(), ApplicationData.getData(Permission.ADD_FRIEND_DISAGREE));
                    break;
            }
            sendInfo.setSender_account(name.getText().toString().trim());
            CommunityBoot.getBoot().senderData(sendInfo);
        }
    }

}
