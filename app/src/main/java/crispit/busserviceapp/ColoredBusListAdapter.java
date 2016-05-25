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

public class ColoredBusListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<String> busList;
    private String busId;
    private int row;
    DBHelper mydb;

    public ColoredBusListAdapter(Activity act, int resource, List<String> arrayList, DBHelper mydb) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.busList = arrayList;
        this.mydb = mydb;

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
        view.setBackgroundResource(R.drawable.list_bg);
        if (mydb.getUnsolvedBusReports(busId).isEmpty()) {
            view.setBackgroundColor(this.getContext().getResources().getColor(R.color.lightgreen2));
        } else {
            view.setBackgroundColor(this.getContext().getResources().getColor(R.color.lightred));
        }

        return view;
    }

    public class ViewHolder {

        public TextView bus;

    }

}
