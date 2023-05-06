package com.example.helasaviya_delivery.models

data class DeliveryModel(
    var id:String? = null,
    var name: String? = null,
    var phone: String? = null,
    var address1: String? = null,
    var address2: String? = null,
    var province: String? = null,
    val total: String? = null,
    val status: String? =null
) {


}
