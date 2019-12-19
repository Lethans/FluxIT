package com.example.android.desafio_fluxit.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.android.desafio_fluxit.R;
import com.example.android.desafio_fluxit.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.UserViewHolder> {

    private List<User> newUserList;
    private List<User> userList;
    private RecyclerMainListener recyclerMainListener;

    public RecyclerMainAdapter( RecyclerMainListener recyclerMainListener) {
        this.userList = new ArrayList<>();
        this.newUserList = new ArrayList<>();
        this.recyclerMainListener = recyclerMainListener;
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public List<User> getNewUserList() {
        return newUserList;
    }

    public void setNewUserList(List<User> newList) {
        this.newUserList = newList;
        notifyDataSetChanged();
    }

    public void addUserList(List<User> userList) {
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        UserViewHolder userViewHolder = holder;
        userViewHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycler_main_profilePic)
        CircleImageView profilePic;
        @BindView(R.id.recycler_main_username)
        TextView username;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = userList.get(getAdapterPosition());
                    recyclerMainListener.userSelect(user);
                }
            });
        }

        public void bind(User user) {
            Glide.with(itemView).load(user.getPicture().getThumbnail()).into(profilePic);
            username.setText(user.getLogin().getUsername());
        }
    }

    public interface RecyclerMainListener {
        void userSelect(User user);
    }
}


