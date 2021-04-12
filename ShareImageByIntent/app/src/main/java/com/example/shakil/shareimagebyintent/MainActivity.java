package com.example.shakil.shareimagebyintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView imgBird;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBird = findViewById(R.id.imgBird);
        btnShare = findViewById(R.id.btnShare);

        String imageUrl = "https://images.unsplash.com/photo-1452570053594-1b985d6ea890?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHw%3D&auto=format&fit=crop&w=800&q=60";
        Picasso.get().load(imageUrl).into(imgBird);

        btnShare.setOnClickListener(v -> {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgBird.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),
                    bitmap, "Some Title", null);
            Uri bitmapUri = Uri.parse(bitmapPath);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
            startActivity(Intent.createChooser(intent, "Share Image "));
        });
    }
}