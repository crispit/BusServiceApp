package crispit.busserviceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fredrikhansson on 23/05/16.
 */
public class BusHistoryList extends AppCompatActivity{



    ListView listView ;
    DBHelper mydb;
    private ArrayList<String> list;
    String busId;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_history_list);

        //busId = "Vin_Num_001";

        //Setting the context for the database to the shared database
        Context sharedContext = null;
        try {
            sharedContext = this.createPackageContext("com.example.fredrikhansson.komigennuraa", Context.CONTEXT_INCLUDE_CODE);
            if (sharedContext == null) {
                return;
            }
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.print(error);
            return;
        }

        mydb = new DBHelper(sharedContext);

        listView = (ListView) findViewById(R.id.busList);

        list = new ArrayList<>();

        list = mydb.getAllBuses();

        setAdapterToListview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ShowingErrorReports.class);
                Bundle bundle = new Bundle();
                bundle.putString("typeOfErrorReports","History");
                bundle.putString("busId", list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

    }

    public void setAdapterToListview() {
        CustomListAdapter objAdapter = new CustomListAdapter(BusHistoryList.this,
                R.layout.custom_list, list);
        listView.setAdapter(objAdapter);
    }

}
