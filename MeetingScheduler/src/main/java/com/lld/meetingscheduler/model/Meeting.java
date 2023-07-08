package com.lld.meetingscheduler.model;

import java.util.List;
import java.util.Objects;

import com.lld.meetingscheduler.model.notification.Notifications;

public class Meeting
{
    private List<User> participants;
    private String meetingName;
    private String location;
    private Interval schedule;
    
    public Meeting(List<User> participants, String meetingName, String location, Interval schedule)
    {
        this.participants = participants;
        this.meetingName = meetingName;
        this.location = location;
        this.schedule = schedule;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Meeting)) return false;
        Meeting meeting = (Meeting)o;
        return participants.equals(meeting.participants) && meetingName.equals(meeting.meetingName) && location.equals(meeting.location) && schedule.equals(meeting.schedule);
    }
    
    public void sendNotification(Notifications.NotificationType notificationType)
    {
        for(User participant : participants)
        {
            participant.receiveNotification(Notifications.getNotificationBasedOnType(notificationType, meetingName, location, schedule));
        }
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(participants, meetingName, location, schedule);
    }
    
    protected List<User> getParticipants()
    {
        return participants;
    }
    
    protected String getMeetingName()
    {
        return meetingName;
    }
    
    protected String getLocation()
    {
        return location;
    }
    
    protected Interval getSchedule()
    {
        return schedule;
    }
    
    public void setSchedule(Interval schedule)
    {
        this.schedule = schedule;
    }
}
