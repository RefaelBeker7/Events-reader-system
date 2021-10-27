package com.refael.events_reader_system.scheduling;

import com.refael.events_reader_system.controller.XmlController;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//"0 0 * * * *" = the top of every hour of every day.
//"*/10 * * * * *" = every ten seconds.
//"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//"0 0 8,10 * * *" = 8 and 10 o'clock of every day.
//"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//"0 0 0 25 12 ?" = every Christmas Day at midnight


@Component
public class ScheduleJob {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJob.class);
    public XmlController xmlController;

    @Scheduled(cron = "0 0 * * * *")
    public void cronJobXML() {
        LOG.debug("Start XML job");
        xmlController.readXmlFile();
    }
}
