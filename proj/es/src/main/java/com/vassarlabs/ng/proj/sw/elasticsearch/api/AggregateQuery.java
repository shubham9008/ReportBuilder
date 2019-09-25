package com.vassarlabs.ng.proj.sw.elasticsearch.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elasticsearch.script.Script;

@Getter
@AllArgsConstructor
public class AggregateQuery {

    int size;
    Query termsQuery;
    Query sumQuery;
    Query filterQuery;

    public AggregateQuery() {
        size = 1000;
        termsQuery = new Query();
        sumQuery = new Query();
        filterQuery = new Query();
    }

    public void setSize(int size) { this.size = size; }

    public void putTermsQuery(String name, String field) { this.termsQuery.putTerms(name, field); }

    public void putTermsQuery(String name, Script code) { this.termsQuery.putScript(name, code); }

    public void putSumQuery(String name, String field) { this.sumQuery.putTerms(name, field); }

    public void putSumQuery(String name, Script code) { this.sumQuery.putScript(name, code); }

    public void putFilterQuery(String name, String field) { this.filterQuery.putTerms(name, field); }

    public void putFilterQuery(String name, Script code) { this.filterQuery.putScript(name, code); }


}
