package au.edu.unimelb.stockahead.shipment

import java.io.Serializable

data class Shipment(
    val id: Int,
    val shipment_name: String,
    val supplier_id: Int,
    val customer_id: Int,
    val start_time: String,
    val status: String
) : Serializable
