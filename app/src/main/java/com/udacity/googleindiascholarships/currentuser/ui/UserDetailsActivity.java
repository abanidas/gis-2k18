package com.udacity.googleindiascholarships.currentuser.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsViewPagerAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsActivity extends AppCompatActivity {


    ViewPager vpUserDetails;
    TabLayout tabsUserDetails;
    Toolbar toolbar;

    TextView tvUserName;
    ImageButton btnEditUserProfile;
    ImageButton btnUploadUserProfilePic;
    EditText etEditUserShortDescription;
    TextView tvUserShortDescription;
    boolean userProfileEditFlag = false;
    CircleImageView imgProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vpUserDetails = findViewById(R.id.vp_user_details);
        tabsUserDetails = findViewById(R.id.tabs_user_details);
        tvUserName = findViewById(R.id.tv_user_name);
        btnEditUserProfile = findViewById(R.id.btn_edit_user_profile);
        btnUploadUserProfilePic = findViewById(R.id.btn_upload_user_profile_pic);
        etEditUserShortDescription = findViewById(R.id.et_edit_user_short_description);
        tvUserShortDescription = findViewById(R.id.tv_user_short_description);
        imgProfilePicture = findViewById(R.id.img_profile_picture);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        vpUserDetails.setOffscreenPageLimit(3);
        setupViewPager();
        tabsUserDetails.setupWithViewPager(vpUserDetails);

        setupButtonClickListeners();
    }

    private void setupViewPager() {
        UserDetailsViewPagerAdapter adapter = new UserDetailsViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserDetailsAboutTabFragment(), "About");
        adapter.addFragment(new UserDetailsGISTabFragment(), "GIS");
        adapter.addFragment(new UserDetailsProjectsTabFragment(), "Projects");
        vpUserDetails.setAdapter(adapter);
    }

    private void setupButtonClickListeners(){
        btnEditUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userProfileEditFlag) {
                    userProfileEditFlag = true;
                    btnUploadUserProfilePic.setVisibility(View.VISIBLE);
                    etEditUserShortDescription.setVisibility(View.VISIBLE);
                    tvUserShortDescription.setVisibility(View.GONE);
                    btnEditUserProfile.setImageResource(R.drawable.ic_tick_save);
                    etEditUserShortDescription.setText(tvUserShortDescription.getText());
                } else {
                    userProfileEditFlag = false;
                    btnUploadUserProfilePic.setVisibility(View.GONE);
                    etEditUserShortDescription.setVisibility(View.GONE);
                    tvUserShortDescription.setVisibility(View.VISIBLE);
                    btnEditUserProfile.setImageResource(R.drawable.ic_edit_black_24dp);
                    tvUserShortDescription.setText(etEditUserShortDescription.getText());
                }
            }
        });

        btnUploadUserProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, 0);
    }

    private void takePhotoFromCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        22);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        }else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 22) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == 0) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);

                    //String path = saveImage(bitmap);
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imgProfilePicture.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == 1) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgProfilePicture.setImageBitmap(thumbnail);

            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
}