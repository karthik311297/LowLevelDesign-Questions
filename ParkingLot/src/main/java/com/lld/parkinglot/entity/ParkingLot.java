package com.lld.parkinglot.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParkingLot
{
    private Map<Integer,ParkingFloor> parkingFloorMap = new LinkedHashMap<>();
    
    public Map<Integer,ParkingFloor> getParkingFloorMap()
    {
        return parkingFloorMap;
    }
    
    public void setParkingFloorMap(Map<Integer,ParkingFloor> parkingFloorMap)
    {
        this.parkingFloorMap = parkingFloorMap;
    }
}
