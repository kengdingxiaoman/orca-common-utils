package com.orca.common.utils.bytes;

import com.orca.common.constants.CharConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 对字节操作的工具类
 * @author master.yang
 */
public abstract class ByteUtils {

    /**
     * hex 转成 byte[]
     * 例如：hex2byte("21d153ef") = 0x21, 0xd1, 0x53, 0xef
     * 
     * @param str
     * @return
     */
    public static final byte[] hex2byte(String str) {
        byte[] ret = null;
        if (StringUtils.isBlank(str)) {
            return ret;
        }
        if (str.toCharArray().length % 2 != 0) {
            str = "0" + str; //如果是奇数位
        }
        char[] arr = str.toCharArray();
        ret = new byte[str.length() / 2];
        int length = str.length();

        for (int i = 0, j = 0, l = length; i < l; i++, j++) {
            StringBuffer swap = new StringBuffer().append(arr[i++]).append(arr[i]);
            int byteint = Integer.parseInt(swap.toString(), 16) & 0xFF;
            ret[j] = new Integer(byteint).byteValue();
        }
        return ret;
    }

    /**
     * byte[] 转成 hex
     * 
     * @param src
     * @return
     */
    public static final String byte2hex(byte[] src) {
        StringBuffer ret = new StringBuffer();
        if (!ArrayUtils.isEmpty(src)) {
            for (int i = 0; i < src.length; i++) {
                byte b = src[i];
                String stmp = (Integer.toHexString(b & 0XFF));
                if (stmp.length() == 1) {
                    ret.append("0").append(stmp);
                } else {
                    ret.append(stmp);
                }
            }
        }
        return ret.toString();
    }

    /**
     * byte数组转换为ASCII形式字符串
     * 例如：byte2hexSpacingWithBlank(0x21, 0xd1, 0x53, 0xef) = "21 d1 53 ef"
     * 
     * @param bytes
     * @return
     */
    public static final String byte2hexSpacingWithBlank(byte[] bytes) {
        if (ArrayUtils.isEmpty(bytes)) {
            return StringUtils.EMPTY;
        }

        StringBuffer ret = new StringBuffer();
        int bytesLength = bytes.length;
        for (int i = 0; i < bytesLength; i++) {
            byte b = bytes[i];
            String hexString = (Integer.toHexString(b & 0XFF));
            if (hexString.length() == 1) {
                ret.append("0");
            }
            ret.append(hexString);
            ret.append(CharConstants.BLANK);
        }
        return ret.toString();
    }

    /**
     * 字符转换成16进制
     * 例如：convert2Hex("65765") = 0x36, 0x35, 0x37, 0x36, 0x35
     *
     * @param str
     * @return
     */
    public static String convert2Hex(String str) {
        return byte2hex(str.getBytes());
    }
}
