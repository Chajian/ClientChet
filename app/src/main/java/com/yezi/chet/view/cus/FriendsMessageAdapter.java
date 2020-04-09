package com.yezi.chet.view.cus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yezi.chet.R;
import com.yezi.chet.data.user.Message;

import java.util.List;

public class FriendsMessageAdapter extends RecyclerView.Adapter<FriendsMessageAdapter.ViewHOlder> {

    List<Message> list;

    public FriendsMessageAdapter(List<Message> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_friend_message,parent,false);
        ViewHOlder viewHOlder = new ViewHOlder(view);
        return viewHOlder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        Message message = list.get(position);
        if(message!=null) {
            holder.setStatus(message.rightORleft());
            holder.setText(message.getMessage());
            holder.hide();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHOlder extends RecyclerView.ViewHolder{

        LinearLayout left,right;
        ImageView img_left,img_right;
        TextView message_left,message_right;
        int status = 0;//0显示右边，1显示左边

        public ViewHOlder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        public void init(View view){

            left = view.findViewById(R.id.friends_message_left_layout);
            right = view.findViewById(R.id.friends_message_right_layout);
            img_left = view.findViewById(R.id.friends_message_left_img);
            img_right = view.findViewById(R.id.friends_message_right_img);
            message_left = view.findViewById(R.id.friends_message_left_text);
            message_right = view.findViewById(R.id.friends_message_right_text);
        }


        public void setStatus(Integer status) {
            if(status!=null)
                this.status = status;
        }

        public void hide(){
            switch (status){
                case 0:
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);

                    break;

                case 1:
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                    break;
            }
        }

        public void setText(String test){
            switch (status){
                case 0:
                    message_right.setText(test);
                    break;

                case 1:
                    message_left.setText(test);
                    break;
            }
        }
    }
}
