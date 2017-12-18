package com.orca.common.utils.improve;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * orca框架的集合相关工具类
 * created by yangyebo 2017-12-18 下午2:51
 */
public abstract class OrcaListUtils{

    public static boolean isOnlyOneElement(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return list.size() == 1;
    }

    public static Object geFirstElement(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }
}
