package com.glens.jksd.bean.deteect;

public class GroundUpImgBean {

    /**
     * data : {"recodeCode":"c7d302753cd94dbd9b567877a686512e"}
     * httpCode : 200
     * msg : 操作成功！
     * result : true
     */

    private DataBean data;
    private int httpCode;
    private String msg;
    private boolean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * recodeCode : c7d302753cd94dbd9b567877a686512e
         */

        private String recodeCode;

        public String getRecodeCode() {
            return recodeCode;
        }

        public void setRecodeCode(String recodeCode) {
            this.recodeCode = recodeCode;
        }
    }
}
