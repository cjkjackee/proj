package com.test.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test.project.db.AssetsDatabaseManager;
import com.test.project.db.database;
import com.test.project.db.disease;

import java.util.List;

public class search extends AppCompatActivity {

    TextView textView,Search_result;
    Spinner spinner,spinner1,spinner2;
    Button b1_summit,b2_reset,b3_search;


    ArrayAdapter<CharSequence> adapter;
    //  String selectedSys;
    String text,text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetsDatabaseManager.initManager(getApplication());
        setContentView(R.layout.activity_search);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        Search_result = (TextView)findViewById(R.id.textView3);
        b1_summit=(Button)findViewById(R.id.Submit_button);
        b2_reset=(Button)findViewById(R.id.Reset_button);
        b3_search=(Button)findViewById(R.id.Search_button);

        adapter = ArrayAdapter.createFromResource(this,R.array.sysptoms,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        b1_summit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView=(TextView)findViewById(R.id.textView2);
                if (textView.getText().toString().equals(null)) {
                    textView.setText("");
                }
                if(spinner.getSelectedItemPosition()!=0 && textView.getText().toString().contains(text)==false) {
                    textView.append(text);
                    textView.append(",");
                }
                if(spinner1.getSelectedItemPosition()!=0 && textView.getText().toString().contains(text1)==false) {
                    textView.append(text1);
                    textView.append(",");
                }
                if(spinner2.getSelectedItemPosition()!=0 && textView.getText().toString().contains(text2)==false) {
                    textView.append(text2);
                    textView.append(",");
                }

                // selectedSys=text+text1+text2;
                // spinner.setSelection(0);
                // spinner1.setSelection(0);
                // spinner2.setSelection(0);
            }
        });
        b2_reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView=(TextView)findViewById(R.id.textView2);
                textView.setText("");
                Search_result.setText("");
                spinner.setSelection(0);
                spinner1.setSelection(0);
                spinner2.setSelection(0);
            }


        });

        b3_search.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "";
                String line = textView.getText().toString();
                String[] name = line.split(",");
                String cmd = "";
                //Search feunctions
                AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
                database db = new database();
                db.setData(mg.getDatabase("project.db"));


                if (name.length > 0) {
                    cmd = "SELECT * FROM disease WHERE disease.id in (SELECT d_id FROM symptom WHERE symptom.name='" + name[0] + "'";
                    for (int i = 1; i < name.length; ++i) {
                        cmd += " INTERSECT ";
                        cmd += "SELECT d_id FROM symptom WHERE symptom.name='" + name[i] + "'";
                    }
                    cmd += ")";

                    List<disease> D = db.query(cmd);
                    //pseudo ans
                    if (D.size() > 0) {
                        for (disease disease : D) {
                            message += disease.getName() + ", ";
                        }
                    }
                    else{
                        message = "none of the diseases has a symptom like that";
                    }

                    Search_result.setText(message);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please insert a symptom for me", Toast.LENGTH_SHORT).show();
                }
            }


        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();

                text= spinner.getSelectedItem().toString();

                //  textView=(TextView)findViewById(R.id.textView);
                //  textView.append(text);
                // textView.setText(text);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
                text1= spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
                text2= spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
