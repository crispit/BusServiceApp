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
        Context sharedContext = null;
        try {
            sharedContext = this.createPackageContext("crispit.errorextractor", Context.CONTEXT_INCLUDE_CODE);
            if (sharedContext == null) {
                return;
            }
        } catch (Exception e) {
            String error = e.getMessage();
            return;
        }

        mydb = new DBHelper(sharedContext);
        detailedList = new ArrayList<>();

        Cursor res = mydb.getData(errorId);
        res.moveToFirst();

        for(int i=0;i<res.getColumnCount();i++){
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
        mydb.updateStatus(errorId, "fixed");
        detailedList.set(6, "Status: fixed");
        objAdapter.notifyDataSetChanged();
    }

    public void unfix(View view){
        mydb.updateStatus(errorId,"completed");
        detailedList.set(6, "Status: completed");
        objAdapter.notifyDataSetChanged();
    }

}
