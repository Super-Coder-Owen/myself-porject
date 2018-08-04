package com.vip.spring.formework.webmvc;

import java.io.File;

/**
 * 1.将静态文件变成动态文件
 * 2.根据用户参数，产生不同的结果
 */
public class ViewResolver {
    private String viewName;

    private File templateFile;

    public ViewResolver(String viewName, File templateFile) {

    }

    public String viewResolver(ModelAndView modelAndView) {

        return null;
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
