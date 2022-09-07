package com.mynt.programming.exam.ParcelDeliverySystem.parcel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 * Entity class for Parcel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Parcel {
    private Long id;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private Double cost;
    private Double volume;
    private Priority priority;
    private Voucher voucher;

    private String remarks;

    public Parcel(){}
    public Parcel(Double weight, Double height, Double width, Double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        calculateValues(null);
    }

    public Parcel(Double weight, Double height, Double width, Double length,Voucher voucher){
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.voucher = voucher;
        calculateValues(voucher);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", cost=" + cost +
                ", volume=" + volume +
                ", priority=" + priority +
                ", voucher=" + voucher +
                '}';
    }

    private Priority computePriority() {
        if(this.weight>50)
            return Priority.REJECT;
        else if(this.weight>10)
            return Priority.HEAVY_PARCEL;
        else if(volume<1500)
            return Priority.SMALL_PARCEL;
        else if(volume<2500)
            return Priority.MEDIUM_PARCEL;
        else return Priority.LARGE_PARCEL;
    }

    private double calculateVolume() {
        return this.height * this.width * this.length;
    }
    private Double computeCost() throws ParcelRejectedException {
        switch (this.priority){
            case REJECT:
                throw new ParcelRejectedException("Weight exceeds 50kg.");
            case HEAVY_PARCEL:
                return 20*this.weight;
            case SMALL_PARCEL:
                return 0.03*this.volume;
            case MEDIUM_PARCEL:
                return 0.04*this.volume;
            default:
                return 0.05*this.volume;
        }
    }

    private void handleParcelRejection() {
        this.cost = 0.0;
        this.remarks="PARCEL REJECTED: Weight of Package is Greater than 50 Kilograms";
    }

    private void calculateValues(Voucher voucher) {
        this.volume= calculateVolume();
        this.priority=computePriority();
        try {
            this.cost = computeCost();
            if(voucher!=null){
                this.cost -=voucher.getDiscount();
            }
            this.remarks="The cost of delivering the parcel is: PHP"+this.cost;
        }catch (ParcelRejectedException e){
            handleParcelRejection();
        }
    }

}
