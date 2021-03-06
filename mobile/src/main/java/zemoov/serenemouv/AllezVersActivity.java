package zemoov.serenemouv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllezVersActivity extends AppCompatActivity {

    private EditText destination;
    private ListView destinationList;
    private List<String> destinationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allez_vers);

        destinationName = new ArrayList<>();
        destinationName.add("test 1");
        destinationName.add("test 2");
        destinationName.add("test 3");

        destinationList = (ListView) findViewById(R.id.destinationList);

        destinationList.setAdapter(new DestinationNameAdapter(this,destinationName));


        destination = (EditText) findViewById(R.id.destination);
        destination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        // the user is done typing. return true;
                        Toast.makeText(getApplicationContext(),destination.getText(),Toast.LENGTH_SHORT).show();
                        return  true;
                        // consume.
                        }
                }

                        return false;
                    }
                });

            }


        }

