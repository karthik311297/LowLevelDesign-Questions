package com.lld.meetingscheduler.model.notification;

import com.lld.meetingscheduler.model.Interval;

public class BookingCanceledNotification extends Notification
{
    public BookingCanceledNotification(String meetingName, String location, Interval schedule)
    {
        super(meetingName, location, schedule);
    }
    
    @Override
    protected String getMessage()
    {
        return "The " + meetingName + " at " + location + ", which was scheduled at " + schedule + " has been canceled";
    }
}
