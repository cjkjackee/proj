package com.test.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class condittions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condittions);

        String[] color = getResources().getStringArray(R.array.sysptoms);

        MultiAutoCompleteTextView autocomplete = (MultiAutoCompleteTextView)findViewById(R.id.symptoms_edittext);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,symptoms);
        autocomplete.setAdapter(adapter);
        autocomplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private static final String[] symptoms = new String[] {
            "呼吸困難","呼吸暫停","呼吸短促","口乾","口渴","口臭","口苦","口腔腫塊","口腔粘膜變白","口腔潰爛"
    };

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };


}
