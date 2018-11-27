package com.xk.mutils;

import java.util.List;

public class Config {
    private String adbPath;


    private List<Device> deviceList;
    private List<AdbCmd> adbCmdList;


    public String getAdbPath() {
        return adbPath;
    }

    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
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
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
