package com.lld.meetingscheduler.model.notification;

import com.lld.meetingscheduler.model.Interval;

public class BookingSuccessNotification extends Notification
{
    public BookingSuccessNotification(String meetingName, String location, Interval schedule)
    {
        super(meetingName, location, schedule);
    }
    
    @Override
    protected String getMessage()
    {
        return "You are invited to " + meetingName + " at " + location + ", scheduled at " + schedule;
    }
}
