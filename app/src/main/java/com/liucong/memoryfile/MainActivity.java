package com.liucong.memoryfile;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;

public class MainActivity extends Activity {

    private static String TAG = "memoryfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                byte[] content = new byte[5];
                com.liucong.memoryfile.IMemoryAidlInterface iMemoryAidlInterface
                        = com.liucong.memoryfile.IMemoryAidlInterface.Stub.asInterface(service);
                try {
                    ParcelFileDescriptor parcelFileDescriptor = iMemoryAidlInterface.getParcelFileDescriptor();
                    FileDescriptor descriptor = parcelFileDescriptor.getFileDescriptor();
                    FileInputStream fileInputStream = new FileInputStream(descriptor);
                    fileInputStream.read(content);
                    Log.d(TAG, "onServiceConnected ---> content:" + new String(content));
                } catch (Exception e) {
                }
                unbindService(this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Service.BIND_AUTO_CREATE);
    }
}
