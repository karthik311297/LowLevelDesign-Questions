package com.lld.meetingscheduler.model.notification;

import com.lld.meetingscheduler.model.Interval;

public class Notifications
{
    public enum NotificationType
    {
        BOOKING_SUCCESS,
        BOOKING_CANCELED,
        BOOKING_RESCHEDULED
    }
    
    public static Notification getNotificationBasedOnType(NotificationType notificationType, String meetingName, String location, Interval schedule)
    {
        Notification notification = null;
        switch(notificationType)
        {
            case BOOKING_SUCCESS:
                notification = new BookingSuccessNotification(meetingName, location, schedule);
                break;
            case BOOKING_CANCELED:
                notification = new BookingCanceledNotification(meetingName, location, schedule);
                break;
            case BOOKING_RESCHEDULED:
                notification = new BookingRescheduledNotification(meetingName, location, schedule);
                break;
        }
        return notification;
    }
}
