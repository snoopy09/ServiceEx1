package jp.ac.titech.itpro.sdl.serviceex1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private BroadcastReceiver btScanReceiver;
    private IntentFilter btScanFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);
        btScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, "onReceive: " + action);
                if (action == null) return;
                switch (action) {
                    case Intent.ACTION_ANSWER:
                        show_message("Receive!!!");
                        break;
                }
            }
        };
        btScanFilter = new IntentFilter();
        btScanFilter.addAction(Intent.ACTION_ANSWER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        registerReceiver(btScanReceiver, btScanFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        unregisterReceiver(btScanReceiver);
    }

    public void pressTest1(View v) {
        testService1();
    }

    public void pressTest2(View v) {
        testService2();
    }

    public void pressTest3(View v) {
        testService3();
    }

    private void testService1() {
        Log.d(TAG, "testService1 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService1.class);
        intent.putExtra(TestService1.EXTRA_MYARG, "Hello, Service1");
        startService(intent);
    }

    private void testService2() {
        Log.d(TAG, "testService2 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService2.class);
        intent.putExtra(TestService2.EXTRA_MYARG, "Hello, Service2");
        startService(intent);
    }

    private void testService3() {
        Log.d(TAG, "testService3 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService3.class);
        intent.putExtra(TestService3.EXTRA_MYARG, "Hello, Service3");
        startService(intent);
    }

    private void show_message(String msg) {
        Log.d(TAG, "show_message");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
