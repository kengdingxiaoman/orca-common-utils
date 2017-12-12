package com.orca.common.utils.improve;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 随机值生成工具
 * 生成随机值
 * created by yangyebo 2017-12-11 上午10:45
 */
public class RandomGenerator{

    /**
     * 生成随机的在给定范围内的整数
     * @param begin
     * @param endNoInclude
     * @return
     */
    public static int generateNumberInRange(int begin, int endNoInclude) {
        if(endNoInclude <= begin) {
            return begin;
        }
        /**
         * new Random().nextInt()假设
         * new Random().nextInt(a)，那么返回的值是 0 - a 之间的随机数，但不包含 a
         */
        return new Random().nextInt((endNoInclude - begin)) + begin;
    }

    /**
     * 生成随机n位含大字母和数字的字串
     *
     * @return
     */
    public static String genRandomAlphaNumerical(int requiredLength) {
        if (requiredLength <= 0) {
            return StringUtils.EMPTY;
        }
        StringBuilder ret = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < requiredLength; i++) {
            boolean isNum = random.nextInt(2) % 2 == 0;
            if (isNum) {
                ret.append(String.valueOf(random.nextInt(10)));
            } else {
                ret.append((char) (65 + random.nextInt(26)));
            }
        }
        return ret.toString();
    }

    /**
     * 生成n位随机数
     *
     * @param digitLength
     * @return
     */
    public static String generateRandomNumber(int digitLength) {
        Random random = new Random();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < digitLength; i++) {
            ret.append(random.nextInt(10));
        }
        return ret.toString();
    }
}
