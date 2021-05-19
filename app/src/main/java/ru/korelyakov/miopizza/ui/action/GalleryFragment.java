package ru.korelyakov.miopizza.ui.action;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.ui.contacts.SlideshowViewModel;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private ProgressDialog dialog;
    ImageView map;

    public GalleryFragment() {
        super(R.layout.fragment_action);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

           StorageReference storageReference = FirebaseStorage.getInstance().getReference("map.png");
           map = view.findViewById(R.id.map);



                dialog=new ProgressDialog(getContext());
                dialog.setMessage("Проверяем акции...");
               // dialog.setCancelable(false);
              //  dialog.setInverseBackgroundForced(false);
                dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1000*2);






        try {
            final File localFile = File.createTempFile("map", "png");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    map.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {

        }

      //     Glide.with(this).load(storageReference).into(map);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }
}

      //  FirebaseStorage storage = FirebaseStorage.getInstance();
      //  StorageReference storageRef = storage.getReference();
      //  StorageReference mountainsRef = storageRef.child("map.jpg");



     //   StorageReference storageReference = FirebaseStorage.getInstance().getReference("map.png");
     //   map = view.findViewById(R.id.map);
     //   Glide.with(this).load(storageReference).into(map);

