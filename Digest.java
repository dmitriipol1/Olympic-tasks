package com.javarush.test.test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Digest {

    private Map<byte[], byte[]> cache = new HashMap();

    public byte[] digest(byte[] input) {

        byte[] result = cache.get(input);
        System.out.println(Thread.currentThread().getName() + " получил value");
        if (result == null) {
            System.out.println(Thread.currentThread().getName() + " перед synchronized");
            synchronized (cache) {
                result = cache.get(input);
                if (result == null) {
                    result = doDigest(input);
                    System.out.println("Заносим в кэш- " + cache.size() + "   " + Thread.currentThread().getName() + " cache:" + cache.size());
                    cache.put(input, result);
                }
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": " + Arrays.toString(input) + " уже в кэше:" + Arrays.toString(result) + ", длина кэша-" + cache.size());
        }
        return result;

    }

    protected abstract byte[] doDigest(byte[] input);
}
//
//import java.util.HashMap;
//import java.util.Map;
//
//public abstract class Digest {
//    private Map<byte[]> cache = new HashMap();
//
//    public byte[] digest(byte[] input) {
//        byte[] result = cache.get(input);
//        if (result == null) {
//            synchronized (cache) {
//                result = cache.get(input);
//                if (result == null) {
//                    result = doDigest(input);
//                    cache.put(input, result);
//                }
//            }
//        }
//        return result;
//    }
//
//    protected abstract byte[] doDigest(byte[] input);
//}