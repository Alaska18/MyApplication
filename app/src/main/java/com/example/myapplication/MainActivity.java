package com.example.myapplication;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends Activity implements View.OnClickListener {

    private ToggleButton button_light1, button_light2, button_light3, button_fan;
    String IP = "http://192.168.1.187/";
    int buttonNum;
    String button1;
    String button2;
    String button3;
    String button4;
    public int itemid;
    FirebaseDatabase energydata;
    DatabaseReference ref;
    String Text1;


    BottomNavigationView bottomNavigationView;
    public interface statusofthedata
    {
        public void  datauploaded(querysnapshot Querysnapshot, List<String> keys);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home: {
                        break;
                    }

                    case R.id.navigation_energy: {
                        Intent newintent = new Intent(MainActivity.this, energy.class);
                        startActivity(newintent);
                        break;
                    }
                    case R.id.navigation_settings: {
                        Intent newintent = new Intent(MainActivity.this, settings.class);
                        startActivityForResult(newintent, 3);
                        break;
                    }
                }
                return false;
            }
        });

        addClick();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3)
        {
            if(resultCode == 1)
            {
                Intent intent = data;
                IP = intent.getStringExtra("IPADDRESS");
                Log.d("LOGTAG", "Mainactivity" + IP);
                button1 = intent.getStringExtra("button1");
                button2 = intent.getStringExtra("button2");
                button3 = intent.getStringExtra("button3");
                button4 = intent.getStringExtra("button4");
                TextView text1 = (TextView) findViewById(R.id.Text1);
                text1.setText(button1);
                TextView text2 = (TextView) findViewById(R.id.Text2);
                text2.setText(button2);
                TextView text3 = (TextView) findViewById(R.id.Text3);
                text3.setText(button3);
                TextView text4 = (TextView) findViewById(R.id.Text4);
                text4.setText(button4);


            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("button1Text", button1);
        outState.putString("button2Text", button2);
        outState.putString("button3Text", button3);
        outState.putString("button4Text", button4);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Text1 = savedInstanceState.getString("button1Text");
    }

    public void addClick() {

        button_light1 = (ToggleButton) findViewById(R.id.toggleButton1);
        System.out.println("Reached here3");
        button_light2 = (ToggleButton) findViewById(R.id.toggleButton2);
        System.out.println("Reached here4");
        button_fan = (ToggleButton) findViewById(R.id.toggleButton3);
        System.out.println("Reached here5");
        button_light3 = (ToggleButton) findViewById(R.id.toggleButton4);

        button_light1.setOnClickListener(this);
        System.out.println("Reached here6");
        button_light2.setOnClickListener(this);
        System.out.println("Reached here7");
        button_light3.setOnClickListener(this);
        System.out.println("Reached here8");
        button_fan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        buttonNum = view.getId();
        new Task().execute(IP);
    }

    public class Task extends AsyncTask<String, String, String> {

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {
            {

                //  while (ipAddress.getText().toString().equals((""))) {

                //    System.out.println("Reached here10");

                //  Toast.makeText(MainActivity.this, "Please enter the ip address...", Toast.LENGTH_SHORT).show();
                //  }

                String url;
                switch (buttonNum) {

                    case R.id.toggleButton1:

                        System.out.println("Reached here11");
                        if (button_light1.isChecked()) {
                            url = IP + "r1on";

                            try {
                                URL K = new URL(url);
                                //System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println(K);
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                //System.out.println(C);
                                C.setRequestMethod("GET");
                                //System.out.println("Reached here20");
                                C.setReadTimeout(1000);
                                //System.out.println("Reached here21");
                                C.setConnectTimeout(1500);
                                //System.out.println("Reached here22");
                                C.connect();
                                System.out.println("reached112");
                                int responseCode = C.getResponseCode();
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                                if(C.getResponseCode() == 200 && button_light1.isChecked())
                                {

                                    button_light1.setBackground(getResources().getDrawable(R.color.red));
                                }
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally1");
                            }

                        } else {
                            url = IP + "r1off";

                            try {
                                URL K = new URL(url);
                                System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println("Reached here13");
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                System.out.println("Reached here14");
                                C.setRequestMethod("GET");
                                int responseCode = C.getResponseCode();
                                if(C.getResponseCode() == 200 && !(button_light1.isChecked()))
                                {

                                    button_light1.setBackground(getResources().getDrawable(R.color.colorAccent));
                                }
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally2");
                            }
                        }
                        break;

                    case R.id.toggleButton2:


                        System.out.println("Reached here11");
                        if (button_light2.isChecked()) {
                            url = IP + "r2on";

                            try {
                                URL K = new URL(url);
                                //System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println(K);
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                //System.out.println(C);
                                C.setRequestMethod("GET");
                                //System.out.println("Reached here20");
                                C.setReadTimeout(1000);
                                //System.out.println("Reached here21");
                                C.setConnectTimeout(1500);
                                //System.out.println("Reached here22");
                                C.connect();
                                if(C.getResponseCode() == 200 && (button_light2.isChecked()))
                                {

                                    button_light2.setBackground(getResources().getDrawable(R.color.red));
                                }
                                System.out.println("reached112");
                                int responseCode = C.getResponseCode();
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally1");
                            }
                        } else {
                            url = IP + "r2off";

                            try {
                                URL K = new URL(url);
                                System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println("Reached here13");
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                System.out.println("Reached here14");
                                C.setRequestMethod("GET");
                                int responseCode = C.getResponseCode();
                                if(C.getResponseCode() == 200 && !(button_light2.isChecked()))
                                {

                                    button_light2.setBackground(getResources().getDrawable(R.color.colorAccent));
                                }
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally2");
                            }
                        }
                        break;
                    //Send a string to nodemcu as a command


                    case R.id.toggleButton3:

                        System.out.println("Reached here11");
                        if (button_light3.isChecked()) {
                            url = IP + "r3on";

                            try {
                                URL K = new URL(url);
                                //System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println(K);
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                //System.out.println(C);
                                C.setRequestMethod("GET");
                                //System.out.println("Reached here20");
                                C.setReadTimeout(1000);
                                //System.out.println("Reached here21");
                                C.setConnectTimeout(1500);
                                //System.out.println("Reached here22");
                                C.connect();
                                if(C.getResponseCode() == 200 && button_light3.isChecked())
                                {

                                    button_light3.setBackgroundColor(getResources().getColor(R.color.red));
                                }
                                System.out.println("reached112");
                                int responseCode = C.getResponseCode();
                                System.out.println(responseCode);
                                System.out.println("Reached here99");

                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally1");
                            }
                        } else {
                            url = IP + "r3off";

                            try {
                                URL K = new URL(url);
                                System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println("Reached here13");
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                System.out.println("Reached here14");
                                C.setRequestMethod("GET");
                                int responseCode = C.getResponseCode();
                                if(C.getResponseCode() == 200 && !(button_light3.isChecked()))
                                {

                                    button_light3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                }
                                System.out.println(responseCode);
                                System.out.println("Reached here99");

                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally2");
                            }
                        }
                        break;

                    case R.id.toggleButton4:

                        System.out.println("Reached here11");
                        if (button_fan.isChecked()) {
                            url = IP + "r4on";

                            try {
                                URL K = new URL(url);
                                //System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println(K);
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                //System.out.println(C);
                                C.setRequestMethod("GET");
                                //System.out.println("Reached here20");
                                C.setReadTimeout(1000);
                                //System.out.println("Reached here21");
                                C.setConnectTimeout(1500);
                                //System.out.println("Reached here22");
                                C.connect();
                                if(C.getResponseCode() == 200 && button_fan.isChecked())
                                {

                                    button_fan.setBackgroundColor(getResources().getColor(R.color.red));
                                }
                                System.out.println("reached112");
                                int responseCode = C.getResponseCode();
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally1");
                            }
                        } else {
                            url = IP + "r4off";

                            try {
                                URL K = new URL(url);
                                System.out.println("Reached here12");
                                URLConnection urlConnection = K.openConnection();
                                System.out.println("Reached here13");
                                HttpURLConnection C = null;
                                C = (HttpURLConnection) urlConnection;
                                System.out.println("Reached here14");
                                C.setRequestMethod("GET");
                                int responseCode = C.getResponseCode();
                                if(C.getResponseCode() == 200 && !(button_fan.isChecked()))
                                {

                                    button_fan.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                }
                                System.out.println(responseCode);
                                System.out.println("Reached here99");
                            } catch (IOException e) {
                                System.out.println("Reached here111111");
                                e.printStackTrace();
                            }
                            finally {
                                System.out.println("Finally2");
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("ReachedPOSTEXE");
        }
    }
}