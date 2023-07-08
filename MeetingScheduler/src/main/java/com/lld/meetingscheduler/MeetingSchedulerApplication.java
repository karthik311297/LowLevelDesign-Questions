package com.lld.meetingscheduler;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import com.lld.meetingscheduler.model.MeetingRoom;
import com.lld.meetingscheduler.model.User;

public class MeetingSchedulerApplication
{
    public static void main(String[] args)
    {
        List<MeetingRoom> meetingRoomList = Arrays.asList(new MeetingRoom("m1", 2), new MeetingRoom("m2", 4),
                new MeetingRoom("m3", 2), new MeetingRoom("m4", 4), new MeetingRoom("m5", 6));
        MeetingScheduler meetingScheduler = new MeetingScheduler(meetingRoomList);
        List<User> employees = Arrays.asList(new User("employee1", "e1"), new User("employee2", "e2"),
                new User("employee3", "e3"), new User("employee4", "e4"), new User("employee5", "e5"));
        System.out.println(meetingScheduler.getMeetRoomsAvailableAtTimeSlot(LocalDateTime.of(2023, Month.APRIL, 20, 2, 0), LocalDateTime.of(2023, Month.APRIL, 20, 3, 0)));
        
    }
}
