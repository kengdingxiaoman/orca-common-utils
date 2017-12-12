package com.orca.common.exception;

import com.orca.common.constants.OrcaSystemConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;

/**
 * orca框架异常
 * 使用该异常，打印的日志只会显示orca框架内部的报错，而不会显示第三方框架的报错
 * 减少错误日志的数量
 * created by yangyebo 2017-12-12 上午10:32
 */
public class OrcaException extends RuntimeException {

    private static final long serialVersionUID = -4811564165918040174L;

    public OrcaException(String message) {
        super(message);
    }

    public OrcaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    @Override
    public void printStackTrace(PrintWriter s) {
        StackTraceElement[] traceElements = getStackTrace();
        if (ArrayUtils.isEmpty(traceElements)) {
            super.printStackTrace(s);
            return;
        }

        int theOriginalOrcaClassIndex = locateOriginalOrcaClassIndex(traceElements);
        if (theOriginalOrcaClassIndex >= 0) {
            //ArrayUtils.subarray方法内部会保证即使lastIndex + 1 > traceElements.length，也不会发生数组越界
            StackTraceElement[] filteredTraceElements = (StackTraceElement[]) ArrayUtils.subarray(
                    traceElements, 0, theOriginalOrcaClassIndex + 1);
            setStackTrace(filteredTraceElements);
        }
        super.printStackTrace(s);
    }

    /**
     * 定位最初(如果按照日志从上往下看，应该是最后一次)出现匹配com.orca.*路径的类的位置
     *
     * @param traceElements
     * @return
     */
    private int locateOriginalOrcaClassIndex(StackTraceElement[] traceElements) {
        int theOriginalOrcaClassIndex = -1;
        int elementsLength = traceElements.length;
        for (int currentIndex = 0; currentIndex < elementsLength; currentIndex++) {
            if (isOrcaProjectClass(traceElements[currentIndex])) {
                theOriginalOrcaClassIndex = currentIndex;
                continue;
            }
            if (hasFoundOriginalOrcaClass(theOriginalOrcaClassIndex)) {
                break;
            }
        }
        return theOriginalOrcaClassIndex;
    }

    private boolean hasFoundOriginalOrcaClass(int theOriginalOrcaClassIndex) {
        return theOriginalOrcaClassIndex != -1;
    }

    private boolean isOrcaProjectClass(StackTraceElement traceElement) {
        if (traceElement == null) {
            return false;
        }
        if (StringUtils.isBlank(traceElement.getClassName())) {
            return false;
        }
        return traceElement.getClassName().startsWith(
                OrcaSystemConstants.ORCA_CLASS_PATH_FIXED_PREFIX);
    }
}
