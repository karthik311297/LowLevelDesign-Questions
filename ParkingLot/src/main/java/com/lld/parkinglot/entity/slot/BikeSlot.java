package com.lld.parkinglot.entity.slot;

public class BikeSlot implements ParkingSlot
{
    @Override
    public int getHourlyPrice()
    {
        return 10;
    }
    
    @Override
    public SlotType getSlotType()
    {
        return SlotType.MOTORBIKE;
    }
}
