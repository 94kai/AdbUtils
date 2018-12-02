package com.xk.adbutils.bean;

import java.util.List;

public class ShellResult {


    private String shell;
    private List<String> resultList;
    
    
    private List<String> errorResultList;

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    public List<String> getErrorResultList() {
        return errorResultList;
    }

    public void setErrorResultList(List<String> errorResultList) {
        this.errorResultList = errorResultList;
    }
}
