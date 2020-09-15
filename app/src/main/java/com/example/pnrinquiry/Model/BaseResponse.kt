package com.example.pnrinquiry.Model

data class BaseResponse(
    val arrival_data: ArrivalData,
    val boarding_station: String,
    val chart_status: String,
    val `class`: String,
    val departure_data: DepartureData,
    val passenger: ArrayList<Passenger>,
    val quota: String,
    val reservation_upto: String,
    val train_name: String,
    val train_number: String
)