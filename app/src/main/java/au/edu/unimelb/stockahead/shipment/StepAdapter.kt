package au.edu.unimelb.stockahead.shipment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import au.edu.unimelb.stockahead.R


class StepAdapter(private val listt: List<Step>, val navController: NavController) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtShippingId = itemView.findViewById<TextView>(R.id.txtShippingId)
        val txtStartLoc = itemView.findViewById<TextView>(R.id.txtStartLoc)
        val txtEndLoc = itemView.findViewById<TextView>(R.id.txtEndLoc)
        val txtTimes = itemView.findViewById<TextView>(R.id.txtTimes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.row_step, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listt[position]
        holder.txtShippingId.text = "SHIP100${current.shipment_id}"
        holder.txtStartLoc.text = current.start_loc
        holder.txtEndLoc.text = current.end_loc
        holder.txtTimes.text = "${current.start_time}\n${current.end_time}"
    }

    override fun getItemCount(): Int {
        return listt.size
    }

}