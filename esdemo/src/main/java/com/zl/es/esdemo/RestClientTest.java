package com.zl.es.esdemo;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author PC028
 */
@Service
public class RestClientTest {

     private static Logger logger = LogManager.getRootLogger();

     @Autowired
    private RestHighLevelClient client;

     public void IndexDocument() {
         try {
             // 1、创建索引请求
             IndexRequest request = new IndexRequest(
                     "mess",   //索引
                     "_doc",     // mapping type
                     "1");     //文档id

             // 2、准备文档数据
             // 方式一：直接给JSON串
             String jsonString = "{" +
                     "\"user\":\"kimchy\"," +
                     "\"postDate\":\"2013-01-30\"," +
                     "\"message\":\"trying out Elasticsearch\"" +
                     "}";
             request.source(jsonString, XContentType.JSON);

             // 方式二：以map对象来表示文档
            /*
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("user", "kimchy");
            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearch");
            request.source(jsonMap);
            */

             // 方式三：用XContentBuilder来构建文档
            /*
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.field("user", "kimchy");
                builder.field("postDate", new Date());
                builder.field("message", "trying out Elasticsearch");
            }
            builder.endObject();
            request.source(builder);
            */

             // 方式四：直接用key-value对给出
            /*
            request.source("user", "kimchy",
                            "postDate", new Date(),
                            "message", "trying out Elasticsearch");
            */

             //3、其他的一些可选设置
            /*
            request.routing("routing");  //设置routing值
            request.timeout(TimeValue.timeValueSeconds(1));  //设置主分片等待时长
            request.setRefreshPolicy("wait_for");  //设置重刷新策略
            request.version(2);  //设置版本号
            request.opType(DocWriteRequest.OpType.CREATE);  //操作类别
            */

             //4、发送请求
             IndexResponse indexResponse = null;
             try {
                 // 同步方式
                 indexResponse = client.index(request);
             } catch (ElasticsearchException e) {
                 // 捕获，并处理异常
                 //判断是否版本冲突、create但文档已存在冲突
                 if (e.status() == RestStatus.CONFLICT) {
                     logger.error("冲突了，请在此写冲突处理逻辑！\n" + e.getDetailedMessage());
                 }

                 logger.error("索引异常", e);
             }

             //5、处理响应
             if (indexResponse != null) {
                 String index = indexResponse.getIndex();
                 String type = indexResponse.getType();
                 String id = indexResponse.getId();
                 long version = indexResponse.getVersion();
                 if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                     System.out.println("新增文档成功，处理逻辑代码写到这里。");
                 } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                     System.out.println("修改文档成功，处理逻辑代码写到这里。");
                 }
                 // 分片处理信息
                 ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
                 if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

                 }
                 // 如果有分片副本失败，可以获得失败原因信息
                 if (shardInfo.getFailed() > 0) {
                     for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                         String reason = failure.reason();
                         System.out.println("副本失败原因：" + reason);
                     }
                 }
             }


             //异步方式发送索引请求
            /*ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            };
            client.indexAsync(request, listener);
            */

         } catch (IOException e) {
             e.printStackTrace();
         }
     }


     public void get() {
            try {
            // 1、创建获取文档请求
            GetRequest request = new GetRequest(
                    "mess",   //索引
                    "_doc",     // mapping type
                    "1");     //文档id

            // 2、可选的设置
            //request.routing("routing");
            //request.version(2);

            //request.fetchSourceContext(new FetchSourceContext(false)); //是否获取_source字段
            //选择返回的字段
            String[] includes = new String[]{"message", "*Date"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);

            //也可写成这样
            /*String[] includes = Strings.EMPTY_ARRAY;
            String[] excludes = new String[]{"message"};
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);*/


            // 取stored字段
            /*request.storedFields("message");
            GetResponse getResponse = client.get(request);
            String message = getResponse.getField("message").getValue();*/


            //3、发送请求
            GetResponse getResponse = null;
            try {
                // 同步请求
                getResponse = client.get(request);
            } catch (ElasticsearchException e) {
                if (e.status() == RestStatus.NOT_FOUND) {
                    logger.error("没有找到该id的文档" );
                }
                if (e.status() == RestStatus.CONFLICT) {
                    logger.error("获取时版本冲突了，请在此写冲突处理逻辑！" );
                }
                logger.error("获取文档异常", e);
            }

            //4、处理响应
            if(getResponse != null) {
                String index = getResponse.getIndex();
                String type = getResponse.getType();
                String id = getResponse.getId();
                if (getResponse.isExists()) { // 文档存在
                    long version = getResponse.getVersion();
                    String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
                    Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
                    byte[] sourceAsBytes = getResponse.getSourceAsBytes();    //结果取成字节数组

                    logger.info("index:" + index + "  type:" + type + "  id:" + id);
                    logger.info(sourceAsString);

                } else {
                    logger.error("没有找到该id的文档" );
                }
            }


            //异步方式发送获取文档请求
            /*
            ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
                @Override
                public void onResponse(GetResponse getResponse) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            };
            client.getAsync(request, listener);
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

     }

     public void BulkDemo(){
        try{
         // 1、创建批量操作请求
            BulkRequest request = new BulkRequest();
            request.add(new IndexRequest("mess", "_doc", "1")
                    .source(XContentType.JSON,"field", "foo"));
            request.add(new IndexRequest("mess", "_doc", "2")
                    .source(XContentType.JSON,"field", "bar"));
            request.add(new IndexRequest("mess", "_doc", "3")
                    .source(XContentType.JSON,"field", "baz"));

            /*
            request.add(new DeleteRequest("mess", "_doc", "3"));
            request.add(new UpdateRequest("mess", "_doc", "2")
                    .doc(XContentType.JSON,"other", "test"));
            request.add(new IndexRequest("mess", "_doc", "4")
                    .source(XContentType.JSON,"field", "baz"));
            */

            // 2、可选的设置
            /*
            request.timeout("2m");
            request.setRefreshPolicy("wait_for");
            request.waitForActiveShards(2);
            */


            //3、发送请求

            // 同步请求
            BulkResponse bulkResponse = client.bulk(request);


            //4、处理响应
            if(bulkResponse != null) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                    if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        //TODO 新增成功的处理

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                       //TODO 修改成功的处理

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                        //TODO 删除成功的处理
                    }
                }
            }


            //异步方式发送批量操作请求
            /*
            ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
                @Override
                public void onResponse(BulkResponse bulkResponse) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            };
            client.bulkAsync(request, listener);
            */

        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public void search() throws IOException {

         // 1、创建search请求
            //SearchRequest searchRequest = new SearchRequest();
            SearchRequest searchRequest = new SearchRequest("bank");
            searchRequest.types("_doc");

            // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            //构造QueryBuilder
            /*QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("user", "kimchy")
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
            sourceBuilder.query(matchQueryBuilder);*/

            sourceBuilder.query(QueryBuilders.termQuery("age", 24));
            sourceBuilder.from(0);
            sourceBuilder.size(10);
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

            //是否返回_source字段
            //sourceBuilder.fetchSource(false);

            //设置返回哪些字段
            /*String[] includeFields = new String[] {"title", "user", "innerObject.*"};
            String[] excludeFields = new String[] {"_type"};
            sourceBuilder.fetchSource(includeFields, excludeFields);*/

            //指定排序
            //sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            //sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));

            // 设置返回 profile
            //sourceBuilder.profile(true);

            //将请求体加入到请求中
            searchRequest.source(sourceBuilder);

            // 可选的设置
            //searchRequest.routing("routing");

            // 高亮设置
            /*
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightBuilder.Field highlightTitle =
                    new HighlightBuilder.Field("title");
            highlightTitle.highlighterType("unified");
            highlightBuilder.field(highlightTitle);
            HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("user");
            highlightBuilder.field(highlightUser);
            sourceBuilder.highlighter(highlightBuilder);*/


            //加入聚合
            /*TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
                    .field("company.keyword");
            aggregation.subAggregation(AggregationBuilders.avg("average_age")
                    .field("age"));
            sourceBuilder.aggregation(aggregation);*/

            //做查询建议
            /*SuggestionBuilder termSuggestionBuilder =
                    SuggestBuilders.termSuggestion("user").text("kmichy");
                SuggestBuilder suggestBuilder = new SuggestBuilder();
                suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);*/

            //3、发送请求
            SearchResponse searchResponse = client.search(searchRequest);


            //4、处理响应
            //搜索结果状态信息
            RestStatus status = searchResponse.status();
            TimeValue took = searchResponse.getTook();
            Boolean terminatedEarly = searchResponse.isTerminatedEarly();
            boolean timedOut = searchResponse.isTimedOut();

            //分片搜索情况
            int totalShards = searchResponse.getTotalShards();
            int successfulShards = searchResponse.getSuccessfulShards();
            int failedShards = searchResponse.getFailedShards();
            for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
                // failures should be handled here
            }

            //处理搜索命中文档结果
            SearchHits hits = searchResponse.getHits();

            long totalHits = hits.getTotalHits();
            float maxScore = hits.getMaxScore();

            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                // do something with the SearchHit

                String index = hit.getIndex();
                String type = hit.getType();
                String id = hit.getId();
                float score = hit.getScore();

                //取_source字段值
                String sourceAsString = hit.getSourceAsString();
                //取成json串
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                // 取成map对象
                //从map中取字段值
                /*
                String documentTitle = (String) sourceAsMap.get("title");
                List<Object> users = (List<Object>) sourceAsMap.get("user");
                Map<String, Object> innerObject = (Map<String, Object>) sourceAsMap.get("innerObject");
                */
                logger.info("index:" + index + "  type:" + type + "  id:" + id);
                logger.info(sourceAsString);

                //取高亮结果
                /*Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlight = highlightFields.get("title");
                Text[] fragments = highlight.fragments();
                String fragmentString = fragments[0].string();*/
            }

            // 获取聚合结果
            /*
            Aggregations aggregations = searchResponse.getAggregations();
            Terms byCompanyAggregation = aggregations.get("by_company");
            Bucket elasticBucket = byCompanyAggregation.getBucketByKey("Elastic");
            Avg averageAge = elasticBucket.getAggregations().get("average_age");
            double avg = averageAge.getValue();
            */

            // 获取建议结果
            /*Suggest suggest = searchResponse.getSuggest();
            TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user");
            for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
                for (TermSuggestion.Entry.Option option : entry) {
                    String suggestText = option.getText().string();
                }
            }
            */

            //异步方式发送获查询请求
            /*
            ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
                @Override
                public void onResponse(SearchResponse getResponse) {
                    //结果获取
                }

                @Override
                public void onFailure(Exception e) {
                    //失败处理
                }
            };
            client.searchAsync(searchRequest, listener);
            */
     }

     public void highlight() throws IOException {
         // 1、创建search请求
            SearchRequest searchRequest = new SearchRequest("hl_test");

            // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            //构造QueryBuilder
            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "lucene solr");
            sourceBuilder.query(matchQueryBuilder);

            //分页设置
            /*sourceBuilder.from(0);
            sourceBuilder.size(5); ;*/


            // 高亮设置
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.requireFieldMatch(false).field("title").field("content")
                .preTags("<strong>").postTags("</strong>");
            //不同字段可有不同设置，如不同标签
            /*HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
            highlightTitle.preTags("<strong>").postTags("</strong>");
            highlightBuilder.field(highlightTitle);
            HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
            highlightContent.preTags("<b>").postTags("</b>");
            highlightBuilder.field(highlightContent).requireFieldMatch(false);*/

            sourceBuilder.highlighter(highlightBuilder);

            searchRequest.source(sourceBuilder);

            //3、发送请求
            SearchResponse searchResponse = client.search(searchRequest);


            //4、处理响应
            if(RestStatus.OK.equals(searchResponse.status())) {
                //处理搜索命中文档结果
                SearchHits hits = searchResponse.getHits();
                long totalHits = hits.getTotalHits();

                SearchHit[] searchHits = hits.getHits();
                for (SearchHit hit : searchHits) {
                    String index = hit.getIndex();
                    String type = hit.getType();
                    String id = hit.getId();
                    float score = hit.getScore();

                    //取_source字段值
                    //String sourceAsString = hit.getSourceAsString(); //取成json串
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 取成map对象
                    //从map中取字段值
                    /*String title = (String) sourceAsMap.get("title");
                    String content  = (String) sourceAsMap.get("content"); */
                    logger.info("index:" + index + "  type:" + type + "  id:" + id);
                    logger.info("sourceMap : " +  sourceAsMap);
                    //取高亮结果
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    HighlightField highlight = highlightFields.get("title");
                    if(highlight != null) {
                        Text[] fragments = highlight.fragments();  //多值的字段会有多个值
                        if(fragments != null) {
                            String fragmentString = fragments[0].string();
                            logger.info("title highlight : " +  fragmentString);
                            //可用高亮字符串替换上面sourceAsMap中的对应字段返回到上一级调用
                            //sourceAsMap.put("title", fragmentString);
                        }
                    }

                    highlight = highlightFields.get("content");
                    if(highlight != null) {
                        Text[] fragments = highlight.fragments();  //多值的字段会有多个值
                        if(fragments != null) {
                            String fragmentString = fragments[0].string();
                            logger.info("content highlight : " +  fragmentString);
                            //可用高亮字符串替换上面sourceAsMap中的对应字段返回到上一级调用
                            //sourceAsMap.put("content", fragmentString);
                        }
                    }
                }
            }
     }

     public void SuggestDemo() throws IOException {
         // 1、创建search请求
            //SearchRequest searchRequest = new SearchRequest();
            SearchRequest searchRequest = new SearchRequest("mess");

            // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            sourceBuilder.size(0);

            //做查询建议
            //词项建议
            SuggestionBuilder termSuggestionBuilder =
                    SuggestBuilders.termSuggestion("user").text("kmichy");
            SuggestBuilder suggestBuilder = new SuggestBuilder();
            suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);

            searchRequest.source(sourceBuilder);

            //3、发送请求
            SearchResponse searchResponse = client.search(searchRequest);


            //4、处理响应
            //搜索结果状态信息
            if(RestStatus.OK.equals(searchResponse.status())) {
                // 获取建议结果
                Suggest suggest = searchResponse.getSuggest();
                TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user");
                for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
                    logger.info("text: " + entry.getText().string());
                    for (TermSuggestion.Entry.Option option : entry) {
                        String suggestText = option.getText().string();
                        logger.info("   suggest option : " + suggestText);
                    }
                }
            }
            /*
              "suggest": {
                "my-suggestion": [
                  {
                    "text": "tring",
                    "offset": 0,
                    "length": 5,
                    "options": [
                      {
                        "text": "trying",
                        "score": 0.8,
                        "freq": 1
                      }
                    ]
                  },
                  {
                    "text": "out",
                    "offset": 6,
                    "length": 3,
                    "options": []
                  },
                  {
                    "text": "elasticsearch",
                    "offset": 10,
                    "length": 13,
                    "options": []
                  }
                ]
              }*/
    }

    //自动补全，根据用户的输入联想到可能的词或者短语
    public  void completionSuggester() {
        try {

            // 1、创建search请求
            //SearchRequest searchRequest = new SearchRequest();
            SearchRequest searchRequest = new SearchRequest("music");

            // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            sourceBuilder.size(0);

            //做查询建议
            //自动补全
            /*POST music/_search?pretty
                    {
                        "suggest": {
                            "song-suggest" : {
                                "prefix" : "lucene s",
                                "completion" : {
                                    "field" : "suggest" ,
                                    "skip_duplicates": true
                                }
                            }
                        }
                    }*/

            SuggestionBuilder termSuggestionBuilder =
                    SuggestBuilders.completionSuggestion("suggest").prefix("lucene s")
                    .skipDuplicates(true);
            SuggestBuilder suggestBuilder = new SuggestBuilder();
            suggestBuilder.addSuggestion("song-suggest", termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);

            searchRequest.source(sourceBuilder);

            //3、发送请求
            SearchResponse searchResponse = client.search(searchRequest);


            //4、处理响应
            //搜索结果状态信息
            if(RestStatus.OK.equals(searchResponse.status())) {
                // 获取建议结果
                Suggest suggest = searchResponse.getSuggest();
                CompletionSuggestion termSuggestion = suggest.getSuggestion("song-suggest");
                for (CompletionSuggestion.Entry entry : termSuggestion.getEntries()) {
                    logger.info("text: " + entry.getText().string());
                    for (CompletionSuggestion.Entry.Option option : entry) {
                        String suggestText = option.getText().string();
                        logger.info("   suggest option : " + suggestText);
                    }
                }
            }

        } catch (IOException e) {
            logger.error(e);
        }
    }

   public void AggregationDemo () throws IOException {
         // 1、创建search请求
            //SearchRequest searchRequest = new SearchRequest();
            SearchRequest searchRequest = new SearchRequest("bank");

            // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            sourceBuilder.size(0);

            //加入聚合
            //字段值项分组聚合
            TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_age")
                    .field("age").order(BucketOrder.aggregation("average_balance", true));
            //计算每组的平均balance指标
            aggregation.subAggregation(AggregationBuilders.avg("average_balance")
                    .field("balance"));
            sourceBuilder.aggregation(aggregation);

            searchRequest.source(sourceBuilder);

            //3、发送请求
            SearchResponse searchResponse = client.search(searchRequest);

            //4、处理响应
            //搜索结果状态信息
            if(RestStatus.OK.equals(searchResponse.status())) {
                // 获取聚合结果
                Aggregations aggregations = searchResponse.getAggregations();
                Terms byAgeAggregation = aggregations.get("by_age");
                logger.info("aggregation by_age 结果");
                logger.info("docCountError: " + byAgeAggregation.getDocCountError());
                logger.info("sumOfOtherDocCounts: " + byAgeAggregation.getSumOfOtherDocCounts());
                logger.info("------------------------------------");
                for(Terms.Bucket buck : byAgeAggregation.getBuckets()) {
                    logger.info("key: " + buck.getKeyAsNumber());
                    logger.info("docCount: " + buck.getDocCount());
                    logger.info("docCountError: " + buck.getDocCountError());
                    //取子聚合
                    Avg averageBalance = buck.getAggregations().get("average_balance");

                    logger.info("average_balance: " + averageBalance.getValue());
                    logger.info("------------------------------------");
                }
                //直接用key 来去分组
                /*Bucket elasticBucket = byCompanyAggregation.getBucketByKey("24");
                Avg averageAge = elasticBucket.getAggregations().get("average_age");
                double avg = averageAge.getValue();*/

            }

   }

    public static void main(String[] args) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("es-customer");
        searchRequest.types("customer");

        SearchSourceBuilder smith = new SearchSourceBuilder();
        smith.query(QueryBuilders.queryStringQuery("Smith"));
        smith.from(0);
        smith.size(10);
        searchRequest.source(smith);

        RestHighLevelClient  clients = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        SearchResponse searchResponse = clients.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse);
        //4、处理响应
        //搜索结果状态信息
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        //分片搜索情况
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }

        //处理搜索命中文档结果
        SearchHits hits = searchResponse.getHits();

        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();

        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit : searchHits) {
            // do something with the SearchHit
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();

            //取_source字段值
            String sourceAsString = hit.getSourceAsString();
            //取成json串
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            // 取成map对象
            //从map中取字段值
            /*
            String documentTitle = (String) sourceAsMap.get("title");
            List<Object> users = (List<Object>) sourceAsMap.get("user");
            Map<String, Object> innerObject = (Map<String, Object>) sourceAsMap.get("innerObject");
            */
            logger.info("index:" + index + "  type:" + type + "  id:" + id);
            logger.info(sourceAsString);

        }
        clients.close();

    }




}
