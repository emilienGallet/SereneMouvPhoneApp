package zemoov.serenemouv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
        convertView = inflater.inflate(R.layout.row_operateur,null);

        final String curentName = getItem(position);
        Switch nameView = convertView.findViewById(R.id.operateurswitch);
        nameView.setText(curentName);


        return convertView;
    }
}
