package ca.judacribz.multifunction;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraActivity extends AppCompatActivity {

    private static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    private static final String IMG_EXT = ".jpg";
    private static final String FILE_PROVIDER_AUTHORITY = "com.example.android.fileprovider";
    private static final int REQUEST_TAKE_PHOTO = 1;

    private ImageView imageView;
    private String photoFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.ivImage);
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (photoFile != null) {

                photoFilePath = photoFile.getAbsolutePath();

                Uri photoURI = FileProvider.getUriForFile(
                        this,
                        FILE_PROVIDER_AUTHORITY,
                        photoFile
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // (image file name, image file extension, storage directory)
        return File.createTempFile(
                new SimpleDateFormat(DATE_FORMAT, Locale.US).format(new Date()),
                IMG_EXT,
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(photoFilePath));
        }
    }
}
