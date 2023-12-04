package com.lld.parkinglot.entity.slot;

public class TruckSlot implements ParkingSlot
{
    @Override
    public int getHourlyPrice()
    {
        return 40;
    }
    
    @Override
    public SlotType getSlotType()
    {
        return SlotType.TRUCK;
    }
}
