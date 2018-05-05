package com.udacity.googleindiascholarships.currentuser.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsProjectsAdapter;

/**
 * Created by user on 5/2/2018.
 */

public class UserDetailsProjectsTabFragment extends Fragment {

    LinearLayoutManager llmUserProjects;
    RecyclerView rvUserProjects;
    UserDetailsProjectsAdapter userDetailsProjectsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View customView = inflater.inflate(R.layout.fragment_user_details_projects_tab, container, false);

        rvUserProjects = (RecyclerView) customView.findViewById(R.id.rv_user_projects);

        llmUserProjects = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvUserProjects.setLayoutManager(llmUserProjects);

        userDetailsProjectsAdapter = new UserDetailsProjectsAdapter();
        rvUserProjects.setAdapter(userDetailsProjectsAdapter);




        return customView;
    }

}
