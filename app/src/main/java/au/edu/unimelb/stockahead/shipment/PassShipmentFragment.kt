package au.edu.unimelb.stockahead.shipment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import au.edu.unimelb.stockahead.databinding.FragmentPassShipmentBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass.
 * Use the [PassShipmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PassShipmentFragment : Fragment() {
    private var _binding: FragmentPassShipmentBinding? = null
    private var shipmentId: Int = 0
    private var prevStep: Step? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPassShipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shipmentId = PassShipmentFragmentArgs.fromBundle(requireArguments()).shipmentId
        prevStep = PassShipmentFragmentArgs.fromBundle(requireArguments()).previousStep

        binding.btnPass.setOnClickListener {
            pushData()
            prevStep?.let {
                updatePrevious(it)
            }
        }
    }

    private fun pushData() {
        val queue = Volley.newRequestQueue(requireContext())
        val params = HashMap<String, String>()
        params["shipment_id"] = shipmentId.toString()
        params["transporter_id"] = binding.inputCompanyId.text.toString()
        params["start_loc"] = binding.inputStartLoc.text.toString()
        params["end_loc"] = binding.inputEndLoc.text.toString()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        params["start_time"] = LocalDateTime.now().format(formatter)
        params["end_time"] = ""
        params["status"] = "IN TRANSIT"

        val url = "https://dakshag.pythonanywhere.com/step/add"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, JSONObject((params as Map<*, *>?)!!), { response ->
                Toast.makeText(requireContext(), "Shipment Passed", Toast.LENGTH_SHORT).show()
            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }

        )
        queue.add(jsonObjectRequest)
    }

    private fun updatePrevious(ps: Step) {
        val queue = Volley.newRequestQueue(requireContext())
        val params = HashMap<String, String>()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        params["end_time"] = LocalDateTime.now().format(formatter)
        params["status"] = "COMPLETE"

        val url = "https://dakshag.pythonanywhere.com/step/update_status/${ps.id}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, JSONObject((params as Map<*, *>?)!!), { response ->
                Log.d("COMP", response.toString())
            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }

        )
        queue.add(jsonObjectRequest)
    }
}