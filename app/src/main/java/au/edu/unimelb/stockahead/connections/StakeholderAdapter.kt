package au.edu.unimelb.stockahead.connections

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import au.edu.unimelb.stockahead.R
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils


class StakeholderAdapter(private val listt: List<Stakeholder>, val navController: NavController) :
    RecyclerView.Adapter<StakeholderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
        val txtMsg1 = itemView.findViewById<TextView>(R.id.txt_msg1)
        val txtMsg2 = itemView.findViewById<TextView>(R.id.txt_msg2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.row_stakeholder, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listt[position]
        holder.txtTitle.text = current.name
        holder.txtMsg1.text = "${current.msg1?.sender?.name}: ${current.msg1?.message}"
        holder.txtMsg2.text = "${current.msg2?.sender?.name}: ${current.msg2?.message}"
        val badgeDrawable = BadgeDrawable.create(holder.itemView.context)
        badgeDrawable.number = current.numNotifications
        badgeDrawable.isVisible = true
        //BadgeUtils.attachBadgeDrawable(badgeDrawable, holder.itemView)
    }

    override fun getItemCount(): Int {
        return listt.size
    }

}