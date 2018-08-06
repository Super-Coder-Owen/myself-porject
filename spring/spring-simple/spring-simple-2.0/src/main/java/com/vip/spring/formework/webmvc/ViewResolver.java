package com.vip.spring.formework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.将静态文件变成动态文件
 * 2.根据用户参数，产生不同的结果
 */
public class ViewResolver {
    private String viewName;

    private File templateFile;


    public ViewResolver(String viewName, File templateFile) {
        this.templateFile = templateFile;
        this.viewName = viewName;
    }

    public String viewResolver(ModelAndView modelAndView) throws Exception {
        StringBuffer sb = new StringBuffer();
        RandomAccessFile ra = new RandomAccessFile(this.templateFile, "r");
        String line = null;
        while (null != (line = ra.readLine())) {
            Matcher m = matcher(line);
            while (m.find()) {
                for (int i = 1; i <= m.groupCount(); i++) {
                    // 要把￥{}中间的字符串取出来
                    String paramName = m.group();
                    Object paramValue = modelAndView.getModel().get(paramName);
                    if (null == paramValue) {
                        continue;
                    }
                    line = line.replace("$\\{" + paramName + "\\}", paramValue.toString());
                }
            }
            sb.append(line);
        }
        return sb.toString();
    }

    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }
}
