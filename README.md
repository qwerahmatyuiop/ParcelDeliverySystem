# ParcelDeliverySystem
Sample Demo Parcel Delivery System

//For calculating of Cost of delivery for parcel use the following endpoint:
localhost:8080/api/v1/parcel

required parameters:
weight(kg)
height(cm)
width(cm)
length(cm)

optional parameters:
voucher

sample request:
localhost:8080/api/v1/parcel?weight=10&height=10&width=10&length=10&voucher=MYNT

sample response:
{
"id": null,
"weight": 10.0,
"height": 10.0,
"width": 10.0,
"length": 10.0,
"cost": 17.75,
"volume": 1000.0,
"priority": "SMALL_PARCEL",
"voucher": {
"id": null,
"code": "MYNT",
"discount": 12.25,
"expiry": "2020-09-16"
},
"remarks": "The cost of delivering the parcel is: PHP17.75"
}
