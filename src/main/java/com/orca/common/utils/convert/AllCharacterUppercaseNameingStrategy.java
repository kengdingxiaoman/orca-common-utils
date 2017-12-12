package com.orca.common.utils.convert;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * jackson获取对应属性的策略
 * 属性字母全部转换为答谢，例如： {"USERMP":"13818930251"}"
 * @author master.yang
 */
public class AllCharacterUppercaseNameingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase{

    private static AllCharacterUppercaseNameingStrategy strategy = null;

    private AllCharacterUppercaseNameingStrategy() {
    }

    public synchronized static AllCharacterUppercaseNameingStrategy getInstance() {
        if (strategy == null) {
            strategy = new AllCharacterUppercaseNameingStrategy();
        }
        return strategy;
    }

    /** 
     * @see com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase#translate(String)
     */
    @Override
    public String translate(String propertyName) {
        return propertyName.toUpperCase();
    }
}
