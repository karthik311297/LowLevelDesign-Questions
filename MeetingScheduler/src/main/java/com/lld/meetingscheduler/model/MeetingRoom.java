package com.lld.meetingscheduler.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lld.meetingscheduler.model.notification.Notifications;

public class MeetingRoom
{
    private String uniqueID;
    private int capacity;
    private MeetingCalendar meetingCalendar;
    private Set<Meeting> meetings;
    
    public MeetingRoom(String uniqueID, int capacity)
    {
        this.uniqueID = uniqueID;
        this.capacity = capacity;
        meetingCalendar = new MeetingCalendar();
        meetings = new HashSet<>();
    }
    
    public String getUniqueID()
    {
        return uniqueID;
    }
    
    public int getCapacity()
    {
        return capacity;
    }
    
    @Override
    public String toString()
    {
        return "MeetingRoom{" +
                "uniqueID='" + uniqueID + '\'' +
                ", capacity=" + capacity +
                '}';
    }
    
    public boolean isAvailableForTimeSlot(LocalDateTime startTime, LocalDateTime endTime)
    {
        Interval interval = new Interval(startTime, endTime);
        return meetingCalendar.isSlotAvailable(interval);
    }
    
    public String getFreeTimeSlots()
    {
        System.out.println("Available Slots: ");
        StringBuilder stringBuilder = new StringBuilder();
        meetingCalendar.getAvailableSlots()
                .stream()
                .forEach(interval -> stringBuilder.append(interval.toString()));
        return stringBuilder.toString();
    }
    
    public boolean bookMeeting(List<User> participants, String meetingName,
                            LocalDateTime startTime, LocalDateTime endTime)
    {
        Interval interval = new Interval(startTime, endTime);
        boolean slotBookingSuccess = meetingCalendar.bookSlot(interval);
        if(slotBookingSuccess)
        {
            Meeting meeting = new Meeting(participants, meetingName, this.uniqueID, interval);
            meetings.add(meeting);
            meeting.sendNotification(Notifications.NotificationType.BOOKING_SUCCESS);
            return true;
        }
        return false;
    }
    
    public boolean cancelMeeting(Meeting meeting)
    {
        if(!meetings.contains(meeting)) return false;
        Interval interval = meeting.getSchedule();
        boolean slotReclaimSuccess = meetingCalendar.reclaimSlot(interval);
        if(slotReclaimSuccess)
        {
            meetings.remove(meeting);
            meeting.sendNotification(Notifications.NotificationType.BOOKING_CANCELED);
            return true;
        }
        return false;
    }
    
    public boolean rescheduleMeeting(Meeting meeting, LocalDateTime startTime, LocalDateTime endTime)
    {
        if(!meetings.contains(meeting)) return false;
        Interval currentInterval = meeting.getSchedule();
        boolean slotReclaimSuccess = meetingCalendar.reclaimSlot(currentInterval);
        Interval interval = new Interval(startTime, endTime);
        boolean slotBookingSuccess = meetingCalendar.bookSlot(currentInterval);
        if(slotReclaimSuccess && slotBookingSuccess)
        {
            meetings.remove(meeting);
            meeting.setSchedule(interval);
            meetings.add(meeting);
            meeting.sendNotification(Notifications.NotificationType.BOOKING_RESCHEDULED);
            return true;
        }
        return false;
    }
}
