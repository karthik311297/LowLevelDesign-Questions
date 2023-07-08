package com.lld.meetingscheduler.model.notification;

import com.lld.meetingscheduler.model.Interval;

public class BookingRescheduledNotification extends Notification
{
    public BookingRescheduledNotification(String meetingName, String location, Interval schedule)
    {
        super(meetingName, location, schedule);
    }
    
    @Override
    protected String getMessage()
    {
        return "The " + meetingName + " at " + location + " has been rescheduled to " + schedule;
    }
}
