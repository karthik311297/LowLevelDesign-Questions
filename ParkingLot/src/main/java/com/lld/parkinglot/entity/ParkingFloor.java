package com.lld.parkinglot.entity;

import java.util.ArrayList;
import java.util.List;

import com.lld.parkinglot.entity.slot.ParkingSlot;
import com.lld.parkinglot.entity.slot.SlotType;

public class ParkingFloor
{
    private List<ParkingSlot> freeParkingSlotList = new ArrayList<>();
    private List<ParkingSlot> usedParkingSlotList = new ArrayList<>();
    
    public boolean isFull()
    {
        return freeParkingSlotList.isEmpty();
    }
    
    public void addParkingSlot(ParkingSlot parkingSlot)
    {
        freeParkingSlotList.add(parkingSlot);
    }
    
    public ParkingSlot getParkingSlot(SlotType slotType)
    {
        ParkingSlot parkingSlot = null;
        for(int i = 0; i < freeParkingSlotList.size();i++)
        {
            if(freeParkingSlotList.get(i).getSlotType() == slotType)
            {
                parkingSlot = freeParkingSlotList.get(i);
                freeParkingSlotList.remove(i);
                usedParkingSlotList.add(parkingSlot);
                break;
            }
        }
        return parkingSlot;
    }
    
    public void reclaimParkingSlot(SlotType slotType)
    {
        for(int i = 0; i < usedParkingSlotList.size();i++)
        {
            if(usedParkingSlotList.get(i).getSlotType() == slotType)
            {
                ParkingSlot parkingSlot = usedParkingSlotList.get(i);
                usedParkingSlotList.remove(i);
                freeParkingSlotList.add(parkingSlot);
                break;
            }
        }
    }
    
}
