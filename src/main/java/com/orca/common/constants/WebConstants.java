package com.orca.common.constants;

/**
 * WEB相关会用到的常量
 * @author master.yang
 */
public abstract class WebConstants {

    /**
     * http协议
     */
    public static final String HTTP_PROTOCOL_SCHEME = "http";

    /**
     * https协议
     */
    public static final String HTTPS_PROTOCOL_SCHEME = "https";

    /**
     * https协议默认的端口: 443
     */
    public static final int DEFAULT_PORT_NUMBER_FOR_HTTPS = 443;

    /**
     * URL标示为本地时域名方式的值：localhost
     */
    public final static String URL_LOCALHOST_DOMAIN_STYLE = "localhost";
    
    /**
     * URL标示为本地时IP方式的值：127.0.0.1
     */
    public final static String URL_LOCALHOST_IP_STYLE = "127.0.0.1";

    public final static String UTF8 = "UTF-8";

    /**
     * 默认超时时间：3秒
     */
    public static final int DEFAULT_TIME_OUT_MILLISECONDS = 3000;
}
