package com.orca.common.utils.convert;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

/**
 * 
 * jackson获取对应属性的策略
 * 词首字母为大写，例如： {"UserMp":"13818930251"}"
 * @author master.yang
 */
public class FirstLetterUpperCaseNamingStrategy extends PropertyNamingStrategyBase {

    private static FirstLetterUpperCaseNamingStrategy strategy = null;

    private FirstLetterUpperCaseNamingStrategy() {
    }

    public synchronized static FirstLetterUpperCaseNamingStrategy getInstance() {
        if (strategy == null) {
            strategy = new FirstLetterUpperCaseNamingStrategy();
        }
        return strategy;
    }

    /** 
     * @see com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase#translate(String)
     */
    @Override
    public String translate(String propertyName) {
        StringBuffer sb = new StringBuffer(propertyName);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
