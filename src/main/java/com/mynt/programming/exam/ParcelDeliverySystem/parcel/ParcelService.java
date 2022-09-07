package com.mynt.programming.exam.ParcelDeliverySystem.parcel;

import org.springframework.stereotype.Service;

/**
 * Service Class for Parcel
 */
@Service
public class ParcelService {

    /**
     * Returns new Parcel with cost calculated already calculated
     * @param weight
     * @param height
     * @param width
     * @param length
     * @param voucher
     * @return
     * @throws ParcelRejectedException
     */
    public Parcel getCostOfParcelDelivery(Double weight,Double height, Double width, Double length,Voucher voucher)  {
        return (voucher!=null) ? new Parcel(weight,height,width,length,voucher) : new Parcel(weight,height,width,length);
    }

}
