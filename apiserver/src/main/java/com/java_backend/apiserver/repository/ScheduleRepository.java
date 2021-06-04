package com.java_backend.apiserver.repository;

import com.java_backend.apiserver.model.Schedule;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface ScheduleRepository extends MongoRepository<Schedule,String>{
    
}
