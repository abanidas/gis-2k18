package com.udacity.googleindiascholarships.currentuser.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by user on 5/2/2018.
 */

public class UserDetailsGISStoriesAdapter extends RecyclerView.Adapter<UserDetailsGISStoriesAdapter.UserDetailsGISStoriesViewHolder>{

    public  UserDetailsGISStoriesAdapter() {
    }

    class  UserDetailsGISStoriesViewHolder extends RecyclerView.ViewHolder{
        public  UserDetailsGISStoriesViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public UserDetailsGISStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_details_gis_stories,parent,false);
        return new UserDetailsGISStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( UserDetailsGISStoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}