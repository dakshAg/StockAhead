package au.edu.unimelb.stockahead.connections

data class Stakeholder(
    val name: String,
    val numNotifications: Int,
    val msg1: ChatMessage?,
    val msg2: ChatMessage?
)
