package com.lld.meetingscheduler.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MeetingCalendar
{
    private Set<Interval> availableSlots;
    private Set<Interval> bookedSlots;
    
    protected MeetingCalendar()
    {
        availableSlots = getInitialAvailableSlots(); // Need to be initialized with 0 to 12 hrs
        bookedSlots = new HashSet<>();
    }
    
    private Set<Interval> getInitialAvailableSlots()
    {
        Set<Interval> slots = new HashSet<>(
                Arrays.asList(
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 0, 0), LocalDateTime.of(2023, Month.APRIL, 20, 1, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 1, 0), LocalDateTime.of(2023, Month.APRIL, 20, 2, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 2, 0), LocalDateTime.of(2023, Month.APRIL, 20, 3, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 3, 0), LocalDateTime.of(2023, Month.APRIL, 20, 4, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 4, 0), LocalDateTime.of(2023, Month.APRIL, 20, 5, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 5, 0), LocalDateTime.of(2023, Month.APRIL, 20, 6, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 6, 0), LocalDateTime.of(2023, Month.APRIL, 20, 7, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 7, 0), LocalDateTime.of(2023, Month.APRIL, 20, 8, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 8, 0), LocalDateTime.of(2023, Month.APRIL, 20, 9, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 9, 0), LocalDateTime.of(2023, Month.APRIL, 20, 10, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 10, 0), LocalDateTime.of(2023, Month.APRIL, 20, 11, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 11, 0), LocalDateTime.of(2023, Month.APRIL, 20, 12, 0)),
                        new Interval(LocalDateTime.of(2023, Month.APRIL, 20, 12, 0), LocalDateTime.of(2023, Month.APRIL, 20, 13, 0))));
        return slots;
    }
    
    protected boolean isSlotAvailable(Interval interval)
    {
        return availableSlots.contains(interval);
    }
    
    protected boolean bookSlot(Interval interval)
    {
        if(availableSlots.contains(interval))
        {
            bookedSlots.add(interval);
            availableSlots.remove(interval);
            return true;
        }
        return false;
    }
    
    protected boolean reclaimSlot(Interval interval)
    {
        if(bookedSlots.contains(interval))
        {
            bookedSlots.remove(interval);
            availableSlots.add(interval);
            return true;
        }
        return false;
    }
    
    protected Set<Interval> getAvailableSlots()
    {
        return availableSlots;
    }
}
