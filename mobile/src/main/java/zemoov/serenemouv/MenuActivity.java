package zemoov.serenemouv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {
    private Button btnAllezVers;
    private Button btnHistorique;
    private ImageButton btnJour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAllezVers =(Button) findViewById(R.id.allez_vers);
        btnHistorique = (Button) findViewById(R.id.historique);
        btnJour = (ImageButton) findViewById(R.id.jour);

        btnAllezVers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allezVersActivity = new Intent(MenuActivity.this,AllezVersActivity.class);
                startActivity(allezVersActivity);

            }
        });

        btnHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historiqueActivity = new Intent(MenuActivity.this,HistoriqueActivity.class);
                startActivity(historiqueActivity);
            }
        });

        btnJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}