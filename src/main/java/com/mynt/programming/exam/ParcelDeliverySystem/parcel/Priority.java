package com.mynt.programming.exam.ParcelDeliverySystem.parcel;

public enum Priority {
    REJECT(1,"Reject"),
    HEAVY_PARCEL(2,"Heavy Parcel"),
    SMALL_PARCEL(3,"Small Parcel"),
    MEDIUM_PARCEL(4,"Medium Parcel"),
    LARGE_PARCEL(5,"Large Parcel");

    private int priority;
    private String description;

    private Priority(int priority, String description) {
        this.priority = priority ;
        this.description = description ;
    }
}
