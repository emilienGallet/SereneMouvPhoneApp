package zemoov.serenemouv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.util.Log;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import zemoov.serenemouv.GB.Gb;

public class LaunchApp extends AppCompatActivity {
    Handler handler = new Handler();
    ProgressBar progressBar;
    Runnable runnable;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("StartApp","My app start");
        setContentView(R.layout.activity_launch_app);

        Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        ImageView roue = (ImageView) findViewById(R.id.roueImg);
        roue.startAnimation(animRotate);

         progressBar = (ProgressBar)findViewById(R.id.progressBar);
     /*   for (int i =0 ; i < 100 ; i++){
            progressBar.incrementProgressBy(1);
        }
*/
             runnable = new Runnable() {
                @Override
                public void run() {
                    if (!Gb.estConnecter()) {
                        final CustomPopup customPopup = new CustomPopup(activity);
                        customPopup.setTitle("ERREUR ");
                        customPopup.setSubTitle("Nous n'arrivons pas à communiquer physiquement avec la batterie.\n Souhaitez vous réessayer en vérifiant si la prise OBD2 est bien insérée ?\n");
                        customPopup.getYesButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "OUI", Toast.LENGTH_SHORT).show();
                                customPopup.dismiss();
                                run();
                            }
                        });
                        customPopup.getNoButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customPopup.dismiss();
                                finish();
                            }
                        });
                        customPopup.build();
                    }else{
                        //Connection étable (y a le cable de branché)
                        progressBar.incrementProgressBy(30);


                        if(Gb.isRecupData()){
                            Intent launchActivity = new Intent(LaunchApp.this,MenuActivity.class);
                            startActivity(launchActivity);
                        }else{
                            final CustomPopup customPopup = new CustomPopup(activity);
                            customPopup.setTitle("ERREUR ");
                            customPopup.setSubTitle("Echec de la récupération des données de la batterie");
                            customPopup.getYesButton().setVisibility(View.INVISIBLE);
                            customPopup.getNoButton().setVisibility(View.INVISIBLE);

                            customPopup.build();
                            Runnable terminer = new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            };
                            handler.postDelayed(terminer,5000);

                        }

                    }
                }
            };

            runnable.run();



        /*

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


*/
    }

}
