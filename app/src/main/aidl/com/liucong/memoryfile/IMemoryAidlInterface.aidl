// IMemoryAidlInterface.aidl
package com.liucong.memoryfile;
import android.os.ParcelFileDescriptor;
// Declare any non-default types here with import statements

interface IMemoryAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
    //        double aDouble, String aString);
    ParcelFileDescriptor getParcelFileDescriptor();
}
