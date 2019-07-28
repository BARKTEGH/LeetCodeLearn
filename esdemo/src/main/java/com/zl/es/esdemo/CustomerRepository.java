package com.zl.es.esdemo;

import com.zl.es.esdemo.customer.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer,String> {
}
