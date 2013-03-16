package sms.send;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class Test_smsActivity extends Activity implements DialogInterface.OnClickListener{
	
	
	public native String stringFromJNI();
	
    static{
    	System.loadLibrary("szxy-jni");
    }
	  
	private TextView tv;
	private Button shanxunButton;
	private Button wifiButton;
	
	private String aa;
	
	private static final String shanxunNum = "1065930051";
	private static final String wifiNum = "10001";
	private static final String wifiContent = "552";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        shanxunButton = (Button) findViewById(R.id.send);
        wifiButton = (Button) findViewById(R.id.send2);
              
        
        shanxunButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        aa = stringFromJNI();
		        aa = "MM|"+aa;
			    Log.d("shanxun", aa);
				smsSend(shanxunNum,aa);
				new AlertDialog.Builder(Test_smsActivity.this).setMessage("发送成功").setPositiveButton("确定", Test_smsActivity.this).create().show();
			}
		});
        
        wifiButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				smsSend(wifiNum, wifiContent);
				new AlertDialog.Builder(Test_smsActivity.this).setMessage("发送成功").setPositiveButton("确定", Test_smsActivity.this).create().show();
			}
		});
        
    }

    private void smsSend(String number,String content)
    {
    	SmsManager sm = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(Test_smsActivity.this, 0, new Intent(), 0);
		sm.sendTextMessage(number, null, content, sentIntent, null);
    }

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
	}
    


}