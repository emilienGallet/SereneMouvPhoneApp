package zemoov.serenemouv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DestinationNameAdapter extends BaseAdapter {

    private Context context;
    private List<String> nameList;
    private LayoutInflater inflater;

    public DestinationNameAdapter(Context context, List<String> nameList) {
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
        convertView = inflater.inflate(R.layout.adapter_name_destination,null);

        final String curentName = getItem(position);
        TextView nameView = convertView.findViewById(R.id.nameD);
        nameView.setText(curentName);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,curentName,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
