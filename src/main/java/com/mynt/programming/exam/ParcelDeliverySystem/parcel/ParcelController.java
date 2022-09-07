package com.mynt.programming.exam.ParcelDeliverySystem.parcel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for Parcel
 */
@RestController
@RequestMapping(path = "api/v1/parcel")
public class ParcelController {

    private static final Logger logger = LogManager.getLogger(ParcelController.class);
    private final ParcelService ParcelService;

    @Value("${voucher.url:https://mynt-exam.mocklab.io/voucher}")
    private String voucherURL;

    @Value("${voucher.apikey:apikey}")
    private String apikey;

    @Autowired
    public ParcelController(ParcelService ParcelService) {
        this.ParcelService = ParcelService;
    }


    @GetMapping
    @ResponseBody
    public Parcel getCostOfParcelDelivery(@RequestParam Double weight, @RequestParam Double height,
                                          @RequestParam Double width,@RequestParam Double length,
                                          @RequestParam(required = false) String voucher){

        Voucher discountVoucher =null;
        try {
            if(voucher!= null && !voucher.isEmpty()){
                discountVoucher = getVoucherFromAPI(voucher);
            }
        } catch (HttpClientErrorException e) {
            logger.warn("Voucher Input is incorrect, will continue to calculate cost without discount voucher");
        }
        return this.ParcelService.getCostOfParcelDelivery(weight,height,width,length,discountVoucher);

    }

    /**
     * Consumes the voucher from https://app.swaggerhub.com/apis/mynt-iat/mynt-programming-exams/1.1.0#/voucher/voucher
     * @param voucher
     * @returns entity voucher
     */
    private Voucher getVoucherFromAPI(String voucher) {
        RestTemplate restTemplate = new RestTemplate();
        String url = voucherURL+"/{voucher}?key={apikey}";
        Map<String, String> map = new HashMap<>();
        map.put("voucher", voucher);
        map.put("apikey", apikey);
        return restTemplate.getForObject(url, Voucher.class,map);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
