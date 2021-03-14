package zemoov.serenemouv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zemoov.serenemouv.CMTA.Cmta;
import zemoov.serenemouv.CMTA.Localisation;
import zemoov.serenemouv.CMTA.Preference;
import zemoov.serenemouv.CMTA.Vehicule;

public class DestinationNameAdapter extends BaseAdapter {

    private Context context;
    private List<Localisation> nameList;
    private LayoutInflater inflater;

    public DestinationNameAdapter(Context context, List<Localisation> nameList) {
        this.context = context;
        this.nameList = nameList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Localisation getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_name_destination,null);

        final Localisation curentLocalisation = getItem(position);
        TextView nameView = convertView.findViewById(R.id.nameD);
        nameView.setText(curentLocalisation.getNameLocation());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllezVersActivity.end = curentLocalisation;
                Toast.makeText(context,curentLocalisation.getNameLocation(),Toast.LENGTH_SHORT).show();

            }
        });
        return convertView;
    }
}
