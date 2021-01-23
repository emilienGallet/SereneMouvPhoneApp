package zemoov.serenemouv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class GBE extends AppCompatActivity  { //implements OnMapReadyCallback
    private GoogleMap mMap;
    private View mapView;
    private Button btnFind;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //btnFind = findViewById(R.id.btn_find);

        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        //mapView = mapFragment.getView();
    }
}
