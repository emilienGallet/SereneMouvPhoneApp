package zemoov.serenemouv;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

public class CustomPopup extends Dialog {



    private String title;
    private String subTitle;
    private TextView titleView,subTitleView;
    private Button yesButton, noButton;

    public CustomPopup (Activity activity){
        super(activity,R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popup_template);
        this.title = "Title";
        this.subTitle = "subtitle";
        this.titleView = findViewById(R.id.title);
        this.subTitleView = findViewById(R.id.subtitle);
        this.yesButton = findViewById(R.id.oui);
        this.noButton = findViewById(R.id.non);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Button getYesButton() {
        return yesButton;
    }

    public Button getNoButton() {
        return noButton;
    }

    public  void  build(){
        show();
        titleView.setText(this.title);
        subTitleView.setText(this.subTitle);
    }

}
