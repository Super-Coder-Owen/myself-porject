package com.vip.spring.formework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

/**
 *
 */
public class HandlerAdapter {
    private Map<String, Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public ModelAndView handler(HttpServletRequest req, HttpServletResponse resp, HandlerMapping hm) throws InvocationTargetException, IllegalAccessException {
        // 1.方法的形参列表
        Class<?>[] paramTypes = hm.getMethod().getParameterTypes();
        // 2.拿到自定义命令参数所在位置
        // 用户传递参数
        Map<String, String[]> reqParamMap = req.getParameterMap();
        // 构造实参列表
        Object[] paramValues = new Object[paramTypes.length];
        for (Map.Entry<String, String[]> reqParamMapEntry : reqParamMap.entrySet()) {
            String value = Arrays.toString(reqParamMapEntry.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if (!this.paramMapping.containsKey(reqParamMapEntry.getKey())) {
                continue;
            }
            int index = this.paramMapping.get(reqParamMapEntry.getKey());

            // 针对用户传的参数进行类型转换
            paramValues[index] = caseStringValue(value, paramTypes[index]);
        }

        if (this.paramMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if (this.paramMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object result = hm.getMethod().invoke(hm.getController(), paramValues);
        if (null != result) {
            Boolean isModelAndView = hm.getMethod().getReturnType() == ModelAndView.class;
            if (isModelAndView) {
                return (ModelAndView) result;
            }
        }
        return null;
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }

    public Map<String, Integer> getParamMapping() {
        return paramMapping;
    }

    public void setParamMapping(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }
}
