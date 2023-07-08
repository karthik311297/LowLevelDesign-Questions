package com.lld.meetingscheduler.model;

import java.util.ArrayList;
import java.util.List;

import com.lld.meetingscheduler.model.notification.Notification;

public class User
{
    private String name;
    private String employeeID;
    private List<Notification> notifications;
    
    public User(String name, String employeeID)
    {
        this.name = name;
        this.employeeID = employeeID;
        this.notifications = new ArrayList<>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getEmployeeID()
    {
        return employeeID;
    }
    
    public List<Notification> getAllNotifications()
    {
        return notifications;
    }
    
    public void receiveNotification(Notification notification)
    {
        notifications.add(notification);
    }
}
