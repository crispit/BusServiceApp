package crispit.busserviceapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mikael on 2016-04-18.
 */
public class DetailedErrorReport extends AppCompatActivity {

    ArrayList<String> detailedList;
    ListView listView;
    DBHelper mydb;
    String errorId;
    CustomListAdapter objAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailederrorreport);

        errorId = getIntent().getStringExtra("errorId");

        //Setting the context for the database to the shared database
        Context sharedContext;
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
        detailedList = new ArrayList<>();

        Cursor res = mydb.getData(errorId);
        res.moveToFirst();

        for(int i=0;i<res.getColumnCount();i++){
            if (res.getColumnName(i).equals("Status") && !res.getString(i).equals("Löst")){
                detailedList.add(res.getColumnName(i)+ ": " + "Icke löst");
            }
            detailedList.add(res.getColumnName(i)+ ": " + res.getString(i));
        }

        listView = (ListView) findViewById(R.id.detailedErrorReportView);
        setAdapterToListview();
    }

    public void setAdapterToListview() {
        objAdapter = new CustomListAdapter(DetailedErrorReport.this,
                R.layout.custom_list, detailedList);
        listView.setAdapter(objAdapter);
    }

    public void fix(View view){
        mydb.updateStatus(errorId, "Löst");
        detailedList.set(6, "Status: Löst");
        objAdapter.notifyDataSetChanged();
    }

    public void unfix(View view){
        mydb.updateStatus(errorId,"Kommenterad");
        detailedList.set(6, "Status: Icke löst");
        objAdapter.notifyDataSetChanged();
    }

}
