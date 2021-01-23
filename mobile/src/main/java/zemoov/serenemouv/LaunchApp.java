package zemoov.serenemouv;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class LaunchApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_app);

        Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        ImageView roue = (ImageView) findViewById(R.id.roueImg);
        roue.startAnimation(animRotate);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        for (int i =0 ; i < 100 ; i++){
            progressBar.incrementProgressBy(1);
        }

        Intent launchActivity = new Intent(LaunchApp.this,MenuActivity.class);
        startActivity(launchActivity);
    }
}
