package au.edu.unimelb.stockahead.connections

import au.edu.unimelb.stockahead.Company

data class ChatMessage(val sender: Company, val message: String)
