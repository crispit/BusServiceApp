package crispit.busserviceapp;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ListView;

import crispit.busserviceapp.R;

/**
 * Created by manslofas1 on 16-05-11.
 */
public class SearchableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.bus_selector);
       /* TextView txt = (TextView)findViewById(R.id.);
        //ListView txt = (ListView)findViewById(R.id.busList);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            txt.setText("Searching by: "+ query);

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            txt.setText("Suggestion: "+ uri);
        }*/

    }
}