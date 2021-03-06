package zemoov.serenemouv;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LaunchApp extends AppCompatActivity {
    Handler handler = new Handler();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_app);

        Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        ImageView roue = (ImageView) findViewById(R.id.roueImg);
        roue.startAnimation(animRotate);

         progressBar = (ProgressBar)findViewById(R.id.progressBar);
     /*   for (int i =0 ; i < 100 ; i++){
            progressBar.incrementProgressBy(1);
        }
*/

        final CustomPopup customPopup = new CustomPopup(this);
        customPopup.setTitle("TEST POPUP");
        customPopup.setSubTitle("test popup ");
        customPopup.getYesButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"OUI",Toast.LENGTH_SHORT).show();
                customPopup.dismiss();
            }
        });
        customPopup.getNoButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();

            }
        });
        customPopup.build();
        Runnable run = new Runnable() {

            int i =0;

            @Override
            public void run() {
                if(i == 100){
                    Intent launchActivity = new Intent(LaunchApp.this,MenuActivity.class);
                    startActivity(launchActivity);
                }else {
                    progressBar.incrementProgressBy(1);

                    i++;
                    handler.postDelayed(this, 100);
                }
            }
        };
        run.run();



    }

}
