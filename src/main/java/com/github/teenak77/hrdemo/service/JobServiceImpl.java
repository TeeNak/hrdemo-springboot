package com.github.teenak77.hrdemo.service;

import com.github.teenak77.hrdemo.model.domain.Job;
import com.github.teenak77.hrdemo.repository.JobRepository;
import com.github.teenak77.hrdemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by teenak on 2015/02/07.
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepo;

    public Collection<Job> getAll() {
        return jobRepo.findAll();
    }

    public Job add(Job job) {
        return jobRepo.save(job);
    }

    public Job getByKey(Integer key) {
        return jobRepo.findOne(key);
    }

    public void updateOne(Job job) {
        jobRepo.save(job);
    }

    public void deleteOne(Job job) {
        jobRepo.delete(job);
    }
}
