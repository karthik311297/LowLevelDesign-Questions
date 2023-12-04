package com.lld.parkinglot.entity;

import java.time.LocalDateTime;

import com.lld.parkinglot.entity.slot.ParkingSlot;

public class User
{
    private String name;
    private LocalDateTime entryTime;
    private ParkingSlot assignedSlot;
    private Integer parkingFloor;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public LocalDateTime getEntryTime()
    {
        return entryTime;
    }
    
    public void setEntryTime(LocalDateTime entryTime)
    {
        this.entryTime = entryTime;
    }
    
    
    public ParkingSlot getAssignedSlot()
    {
        return assignedSlot;
    }
    
    public void setAssignedSlot(ParkingSlot assignedSlot)
    {
        this.assignedSlot = assignedSlot;
    }
    
    public Integer getParkingFloor()
    {
        return parkingFloor;
    }
    
    public void setParkingFloor(Integer parkingFloor)
    {
        this.parkingFloor = parkingFloor;
    }
}
