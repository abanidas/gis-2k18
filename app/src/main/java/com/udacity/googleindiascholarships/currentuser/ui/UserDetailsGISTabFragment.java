package com.udacity.googleindiascholarships.currentuser.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsGISProjectsAdapter;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsGISStoriesAdapter;

/**
 * Created by user on 5/2/2018.
 */

public class UserDetailsGISTabFragment extends Fragment {

    public UserDetailsGISTabFragment() {
        // Required empty public constructor
    }


    RecyclerView rvUserGISStories,rvUserGISProjects;
    LinearLayoutManager llmUserGISStories;
    GridLayoutManager glmUserGISProjects;
    UserDetailsGISStoriesAdapter userDetailsGISStoriesAdapter;
    UserDetailsGISProjectsAdapter userDetailsGISProjectsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View customView = inflater.inflate(R.layout.fragment_user_details_gistab, container, false);

        rvUserGISStories = (RecyclerView) customView.findViewById(R.id.rv_user_gis_stories);
        rvUserGISProjects = (RecyclerView) customView.findViewById(R.id.rv_user_gis_projects);

        llmUserGISStories = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvUserGISStories.setLayoutManager(llmUserGISStories);

        userDetailsGISStoriesAdapter = new UserDetailsGISStoriesAdapter();
        rvUserGISStories.setAdapter(userDetailsGISStoriesAdapter);


        glmUserGISProjects = new GridLayoutManager(getContext(),2);
        rvUserGISProjects.setLayoutManager(glmUserGISProjects);

        userDetailsGISProjectsAdapter = new UserDetailsGISProjectsAdapter(getContext());
        rvUserGISProjects.setAdapter(userDetailsGISProjectsAdapter);



        return customView;
    }
}
