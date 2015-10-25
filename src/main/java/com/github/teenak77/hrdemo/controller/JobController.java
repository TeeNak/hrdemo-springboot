package com.github.teenak77.hrdemo.controller;

import com.github.teenak77.hrdemo.exception.ResourceNotFoundException;
import com.github.teenak77.hrdemo.model.domain.Job;
import com.github.teenak77.hrdemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by teenak77 on 2015/07/20.
 */
@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/")
    public String root(Model model) {
        return "redirect:/secure/job/list";
    }


    @RequestMapping(value = "/secure/job/list", method = RequestMethod.GET)
    public ModelAndView  dispJobList(Map<String, Object> model) {
        //model.put("jobList", jobService.getAll());
        //return "jobList";

        return new ModelAndView("jobs/list", "jobs", jobService.getAll() );
    }


    @RequestMapping(value = "/secure/job/new", method = RequestMethod.GET)
    public String createNew(@ModelAttribute Job job) {
        return "jobs/form";
    }

    @RequestMapping(value = "/secure/job/new", method = RequestMethod.POST)
    public ModelAndView doCreateNew(@Valid Job job, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("jobs/form", "formErrors", result.getAllErrors());
        }
        job = this.jobService.add(job);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new job");
        return new ModelAndView("redirect:/secure/job/{job.jobId}", "job.jobId", job.getJobId());
    }

    @RequestMapping(value="/secure/job/{jobId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("jobId") Integer jobId)
    {
        Job job = jobService.getByKey(jobId);
        if( job == null ) {
            throw new ResourceNotFoundException();
        }

        return new ModelAndView("jobs/form", "job", job);
    }

    @RequestMapping(value="/secure/job/{jobId}", method = RequestMethod.POST)
    public ModelAndView doEdit(@Valid Job job, BindingResult result,
                             RedirectAttributes redirect)
    {
        if (result.hasErrors()) {
            return new ModelAndView("jobs/form", "formErrors", result.getAllErrors());
        }
        this.jobService.updateOne(job);
        redirect.addFlashAttribute("globalMessage", "Successfully updated the job");
        return new ModelAndView("redirect:/secure/job/{job.jobId}", "job.jobId", job.getJobId());

    }

}
