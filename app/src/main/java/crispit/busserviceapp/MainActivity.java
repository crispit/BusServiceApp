package crispit.busserviceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    DBHelper mydb;
    private ArrayList<String> list;
    String busId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void openLivefeed (View V) {
        Intent intent = new Intent(this, ShowingErrorReports.class);
        Bundle bundle = new Bundle();
        bundle.putString("typeOfErrorReports","Livefeed");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void openBusSelector (View V) {

        Intent intent = new Intent(this, BusList.class);
        Bundle bundle = new Bundle();
        bundle.putString("typeOfBusList","BusInfo");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void openHistory (View V) {

        Intent intent = new Intent(this, BusList.class);
        Bundle bundle = new Bundle();
        bundle.putString("typeOfBusList","History");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
