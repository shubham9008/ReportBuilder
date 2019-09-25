/*
package com.vassarlabs.ng.proj.sw.elasticsearch.controller;


import com.vassarlabs.ng.proj.sw.elasticsearch.api.ApiResponse;
import com.vassarlabs.ng.proj.sw.elasticsearch.api.ApiResponseFactory;
import com.vassarlabs.ng.proj.sw.elasticsearch.api.SearchQuery;
import com.vassarlabs.ng.proj.sw.elasticsearch.api.SourceDocument;
import com.vassarlabs.ng.proj.sw.elasticsearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @RequestMapping(value = "es/index/", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse indexDocument(@RequestBody SourceDocument document)  {
        try {
            return ApiResponseFactory.createResponse(elasticSearchService.indexDocument(document));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResponseFactory.createErrorResponse(500, "Could not index data due to internal server error!");
    }

    @RequestMapping(value = "es/bulk/", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse bulkIndexDocument(@RequestBody List<SourceDocument> documents)  {
        try {
            return ApiResponseFactory.createResponse(elasticSearchService.bulkIndexDocument(documents));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResponseFactory.createErrorResponse(500, "Could not index data due to internal server error!");
    }

    @RequestMapping(value = "es/search/{index}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse findById(@PathVariable("index") String index, @PathVariable("id") String id)  {
        try {
            return ApiResponseFactory.createResponse(elasticSearchService.findById(index, id));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResponseFactory.createErrorResponse(500, "Internal server error!");
    }

    @RequestMapping(value = "es/search", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse boolSearch(@RequestBody SearchQuery query)  {
        try {
            return ApiResponseFactory.createResponse(elasticSearchService.boolSearch(query));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResponseFactory.createErrorResponse(500, "Internal server error!");
    }


}
*/
