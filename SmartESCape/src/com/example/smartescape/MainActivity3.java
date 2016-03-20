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
import android.telephony.SmsManager;
import android.view.View.OnClickListener;
import android.app.AlertDialog;




public class MainActivity3 extends Activity implements OnClickListener
{
	
	String str="My1.db";
	SQLiteDatabase db1;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity3);
		//findViewById(R.id.button1).setOnClickListener(this);

		db1=openOrCreateDatabase(str, MODE_PRIVATE, null);
		db1.execSQL("create table if not exists contact1(phno1)");
		Toast.makeText(getApplicationContext(), "-*********-", Toast.LENGTH_LONG).show();
	}
	public void Finish(View v)
	{
		Toast.makeText(getApplicationContext(), "------------", Toast.LENGTH_LONG).show();
	     
		EditText ed1=(EditText)findViewById(R.id.editText1);
	//	EditText ed2=(EditText)findViewById(R.id.editText2);
	//	EditText ed3=(EditText)findViewById(R.id.editText3);
		
		
		String strph1=ed1.getText().toString();
	//	String strph2=ed2.getText().toString();
	//	String strph3=ed3.getText().toString();
	
		db1.execSQL("insert into contact1(phno1)values('"+strph1+"')");
		Toast.makeText(getApplicationContext(), "Details are registered", Toast.LENGTH_LONG).show();
	     
		
		
		
		Cursor cr1=db1.rawQuery("select * from contact1", null);
		
		if(cr1!=null)
		{
			if(cr1.moveToFirst())
			{
				do
				{
					String strphno1=cr1.getString(cr1.getColumnIndex("phno1"));
				    //String strphno2=cr1.getString(cr1.getColumnIndex("phno2"));
					//String strphno3=cr1.getString(cr1.getColumnIndex("phno3"));
					//String strgid1=cr.getString(cr.getColumnIndex("gid"));	
					try
					{
					  	SmsManager.getDefault().sendTextMessage(strphno1, null, "Hello SMS!", null, null);
						Toast.makeText(getApplicationContext(), "msg send successfully", Toast.LENGTH_LONG).show();
					}
					catch (Exception e)
					{
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
						AlertDialog dialog = alertDialogBuilder.create();	
						dialog.setMessage(e.getMessage());
						dialog.show();
					}
					Toast.makeText(getApplicationContext(), "ph_no1:"+strphno1, Toast.LENGTH_LONG).show();
				}while(cr1.moveToNext());

			}
		}
		
			        

		
		
		//Intent i=new Intent(getApplicationContext(),MainActivity3.class);
		//startActivity(i);
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity3, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
