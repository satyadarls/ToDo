package com.littleturn.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.littleturn.todo.ReminderAlarm;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView l1;
	int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;
    Calendar c;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	boolean dateFlag=false;
	boolean timeFlag=false;
	int notificationCount;
	String time,contentTitle;
	Context mContext;
	EditText e;    
	Button dt,tm,dialogButton;   
    List<RowItem> rowItems;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        rowItems = new ArrayList<RowItem>();
        l1=(ListView)findViewById(R.id.list);
        adapter = new CustomAdapter(this, rowItems);
        l1.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	
    	switch (item.getItemId()) {
		case R.id.contactsicon:
			
			final AlertDialog dialogDetails;
			LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
			View dialogview = inflater.inflate(R.layout.customdialog_layout, null);
			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
			dialogbuilder.setTitle("Add New Task");
			dialogbuilder.setView(dialogview);
			dialogDetails = dialogbuilder.create();
			dialogDetails.show();
			TextView t = (TextView)dialogview.findViewById(R.id.textView1);
			t.setText("Task :");
			e =(EditText) dialogview.findViewById(R.id.dialogTaskText); 
			dt=(Button)dialogview.findViewById(R.id.datebutton);
			tm=(Button)dialogview.findViewById(R.id.timebutton);
			dialogButton = (Button) dialogview.findViewById(R.id.dialogButton);
			TextListener textListener=new TextListener();
			dt.setEnabled(false);
			tm.setEnabled(false);
			dialogButton.setEnabled(false);
			e.addTextChangedListener(textListener);
			tm.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						showDialog(TIME_DIALOG_ID);
				}
			});
			dt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						showDialog(DATE_DIALOG_ID);
				}
			});
			dialogButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				if(dateFlag==true && timeFlag==true){
					notificationCount  = notificationCount+1;
				      dateFlag = false;
				      timeFlag = false;
				      time = mYear+"-"+mMonth+"-"+mDay+" "+mHour+"-"+mMinute;                                
				            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm");  
				            Date date = null;
				            try {
				            			date = df.parse(time);
				            	} catch (ParseException e) {
				            		// TODO Auto-generated catch block
				            		e.printStackTrace();
				            	}                                        
				            long when = date.getTime();                
				            contentTitle = e.getText().toString();         
				         AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
				         Intent notificationIntent = new Intent(mContext, ReminderAlarm.class);
				         notificationIntent.putExtra("Name",contentTitle ); 
				         notificationIntent.putExtra("NotifyCount",notificationCount );
				         PendingIntent pi = PendingIntent.getBroadcast(mContext, notificationCount, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				         mgr.set(AlarmManager.RTC_WAKEUP,when, pi);
				         Toast.makeText(mContext, "A NEW TASK ADDED", Toast.LENGTH_LONG).show();
				         
				         
					RowItem item = new RowItem(R.drawable.ic_launcher, e.getText().toString(),""+mHour+":"+mMinute);
		            rowItems.add(item);
		            adapter.notifyDataSetChanged();
					dialogDetails.dismiss();
				}
				else if (dateFlag==false) {
					Toast.makeText(getApplicationContext(), "Please set Date!!", Toast.LENGTH_SHORT).show();
				}
				else if (timeFlag==false) {
					Toast.makeText(getApplicationContext(), "Please set Time!!", Toast.LENGTH_SHORT).show();
				}
				}
			});  
			
			break;

		default:
			break;
		}
    	
    	return super.onOptionsItemSelected(item);
    }
    public class TextListener implements TextWatcher{

    	   @Override
    	  public void afterTextChanged(Editable s) {
    	   // TODO Auto-generated method stub   
    	  }

    	   @Override
    	  public void beforeTextChanged(CharSequence s, int start, int count,
    	    int after) {
    	   // TODO Auto-generated method stub   
    	  }

    	   @Override
    	  public void onTextChanged(CharSequence s, int start, int before,
    	    int count) {
    	   // TODO Auto-generated method stub
    	   if(e.getText().length()==0){
    	    dt.setEnabled(false);
    	    tm.setEnabled(false);
    	    dialogButton.setEnabled(false);
    	   }
    	   else if(e.getText().length()>0){
    	    dt.setEnabled(true);
    	    tm.setEnabled(true);
    	    dialogButton.setEnabled(true);
    	   }
    	  }     
    }
  
    @Override
    protected Dialog onCreateDialog(int id) {
     // TODO Auto-generated method stub
     switch (id) {
      case TIME_DIALOG_ID:
         return new TimePickerDialog(this,
              mTimeSetListener, mHour, mMinute, false);
      case DATE_DIALOG_ID:
          return new DatePickerDialog(this,
                      mDateSetListener,
                      mYear, mMonth, mDay);
      }
     return super.onCreateDialog(id);
    }
    
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear+1;
                mDay = dayOfMonth;                
                dateFlag = true;
            }
    };
        
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                timeFlag = true;
            }
    };
     
    
}
