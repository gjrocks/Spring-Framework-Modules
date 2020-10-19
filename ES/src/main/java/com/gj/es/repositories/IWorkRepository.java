package com.gj.es.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gj.es.model.WorkDTO;

public interface IWorkRepository extends ElasticsearchRepository<WorkDTO,Long>{
WorkDTO findByWorkID(String workid);
}
