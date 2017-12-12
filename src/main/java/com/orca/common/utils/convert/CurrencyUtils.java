package com.orca.common.utils.convert;

import com.orca.common.model.Money;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 货币工具类
 * @author master.yang
 */
public abstract class CurrencyUtils {

    /**
     * 将浮点数精确到两位后转化为字符串
     */
    public static String float2str(float f) {
        long amt;

        amt = (long) (fRound(f) * 100);

        return (leftPad("" + amt, 12, '0'));

    }

    /**
     * 将金额字符串格式化
     * 
     * @return 转化为以分为单位前补零的12位字符串。
     */
    public static String amt2str(String s) {
        long amt;

        amt = (long) ((new Double(s)).doubleValue() * 100);

        return (leftPad("" + amt, 12, '0'));
    }

    /**
     * 将以元为单位的金额字符串转换为以分为单位的金额字符串
     * 0.00 -> 0
     * 0.01 -> 1
     * 532.3 -> 53230
     * "" -> 0
     * null -> 0
     * 
     * @param s 以元为单位的金额字符串
     * @return 以分为单位的金额字符串
     */
    public static String amtMovePoint(String s) {
        if (s == null)
            return "0";

        if (s.trim().equals(""))
            return "0";

        try {
            double sAmt = Double.parseDouble(s) * 100;
            DecimalFormat dmf = new DecimalFormat("#");
            return dmf.format(sAmt);
        } catch (Exception e) {
            System.out.println("金额转换出错[" + s + "]");
            return "0";
        }

    }

    /**
     * 将以分为单位的金额字符串转换为以元为单位的金额字符串
     * 0.00 <- 0
     * 0.01 <- 1
     * 532.3 <- 53230
     * "" -> 0.00
     * null -> 0.00
     * @author dayo.wang
     * @param s 以分为单位的金额字符串
     * @return 以元为单位的金额字符串
     */
    public static String amtAddPoint(String s) {
        if (s == null)
            return "0.00";

        if (s.trim().equals(""))
            return "0.00";

        try {
            double sAmt = Double.parseDouble(s) / 100;
            DecimalFormat dmf = new DecimalFormat("0.00");
            return dmf.format(sAmt);
        } catch (Exception e) {
            System.out.println("金额转换出错[" + s + "]");
            return "0.00";
        }

    }

    /**
     * 将小写的数字变成大写的汉字
     * 
     * @param number
     *            小写的数字
     * @return 大写的汉字
     */
    public static String number2Chinese(int number) {

        String str = null;

        switch (number) {
            case 0:
                str = "零";
                break;
            case 1:
                str = "壹";
                break;
            case 2:
                str = "贰";
                break;
            case 3:
                str = "叁";
                break;
            case 4:
                str = "肆";
                break;
            case 5:
                str = "伍";
                break;
            case 6:
                str = "陆";
                break;
            case 7:
                str = "柒";
                break;
            case 8:
                str = "捌";
                break;
            case 9:
                str = "玖";
                break;
        }

        return str;
    }

    /**
     * 将以元为单位的金额字符串转换为汉字的字符串 93.56 -> 玖拾叁元伍角陆分
     * 
     * @param s
     *            以元为单位的金额字符串
     * @return 汉字金额字符串
     */
    public static String amt2Chinese(String s) {

        String str = "";
        String amt = amtMovePoint(s);
        String unit = "";

        int number = 0;

        if (amt.length() > 10)
            return "";

        //int k = 0;
        int j = amt.length();
        for (int i = amt.length(); i > 0; i--) {
            switch (j - i) {
                case 0:
                    unit = "分";
                    break;
                case 1:
                    unit = "角";
                    break;
                case 2:
                    unit = "元";
                    break;
                case 3:
                    unit = "拾";
                    break;
                case 4:
                    unit = "佰";
                    break;
                case 5:
                    unit = "仟";
                    break;
                case 6:
                    unit = "万";
                    break;
                case 7:
                    unit = "拾";
                    break;
                case 8:
                    unit = "佰";
                    break;
                case 9:
                    unit = "仟";
                    break;
            }

            /* 截出最后一位 */
            number = Integer.parseInt(amt.substring(i - 1));
            amt = amt.substring(0, i - 1);

            str = number2Chinese(number) + unit + str;

        }

        str = str.replaceAll("零仟", "零");
        str = str.replaceAll("零佰", "零");
        str = str.replaceAll("零拾", "零");

        str = str.replaceAll("零零零", "零");
        str = str.replaceAll("零零", "零");

        str = str.replaceAll("零万", "万");
        str = str.replaceAll("零元", "元");

        str = str.replaceAll("零角零分", "整");
        str = str.replaceAll("零角", "零");
        str = str.replaceAll("零分", "");

        return str;
    }

    /**
     * 将以笔为单位的数字字符串转换为汉字的字符串 93 -> 玖拾叁笔
     * @author dayo.wang
     * @param s
     *            以笔为单位的数字字符串
     * @return 汉字金额字符串
     */
    public static String count2Chinese(String s) {

        String str = "";
        String count = s;
        String unit = "";

        int number = 0;

        if (count.length() > 10)
            return "";

        //int k = 0;
        int j = count.length();
        for (int i = count.length(); i > 0; i--) {
            switch (j - i) {
                case 0:
                    unit = "笔";
                    break;
                case 1:
                    unit = "拾";
                    break;
                case 2:
                    unit = "佰";
                    break;
                case 3:
                    unit = "仟";
                    break;
                case 4:
                    unit = "万";
                    break;
                case 5:
                    unit = "拾";
                    break;
                case 6:
                    unit = "佰";
                    break;
                case 7:
                    unit = "仟";
                    break;
            }

            /* 截出最后一位 */
            number = Integer.parseInt(count.substring(i - 1));
            count = count.substring(0, i - 1);

            str = number2Chinese(number) + unit + str;

        }

        str = str.replaceAll("零仟", "零");
        str = str.replaceAll("零佰", "零");
        str = str.replaceAll("零拾", "零");

        str = str.replaceAll("零零零", "零");
        str = str.replaceAll("零零", "零");

        str = str.replaceAll("零万", "万");
        str = str.replaceAll("零笔", "笔");

        return str;
    }

    public static String double2amt(String s_amt) {
        return (double2amt((new Double(s_amt)).doubleValue()));
    }

    public static String double2amt(double f) {
        DecimalFormat dmf = new DecimalFormat("0.00");
        return (dmf.format(f));
    }

    /**
     * 对浮点数精确到小数点后两位，四舍五入 
     * input : 10.004 , output : 10.00 ; 
     * input : 10.006 , output : 10.01 ;
     * 
     * @return 精确后的浮点数
     */
    public static float fRound(float f) {

        long l, l0;

        l = (long) (f * 100);
        l0 = (long) (f * 1000);

        l0 = l0 - l * 10;
        if (l0 >= 5)
            l++;

        return ((float) (l / 100.0));
    }

    /**
     * 将String 转化为金额小数点后定长的String
     * 
     * @param bit
     *            小数点后精确到多少位
     * @return String为带小数点后bit位的String， 比原来减位的话，为四舍五入。 本函数谨慎使用，扩位结果不可测。
     */
    public static String float2amt(String s, int bit) {

        return (float2amt((new Float(s)).floatValue(), bit));
    }

    /**
     * 将浮点数转化为金额float
     * 
     * @param bit
     *            小数点后精确到多少位
     * @return 对浮点数进行去尾取整，比如float2amt(5.9876,5)，返回值为String "5.00000"
     */
    public static String float2amt(float f, int bit) {

        long l1, l2, l;
        float ff;

        l1 = (long) f;
        ff = f - l1;
        l = 1;

        int i;
        for (i = 0; i < bit; i++) {
            l = l * 10;
        }

        l2 = (long) (ff * l);

        if (ff * l * 10 - l2 * 10 >= 5)
            l2++;

        return ("" + l1 + "." + leftPad("" + l2, bit, '0'));
    }

    /**
     * 将字符型金额转换为long型
     * 
     * @return long为去除String右起第三位后的转化结果。如"5432"结果为532
     */
    public static long amt2long(String s) {
        long l1, l2;

        int len = s.length();
        l1 = new Long(s.substring(0, len - 3)).longValue();
        l2 = new Long(s.substring(len - 2)).longValue();

        return (l1 * 100 + l2);
    }

    /**
     * 将字符型金额最后两位转换为小数点后两位。
     * 
     * @return String里原String的最后两位会被认为是小数点后两位。
     */
    public static String long2amt(String s) {
        return (long2amt((new Long(s)).longValue()));
    }

    /**
     * 将long型金额转换为字符型
     * 
     * @return String里long的最后两位会被认为是小数点后两位。
     */
    public static String long2amt(long l) {
        long amt1, amt2;

        amt1 = Math.abs((long) (l / 100));

        amt2 = Math.abs(l % 100);
        if (l < 0) {
            return ("-" + amt1 + "." + leftPad("" + amt2, 2, '0'));
        } else {
            return ("" + amt1 + "." + leftPad("" + amt2, 2, '0'));
        }
    }

    /**
     * 将字符型金额最后两位转换为小数点后两位。
     * 
     * @return String里原String的最后两位会被认为是小数点后两位。
     */

    static public String str2amt(String Amt) {
        long amt, amt1, amt2;

        amt = (new Long(Amt)).longValue();

        amt1 = (long) (amt / 100);

        amt2 = amt % 100;

        return ("" + amt1 + "." + leftPad("" + amt2, 2, '0'));
    }

    public static boolean isEquals(String amount1, Money amount2) {
        Assert.hasText(amount1, "amount1 is blank");
        return isEquals(new Money(amount1), amount2);
    }

    public static boolean isEquals(Money amount1, Money amount2) {
        Assert.notNull(amount1, "amount1 is null");
        Assert.notNull(amount2, "amount2 is null");
        return amount1.compareTo(amount2) == 0;
    }

    public static boolean isZero(Money amount) {
        Assert.notNull(amount, "amount is null");
        return amount.compareTo(new Money("0.00")) == 0;
    }

    public static boolean isMoreOrEqualThan(Money amount1, Money amount2) {
        Assert.notNull(amount1, "amount1 is null");
        Assert.notNull(amount2, "amount2 is null");
        return amount1.compareTo(amount2) >= 0;
    }

    public static boolean isMoreOrEqualThan(String amount1, String amount2) {
        Assert.notNull(amount1, "amount1 is null");
        Assert.notNull(amount2, "amount2 is null");
        return isMoreOrEqualThan(new Money(amount1), new Money(amount2));
    }

    public static boolean isMoreThan(Money amount1, Money amount2) {
        Assert.notNull(amount1, "amount1 is null");
        Assert.notNull(amount2, "amount2 is null");
        return amount1.compareTo(amount2) > 0;
    }

    public static boolean isMoreThan(String amount1, String amount2) {
        Assert.notNull(amount1, "amount1 is null");
        Assert.notNull(amount2, "amount2 is null");
        return isMoreThan(new Money(amount1), new Money(amount2));
    }

    public static Money getTheSmaller(Money amount1, Money amount2) {
        return isMoreThan(amount1, amount2) ? amount2 : amount1;
    }

    /**
     * 将浮点数格式字串转换为含千分位的###,##0.00格式字串，如如 1234567转换后为1,234,567.00
     * 若为空或null,则转换为0.00
     * 
     * @param amount
     * @return
     */
    public static String format2CurrencyFormatOrNull(BigDecimal amount) {
        if (null == amount || StringUtils.isBlank(amount.toString())) {
            return "0.00";
        }
        String amt = amount.toString();
        Assert.isTrue(NumberUtils.isNumber(amt), "'" + amount + "' must be a Number format here.");
        return format2CurrencyFormat(Double.parseDouble(amt));
    }

    /**
     * 将浮点数格式字串转换为含千分位的###,##0.00格式字串，如如 1234567转换后为1,234,567.00
     * 若为空或null,则转换为0.00
     * 
     * @param amount
     * @return
     */
    public static String format2CurrencyFormatOrNull(String amount) {
        if (StringUtils.isBlank(amount)) {
            return "0.00";
        }
        Assert.isTrue(NumberUtils.isNumber(amount), "'" + amount
                                                    + "' must be a Number format here.");
        return format2CurrencyFormat(Double.parseDouble(amount));
    }

    /**
     * 将浮点数格式字串转换为含千分位的###,##0.00格式字串，如如 1234567转换后为1,234,567.00
     * 
     * @param amount
     * @return
     */
    public static String format2CurrencyFormat(String amount) {
        Assert.isTrue(NumberUtils.isNumber(amount), "'" + amount
                                                    + "' must be a Number format here.");
        return format2CurrencyFormat(Double.parseDouble(amount));
    }

    /**
     * 将浮点数转换为含千分位的###,##0.00格式字串，如如 1234567转换后为1,234,567.00
     * 
     * @param amount
     * @return
     */
    public static String format2CurrencyFormat(double amount) {
        return new DecimalFormat("###,##0.00").format(amount);
    }

    /**
     * 将数字转换为精简的格式,有几位小数点保留几位小数点
     * 正常的没有问题，主要是识别千分位，去除转换千分位
     * 
     * @param amount
     * @return
     */
    public static String formatSimplifyStyle(String amount) {
        try {
            double d = new DecimalFormat().parse(amount).doubleValue();
            return String.valueOf(d);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Money.getZeroYuanInstance().getYuan();
        }
    }

    /**
     * 将浮点数转换为0.00格式字串，如13.4->13.40,2->2.00
     * 
     * @param amount
     * @return
     */
    public static String format2YuanFormat(double amount) {
        return new DecimalFormat("0.00").format(amount);
    }

    private static String leftPad(String str, int len, char ch) {
        return StringUtils.leftPad(str, len, ch);
    }
}
