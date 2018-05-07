package com.udacity.googleindiascholarships.members.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;

/**
 * Created by HP on 05-05-2018.
 */

public class EditProfileAboutTabFragment extends Fragment {

    private boolean editSkillFlag = false, editDescriptionFlag = false;
    TextView tvUserSkills;
    EditText etUserSkills;
    ImageButton btnUserSkillEdit;
    TextView tvUserDescription;
    EditText etUserDescription;
    ImageButton btnUserDescriptionEdit;


    public EditProfileAboutTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_about_tab, container, false);

        tvUserSkills = view.findViewById(R.id.tv_user_skills);
        etUserSkills = view.findViewById(R.id.et_user_skills);
        btnUserSkillEdit = view.findViewById(R.id.btn_user_skill_edit);

        tvUserDescription = view.findViewById(R.id.tv_user_description);
        etUserDescription = view.findViewById(R.id.et_user_description_edit);
        btnUserDescriptionEdit = view.findViewById(R.id.btn_user_description_edit);

        setupButtonClickListenersForAboutSection();

        return view;
    }

    private void setupButtonClickListenersForAboutSection() {

        btnUserSkillEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editSkillFlag) {
                    tvUserSkills.setVisibility(View.GONE);
                    etUserSkills.setVisibility(View.VISIBLE);
                    etUserSkills.setText(tvUserSkills.getText());
                    btnUserSkillEdit.setImageResource(R.drawable.ic_tick_save);
                    editSkillFlag = true;
                } else {
                    tvUserSkills.setVisibility(View.VISIBLE);
                    etUserSkills.setVisibility(View.GONE);
                    tvUserSkills.setText(etUserSkills.getText());
                    btnUserSkillEdit.setImageResource(R.drawable.ic_edit_black_24dp);
                    editSkillFlag = false;

                }
            }
        });

        btnUserDescriptionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editDescriptionFlag) {
                    tvUserDescription.setVisibility(View.GONE);
                    etUserDescription.setVisibility(View.VISIBLE);
                    etUserDescription.setText(tvUserDescription.getText());
                    btnUserDescriptionEdit.setImageResource(R.drawable.ic_tick_save);
                    editDescriptionFlag = true;
                } else {
                    tvUserDescription.setVisibility(View.VISIBLE);
                    etUserDescription.setVisibility(View.GONE);
                    tvUserDescription.setText(etUserDescription.getText());
                    btnUserDescriptionEdit.setImageResource(R.drawable.ic_edit_black_24dp);
                    editDescriptionFlag = false;

                }
            }
        });
    }
}
