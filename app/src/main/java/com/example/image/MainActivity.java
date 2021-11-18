package com.example.image;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.function.DoubleBinaryOperator;

public class MainActivity extends AppCompatActivity {
    Db o;
    ImageView iv;
    EditText et;
    final static private int p = 100;
    private Uri image;
    private Bitmap imagetostore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.imageView);
        et = findViewById(R.id.textView2);
        o = new Db(this);
    }

    public void chooseimage(View objview) {
        Intent objectIntent = new Intent();
        objectIntent.setType("image/*");

        objectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objectIntent, p);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == p && resultCode == RESULT_OK && data != null && data.getData() != null) {
            image = data.getData();
            try {
                imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                iv.setImageBitmap(imagetostore);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void storeimage(View view) {
        if (!et.getText().toString().isEmpty() && iv.getDrawable() != null && imagetostore != null) {
            o.storeimage(new Modelclass(et.getText().toString(), imagetostore));
        } else {
            Toast.makeText(this, "ccccccccccc", Toast.LENGTH_SHORT).show();
        }
    }


    public void m(View view) {
        Intent in=new Intent(MainActivity.this,Showimagesactivity.class);
        startActivity(in);
    }
}