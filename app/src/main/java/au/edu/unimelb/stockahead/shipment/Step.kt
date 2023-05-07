package au.edu.unimelb.stockahead.shipment

import java.io.Serializable

data class Step(
    val id: Int,
    val shipment_id: Int,
    val transporter_id: Int,
    val start_time: String,
    val end_time: String,
    val status: String,
    val start_loc: String,
    val end_loc: String
) : Serializable
