package crispit.busserviceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.MenuInflater;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.content.ComponentName;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    ListView listView ;
    DBHelper mydb;
    private ArrayList<String> list;
    String busId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busId = "Vin_Num_001";

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

        listView = (ListView) findViewById(R.id.busList);

        list=new ArrayList<>();

        list = mydb.getAllBuses();

        setAdapterToListview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),BusInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("busId",list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


    }

    public void setAdapterToListview() {
        CustomListAdapter objAdapter = new CustomListAdapter(MainActivity.this,
                R.layout.custom_list, list);
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
            setAdapterToListview();
            return false;
        }

        // User pressed the search button
        ArrayList<String> temp = new ArrayList<>();
        for (String s : list) {
            if (s.contains(query)) {
                temp.add(s);
            }
        }
        CustomListAdapter objAdapter = new CustomListAdapter(MainActivity.this,
                R.layout.custom_list, temp);
        listView.setAdapter(objAdapter);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        if(newText.equals(""))
            setAdapterToListview();
        return false;
    }
}
