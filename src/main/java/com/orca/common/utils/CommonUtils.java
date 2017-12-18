package com.orca.common.utils;

import com.orca.common.constants.CharConstants;
import com.orca.common.constants.OrcaSystemConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用的的工具类
 * @author master.yang
 */
public abstract class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String showDetails(Object object) {
        if (object == null) {
            return "object is null";
        }
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 将请求参数去空，全角转半角
     * 
     * @param value
     * @return
     */
    public static String normalize(String value) {
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }

        String notNullValue = setValueEmptyIfNull(value);
        return QJToBJ(StringUtils.trim(notNullValue));
    }

    public static String setValueEmptyIfNull(String value) {
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }
        if (OrcaSystemConstants.NULL_VALUE.equalsIgnoreCase(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 全角转半角
     * 
     * @param QJstr
     * @return String
     */
    public static String QJToBJ(String QJstr) {
        String outStr = "";
        byte[] b = null;
        try {
            b = QJstr.getBytes("unicode");
        } catch (UnsupportedEncodingException e) {
            logger.error("全角转半角转换原始字符串发生异常, 原始字符串：{}", QJstr, e);
        }

        int pos = 0;

        for (int i = 0; i < QJstr.length(); i++) {
            pos += 2;
            if (b[pos] == -1) {
                b[pos + 1] = (byte) (b[pos + 1] + 32);
                b[pos] = 0;
            }
        }

        try {
            outStr = new String(b, "unicode");
        } catch (UnsupportedEncodingException e) {
            logger.error("全角转半角转换生成字符串发生异常, 原始字符串：{}", QJstr, e);
        }

        return outStr;
    }

    public static String concateStrings(String... strings) {
        if (ArrayUtils.isEmpty(strings)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(StringUtils.isNotBlank(str) ? str : "");
        }
        return sb.toString();
    }

    public static String concateStringsBySeparator(String separator, String... strings) {
        if (ArrayUtils.isEmpty(strings)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(StringUtils.isNotBlank(str) ? str : "").append(separator);
        }
        return StringUtils.removeEnd(sb.toString(), separator);
    }

    /**
     * 用&连接Map中的键值对: key1=value1&key2=value2&key3=value3
     * @param postDataParams
     * @return 形如：acctDate=20121101&chkType=M&chkValue=34TYBC6DWEE
     */
    public static String constitutePostData(Map<String, String> postDataParams) {
        String[] keys = generateSortdKeys(postDataParams);
        if (ArrayUtils.isEmpty(keys)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append(CharConstants.EQUAL).append(postDataParams.get(key))
                .append(CharConstants.AND);
        }

        return StringUtils.removeEnd(sb.toString(), CharConstants.AND);
    }

    /**
     * 用&连接Map中的键值对: key1=value1&key2=value2&key3=value3
     * 中文字符串将被URLEncoder转码 @see java.net.URLEncoder
     * @param postDataParams
     * @param charset 发送数据的编码集
     * @return 形如：acctDate=20121101&chkType=M&chkValue=34TYBC6DWEE
     */
    public static String constitutePostData(Map<String, String> postDataParams, String charset)
                                                                                               throws UnsupportedEncodingException {
        String[] keys = generateSortdKeys(postDataParams);
        if (ArrayUtils.isEmpty(keys)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append(CharConstants.EQUAL)
                .append(URLEncoder.encode(postDataParams.get(key), charset))
                .append(CharConstants.AND);
        }

        return StringUtils.removeEnd(sb.toString(), CharConstants.AND);
    }

    /**
     * 对参数值按照字母顺序排序
     * 
     * @param map
     * @return 如果map中不包含键值对，则返回null
     */
    public static String[] generateSortdKeys(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        Set<String> keysSet = map.keySet();
        int keysNum = keysSet.size();
        String[] keys = (String[]) keysSet.toArray(new String[keysNum]);
        if (ArrayUtils.isEmpty(keys)) {
            return null;
        }
        Arrays.sort(keys);
        return keys;
    }

    public static String generateSQLInCondition(String[] options) {
        if (ArrayUtils.isEmpty(options)) {
            return StringUtils.EMPTY;
        }

        StringBuffer condition = new StringBuffer();
        for (String option : options) {
            if (StringUtils.isBlank(option)) {
                continue;
            }
            condition.append(CharConstants.QUOTE).append(option).append(CharConstants.QUOTE)
                .append(CharConstants.COMMA);
        }
        if (condition.length() == 0) {
            return StringUtils.EMPTY;
        }
        return StringUtils.removeEnd(condition.toString(), CharConstants.COMMA);
    }
}
