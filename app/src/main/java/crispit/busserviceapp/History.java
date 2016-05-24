package crispit.busserviceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Andreas on 2016-05-20.
 */
public class History extends AppCompatActivity {

    ListView listView ;
    private Button sortButton;
    Button updateButton;
    DBHelper mydb;
    private ArrayList<ErrorReport> errorList;
    String busId;
    ListRowAdapter objAdapter;
    int sortState = 1;

    public void sort(View view) {

        if(sortState == 2) {
            sortByGrade();
            sortByDate();

        }

        else if(sortState == 1){
            sortByDate();
            sortByGrade();
        }

    }

    public void sortByGrade(){
        Collections.sort(errorList, new Comparator<ErrorReport>() {
            @Override
            public int compare(ErrorReport report1, ErrorReport report2) {
                int a = report1.getGrade();
                int b = report2.getGrade();
                if(a>b)
                    return -1;
                else if (a<b)
                    return 1;
                else
                    return 0;
            }
        });
        objAdapter.notifyDataSetChanged();
        sortState=2;
        TextView sortText = (TextView)findViewById(R.id.sortText);
        sortText.setText("Grad ▲");
    }
    public void sortByDate (){

        Collections.sort(errorList, new Comparator<ErrorReport>() {
            @Override
            public int compare(ErrorReport report1, ErrorReport report2) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss", Locale.ENGLISH);
                Date date1=null;
                Date date2=null;
                try {
                    date1 = format.parse(report1.getPubdate());
                    date2 = format.parse(report2.getPubdate());
                }
                catch(ParseException e){

                }

                return (date1.compareTo(date2)) * (-1);

            }
        });
        objAdapter.notifyDataSetChanged();
        sortState=1;
        TextView sortText = (TextView)findViewById(R.id.sortText);
        sortText.setText("Rapportdatum ▲");
    }

    public void updateList(View view){

        busId = getIntent().getStringExtra("busId");
        errorList = mydb.getSolvedBusReports(busId);
        setAdapterToListview();
        sortState = sortState%2 +1;
        sort(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        sortButton = (Button) findViewById(R.id.sortButton);
        updateButton = (Button) findViewById(R.id.updateButton);
        busId = getIntent().getStringExtra("busId");
        //Setting the context for the database to the shared database
        Context sharedContext = null;
        try {
            sharedContext = this.createPackageContext("com.example.fredrikhansson.komigennuraa", Context.CONTEXT_INCLUDE_CODE);
            if (sharedContext == null) {
                return;
            }
        } catch (Exception e) {
            String error = e.getMessage();
            return;
        }

        mydb = new DBHelper(sharedContext);
        listView = (ListView) findViewById(R.id.busList);

        errorList = mydb.getSolvedBusReports(busId);

        setAdapterToListview();


        sortByDate();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),DetailedErrorReport.class);
                Bundle bundle = new Bundle();
                bundle.putString("errorId",errorList.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

    }


    public void setAdapterToListview() {
        objAdapter = new ListRowAdapter(History.this,
                R.layout.row, errorList);
        listView.setAdapter(objAdapter);

    }
}
