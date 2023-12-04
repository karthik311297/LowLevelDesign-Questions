package com.lld.parkinglot.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import com.lld.parkinglot.entity.slot.ParkingSlot;
import com.lld.parkinglot.entity.slot.SlotType;
import com.lld.parkinglot.exception.ParkingLotFullException;

public class ParkingLotManager
{
    private static ParkingLot parkingLot;
    
    public static void setParkingLot(ParkingLot updatedParkingLot)
    {
        parkingLot = updatedParkingLot;
    }
    
    public static User authorizeEntry(User user, SlotType slotNeeded, LocalDateTime entryTime) throws ParkingLotFullException
    {
        for(Map.Entry<Integer,ParkingFloor> entry : parkingLot.getParkingFloorMap().entrySet())
        {
            ParkingFloor floor = entry.getValue();
            if(floor.isFull()) continue;
            ParkingSlot slot = floor.getParkingSlot(slotNeeded);
            if(slot == null) continue;
            user.setEntryTime(entryTime);
            user.setAssignedSlot(slot);
            user.setParkingFloor(entry.getKey());
        }
        if(user.getAssignedSlot() == null) throw new ParkingLotFullException("No Space");
        return user;
    }
    
    public static long authorizeExit(User user, LocalDateTime exitTime)
    {
        int floor = user.getParkingFloor();
        SlotType type = user.getAssignedSlot().getSlotType();
        int hourlyPrice = user.getAssignedSlot().getHourlyPrice();
        ParkingFloor parkingFloor = parkingLot.getParkingFloorMap().get(floor);
        parkingFloor.reclaimParkingSlot(type);
        long hours = Duration.between(user.getEntryTime(), exitTime).toHours();
        System.out.println(user.getName() + " has Paid Rupees " + hours*hourlyPrice + " during exit");
        return hours*hourlyPrice;
    }
}
