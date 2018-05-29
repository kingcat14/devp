package com.yunkang.saas.common.util;
/*
*  id 生成器
*  调用方法：long id = IDCreater.instance().gen();
*
 */
public class IDCreater {

    private static long workerId;
    private static long datacenterId;
    private static long sequence = 0L;
    private static long twepoch = 1288834974657L; //初始值
    private static long workerIdBits = 5L;
    private static long datacenterIdBits = 5L;
    private static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static long sequenceBits = 12L;
    private static long workerIdShift = sequenceBits;
    private static long datacenterIdShift = sequenceBits + workerIdBits;
    private static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    private static long lastTimestamp = -1L;
    
    private static class IDCreaterHolder {
        private static final IDCreater instance = new IDCreater();
    }
    public static IDCreater instance(){
        return IDCreaterHolder.instance;
    }
    public IDCreater() {
//    	PropertiesUtil.get("comming", "workerId")
        //TODO: 读配置文件获得数据中心id，及节点id用来构造id
        this(1L, 2L);
    }

    /*
     * id生成器构造函数，需要传入中心id， 节点id
     * @param workId: 节点id 值只能由1-31
     * @param datacenterId: 中心id 值只能由1-31
     */
    public IDCreater(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        IDCreater.workerId = workerId;
        IDCreater.datacenterId = datacenterId;
    }
    /**
     * 生成主键ID方法
     * @return 返回id
     */
    public synchronized static long gen() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp != timestamp) {
            sequence = 0L;
        } else {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        lastTimestamp = timestamp;
         return ((timestamp - twepoch) << timestampLeftShift) 
        	| (datacenterId << datacenterIdShift) 
        	| (workerId << workerIdShift) 
        	| sequence;
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
    
/*	public static void main(String[] args) {
		Map<Long, String> map = new HashMap<Long, String>();
		for (int i = 0; i < 1000000; i++) {
			long order = IDCreater.nextId();
			if (map.get(order) != null) {
				System.out.println("repeat: " + order);
			} else {
				System.out.println(order);
				map.put(order, "");
			}
		}
		System.out.println("finish！");
	}*/
}