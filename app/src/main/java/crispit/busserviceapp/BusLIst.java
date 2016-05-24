package crispit.busserviceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Andreas on 2016-05-20.
 */
public class BusList extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    ListView listView ;
    DBHelper mydb;
    private ArrayList<String> list;
    String busId;
    String typeOfBusList;
    ArrayAdapter objAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_selector);


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

        typeOfBusList = getIntent().getStringExtra("typeOfBusList");
        setTitle();
        mydb = new DBHelper(sharedContext);
        listView = (ListView) findViewById(R.id.busList);
        list = new ArrayList<>();
        list = mydb.getAllBuses();

        setAdapterToListView(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ShowingErrorReports.class);
                Bundle bundle = new Bundle();
                bundle.putString("busId", list.get(position));
                putTypeOfErrorReportInBundle(bundle);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }

        });

    }

    public void putTypeOfErrorReportInBundle(Bundle bundle){
        if (typeOfBusList.equals("History")){
            bundle.putString("typeOfErrorReports","History");
        }

        else if (typeOfBusList.equals("BusInfo")){
            bundle.putString("typeOfErrorReports","BusInfo");
        }


    }

    public void setTitle(){

        if (typeOfBusList.equals("BusInfo")){
            setTitle(R.string.busSelector);
        }

        else if (typeOfBusList.equals("History")){
            setTitle(R.string.BusHistoryList);
        }


    }

    public void setAdapterToListView(ArrayList<String> list) {

        if (typeOfBusList.equals("BusInfo")){
            objAdapter = new ColoredBusListAdapter(BusList.this,
                    R.layout.custom_list, list, mydb);
        }
        else {
            objAdapter = new CustomListAdapter(BusList.this,
                    R.layout.custom_list, list);
        }
        listView.setAdapter(objAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Sök buss");
        searchView.setOnQueryTextListener(this);


        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if(query==null){
            setAdapterToListView(list);
            return false;
        }

        // User pressed the search button
        ArrayList<String> temp = new ArrayList<>();
        for (String s : list) {
            if (s.contains(query)) {
                temp.add(s);
            }
        }

        setAdapterToListView(temp);
        listView.setAdapter(objAdapter);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        if(newText.equals(""))
            setAdapterToListView(list);
        return false;
    }

    //Method for updating the reports list after a change
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {

            list = mydb.getAllBuses(); // Adds all reports in the list
            setAdapterToListView(list);
        }
    }//onActivityResult

}
