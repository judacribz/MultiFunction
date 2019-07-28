package ca.judacribz.multifunction;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToActivity(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnCameraActivity:
                intent = new Intent(this, CameraActivity.class);
                break;
            case R.id.btnEMIActivity:
                intent = new Intent(this, EMIActivity.class);
                break;
            case R.id.btnPersonObjects:
                intent = new Intent(this, PersonsActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
