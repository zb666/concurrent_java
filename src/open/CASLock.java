package open;

import sun.misc.Unsafe;

public class CASLock {

    private Unsafe mUnsafe = Unsafe.getUnsafe();

    private Object object = new Object();

    public void lock(){
        mUnsafe.compareAndSwapInt(object,10,0,1);
    }

    void test(){

    }
}
