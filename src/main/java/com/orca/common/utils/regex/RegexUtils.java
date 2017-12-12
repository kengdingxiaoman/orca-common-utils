package com.orca.common.utils.regex;

import com.orca.common.constants.RegexConstants;
import com.orca.common.model.Money;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 判断各种格式
 * @author master.yang
 */
public abstract class RegexUtils {

    /**
     * 检查（如"0123","123L","12.3S"等带有小数点和后缀的）字串，是否代表数字类型
     * 
     * @param str
     */
    public static final void checkNumber(String str) {
        Assert.isTrue(isNumber(str), "'" + str + "' must be a Number format here.");
    }

    /**
     * 判断（如"0123","123L","12.3S"等带有小数点和后缀的）字串，是否代表数字类型
     * 
     * @param str
     * @return
     */
    public static final boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }

    public static boolean isValidMoney(String orderAmount) {
        if (StringUtils.isBlank(orderAmount)) {
            return false;
        }
        if (!isYuanStyle(orderAmount)) {
            return false;
        }
        if (lessEqualThanZero(orderAmount)) {
            return false;
        }
        return true;
    }

    private static boolean lessEqualThanZero(String orderAmount) {

        return new Money(orderAmount).compareTo(new Money(0)) <= 0;
    }

    /**
     * 金额是否匹配形如："12.00"
     * 
     * @param amount
     * @return "", "12", "12.000", "0.00" 返回false
     */
    public static boolean isYuanStyle(String amount) {
        if (StringUtils.isBlank(amount)) {
            return false;
        }
        if (!isMatch(amount, RegexConstants.YUAN_FORMAT)) {
            return false;
        }
        return true;
    }

    /**
     * 判断用户名是否6到25位
     * 
     * @param loginId
     * @return
     */
    public static final boolean checkLoginId(String loginId) {
        Assert.notNull(loginId, "must be not null .");

        if (isCellPhoneNum(loginId)) {
            return true;
        }

        return !isMatch(loginId, "^[0-9]+$") && !isMatch(loginId, "^[\\-_\\.\\@]+$")
               && isMatch(loginId, "(^[a-zA-Z0-9_\\.\\-\\@]{6,40}$)");
    }

    /**
     * 判断用户名是否6到25位
     * 
     * @param loginId
     * @param reg
     * @return
     */
    public static final boolean checkLoginId(String loginId, String... reg) {
        Assert.notNull(loginId, "must be not null .");
        Assert.notNull(reg, "must be not null .");
        for (String str : reg) {
            if (!isMatch(loginId, str)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断电话号码格式是否正确
     * @param phoneNum
     * @return
     */
    public static final boolean isPhoneNum(String phoneNum) {
        return isMatch(phoneNum, RegexConstants.PHONE_NUMBER);
    }

    /**
     * 判断email格式是否正确
     * @param email
     * @return
     */
    public static final boolean isEmail(String email) {
        return isMatch(email, RegexConstants.EMAIL);
    }

    /**
     * 判断邮编格式是否正确
     * @param postalcode
     * @return
     */
    public static final boolean isPostode(String postalcode) {
        return isMatch(postalcode, RegexConstants.POST_CODE);
    }

    /**
     * 判断身份证号是否正确
     * @param certId
     * @return
     */
    public static final boolean isIdentityId(String certId) {
        return isMatch(certId, RegexConstants.IDENTITY_ID);
    }

    /**
     * 判断ip格式是否正确
     * @param ip
     * @return
     */
    public static final boolean isIPAddress(String ip) {
        return isMatch(ip, RegexConstants.IP_ADDRESS);
    }

    /**
     * 判断手机号码格式是否正确
     * 
     * @param cellPhoneNum
     * @return
     */
    public static final boolean isCellPhoneNum(String cellPhoneNum) {

        return isMatch(cellPhoneNum, RegexConstants.MOBILE_PHONE);
    }

    /**
     * 判断是否正确银行卡号6-32位
     */
    public static final boolean isCardNo(String cardNo) {
        return isMatch(cardNo, RegexConstants.BANK_CARD_NO);
    }

    /**
     * 校验字符串是否是字母和数字的组合
     * @param message
     * @return
     */
    public static final boolean isNumAndLetter(String message) {
        return isMatch(message, RegexConstants.NUMBER_AND_LETTER);
    }

    /**
     * 利用正则表达式检查是否完整匹配
     * 
     * @param text
     * @param reg
     * @return
     */
    public static final boolean isMatch(String text, String reg) {
        if (StringUtils.isNotBlank(reg)) {
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(text);
            return m.matches();
        }
        return false;
    }
}