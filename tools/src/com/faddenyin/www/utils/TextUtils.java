package com.faddenyin.www.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextUtils {

    public static boolean isEmpty(String text) {
        if (text == null)
            return true;

        if (text.trim().length() < 1)
            return true;

        return false;
    }
    public static String md5(String str) {
        try {
            if (str == null) return null;
            //定义一个字节数组
            byte[] secretBytes = null;
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(str.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
            //将加密后的数据转换为16进制数字
            String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
            // 如果生成数字未满32位，需要前面补0
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
    }

}
