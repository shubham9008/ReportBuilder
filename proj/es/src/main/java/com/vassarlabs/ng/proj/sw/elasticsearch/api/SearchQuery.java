package com.vassarlabs.ng.proj.sw.elasticsearch.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public class SearchQuery {

    String index;
    int from;
    int size;
    Map<String, String> match;
    Map<String, Boolean> boolMatch;
    Map<String, Integer> intMatch;
    String rangeFieldName;
    Map<String, Number> term;
    Map<String, Set<Number>> terms;
    Map<String, AggregateQuery> aggregateQueryMap;


    public SearchQuery() {
        from = 0;
        size = 1000;
        match = new HashMap<>();
        boolMatch = new HashMap<>();
        intMatch = new HashMap<>();
        term = new HashMap<>();
        terms = new HashMap<>();
        aggregateQueryMap = new HashMap<>();
    }

    public SearchQuery(String index) {
        this.index = index;
        from = 0;
        size = 1000;
        match = new HashMap<>();
        boolMatch = new HashMap<>();
        intMatch = new HashMap<>();
        term = new HashMap<>();
        terms = new HashMap<>();
        aggregateQueryMap = new HashMap<>();
    }

    public void setSize(int size) { this.size = size; }

    public void putMatch(String key, String val) {
        this.match.put(key, val);
    }

    public void putBoolMatch(String key, Boolean val) {this.boolMatch.put(key, val);}

    public void putIntMatch(String key, Integer val) {this.intMatch.put(key, val);}

    public void putRangeFieldName(String fieldName) {this.rangeFieldName = fieldName;}

    public void putTerm(String key, Number val) {
        this.term.put(key, val);
    }

    public void putTerms(String key, Set<Number> val) {
        this.terms.put(key, val);
    }

    public void putAggregate(String key, AggregateQuery val) {
        this.aggregateQueryMap.put(key, val);
    }


}
