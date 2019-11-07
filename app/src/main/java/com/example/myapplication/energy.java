package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class energy extends Activity {
    BottomNavigationView bottomNavigationView;
    FirebaseDatabase energydata = FirebaseDatabase.getInstance();
    DatabaseReference ref = energydata.getReference();
    String[] power = new String[6];
    Integer[] days = new Integer[6];
    ArrayList<querysnapshot> list = new ArrayList<>();
    int i = 0;
    int d = 0;
    TextView textone;
    TextView texttwo;
    LineGraphSeries<DataPoint> series;
    querysnapshot snap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        drawGraph();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home: {
                        Intent newIntent = new Intent(energy.this, MainActivity.class);
                        startActivity(newIntent);
                        break;
                    }

                    case R.id.navigation_energy: {
                        break;
                    }
                    case R.id.navigation_settings: {
                        Intent newintent1 = new Intent(energy.this, settings.class);
                        startActivity(newintent1);
                        break;
                    }
                }
                return false;
            }
        });


        textone = (TextView) findViewById(R.id.textView1);
        texttwo = (TextView) findViewById(R.id.textView2);
        textone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawGraph();
            }
        });
        texttwo = (TextView) findViewById(R.id.textView2);
        texttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawGraph();
            }
        });

    }
        public void drawGraph () {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        snap = keynode.getValue(querysnapshot.class);
                        Log.d("logtag", snap.getcomponent() + "energy");
                        days[i] = Integer.valueOf(d + 1);
                        power[i] = String.valueOf(snap.getpower());
                        list.add(snap);
                        Log.d("logtag", list.get(i).getcomponent());
                        i++;
                        d++;

                    }
                    Log.d("LOGATAG", "afterthequery" + list.get(1).getcomponent());
                    GraphView energyvsday = (GraphView) findViewById(R.id.graphview);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                            new DataPoint(days[0], list.get(0).getCurrent()),
                            new DataPoint(days[1], list.get(1).getCurrent()),
                            new DataPoint(days[2], list.get(2).getCurrent()),
                            new DataPoint(days[3], list.get(3).getCurrent()),
                            new DataPoint(days[4], list.get(4).getCurrent()),
                    });
                    energyvsday.addSeries(series);
                    energyvsday.getViewport().setMinX(0.000);
                    energyvsday.getViewport().setMaxX(15.000);
                    energyvsday.getViewport().setMinY(0.000);
                    energyvsday.getViewport().setMaxY(15.000);
                    energyvsday.setTitle("energy consumed daily");
                    energyvsday.getViewport().setYAxisBoundsManual(true);
                    energyvsday.getViewport().setYAxisBoundsManual(true);
                    //*energyvsday.getViewport().setScalableY(true);
                    energyvsday.getViewport().setScalable(true);
                    energyvsday.getViewport().setScrollable(true);
                    energyvsday.getGridLabelRenderer().setHorizontalAxisTitle("Days");
                    energyvsday.getGridLabelRenderer().setVerticalAxisTitle("Current");


                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

}
