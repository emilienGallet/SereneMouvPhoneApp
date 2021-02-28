package zemoov.serenemouv;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MenuActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Button btnAllezVers;
    private Button btnHistorique;
    private ImageButton btnJour;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView2);
        mapFragment.getMapAsync(this);


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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}