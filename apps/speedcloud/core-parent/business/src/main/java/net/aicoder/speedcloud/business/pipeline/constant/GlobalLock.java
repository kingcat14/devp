package net.aicoder.speedcloud.business.pipeline.constant;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GlobalLock {

    public static HashMap<Long, Lock> locks = new HashMap<>();

    public static void lock(long lockId) {
        if(!locks.containsKey(lockId)){
            locks.put(lockId, new ReentrantLock());
        }
        locks.get(lockId).lock();
    }


    public static void lockInterruptibly(long lockId) throws InterruptedException {
        if(!locks.containsKey(lockId)){
            locks.put(lockId, new ReentrantLock());
        }
        locks.get(lockId).lockInterruptibly();
    }


    public static boolean tryLock(long lockId) {
        if(!locks.containsKey(lockId)){
            locks.put(lockId, new ReentrantLock());
        }
        return locks.get(lockId).tryLock();
    }


    public static boolean tryLock(long lockId, long time, TimeUnit unit) throws InterruptedException {
        if(!locks.containsKey(lockId)){
            locks.put(lockId, new ReentrantLock());
        }
        return locks.get(lockId).tryLock(time, unit);
    }


    public static void unlock(long lockId) {
        if(locks.containsKey(lockId)){
            Lock lock = locks.get(lockId);
            locks.remove(lockId);
            lock.unlock();
        }
    }

}
