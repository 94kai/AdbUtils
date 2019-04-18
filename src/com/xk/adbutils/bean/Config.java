package com.xk.adbutils.bean;

import java.util.List;
import java.util.Map;

public class Config {

    public static Config instance;
    private List<Device> deviceList;
    private List<AdbCmd> adbCmdList;
    private Map<String, String> variateList;



    public static void setInstance(Config instance) {
        Config.instance = instance;
    }

    public Map<String, String> getVariateList() {
        return variateList;
    }


    public static Config getInstance() {
        return instance;
    }


    public void setVariateList(Map<String, String> variateList) {
        this.variateList = variateList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<AdbCmd> getAdbCmdList() {
        return adbCmdList;
    }

    public void setAdbCmdList(List<AdbCmd> adbCmdList) {
        this.adbCmdList = adbCmdList;
    }

    public class Device {
        private String deviceName;
        private String deviceId;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
    }

    public class AdbCmd {
        private String funName;
        private List<String> cmdArray;
        private boolean isHide;

        public boolean isHide() {
            return isHide;
        }

        public void setHide(boolean hide) {
            isHide = hide;
        }

        public String getFunName() {
            return funName;
        }

        public void setFunName(String funName) {
            this.funName = funName;
        }

        public List<String> getCmdArray() {
            return cmdArray;
        }

        public void setCmdArray(List<String> cmdArray) {
            this.cmdArray = cmdArray;
        }
    }

}
