package com.vassarlabs.ng.proj.sw.elasticsearch.api;

import java.rmi.server.UID;

import lombok.Data;

@Data
public class ApiResponse {

    private String id = (new UID()).toString();
    private SWASResult result;

    public void setResult(Boolean success, Integer status, Object content) {
        this.result = new SWASResult(success, status, (Object)null, content);
    }

    public void setResult(Boolean success, Integer status, Object metadata, Object content) {
        this.result = new SWASResult(success, status, metadata, content);
    }
}
