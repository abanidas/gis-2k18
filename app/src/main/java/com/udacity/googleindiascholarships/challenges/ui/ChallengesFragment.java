package com.udacity.googleindiascholarships.challenges.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesAdapter;
import com.udacity.googleindiascholarships.challenges.ui.entities.Challenge;

import java.util.ArrayList;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class ChallengesFragment extends android.support.v4.app.Fragment {

    RecyclerView challengeRecyclerView;
    Spinner challangeSpinner;
    ArrayList<Challenge> challengeList;

    /*
    * Using this LayoutAnimationController to animate the loading of RecyclerView items
    */
    LayoutAnimationController animationController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);

        challengeRecyclerView = rootView.findViewById(R.id.challenges_recyclerView);
        challangeSpinner = rootView.findViewById(R.id.spinner_challenges);

        animationController = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_move_up);

        challengeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
        * Spinner action to switch between challenges
        */
        challangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                /*
                * we can add switch case statement here using the position parameter
                * to switch between challenges layouts
                * But for now, using the same layout until we get real data
                */
                initListItems();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //no touch event performed
            }
        });

        /*
        * loading the content to display for the first time
        */
        initListItems();

        return rootView;
    }

    private void initListItems(){
        challengeList = new ArrayList<Challenge>();
        challengeList.add(new Challenge("Akshit Jain"));
        challengeList.add(new Challenge("Rahul"));
        challengeList.add(new Challenge("Vineet"));
        challengeList.add(new Challenge("Anuj"));

        challengeRecyclerView.setLayoutAnimation(animationController);
        ChallengesAdapter projectsAdapter = new ChallengesAdapter(getContext(), challengeList);
        challengeRecyclerView.setAdapter(projectsAdapter);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Challenges");
    }
}
