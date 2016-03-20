package com.example.smartescape;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends Activity {
	String str="My.db";
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);
		
		db=openOrCreateDatabase(str, MODE_PRIVATE, null);
		db.execSQL("create table if not exists login(name,phno,eid,gid)");
		
	}

	public void Submit(View v)
	{
		EditText ed1=(EditText)findViewById(R.id.editText1);
		EditText ed2=(EditText)findViewById(R.id.editText2);
		EditText ed3=(EditText)findViewById(R.id.editText3);
		EditText ed4=(EditText)findViewById(R.id.editText4);
		String strun=ed1.getText().toString();
		String strph=ed2.getText().toString();
		String streid=ed3.getText().toString();
		String strgid=ed4.getText().toString();		
		db.execSQL("insert into login(name,phno,eid,gid)values('"+strun+"','"+strph +"','"+streid +"','"+strgid +"')");
		Toast.makeText(getApplicationContext(), "Details are registered", Toast.LENGTH_LONG).show();
			Cursor cr=db.rawQuery("select * from login", null);
		if(cr!=null)
		{
			if(cr.moveToFirst())
			{
				do
				{
					String strun1=cr.getString(cr.getColumnIndex("name"));
					String strph1=cr.getString(cr.getColumnIndex("phno"));
					String streid1=cr.getString(cr.getColumnIndex("eid"));
					String strgid1=cr.getString(cr.getColumnIndex("gid"));	
		
					Toast.makeText(getApplicationContext(), "name:"+strun1+"\nphone no:"+strph1+"\nemail id:"+streid1+"\ngovt id:"+strgid1, Toast.LENGTH_LONG).show();
				}while(cr.moveToNext());
			}
		}
		Intent i=new Intent(getApplicationContext(),MainActivity3.class);
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

}
