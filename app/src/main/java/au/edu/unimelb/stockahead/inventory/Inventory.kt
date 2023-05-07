package au.edu.unimelb.stockahead.inventory

data class Inventory(
    val id: Int,
    val product_name: String,
    val sku: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val company_id: Int
)
