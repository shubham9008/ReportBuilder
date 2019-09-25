package com.vassarlabs.ng.proj.sw.elasticsearch.service;

import com.vassarlabs.ng.proj.sw.elasticsearch.api.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;

import java.io.IOException;
import java.util.Collections;

public class Test {

    public static void main(String[] args) throws IOException {
        ElasticSearchService elasticSearchService = new ElasticSearchService();
        SearchQuery query = new SearchQuery("student_master");
        query.setSize(0);

//        elasticSearchService.testScriptAggregation(query);

//        query.setSize(0);
//        query.putMatch("monthData.1.cfmsStatus.keyword", "3");
//        query.putMatch("acYear.keyword", "2018-19");
//
//        AggregateQuery aggregateQuery6 = new AggregateQuery();
//        aggregateQuery6.setSize(1000);
//        aggregateQuery6.putTerms("acYear", "acYear.keyword");
//        aggregateQuery6.putTerms("deptCode", "deptCode");
//        aggregateQuery6.putTerms("collegeCode", "collegeCode");
//        aggregateQuery6.putTerms("mtfProceedingNo_month6", "monthData.6.proceedingsNo.keyword");
//        aggregateQuery6.putTerms("billGeneratedDate_month6", "monthData.6.billGeneratedDate");
//        aggregateQuery6.putTerms("billSubmittedDate_month6", "monthData.6.tbrDate");
//        aggregateQuery6.putTerms("transactionCompletedDate_month6", "monthData.6.transCompletedDate");
//
//        aggregateQuery6.putSum("mtfReleaseAmount_month6", "monthData.6.mtfRelease");
//        AggregateQuery aggregateQuery1 = new AggregateQuery();
//        aggregateQuery1.setSize(1000);
//        aggregateQuery1.putTerms("acYear", "acYear.keyword");
//        aggregateQuery1.putTerms("deptCode", "deptCode");
//        aggregateQuery1.putTerms("collegeCode", "collegeCode");
//        aggregateQuery1.putTerms("mtfProceedingNo_month1", "monthData.1.proceedingsNo.keyword");
//        aggregateQuery1.putTerms("billGeneratedDate_month1", "monthData.1.billGeneratedDate");
//        aggregateQuery1.putTerms("billSubmittedDate_month1", "monthData.1.tbrDate");
//        aggregateQuery1.putTerms("transactionCompletedDate_month1", "monthData.1.transCompletedDate");
//
//        aggregateQuery1.putSum("mtfReleaseAmount_month1", "monthData.1.mtfRelease");
//
//        query.putAggregate("my_buckets_month1", aggregateQuery1);

//          query.setSize(0);
//          query.putMatch("acYear.keyword", "2018-19");
//
//
//          AggregateQuery aggregateQuery = new AggregateQuery();
//          aggregateQuery.putTerms("acYear", "acYear.keyword");
//          aggregateQuery.setSize(1000);
//          aggregateQuery.putTerms("collegeCode", "collegeCode");
//          aggregateQuery.putTerms("studentId", "entityId.keyword");
//          aggregateQuery.putTerms("studentName", "entityName.keyword");
//          aggregateQuery.putTerms("courseCode", "courseCode");
//          aggregateQuery.putTerms("courseYear", "courseYear");
//
//          ScriptQuery scriptQuery = new ScriptQuery();
//          scriptQuery.setScriptType(ScriptType.INLINE);
//          scriptQuery.setLanguage("painless");
//          scriptQuery.setCode("int total = 0; " +
//                "if(doc.containsKey('monthData.1.mtfDemand') && !(doc['monthData.1.mtfDemand'].size()==0)) {" +
//                "total += doc['monthData.1.mtfDemand'].value" +
//                "}");
//          scriptQuery.setParams(Collections.emptyMap());
//
//          Script script = new Script(scriptQuery.getScriptType(),scriptQuery.getLanguage(),scriptQuery.getCode(),scriptQuery.getParams());
//
//          aggregateQuery.putScript("mtfReleaseSum", script);
//
//          query.putAggregate("my_buckets", aggregateQuery);
/*
          AggregateQuery aggQuery = new AggregateQuery();
          aggQuery.setSize(1000);

          ScriptQuery scriptQuery = new ScriptQuery();
          scriptQuery.setScriptType(ScriptType.INLINE);
          scriptQuery.setLanguage("painless");
          scriptQuery.setCode("int total = 0; " +
                "if(doc.containsKey('monthData.1.mtfDemand') && !(doc['monthData.1.mtfDemand'].size()==0)) {" +
                "total += doc['monthData.1.mtfDemand'].value" +
                "}");
          scriptQuery.setParams(Collections.emptyMap());

          Script script = new Script(scriptQuery.getScriptType(),scriptQuery.getLanguage(),scriptQuery.getCode(),scriptQuery.getParams());

          aggQuery.putTermsQuery("acYear", "acYear.keyword");
          aggQuery.putTermsQuery("studentId", "entityId.keyword");
          aggQuery.putSumQuery("mtfReleaseAmount_month1", "monthData.1.mtfRelease");
          aggQuery.putTermsQuery("mtfReleasesSum", script);
          aggQuery.putSumQuery("sumQuery1", script);

          query.putAggregate("my_buckets",aggQuery);*/

          //elasticSearchService.compositeAggregation(query);
    }
}
