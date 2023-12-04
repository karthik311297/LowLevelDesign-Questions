package com.lld.parkinglot.entity.slot;

public class CarSlot implements ParkingSlot
{
    @Override
    public int getHourlyPrice()
    {
        return 20;
    }
    
    @Override
    public SlotType getSlotType()
    {
        return SlotType.CAR;
    }
}
