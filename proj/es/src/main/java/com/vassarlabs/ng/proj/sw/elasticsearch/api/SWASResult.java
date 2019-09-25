package com.vassarlabs.ng.proj.sw.elasticsearch.api;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SWASResult {

    private Boolean success;

    private Integer status;

    private Object metadata;

    private Object content;

}

