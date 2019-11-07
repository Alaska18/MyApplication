package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class settings extends Activity {
    BottomNavigationView mbottomNavigationView;
    EditText ipadress ;
    String Ip;
    String name1;
    String name2;
    String name3;
    String name4;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    String edit1text;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("button1text", name1);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edit1text = savedInstanceState.getString("button1Text");
    }

    @Override
    protected void onResume() {
        super.onResume();
        edit1.setText(edit1text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button editButton = (Button) findViewById(R.id.editButton);
        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText3);
        edit4 = (EditText) findViewById(R.id.editText4);
        ipadress = (EditText) findViewById(R.id.IpeditText);
        Ip = ipadress.getText().toString();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent editingbuttons = new Intent(settings.this, MainActivity.class);
                 Ip = ipadress.getText().toString();
                 name1 = edit1.getText().toString();
                 name2 = edit2.getText().toString();
                 name3 = edit3.getText().toString();
                 name4 = edit4.getText().toString();
                 editingbuttons.putExtra("IPADRESS", Ip);
                editingbuttons.putExtra("button1", name1);
                editingbuttons.putExtra("button2", name2);
                editingbuttons.putExtra("button3", name3);
                editingbuttons.putExtra("button4", name4);
                setResult(1, editingbuttons);
                finish();
            }
        });

        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView) ;
        Menu menu = mbottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_settings: {
                        break;
                    }

                    case R.id.navigation_energy:
                    {
                        Intent newIntent = new Intent(settings.this, energy.class);
                        startActivity(newIntent);
                        break;
                    }
                    case R.id.navigation_home:
                    {
                        ipadress = (EditText) findViewById(R.id.IpeditText);
                        Ip = ipadress.getText().toString();

                        Intent newIntent = new Intent(settings.this, MainActivity.class);
                        newIntent.putExtra("IPADDRESS", Ip);
                        startActivity(newIntent);
                        break;
                    }
                }
                return false;
            }
        });


    }


}
