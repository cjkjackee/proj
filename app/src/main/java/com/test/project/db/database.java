package com.test.project.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class database {
    private SQLiteDatabase data;

    public void setData(SQLiteDatabase data){
        this.data = data;
    }

    public List<disease> query(String cmd){
        List<disease> DList = new ArrayList<>();
        Cursor cur = data.rawQuery(cmd,null);

        if (cur.moveToFirst()){
            do{
                disease d = new disease();
                d.setId(Integer.parseInt(cur.getString(0)));
                d.setName(cur.getString(1));

                DList.add(d);
            }while(cur.moveToNext());
        }

        cur.close();

        return DList;
    }
}
