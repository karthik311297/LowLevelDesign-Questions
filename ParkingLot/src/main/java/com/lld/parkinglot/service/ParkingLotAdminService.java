package com.lld.parkinglot.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lld.parkinglot.entity.ParkingFloor;
import com.lld.parkinglot.entity.ParkingLot;
import com.lld.parkinglot.entity.slot.ParkingSlot;

public class ParkingLotAdminService
{
    public static ParkingLotAdminService INSTANCE = new ParkingLotAdminService();
    
    public static ParkingLotAdminService getInstance()
    {
        return INSTANCE;
    }
    
    public ParkingLot createNewParkingLot()
    {
        return new ParkingLot();
    }
    
    public ParkingLot addFloorToParkingLot(ParkingLot parkingLot, ParkingFloor parkingFloor)
    {
        Map<Integer, ParkingFloor> parkingFloorMap = parkingLot.getParkingFloorMap();
        int newFloor = parkingFloorMap.isEmpty() ? 0 : Collections.max(parkingFloorMap.keySet()) + 1;
        parkingFloorMap.put(newFloor, parkingFloor);
        parkingLot.setParkingFloorMap(parkingFloorMap);
        return parkingLot;
    }
    
    public ParkingLot addSlotsToParkingLotFloor(ParkingLot parkingLot, int floorNumber, List<ParkingSlot> parkingSlotList)
    {
        Map<Integer, ParkingFloor> parkingFloorMap = parkingLot.getParkingFloorMap();
        ParkingFloor parkingFloor = parkingFloorMap.get(floorNumber);
        parkingSlotList.forEach(parkingFloor::addParkingSlot);
        parkingFloorMap.put(floorNumber, parkingFloor);
        parkingLot.setParkingFloorMap(parkingFloorMap);
        return parkingLot;
    }
    
}
