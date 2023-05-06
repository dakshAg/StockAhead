package au.edu.unimelb.stockahead

import kotlin.math.sqrt

class MPP {
    fun economicOrderQuantity(
        demand: Float,
        orderCost: Float,
        pricePerUnit: Float,
        holdingCost: Float
    ): Float {
        return sqrt(2 * demand * orderCost / (pricePerUnit * holdingCost))
    }

    fun totalCost(d: Float, s: Float, p: Float, i: Float, q: Float): Float {
        return d * s / q + q * i * p / 2 + p * d
    }
}