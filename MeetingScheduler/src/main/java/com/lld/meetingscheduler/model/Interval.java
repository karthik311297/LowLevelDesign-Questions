package com.lld.meetingscheduler.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Interval
{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    protected Interval(LocalDateTime startTime, LocalDateTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Interval)) return false;
        Interval interval = (Interval)o;
        return startTime.equals(interval.startTime) && endTime.equals(interval.endTime);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(startTime, endTime);
    }
    
    @Override
    public String toString()
    {
        return "startTime=" + startTime +
                ", endTime=" + endTime;
    }
}
