package crispit.busserviceapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

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
    String grade;
    private PopupWindow popupMessage;

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;

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

        getList();

        listView = (ListView) findViewById(R.id.detailedErrorReportView);
        setAdapterToListview();
    }

    public void setAdapterToListview() {
        objAdapter = new CustomListAdapter(DetailedErrorReport.this,
                R.layout.custom_list, detailedList);
        listView.setAdapter(objAdapter);
    }

    //Method for selecting a new grade. Opens up a popup-window from which to choose.
    public void changeGrade(View v) {

        try{

            LayoutInflater inflater = (LayoutInflater) DetailedErrorReport.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_choose_grade, (ViewGroup) findViewById(R.id.gradeLayout));
            popupMessage = new PopupWindow(layout, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupMessage.setBackgroundDrawable(new BitmapDrawable());
            popupMessage.setOutsideTouchable(true);
            popupMessage.showAtLocation(v, Gravity.CENTER, 0, -100);

            b1 = (Button) findViewById(R.id.cButton1);
            b2 = (Button) findViewById(R.id.cButton2);
            b3 = (Button) findViewById(R.id.cButton3);
            b4 = (Button) findViewById(R.id.cButton4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//setGrade

    public void gradeSelected(View view){
        switch (view.getId()) {
            case R.id.cButton1:
                grade = "1";
                popupMessage.dismiss();
                break;
            case R.id.cButton2:
                grade = "2";
                popupMessage.dismiss();
                break;
            case R.id.cButton3:
                grade = "3";
                popupMessage.dismiss();
                break;
            case R.id.cButton4:
                grade = "4";
                popupMessage.dismiss();
                break;
        }
        mydb.updateGrade(errorId,grade);
        getList();
        setAdapterToListview();
        objAdapter.notifyDataSetChanged();
    }

    public void fix(View view){
        mydb.updateStatus(errorId, "Löst");
        detailedList.set(6, "Status: Löst");
        objAdapter.notifyDataSetChanged();
    }

    public void unfix(View view){
        Cursor cur = mydb.getData(errorId);
        cur.moveToFirst();

        if (cur.getString(cur.getColumnIndex(mydb.COLUMN_NAME_COMMENT)).equals("Kommentar saknas...")){
            mydb.updateStatus(errorId,"Okommenterad");

        }
        else {
            mydb.updateStatus(errorId,"Kommenterad");
        }

        detailedList.set(6, "Status: Icke löst");
        objAdapter.notifyDataSetChanged();
    }

    public void getList(){
        Cursor res = mydb.getData(errorId);
        res.moveToFirst();

        detailedList.clear();

        for(int i=0;i<res.getColumnCount();i++){
            if (res.getColumnName(i).equals("Status") && !res.getString(i).equals("Löst")){
                detailedList.add(res.getColumnName(i)+ ": " + "Icke löst");
            }
            else {
                detailedList.add(res.getColumnName(i) + ": " + res.getString(i));
            }
        }
    }

}
