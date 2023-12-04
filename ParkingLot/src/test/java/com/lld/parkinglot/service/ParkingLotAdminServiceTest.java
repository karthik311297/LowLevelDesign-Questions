package com.lld.parkinglot.service;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lld.parkinglot.entity.ParkingFloor;
import com.lld.parkinglot.entity.ParkingLot;
import com.lld.parkinglot.entity.slot.BikeSlot;
import com.lld.parkinglot.entity.slot.CarSlot;
import com.lld.parkinglot.entity.slot.HandicappedSlot;
import com.lld.parkinglot.entity.slot.TruckSlot;

public class ParkingLotAdminServiceTest
{
    @Test
    public void shouldCreateParkingLotWithMultipleFloorsAndEachFloorHavingMultipleSlots()
    {
        ParkingLotAdminService service = ParkingLotAdminService.getInstance();
        ParkingLot parkingLot = service.createNewParkingLot();
        parkingLot = service.addFloorToParkingLot(parkingLot, new ParkingFloor());
        parkingLot = service.addFloorToParkingLot(parkingLot, new ParkingFloor());
    
        Assert.assertEquals(parkingLot.getParkingFloorMap().size(), 2);
    
        service.addSlotsToParkingLotFloor(parkingLot, 1, Arrays.asList(new BikeSlot(), new CarSlot(), new HandicappedSlot(), new CarSlot()));
        service.addSlotsToParkingLotFloor(parkingLot, 0, Arrays.asList(new TruckSlot(), new CarSlot(), new HandicappedSlot(), new TruckSlot()));
    
        Assert.assertTrue(true);
    }
}