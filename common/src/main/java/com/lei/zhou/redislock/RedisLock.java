package com.lei.zhou.redislock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * redis加锁解锁工具类
 */
public class RedisLock {
    private static final String NX="NX";
    private static final String PX="PX";
    private static final Long SUCCESS=1L;
    /**
     * 获取redis锁
     * @param jedis redis客户端
     * @param lockKey 锁定的key
     * @param requestId 请求标识,作用标识是谁加的锁要求是谁解锁
     * @param expireTime 过期时间
     * @return
     */
    public static boolean lock(Jedis jedis,String lockKey,String requestId,int expireTime){
        /**
         * NX:表示key不存在进行set，如果存在不做任何操作
         * PX:给加锁的key加一个过期时间
         */
        String result = jedis.set(lockKey,requestId,NX,PX,expireTime);
        if ("OK".equals(result)){
            return true;
        }
        return false;
    }

    /**
     * 解锁
     * @param jedis redis客户端
     * @param lockKey 锁定的key
     * @param requestId 请求标识,作用标识是谁加的锁要求是谁解锁
     * @return
     */
    public static boolean unlock(Jedis jedis,String lockKey,String requestId){
        /**
         * lua脚本
         * KEYS[1]:传入lockKey
         * ARGV[1]:传入requestId
         * 首先get传入的lockKey的value，是否和传入的requestId相同，如果相同删除lockKey
         */
        String script="if redis.call('get',KEYS[1]) == ARGV[1] " +
                      "then return redis.call('del',KEYS[1]) " +
                      "else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(requestId));

        if (SUCCESS.equals(result)){
            return true;
        }
        return false;
    }
}
