package com.lld.meetingscheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.lld.meetingscheduler.model.MeetingRoom;

public class MeetingScheduler
{
    private List<MeetingRoom> meetingRooms;
    
    public MeetingScheduler(List<MeetingRoom> meetingRooms)
    {
        this.meetingRooms = meetingRooms;
    }
    
    public String getMeetRoomsAvailableAtTimeSlot(LocalDateTime startTime, LocalDateTime endTime)
    {
        return String.join("\n", meetingRooms
                .stream()
                .filter(meetingRoom -> meetingRoom.isAvailableForTimeSlot(startTime, endTime))
                .collect(Collectors.toList()).toString());
    }
}
