package com.test.project;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.project.db.databasehelper;

public class conditions extends AppCompatActivity {

    EditText temperatue_edittext,bphigh_edittext,bplow_edittext,symptoms_edittext,other_edittext;
    TextView date_textview;
    Button b1_save_button,b2_clear_button;
    databasehelper mycalendar;
    boolean data_existed;
    String temp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condittions);

        mycalendar = new databasehelper(this);
        temperatue_edittext = (EditText)findViewById(R.id.temperatue_edittext);
        bphigh_edittext = (EditText)findViewById(R.id.bphigh_edittext);
        bplow_edittext=(EditText)findViewById(R.id.bplow_edittext);
        symptoms_edittext=(EditText)findViewById(R.id.symptoms_edittext);
        other_edittext=(EditText)findViewById(R.id.other_edittext);
        date_textview=(TextView)findViewById(R.id.date_textview2);

        date_textview.setText(getIntent().getStringExtra("date"));

        b1_save_button=(Button)findViewById(R.id.save1_button);
        b2_clear_button=(Button)findViewById(R.id.clear_button);



        Cursor res = mycalendar.checkdate(date_textview.getText().toString());

        if(res.getCount()==0){
         //   showMessage("Empty","New data");
            data_existed=false;
        }
        else{

         //   StringBuffer buffer = new StringBuffer();
            res.moveToNext();
            temperatue_edittext.setText(res.getString(1));
            bphigh_edittext.setText(res.getString(2));
            bplow_edittext.setText(res.getString(3));
            symptoms_edittext.setText(res.getString(4));
            other_edittext.setText(res.getString(5));
            data_existed=true;
        }



        adddata();
        cleardata();
        String[] color = getResources().getStringArray(R.array.sysptoms);
        MultiAutoCompleteTextView autocomplete = (MultiAutoCompleteTextView)findViewById(R.id.symptoms_edittext);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,symptoms);
        autocomplete.setAdapter(adapter);
        autocomplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    };

    public void adddata() {
        b1_save_button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!data_existed) {
                    boolean isInserted = mycalendar.insertdata(date_textview.getText().toString(),
                            temperatue_edittext.getText().toString(),
                            bphigh_edittext.getText().toString(),
                            bplow_edittext.getText().toString(),
                            symptoms_edittext.getText().toString(),
                            other_edittext.getText().toString());
                    if (isInserted)
                        Toast.makeText(conditions.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(conditions.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                   }
                else if(data_existed){
                    boolean isUpdated = mycalendar.updatedata(date_textview.getText().toString(),
                           temperatue_edittext.getText().toString(),
                            bphigh_edittext.getText().toString(),
                            bplow_edittext.getText().toString(),
                            symptoms_edittext.getText().toString(),
                            other_edittext.getText().toString());
                    if (isUpdated)
                        Toast.makeText(conditions.this, "Data Updated", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(conditions.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }

        });

    };

    public void viewdatedata() {
        b2_clear_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mycalendar.checkdate(date_textview.getText().toString());
                if(res.getCount() == 0){

                    showMessage("Error","Nothing found!");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Date : " + res.getString(0)+"\n");
                        buffer.append("Temperature : " + res.getString(1)+"\n");
                        buffer.append("Bphigh : " + res.getString(2)+"\n");
                        buffer.append("Bplow : " + res.getString(3)+"\n");
                        buffer.append("Sysm : " + res.getString(4)+"\n");
                        buffer.append("Other : " + res.getString(5)+"\n\n");

                    }

                    //show all data
                    showMessage("Datadd",buffer.toString());
                }
            }


        });
    };


    public void cleardata() {
        b2_clear_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               temperatue_edittext.setText(null);
               bphigh_edittext.setText(null);
               bplow_edittext.setText(null);
               symptoms_edittext.setText(null);
               other_edittext.setText(null);
            }


        });
    };


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    private static final String[] symptoms = new String[] {
            "呼吸困難","呼吸暫停","呼吸短促","口乾","口渴","口臭","口苦","口腔腫塊","口腔粘膜變白","口腔潰爛"
    };


}
