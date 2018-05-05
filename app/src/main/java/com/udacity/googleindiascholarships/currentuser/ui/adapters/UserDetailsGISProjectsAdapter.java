package com.udacity.googleindiascholarships.currentuser.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.udacity.googleindiascholarships.R;

/**
 * Created by user on 5/2/2018.
 */

public class UserDetailsGISProjectsAdapter extends RecyclerView.Adapter<UserDetailsGISProjectsAdapter.UserDetailsGISProjectsViewHolder>{

    Context mContext;

    public UserDetailsGISProjectsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    class  UserDetailsGISProjectsViewHolder extends RecyclerView.ViewHolder{

        ImageView ivUserGISProjects;
        ImageButton btnUserProjectEdit;
        FrameLayout flMenuEditStory;
        boolean editFlag = false;

        public  UserDetailsGISProjectsViewHolder(View itemView) {
            super(itemView);
            ivUserGISProjects = (ImageView) itemView.findViewById(R.id.iv_user_gis_project_image);
            btnUserProjectEdit = (ImageButton) itemView.findViewById(R.id.btn_user_project_edit);
            flMenuEditStory = (FrameLayout) itemView.findViewById(R.id.fl_menu_edit_story);

        }
    }
    @Override
    public UserDetailsGISProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_details_gis_projects,parent,false);
        return new UserDetailsGISProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserDetailsGISProjectsViewHolder holder, int position) {
        if(position%2 == 0){
            holder.ivUserGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_1));
        }else{
            holder.ivUserGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_2));
        }

        holder.btnUserProjectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.editFlag) {
                    holder.editFlag = true;
                    holder.flMenuEditStory.setVisibility(View.VISIBLE);
                    holder.btnUserProjectEdit.setImageResource(R.drawable.ic_done_black_24dp);
                } else {
                    holder.editFlag = false;
                    holder.flMenuEditStory.setVisibility(View.GONE);
                    holder.btnUserProjectEdit.setImageResource(R.drawable.ic_edit_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
