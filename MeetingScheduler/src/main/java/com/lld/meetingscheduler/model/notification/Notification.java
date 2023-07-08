package com.lld.meetingscheduler.model.notification;

import com.lld.meetingscheduler.model.Interval;

public abstract class Notification
{
    protected String meetingName;
    protected String location;
    protected Interval schedule;
    
    public Notification(String meetingName, String location, Interval schedule)
    {
        this.meetingName = meetingName;
        this.location = location;
        this.schedule = schedule;
    }
    
    protected abstract String getMessage();
}
