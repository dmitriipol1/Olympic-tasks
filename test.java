package com.javarush.test.test;

/**
 * Created by crazz on 16.05.2016.
 */
public class test extends Digest {


    @Override
    protected byte[] doDigest(byte[] input) {
        byte[] res = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            res[i] = (byte) (input[i] + 1);
        }
        return res;
    }


}

