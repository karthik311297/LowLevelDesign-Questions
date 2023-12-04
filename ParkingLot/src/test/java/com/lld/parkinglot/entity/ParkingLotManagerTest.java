package com.lld.parkinglot.entity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lld.parkinglot.entity.slot.BikeSlot;
import com.lld.parkinglot.entity.slot.CarSlot;
import com.lld.parkinglot.entity.slot.HandicappedSlot;
import com.lld.parkinglot.entity.slot.SlotType;
import com.lld.parkinglot.entity.slot.TruckSlot;
import com.lld.parkinglot.exception.ParkingLotFullException;
import com.lld.parkinglot.service.ParkingLotAdminService;

public class ParkingLotManagerTest
{
    private ParkingLot parkingLot;
    
    @BeforeMethod
    public void setupDataForTest()
    {
        ParkingLotAdminService service = ParkingLotAdminService.getInstance();
        parkingLot = service.createNewParkingLot();
        parkingLot = service.addFloorToParkingLot(parkingLot, new ParkingFloor());
        parkingLot = service.addFloorToParkingLot(parkingLot, new ParkingFloor());
        
        Assert.assertEquals(parkingLot.getParkingFloorMap().size(), 2);
        
        parkingLot = service.addSlotsToParkingLotFloor(parkingLot, 0, Arrays.asList(new TruckSlot(), new CarSlot(), new HandicappedSlot(), new TruckSlot()));
        parkingLot = service.addSlotsToParkingLotFloor(parkingLot, 1, Arrays.asList(new BikeSlot(), new CarSlot(), new HandicappedSlot(), new CarSlot()));
        
        ParkingLotManager.setParkingLot(parkingLot);
    }
    
    @Test
    public void shouldAuthorizeEntryWhenSlotsAreAvailableInParkingLot() throws ParkingLotFullException
    {
        User user = new User();
        user.setName("Karthik");
        
        user = ParkingLotManager.authorizeEntry(user, SlotType.MOTORBIKE, LocalDateTime.now());
        
        Assert.assertEquals(user.getParkingFloor(), 1);
        Assert.assertEquals(user.getAssignedSlot().getSlotType(), SlotType.MOTORBIKE);
    }
    
    @Test
    public void shouldNotAuthorizeEntryWhenSlotsAreNotAvailableInParkingLot() throws ParkingLotFullException
    {
        User user1 = new User();
        user1.setName("Karthik");
        ParkingLotManager.authorizeEntry(user1, SlotType.MOTORBIKE, LocalDateTime.now());
        User user2 = new User();
        user1.setName("Iyer");
        
        try
        {
            ParkingLotManager.authorizeEntry(user2, SlotType.MOTORBIKE, LocalDateTime.now());
            Assert.fail();
        }
        catch(ParkingLotFullException e)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void shouldAuthorizeExitAndCalculateCorrectPrice() throws ParkingLotFullException
    {
        LocalDateTime exitTime = LocalDateTime.of(2023, Month.APRIL, 25, 14, 0, 0);
        LocalDateTime before3Hours = LocalDateTime.from(exitTime).minusHours(3);
        User user1 = new User();
        user1.setName("Karthik");
        ParkingLotManager.authorizeEntry(user1, SlotType.MOTORBIKE, before3Hours);
    
        long cost = ParkingLotManager.authorizeExit(user1, exitTime);
        
        Assert.assertEquals(cost, 30);
    }
}