package com.refael.events_reader_system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.refael.events_reader_system.scheduling.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private ScheduleJob scheduleJob;

    @GetMapping(value = "/stop")
    public String stopSchedule() {
        postProcessor.postProcessBeforeDestruction(scheduleJob, SCHEDULED_TASKS);
        return "OK";
    }

    @GetMapping(value = "/start")
    public String startSchedule() {
        postProcessor.postProcessAfterInitialization(scheduleJob, SCHEDULED_TASKS);
        return "OK";
    }

    @GetMapping(value = "/deleteAll")
    public String getNextSchedule() {
        postProcessor.destroy();
        return "OK";
    }

    @GetMapping(value = "/list")
    public String listSchedules() throws JsonProcessingException {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        if (!setTasks.isEmpty()) {
            return setTasks.toString();
        } else {
            return "Currently no scheduler tasks are running";
        }
    }
}