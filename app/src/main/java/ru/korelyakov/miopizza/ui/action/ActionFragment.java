package ru.korelyakov.miopizza.ui.action;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import ru.korelyakov.miopizza.R;

public class ActionFragment extends Fragment {

    private ActionViewModel actionViewModel;
    private ProgressDialog dialog;
    ImageView map;

    public ActionFragment() {
        super(R.layout.fragment_action);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actionViewModel =
                new ViewModelProvider(this).get(ActionViewModel.class);

           StorageReference storageReference = FirebaseStorage.getInstance().getReference("map.png");
           map = view.findViewById(R.id.map);


                dialog=new ProgressDialog(getContext());
                dialog.setMessage("Проверяем акции...");
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

        actionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }
}




     //   StorageReference storageReference = FirebaseStorage.getInstance().getReference("map.png");
     //   map = view.findViewById(R.id.map);
     //   Glide.with(this).load(storageReference).into(map);

