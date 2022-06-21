package com.shop.bookshop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CodeGenerateUtils {
    // 订单类别头
    private static final String ORDER_CODE = "1";
    // 随机编码
    private static final int[] r = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};
    // 用户id和随机数总长度
    private static final int maxLength = 14;
    // 根据 用户id 进行加密，加随机数组组成固定长度编码
    private static String toCode(Long id) {
        String idStr = id.toString();
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i) - '0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }
    // 生成固定长度的随机 n 是长度
    private static long getRandom(long n) {
        long min = 1, max = 9;
        for(int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = ((long)(new Random().nextDouble() * (max - min))) + min;
        return rangeLong;
    }

    // 生成时间戳
    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    // 生成不带类别标头的编码
    private static synchronized String getCode(Long userId) {
        userId = userId == null ? 10000 : userId;
        return getDateTime() + toCode(userId);
    }

    // 生产订单编号
    public static String generateOrderSn(Long userId) {
        return ORDER_CODE + getCode(userId);
    }

    public static String generateUnionPaySn() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String dateTime = dateFormat.format(calendar.getTime());
        dateTime = dateTime.substring(2);
        String timestampPart = "" + (Math.random() * 10000) * (System.currentTimeMillis() / 10000);
        timestampPart = timestampPart.replace(".", "").replace("E", "");
        timestampPart = timestampPart.substring(0, 5);
        return dateTime + timestampPart;
    }
}
