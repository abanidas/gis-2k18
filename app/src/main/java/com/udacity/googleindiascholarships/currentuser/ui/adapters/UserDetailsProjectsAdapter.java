package com.udacity.googleindiascholarships.currentuser.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by user on 5/2/2018.
 */

public class UserDetailsProjectsAdapter extends RecyclerView.Adapter<UserDetailsProjectsAdapter.UserDetailsViewHolder> {

    class UserDetailsViewHolder extends RecyclerView.ViewHolder{
        public UserDetailsViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public UserDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_details_projects,parent,false);
        return new UserDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
