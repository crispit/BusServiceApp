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
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomDetailedListAdapter extends ArrayAdapter<HashMap<String,String>> {

    private Activity activity;
    private List<HashMap<String,String>> dataList;
    private String errorData;
    private String errorDescription;
    private int row;

    public CustomDetailedListAdapter(Activity act, int resource, List<HashMap<String,String>> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.dataList = arrayList;

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

        if ((dataList == null) || ((position + 1) > dataList.size()))
            return view;

        errorData = dataList.get(position).get("data");
        errorDescription = dataList.get(position).get("description");

        holder.description = (TextView) view.findViewById(R.id.custom_list_description);
        holder.data = (TextView) view.findViewById(R.id.custom_list_data);
        //holder.gradeButton = (Button)view.findViewById(R.id.changeGradeButton);


        if (holder.description != null && null != errorDescription
                && errorDescription.trim().length() > 0) {
            holder.description.setText(Html.fromHtml(errorDescription));
        }
        if (holder.data != null && null != errorData
                && errorData.trim().length() > 0) {
            holder.data.setText(Html.fromHtml(errorData));
        }
        //here set your color as per position

        //holder.gradeButton = new Button(this.getContext());

        /*if(!holder.description.getText().equals("Gradering:")){

            holder.gradeButton = (Button)view.findViewById(R.id.changeGradeButton);
            //holder.gradeButton.setText("Ändra!");
            //holder.gradeButton.setBackgroundColor(android.R.color.black);

        }*/


        if (position%2 == 0) {
            view.setBackgroundResource(R.drawable.list_bg_even);
        } else if (position%2 == 1) {
            view.setBackgroundResource(R.drawable.list_bg_odd);
        }

        return view;
    }

    public class ViewHolder {

        public TextView data;
        public TextView description;
        public Button gradeButton;

    }

}
