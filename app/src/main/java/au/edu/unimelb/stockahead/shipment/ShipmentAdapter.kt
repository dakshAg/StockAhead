package au.edu.unimelb.stockahead.shipment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import au.edu.unimelb.stockahead.R


class ShipmentAdapter(private val listt: List<Shipment>, val navController: NavController) :
    RecyclerView.Adapter<ShipmentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtShipmentName = itemView.findViewById<TextView>(R.id.txtShipmentName)
        val txtStatus = itemView.findViewById<TextView>(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.row_shipment, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listt[position]
        holder.txtShipmentName.text = current.shipment_name
        holder.txtStatus.text = current.status
        holder.itemView.setOnClickListener {
            navController.navigate(ShipmentsFragmentDirections.track(current))
        }
    }

    override fun getItemCount(): Int {
        return listt.size
    }

}