package au.edu.unimelb.stockahead.shipment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import au.edu.unimelb.stockahead.PreferenceManager
import au.edu.unimelb.stockahead.R
import au.edu.unimelb.stockahead.databinding.FragmentAddInventoryItemBinding
import au.edu.unimelb.stockahead.databinding.FragmentNewShipmentBinding
import au.edu.unimelb.stockahead.databinding.FragmentPassShipmentBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NewShipmentFragment : Fragment() {
    private var _binding: FragmentNewShipmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewShipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            pushData()
        }
    }

    private fun pushData() {
        val queue = Volley.newRequestQueue(requireContext())
        val params = HashMap<String, String>()
        params["shipment_name"] = binding.inputShipmentName.editText?.text.toString()
        params["supplier_id"] = PreferenceManager(requireContext()).getCompanyID().toString()
        params["customer_id"] = "1"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        params["start_time"] = LocalDateTime.now().format(formatter)
        params["status"] = "IN TRANSIT"

        val url = "https://dakshag.pythonanywhere.com/shipment/add"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, JSONObject((params as Map<*, *>?)!!), { response ->
                Toast.makeText(requireContext(), "Shipment Added Successfully", Toast.LENGTH_SHORT)
                    .show()
            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }

        )
        queue.add(jsonObjectRequest)
    }


}