package com.sapling.spiderMans.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;


public class Util {
    private static final String RANDOM_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static Logger logger = LoggerFactory.getLogger(Util.class);

    public static Date parseStringToDate(String flag, String date) {
        Date parse = null;
        try {
            if (Util.isNotEmpty(flag)) {
                parse = (new SimpleDateFormat(flag)).parse(date);
            } else {
                parse = format.parse(date);
            }
        } catch (Exception e) {
            logger.error("Util parseStringToDate:" + e.getMessage(), e);
        }
        return parse;
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String sourceIP = "";
        if (Util.isNotEmpty(ip)) {
            String[] split = ip.split(",");
            if (Util.isNull(split)) {
                sourceIP = ip;
            } else {
                sourceIP = split[0].trim();
            }
        }
        return sourceIP;
    }

    public static SimpleDateFormat getSimpleDateFormat(String flag) {
        if (flag.equalsIgnoreCase("month")) {
            return new SimpleDateFormat("yyyy-MM");
        } else if (flag.equalsIgnoreCase("day")) {
            return new SimpleDateFormat("yyyy-MM-dd");
        } else if (flag.equalsIgnoreCase("hour")) {
            return new SimpleDateFormat("yyyy-MM-dd HH");
        } else if (flag.equalsIgnoreCase("minute")) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    public static SimpleDateFormat getLinkSimpleDateFormat(String flag) {
        if (flag.equalsIgnoreCase("month")) {
            return new SimpleDateFormat("yyyyMM");
        } else if (flag.equalsIgnoreCase("day")) {
            return new SimpleDateFormat("yyyyMMdd");
        } else if (flag.equalsIgnoreCase("hour")) {
            return new SimpleDateFormat("yyyyMMddHH");
        } else if (flag.equalsIgnoreCase("minute")) {
            return new SimpleDateFormat("yyyyMMddHHmm");
        } else if (flag.equalsIgnoreCase("seconds")) {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS");
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String getCurrentDay() {
        SimpleDateFormat countDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar curCalendar = Calendar.getInstance();
        String curDate = countDateFormat.format(curCalendar.getTime());
        return curDate;
    }

    public static String getCurrentDay(String flag) {
        SimpleDateFormat countDateFormat = new SimpleDateFormat(flag);
        Calendar curCalendar = Calendar.getInstance();
        String curDate = countDateFormat.format(curCalendar.getTime());
        return curDate;
    }

    public static String getCurrentDay(String flag, int vetor) {
        SimpleDateFormat countDateFormat = new SimpleDateFormat(flag);
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.add(Calendar.DATE, vetor);
        String curDate = countDateFormat.format(curCalendar.getTime());
        return curDate;
    }

    /**
     * 获取某天的前、后某天
     *
     * @param specifiedDay 指定某天
     * @param flag         时间格式yyyy-MM-dd，yyyy-MM-dd HH:mm:ss
     * @param offset       时间偏移量
     * @return 前、后某天
     */
    public static String getSpecifiedDayBeforeOrAfter(String specifiedDay, String flag, int offset) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(flag).parse(specifiedDay);
        } catch (ParseException e) {
            logger.error("Util getSpecifiedDayBeforeOrAfter:" + e.getMessage(), e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + offset);
        String dayBefore = new SimpleDateFormat(flag).format(c.getTime());
        return dayBefore;
    }

    public static int computeStartPage(int page, int rows) {
        int begin = (page - 1) * rows + 1;
        return begin;
    }

    public static int computeEndPage(int page, int rows) {
        int nmb = page * rows;
        return nmb;
    }

    public static String encryptMD5GBK(String paramString) {
        char[] arrayOfChar1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] arrayOfByte;
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(new String(paramString.getBytes("UTF-8"), "GBK").getBytes());

            arrayOfByte = localMessageDigest.digest();
            int i = arrayOfByte.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            for (int k = 0; k < arrayOfByte.length; k++) {
                int m = arrayOfByte[k];
                arrayOfChar2[(j++)] = arrayOfChar1[(m >>> 4 & 0xF)];
                arrayOfChar2[(j++)] = arrayOfChar1[(m & 0xF)];
            }
            return new String(arrayOfChar2);
        } catch (Exception localException) {
        }
        return null;
    }

    /**
     * md5加密
     *
     * @param paramString
     * @return
     */
    public static String encryptMD5(String paramString) {
        char[] arrayOfChar1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] arrayOfByte = paramString.getBytes();
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(arrayOfByte);
            arrayOfByte = localMessageDigest.digest();
            int i = arrayOfByte.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            for (int k = 0; k < arrayOfByte.length; k++) {
                int m = arrayOfByte[k];
                arrayOfChar2[(j++)] = arrayOfChar1[(m >>> 4 & 0xF)];
                arrayOfChar2[(j++)] = arrayOfChar1[(m & 0xF)];
            }
            return new String(arrayOfChar2);
        } catch (Exception localException) {
        }
        return null;
    }

    /**
     * 包含大小写字母，数字的随机字符串
     *
     * @param len
     * @return
     */
    public static String random(int len) {
        String random = "";
        for (int i = 0; i < len; i++) {
            double rd = Math.random() * 61;
            long seed = Math.round(rd);
            if (seed > 61) {
                seed = 61;
            }
            if (seed < 0) {
                seed = 0;
            }
            String seedStr = String.valueOf(seed);
            int num;
            try {
                num = Integer.parseInt(seedStr);
            } catch (Exception e) {
                num = 0;
            }
            random += String.valueOf(RANDOM_CHAR.charAt(num));
        }
        return random;
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(String s) {
        if (isNull(s)) {
            return true;
        }
        return "".equals(s);
    }

    public static boolean isContainsEmptyString(String... strings) {
        for (String s : strings) {
            if ("".equals(s.trim()))
                return true;
        }
        return false;
    }

    public static boolean isEmpty(Object[] s) {
        return s == null || s.length < 1;
    }

    public static boolean isContainsNullOrEmptyString(String... ss) {
        return isContainsNullObject(ss) || isContainsEmptyString(ss);
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isNotEmpty(Object[] s) {
        return !isEmpty(s);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isContainsNullObject(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                return true;
        }
        return false;
    }


    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static String getRandom(int len) {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        if (len > str.length())
            return str;
        return str.substring(0, len);
    }

    /**
     * formatNumber(2,5 )  return: 00002
     */
    public static String formatNumber(int number, int addpos) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(addpos);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }


    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        int j = 0;
        int size = begin + count;
        if (src.length < size) {
            for (int i = begin; i < src.length; i++) {
                bs[j++] = src[i];
            }
            return bs;
        }
        for (int i = begin; i < begin + count; i++) {
            bs[j++] = src[i];
        }
        return bs;
    }

    public static String getString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str;
    }


    public static String trim(String str) {
        if (str == null) return null;
        return str.trim();
    }

    public static String generateProviderOrderNo(String prefix) {
        return prefix + TimeUtil.dateToString(new Date(), TimeUtil.DATE_YYYY_M_MDD_H_HMMSS_SSSS) + Util.random(6);
    }

    public static String randomNum(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            sb.append(num + "");
        }
        return sb.toString();

    }


    /**
     * 获取产品ID，支持.，_分割,支持多级截取
     *
     * @param suffix true:取后缀，false：取前缀
     * @return
     */
    public static String getSuffixOrPrefix(String code, boolean suffix) {
        String suffixStr = null;
        String prefixStr = null;
        if (Util.isNotEmpty(code)) {
            int last = code.lastIndexOf(".");
            if (last > 0) {
                suffixStr = code.substring(last + 1);
                prefixStr = code.substring(0, last);
            } else {
                int last2 = code.lastIndexOf("_");
                if (last2 > 0) {
                    suffixStr = code.substring(last2 + 1);
                    prefixStr = code.substring(0, last2);
                }
            }
        }
        if (suffix)
            return suffixStr == null ? code : suffixStr;
        return prefixStr == null ? code : prefixStr;
    }

    /**
     * 取后缀
     *
     * @param prodId
     * @return
     */
    public static String getSuffixOrPrefix(String prodId) {
        return getSuffixOrPrefix(prodId, true);
    }

    /**
     * 去掉单位，如产品编码为12M，则去12
     *
     * @param productCode
     * @return
     */
    public static String removeUnit(String productCode, String unit) {
        if (productCode == null)
            return null;
        if (isContainsNullOrEmptyString(unit))
            return productCode;
        String desc = null;
        int idex = productCode.toUpperCase().indexOf(unit);
        if (idex != -1) {
            desc = productCode.substring(0, idex);
        }
        if (desc == null) {
            return productCode;
        }
        return desc;
    }

    public static String gennerateOrderNo() {
        return "f" + TimeUtil.dateToString(new Date(), TimeUtil.DATE_YYYY_M_MDD_H_HMMSS_SSSS) + Util.random(6);
    }

}
