package au.edu.unimelb.stockahead.inventory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import au.edu.unimelb.stockahead.R


class InventoryAdapter(private val listt: List<Inventory>, val navController: NavController) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        val txtDescription = itemView.findViewById<TextView>(R.id.txtDescription)
        val txtQuantity = itemView.findViewById<TextView>(R.id.txtQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.row_inventory, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listt[position]
        holder.txtTitle.text = current.product_name
        holder.txtDescription.text = current.description
        holder.txtQuantity.text = current.quantity.toString()
    }

    override fun getItemCount(): Int {
        return listt.size
    }

}