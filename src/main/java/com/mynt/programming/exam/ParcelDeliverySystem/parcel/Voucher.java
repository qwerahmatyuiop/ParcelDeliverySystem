package com.mynt.programming.exam.ParcelDeliverySystem.parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
/**
 * Entity class for Voucher
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Voucher {

    private Long id;
    private String code;
    private Double discount;
    private LocalDate expiry;

    private Voucher(){}
    public Voucher(String code, Double discount, LocalDate expiry) {
        this.code = code;
        this.discount = discount;
        this.expiry = expiry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", expiry=" + expiry +
                '}';
    }
}
