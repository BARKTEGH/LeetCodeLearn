package com.zl.es.esdemo;

import com.zl.es.esdemo.customer.Customer;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Repository方式来使用es
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public long count() {
        return customerRepository.count();
    }


    public Customer save(Customer commodity) {
        return customerRepository.save(commodity);
    }


    public void delete(Customer commodity) {
        customerRepository.delete(commodity);
//        commodityRepository.deleteById(commodity.getSkuId());
    }


    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }


    public List<Customer> getByName(String name) {
        List<Customer> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("firstName", name);
        Iterable<Customer> iterable = customerRepository.search(matchQueryBuilder);
        iterable.forEach(e->list.add(e));
        return list;
    }


    public Page<Customer> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("firstName", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return customerRepository.search(searchQuery);
    }

}
