package com.orca.common.utils.validate;

import org.apache.commons.lang3.StringUtils;

/**
 * 验证字符串是否符合条件的工具
 * @author master.yang
 */
public abstract class ValidateUtils {

    /**
     * 判断参数str不为空并且小于等于要求的最大长度
     * 
     * @param str
     * @param maxLength
     * @return
     */
    public static boolean notNullAndLessEqualThanMaxLength(String str, int maxLength) {

        if (StringUtils.isBlank(str)) {
            return false;
        }

        return str.getBytes().length <= maxLength;
    }

    /**
     * 判断参数str不为空并且等于要求的长度
     * 
     * @param str
     * @param requiredLength
     * @return
     */
    public static boolean notNullAndEqualThanRequiredLength(String str, int requiredLength) {

        if (StringUtils.isBlank(str)) {
            return false;
        }

        return str.getBytes().length == requiredLength;
    }

    /**
     * 如果不为空，则判断参数str小于等于要求的最大长度
     * 
     * @param str
     * @param maxLength
     * @return
     */
    public static boolean lessEqualThanMaxLengthIfNotNull(String str, int maxLength) {

        if (StringUtils.isBlank(str)) {
            return true;
        }

        return str.getBytes().length <= maxLength;
    }

    /**
     * 如果不为空，判断参数str是否等于要求的长度
     * 
     * @param str
     * @param maxLength
     * @return
     */
    public static boolean equalThanRequiredLengthIfNotNull(String str, int maxLength) {

        if (StringUtils.isBlank(str)) {
            return true;
        }

        return str.getBytes().length == maxLength;
    }

    /**
     * Long对象是否有值且大于0
     * @param value
     * @return
     */
    public static boolean hasValueAndBiggerThanZero(Long value) {
        return value != null && value > 0;
    }
}
