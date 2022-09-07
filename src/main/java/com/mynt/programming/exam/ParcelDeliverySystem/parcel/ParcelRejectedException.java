package com.mynt.programming.exam.ParcelDeliverySystem.parcel;

public class ParcelRejectedException extends Exception {
    public ParcelRejectedException(String errorMessage) {
        super(errorMessage);
    }
}
