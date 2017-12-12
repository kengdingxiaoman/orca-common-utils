package com.orca.common.constants;

/**
 * 正则表达式常量
 * @author master.yang
 */
public abstract class RegexConstants {
    
    /**
     * 可以用来判断是否符合人民币元的格式
     */
    public final static String YUAN_FORMAT = "[\\d]+\\.[\\d]{2}";

    /**
     * 1个或多个空格
     */
    public final static String MULIT_SPACE = "\\s+";

    /**
     * email
     */
    public final static String EMAIL = "^([a-zA-Z0-9_\\.\\-]+)(@{1})([a-zA-Z0-9_\\.\\-]+)(\\.[a-zA-Z0-9]{1,3})$";

    /**
     * 邮编
     */
    public final static String POST_CODE = "^[1-9]\\d{5}$";

    /**
     * 身份证
     */
    public final static String  IDENTITY_ID = "\\d{15}|\\d{17}[\\dXx]";

    /**
     * IP地址
     */
    public final static String IP_ADDRESS = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

    /**
     * 座机号码
     */
    public final static String PHONE_NUMBER = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";

    /**
     * 手机号码
     */
    public final static String MOBILE_PHONE = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1\\d{10}$";

    /**
     * 银行卡号
     */
    public final static String BANK_CARD_NO = "(^[0-9]{6,32}$)";

    /**
     * 字母和数字
     */
    public final static String NUMBER_AND_LETTER = "^[A-Za-z0-9]+$";
}
