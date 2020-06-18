package com.liucong.memoryfile;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;
import java.lang.reflect.Method;

public class MyService extends Service {

    private static String TAG = "memoryfile";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MemoryFetchStub();
    }

    class MemoryFetchStub extends com.liucong.memoryfile.IMemoryAidlInterface.Stub {
        @Override
        public ParcelFileDescriptor getParcelFileDescriptor() throws RemoteException {
            MemoryFile memoryFile = null;
            try {
                memoryFile = new MemoryFile("test_memory", 1024);
                //byte[] bytes = new byte[]{'1', '2', '3', '4', '5', 0};
                String string = "12345";
                Log.d(TAG, "getParcelFileDescriptor ---> " + string);
                memoryFile.getOutputStream().write(string.getBytes());
                Method method = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
                FileDescriptor des = (FileDescriptor) method.invoke(memoryFile);
                return ParcelFileDescriptor.dup(des);
            } catch (Exception e) {
            }
            return null;
        }
    }
}
