package com.example.fit4lessdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        android.os.Debug.waitForDebugger();

        String actionString = intent.getAction();
        //Toast.makeText(context, actionString, Toast.LENGTH_LONG).show();
//
//        String timeZone = intent.getStringExtra("time-zone");
//        Log.d("TAGGG", "onReceive"+ timeZone);
//

        boolean isOn = intent.getBooleanExtra("state", false);
        Log.d("TAGGG", "AIRPLANE MODE is ON"+ isOn);

    }
}




//
//public class MyReceiver extends BroadcastReceiver {
//    public MyReceiver() {
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_DATE_CHANGED);
//
//        com.example.fit4lessdemo.ui.MyReceiver myReceiver = new com.example.fit4lessdemo.ui.MyReceiver();
//        //registerReceiver(myReceiver, filter);
//
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//    }
//
//}




















//
//package com.example.fit4lessdemo;
//
//        import android.content.Context;
//        import android.content.Intent;
//        import android.content.IntentFilter;
//        import android.widget.Toast;
//
//public class BroadcastReceiver  extends android.content.BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
////        if(Intent.ACTION_DATE_CHANGED.equals(intent.getAction())){
////            //to make sure the receiver was triggered by this action, in case you have many receivers
////            Toast.makeText(context,"DATE CHANGED",Toast.LENGTH_LONG).show();
////            //static receiver and the broad cast has been deprecated since api 24 to prevents so many apps from make traffic on the device
////        }
//
//
////                <receiver android:name=".BroadcastReceiver">
////            <intent-filter>
////                <action android:name="android.intent.action.DATE_CHANGED"/>
////                <action android:name="android.intent.action.TIMEZONE_CHANGED"/>
////                <action android:name="android.intent.action.TIME_CHANGE"/>
////                <action android:name="android.intent.action.TIME_TICK"/>
////            </intent-filter>
////        </receiver>
//
//
//        final String action = intent.getAction();
//        if(Intent.ACTION_DATE_CHANGED.equals(action)||Intent.ACTION_TIME_TICK.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)||Intent.ACTION_TIMEZONE_CHANGED.equals(action)){
//            Toast.makeText(context,"DATE OR TIME CHANGED",Toast.LENGTH_LONG).show();
//        }
//
//    }
////
////    static {
////        IntentFilter s_intentFilter = new IntentFilter();
////        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
////        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
////        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
////        s_intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
////    }
//}
















//
//
//package com.example.fit4lessdemo;
//
//        import android.content.BroadcastReceiver;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.net.ConnectivityManager;
//        import android.net.NetworkInfo;
//        import android.util.Log;
//        import android.widget.Toast;
//
//public class ConnectionReceiver extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d("API123",""+intent.getAction());
//
//        if(intent.getAction().equals("com.sk.broadcastreceiver.SOME_ACTION"))
//            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
//        else {
//            ConnectivityManager cm = (ConnectivityManager)
//                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo activeNetwork = null;
//            if (cm != null) {
//                activeNetwork = cm.getActiveNetworkInfo();
//            }
//            boolean isConnected = activeNetwork != null &&
//                    activeNetwork.isConnectedOrConnecting();
//            if (isConnected) {
//                try {
//                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//}