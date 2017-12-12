package com.orca.common.constants;

/**
 * Orca系统会用到的通用常量
 * @author master.yang
 */
public abstract class OrcaSystemConstants{

    /**
     * 字符串值"NULL"，有时一些参数的值为字符串"null"或"NULL"
     * 在判断是否为null时可以转换大写后与此常量进行比较
     */
    public static final String NULL_VALUE = "NULL";

    /**
     * 系统使用的编码集<code>utf-8</code>
     */
    public static final String SYSTEM_CHARSET = "UTF-8";

    /**
     * orca系统前缀
     */
    public static final String ORCA_CLASS_PATH_FIXED_PREFIX = "com.orca";
}
