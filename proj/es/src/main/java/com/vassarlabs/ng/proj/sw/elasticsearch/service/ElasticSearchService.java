package com.vassarlabs.ng.proj.sw.elasticsearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassarlabs.ng.proj.sw.elasticsearch.api.*;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.ParsedComposite;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ElasticSearchService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final int BULK = 1000;

    @Autowired
    private RestHighLevelClient client;

    public IndexResponse indexDocument(SourceDocument document) throws IOException {

        IndexRequest indexRequest = new IndexRequest(document.getIndex()).id(document.getId()).source(document.getSource(), document.getType());
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        return indexResponse;
    }

    public BulkResponse bulkIndexDocument(List<SourceDocument> documents) throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        for (SourceDocument document : documents) {
            IndexRequest indexRequest = new IndexRequest(document.getIndex()).id(document.getId()).source(document.getSource(), document.getType());
            bulkRequest.add(indexRequest);
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);


        return bulkResponse;
    }

    public SourceDocument findById(String index, String id) throws IOException {

        //GetRequest getRequest = new GetRequest(index, id);
        //todo my code type by default all
        GetRequest getRequest = new GetRequest(index,"_all",id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        return new SourceDocument(getResponse.getId(), getResponse.getIndex(), getResponse.getSourceAsMap());
    }

    public List<SourceDocument> findAll() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);

    }

    public List<SourceDocument> boolSearch(SearchQuery query) throws IOException {

        SearchRequest searchRequest = new SearchRequest(query.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder queryBuilder = getBoolQueryBuilder(query);

        searchSourceBuilder.query(queryBuilder).size(query.getSize());
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("searchSourceBuilder = " + searchSourceBuilder);

        return getSearchResult(response);

    }

    /*public List<AggregateDocument> compositeAggregation(SearchQuery query) throws IOException {

        SearchRequest searchRequest = new SearchRequest(query.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        for (Map.Entry<String, AggregateQuery> aggregateQueryEntry: query.getAggregateQueryMap().entrySet()) {
            List<CompositeValuesSourceBuilder<?>> sources = new ArrayList<>();

            //Terms Query - Terms

            Query termsQuery = aggregateQueryEntry.getValue().getTermsQuery();

            for (Map.Entry<String,String> termsSourceEntry: termsQuery.getTerms().entrySet()){
                TermsValuesSourceBuilder source = new TermsValuesSourceBuilder(termsSourceEntry.getKey());
                source.field(termsSourceEntry.getValue()).missingBucket(true);
                sources.add(source);
            }

            CompositeAggregationBuilder compositeQuery = AggregationBuilders.composite(aggregateQueryEntry.getKey(), sources);

            //todo myCode
            //CompositeAggregationBuilder compositeQuery = AggregationBuilder.(aggregateQueryEntry.getKey(), sources);

            compositeQuery.size(aggregateQueryEntry.getValue().getSize());

            Query sumQuery = aggregateQueryEntry.getValue().getSumQuery();
            Query filterQuery = aggregateQueryEntry.getValue().getFilterQuery();

            //Terms Query - Script

            for (Map.Entry<String,Script> termsScriptEntry: termsQuery.getScript().entrySet()){
                AggregationBuilder subAggregation = AggregationBuilders.terms(termsScriptEntry.getKey()).script(termsScriptEntry.getValue());
                compositeQuery.subAggregation(subAggregation);
            }

            //Sum Query - Terms

            for (Map.Entry<String,String> sumSourceEntry: sumQuery.getTerms().entrySet()){
                AggregationBuilder subAggregation = AggregationBuilders.sum(sumSourceEntry.getKey()).field(sumSourceEntry.getValue());
                compositeQuery.subAggregation(subAggregation);
            }

            //Sum Query - Script

            for (Map.Entry<String,Script> sumScriptEntry: sumQuery.getScript().entrySet()){
                AggregationBuilder subAggregation = AggregationBuilders.sum(sumScriptEntry.getKey()).script(sumScriptEntry.getValue());
                compositeQuery.subAggregation(subAggregation);
            }

            //Filter Query - Terms

            for (Map.Entry<String,String> filterSourceEntry: filterQuery.getTerms().entrySet()){
                AggregationBuilder subAggregation = AggregationBuilders.sum(filterSourceEntry.getKey()).field(filterSourceEntry.getValue());
                compositeQuery.subAggregation(subAggregation);
            }

            //Filter Query - Script

            for (Map.Entry<String,Script> filterScriptEntry: filterQuery.getScript().entrySet()){
                AggregationBuilder subAggregation = AggregationBuilders.sum(filterScriptEntry.getKey()).script(filterScriptEntry.getValue());
                compositeQuery.subAggregation(subAggregation);
            }

            searchSourceBuilder.aggregation(compositeQuery);
        }

        BoolQueryBuilder queryBuilder = getBoolQueryBuilder(query);
        searchSourceBuilder.query(queryBuilder).size(query.getSize());
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        return getCompositeAggregations(response);

    }*/


    private BoolQueryBuilder getBoolQueryBuilder(SearchQuery query) {

        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        for (Map.Entry<String, String> entry : query.getMatch().entrySet()) {
            queryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }

        for(Map.Entry<String,Boolean> entry: query.getBoolMatch().entrySet()) {
            queryBuilder.must(QueryBuilders.matchQuery(entry.getKey(),entry.getValue()));
        }

        for(Map.Entry<String,Integer> entry: query.getIntMatch().entrySet()) {
            queryBuilder.must(QueryBuilders.matchQuery(entry.getKey(),entry.getValue()));
        }

        for (Map.Entry<String, Number> entry : query.getTerm().entrySet()) {
            queryBuilder.must(QueryBuilders.termQuery(entry.getKey(), entry.getValue()));
        }

        for (Map.Entry<String, Set<Number>> entry : query.getTerms().entrySet()) {
            queryBuilder.must(QueryBuilders.termsQuery(entry.getKey(), entry.getValue()));
        }

        if(query.getRangeFieldName() != null) {
            queryBuilder.must(QueryBuilders.rangeQuery(query.getRangeFieldName()).gte("0"));
        }

        return queryBuilder;
    }

    private List<SourceDocument> getSearchResult(SearchResponse searchResponse) {

        List<SourceDocument> sourceDocuments = new ArrayList<>();
        searchResponse.getHits().forEach(hit ->
                sourceDocuments.add(new SourceDocument(hit.getId(), hit.getIndex(), hit.getSourceAsMap())));

        return sourceDocuments;
    }

    private List<AggregateDocument> getCompositeAggregations(SearchResponse searchResponse) {

        List<AggregateDocument> aggregateDocuments = new ArrayList<>();

        for (Aggregation agg: searchResponse.getAggregations()) {
            ParsedComposite multiBucketAgg = (ParsedComposite) agg;
            List<? extends ParsedComposite.ParsedBucket> buckets = multiBucketAgg.getBuckets();
            for (int i=0; i<buckets.size(); i++) {
                AggregateDocument document = new AggregateDocument();
                document.setName(multiBucketAgg.getName());
                ParsedComposite.ParsedBucket bucket = buckets.get(i);
                document.setKey(bucket.getKey());
                document.setDocCount(bucket.getDocCount());
                bucket.getAggregations().forEach(
                        a -> document.putValue(a.getName(), ((ParsedSum)a).getValue())
                );

                aggregateDocuments.add(document);
            }
        }
        return aggregateDocuments;
    }

}
