package zemoov.serenemouv;

import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatDelegate;

public class BtnJourNuit {
    private ImageButton btnJour;

    public BtnJourNuit (ImageButton btn){
        this.btnJour = btn;
        this.btnJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  int currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
                switch (currentNightMode) {
                    case Configuration.UI_MODE_NIGHT_NO:
                        // Night mode is not active, we're using the light theme
                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                        // Night mode is active, we're using dark theme
                        break;
                }*/

            }
        });

    }

}
