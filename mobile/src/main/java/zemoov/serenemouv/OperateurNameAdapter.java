package zemoov.serenemouv;

import android.content.Context;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zemoov.serenemouv.CMTA.Operator;

public class OperateurNameAdapter extends BaseAdapter {

    private Context context;
    private List<String> nameList;
    private LayoutInflater inflater;


    public OperateurNameAdapter(Context context, List<String> nameList) {
        this.context = context;
        this.nameList = nameList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public String getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.row_operateur, null);

        final String curentName = getItem(position);
        Switch nameView = convertView.findViewById(R.id.operateurswitch);
        nameView.setText(curentName);

        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operator operator=null;
                if (nameView.getText().equals(Operator.Allego.name())) {
                    operator = Operator.Allego;
                } else if (nameView.getText().equals(Operator.Chargemap.name())) {
                    operator = Operator.Chargemap;
                } else if (nameView.getText().equals(Operator.Freshmile.name())) {
                    operator = Operator.Freshmile;
                } else if (nameView.getText().equals(Operator.Ionity.name())) {
                    operator = Operator.Ionity;
                } else if (nameView.getText().equals(Operator.Iviza.name())) {
                    operator = Operator.Iviza;
                } else if (nameView.getText().equals(Operator.KiwhiPass.name())) {
                    operator = Operator.KiwhiPass;
                }else if (nameView.getText().equals(Operator.Total.name())){
                    operator = Operator.Total;
                }else if (nameView.getText().equals(Operator.ZeWatt.name())){
                    operator = Operator.ZeWatt;
                }

                if (nameView.isChecked()) {
                    AllezVersActivity.badges.add(operator);
                }else{
                    AllezVersActivity.badges.remove(operator);
                }
            }
        });


        return convertView;
    }
}
