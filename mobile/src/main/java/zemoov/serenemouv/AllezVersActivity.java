package zemoov.serenemouv;

import androidx.appcompat.app.AppCompatActivity;
import zemoov.serenemouv.CMTA.Cmta;
import zemoov.serenemouv.CMTA.Operator;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllezVersActivity extends AppCompatActivity {

    final static int PUISSANC_BORNE_MIN_START = 3;
    private EditText destination;
    private ListView destinationList;
    private List<String> destinationName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allez_vers);

        destinationName = new ArrayList<>();
        operateurName = new ArrayList<>();

        for (Operator o : Operator.values()) {
            operateurName.add(o.name());
        }

        destinationList = (ListView) findViewById(R.id.destinationList);


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
                        destinationName = Cmta.getPosibleDestinationName();
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
                switch (checkedId){
                    case R.id.econome:
                        Toast.makeText(context,"econome",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.equilibre:
                        Toast.makeText(context,"equilibre",Toast.LENGTH_SHORT).show();

                        break;
                    case  R.id.rapide:
                        Toast.makeText(context,"rapide",Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        peage = (CheckBox) findViewById(R.id.peage);
        peage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context,isChecked+"",Toast.LENGTH_SHORT).show();

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
        nbPerson.setText(1+"");
        nbPersonSeekBar.setMax(5);
        nbPersonSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nbPerson.setText(progress+"");
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
        poidBagage.setText(0+"");
        poidBagageSeekBar.setMax(1085);
        poidBagageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                poidBagage.setText(progress+"");
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
            }
        });
    }


}

