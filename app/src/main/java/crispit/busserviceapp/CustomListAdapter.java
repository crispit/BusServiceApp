package crispit.busserviceapp;

/**
 * Created by Mikael on 2016-04-20.
 */

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<String> busList;
    private String busId;
    private int row;

    public CustomListAdapter(Activity act, int resource, List<String> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.busList = arrayList;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((busList == null) || ((position + 1) > busList.size()))
            return view;

        busId = busList.get(position);

        holder.bus = (TextView) view.findViewById(R.id.custom_list_text);

        if (holder.bus != null && null != busId
                && busId.trim().length() > 0) {
            holder.bus.setText(Html.fromHtml(busId));
        }
        //here set your color as per position

        if (position%2 == 0) {
            view.setBackgroundResource(R.drawable.list_bg_even);
        } else if (position%2 == 1) {
            view.setBackgroundResource(R.drawable.list_bg_odd);
        }

        return view;
    }

    public class ViewHolder {

        public TextView bus;

    }

}
