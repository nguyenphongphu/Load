package com.thienphu.load;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final int PICK_IMAGE_MULTIPLE = 1;
    private static final int PICK_IMAGE = 2;
    private  RecyclerView recyclerView;
    private HorizontalRecyclerView view;
    String imageEncoded;
    List<Uri> imagesEncodedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);

        recyclerView = findViewById(R.id.recyclerview);
        view=new HorizontalRecyclerView(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(view);
        load();
    }
    private void load(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                .check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if(resultCode == Activity.RESULT_OK) {
                if(data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                    for(int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        imagesEncodedList.add(imageUri);
                        //do something with the image (save it to some directory or whatever you need to do with it here)
                    }
                    view.setData(imagesEncodedList);
                }
            } else if(data.getData() != null) {
                String imagePath = data.getData().getPath();
                //do something with the image (save it to some directory or whatever you need to do with it here)
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}