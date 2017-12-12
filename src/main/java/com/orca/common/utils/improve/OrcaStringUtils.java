package com.orca.common.utils.improve;

import static com.orca.common.constants.CharConstants.STAR;
import static com.orca.common.constants.CharConstants.SPACE;

import com.orca.common.constants.RegexConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * orca框架的string工具类
 * created by yangyebo 2017-12-11 上午10:37
 */
public abstract class OrcaStringUtils{

    /**
     * 两个字符串是否相等
     * 特别的，如果两个字符串都为空格串，也返回相等
     * @param target
     * @param str
     * @return
     */
    public static boolean isStrValueEquals(String target, String str) {
        if (StringUtils.isBlank(target) && StringUtils.isBlank(str)) {
            return true;
        }

        return StringUtils.equals(target, str);
    }

    public static String[] splitPreserveAllTokensTrimmed(String str, String separatorChars) {
        String[] strs = StringUtils.splitPreserveAllTokens(str, separatorChars);
        if (ArrayUtils.isEmpty(strs)) {
            return strs;
        }
        int arrayLength = strs.length;
        for (int index = 0; index < arrayLength; index++) {
            strs[index] = StringUtils.trimToEmpty(strs[index]);
        }
        return strs;
    }

    /**
     * 按照空格分割字符串，不管有多少个空格
     * "a b   c   de   f"
     * 结果是{[a],[b],[c],[de],[f]}
     * @param str "a b   c   de   f"
     * @return {["a"],["b"],["c"],["de"],["f"]}
     */
    public static String[] splitBySpace(String str) {
        if(str == null) {
            return null;
        }
        return str.split(RegexConstants.MULIT_SPACE);
    }

    /**
     * mask cardNo: 622200223564120 -> 6222 00** *** 4120
     *
     * @param str
     * @return
     */
    public static String mask(String str, int headLength, int tailLength) {
        StringBuffer ret = new StringBuffer();

        int starLength = headLength + tailLength;

        if (StringUtils.isBlank(str)) {
            ret.append(str);
        } else if (str.length() <= starLength) {
            ret.append(STAR).append(str.substring(1));
        } else {
            int len = str.length();
            String head = str.substring(0, headLength);
            String tail = str.substring(len - tailLength);
            int div = (len - starLength) / tailLength;
            int mod = (len - starLength) % tailLength;

            ret.append(head).append(" ");
            for (int i = 0; i < div; i++) {
                ret.append(STAR).append(STAR).append(STAR).append(STAR).append(SPACE);
            }
            for (int i = 0; i < mod; i++) {
                ret.append(STAR);
            }
            if (mod > 0) {
                ret.append(SPACE);
            }
            ret.append(tail);
        }
        return ret.toString();
    }
}
