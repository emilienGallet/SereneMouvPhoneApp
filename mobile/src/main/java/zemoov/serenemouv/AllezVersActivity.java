package zemoov.serenemouv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import zemoov.serenemouv.CMTA.Cmta;
import zemoov.serenemouv.CMTA.Exceptions.CMTAException;
import zemoov.serenemouv.CMTA.Exceptions.CMTAWarning;
import zemoov.serenemouv.CMTA.Localisation;
import zemoov.serenemouv.CMTA.Operator;
import zemoov.serenemouv.CMTA.Preference;
import zemoov.serenemouv.CMTA.Vehicule;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllezVersActivity extends AppCompatActivity implements LocationListener {

    final static int PUISSANC_BORNE_MIN_START = 3;
    private static final int PERMS_CALL_ID = 1234;
    private LocationManager lm;


    private EditText destination;
    private ListView destinationList;
    private List<Localisation> destinationName;
    private Context context = this;

    private ListView listOperateur;
    // private ExpandableListView listOperateur;
    private List<String> operateurName;
    // List<String> expandableListTitle;
    // HashMap<String, List<String>> expandableListDetail;

    private SeekBar puissanceBorneMin;
    private TextView puissance;
    private SeekBar nbPersonSeekBar;
    private TextView nbPerson;
    private SeekBar poidBagageSeekBar;
    private TextView poidBagage;

    private RadioGroup preference;
    private RadioButton econome;
    private CheckBox peage;

    private Button btnValider;

    private Preference pref;
    private Localisation start;
    public static Localisation end;

    public static ArrayList<Operator>  badges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allez_vers);

        badges = new ArrayList<>();
        destinationName = new ArrayList<>();
        operateurName = new ArrayList<>();

        for (Operator o : Operator.values()) {
            operateurName.add(o.name());
        }

        destinationList = (ListView) findViewById(R.id.destinationList);

        destinationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(context,"sdf",Toast.LENGTH_SHORT).show();
            }
        });


        listOperateur = (ListView) findViewById(R.id.listoperateur);
        // expandableListDetail = new HashMap<>();
        // expandableListTitle = new ArrayList<>();
        // expandableListTitle.add("Liste des operateurs");
        // expandableListDetail.put("Liste des operateurs", operateurName);
        //listOperateur.setAdapter(new ExpemdableOperateurNameAdapter(context,expandableListTitle,expandableListDetail));

        listOperateur.setAdapter(new OperateurNameAdapter(context, operateurName));
        destination = (EditText) findViewById(R.id.destination);
        destination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        // the user is done typing. return true;
                        Toast.makeText(getApplicationContext(), destination.getText(), Toast.LENGTH_SHORT).show();
                        destinationName = Cmta.getPosibleDestinationName(destination.getText().toString());
                        //  destinationName.add("test 1");
                        //  destinationName.add("test 2");
                        //  destinationName.add("test 3");
                        destinationList.setAdapter(new DestinationNameAdapter(context, destinationName));

                        return true;
                        // consume.
                    }
                }

                return false;
            }
        });

        preference = (RadioGroup) findViewById(R.id.preference);
        econome = (RadioButton) findViewById(R.id.econome);
        econome.setChecked(true);
        preference.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.econome:
                        pref = Preference.ECO;

                        break;
                    case R.id.equilibre:
                        pref = Preference.NORMAL;

                        break;
                    case R.id.rapide:
                        pref = Preference.FAST;


                        break;
                }
            }
        });

        peage = (CheckBox) findViewById(R.id.peage);
        peage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, isChecked + "", Toast.LENGTH_SHORT).show();

            }
        });

        puissance = (TextView) findViewById(R.id.puissance);
        puissanceBorneMin = (SeekBar) findViewById(R.id.puissance_minimal_borne);
        puissanceBorneMin.setProgress(PUISSANC_BORNE_MIN_START);
        puissance.setText(PUISSANC_BORNE_MIN_START + " Kw");

        puissanceBorneMin.setMax(150);
        puissanceBorneMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                puissance.setText(progress + " Kw");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        nbPerson = (TextView) findViewById(R.id.nbPerson);
        nbPersonSeekBar = (SeekBar) findViewById(R.id.nbPersonSeekBar);
        nbPersonSeekBar.setProgress(1);
        nbPerson.setText(1 + "");
        nbPersonSeekBar.setMax(5);
        nbPersonSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nbPerson.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        poidBagage = (TextView) findViewById(R.id.poidBagage);
        poidBagageSeekBar = (SeekBar) findViewById(R.id.poidBagageSeekBar);
        poidBagageSeekBar.setProgress(0);
        poidBagage.setText(0 + "");
        poidBagageSeekBar.setMax(1085);
        poidBagageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                poidBagage.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnValider = (Button) findViewById(R.id.buttonValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(context,end.getNameLocation(),Toast.LENGTH_SHORT).show();

               try {
                    Cmta.build(nbPersonSeekBar.getProgress(),
                            poidBagageSeekBar.getProgress(),
                            pref,
                            badges,
                            new Vehicule(),
                            43,
                            puissanceBorneMin.getProgress(),
                            start,
                            end,
                            null,
                            false,
                            false,
                            true,
                            peage.isChecked());
                } catch (CMTAException e) {
                    e.printStackTrace();
                } catch (CMTAWarning cmtaWarning) {
                    cmtaWarning.printStackTrace();
                }
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMS_CALL_ID);
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lm != null) {
            lm.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double hauteur = location.getAltitude();
        start = new Localisation("start", latitude, longitude, hauteur);

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

