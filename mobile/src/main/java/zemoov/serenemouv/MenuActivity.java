package zemoov.serenemouv;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MenuActivity extends AppCompatActivity implements LocationListener {
    private  static  final int PERMS_CALL_ID = 1234;
    private Button btnAllezVers;
    private Button btnHistorique;
    private ImageButton btnJour;
    private GoogleMap mMap;
    private LocationManager lm;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView2);


        btnAllezVers = (Button) findViewById(R.id.allez_vers);
        btnHistorique = (Button) findViewById(R.id.historique);
        btnJour = (ImageButton) findViewById(R.id.jour);

        btnAllezVers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allezVersActivity = new Intent(MenuActivity.this, AllezVersActivity.class);
                startActivity(allezVersActivity);

            }
        });

        btnHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historiqueActivity = new Intent(MenuActivity.this, HistoriqueActivity.class);
                startActivity(historiqueActivity);
            }
        });

        btnJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreate();
           //     setTheme(darkTheme ? R.style.AppThemeDark : R.style.AppThemeLight);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION},PERMS_CALL_ID);
            return;
        }

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        }
        loadMap();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(lm != null){
            lm.removeUpdates(this);
        }
    }

    private void loadMap(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MenuActivity.this.mMap = googleMap;
               // mMap.moveCamera(CameraUpdateFactory.zoomBy(5));
                mMap.setMinZoomPreference(20);
                mMap.setMaxZoomPreference(7);
                mMap.setMyLocationEnabled(true);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Toast.makeText(this,"lat "+latitude+"  long "+longitude,Toast.LENGTH_LONG);
        if(mMap != null){

            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
            mMap.clear();

            mMap.addCircle(new CircleOptions().center(new LatLng(latitude,longitude))
                    .radius(100000).fillColor(Color.argb(50,0,50,0)).strokeColor(Color.BLUE).strokeWidth(2));

        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}