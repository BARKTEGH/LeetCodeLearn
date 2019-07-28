package com.zl.es.esdemo;

import com.zl.es.esdemo.customer.Customer;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 使用ElasticsearchTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTemplateTest {

    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert() {
        Customer commodity = new Customer();
        commodity.setId("1501009005");
        commodity.setFirstName("zl");
        commodity.setLastName("llll");

        IndexQuery indexQuery = new IndexQueryBuilder().withObject(commodity).build();
        elasticsearchTemplate.index(indexQuery);

    }

    @Test
    public void testQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhrasePrefixQuery("firstName", "z"))
                .build();
        List<Customer> list = elasticsearchTemplate.queryForList(searchQuery, Customer.class);
        System.out.println(list);
    }

}

