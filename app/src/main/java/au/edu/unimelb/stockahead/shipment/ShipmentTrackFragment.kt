package au.edu.unimelb.stockahead.shipment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.unimelb.stockahead.PreferenceManager
import au.edu.unimelb.stockahead.R
import au.edu.unimelb.stockahead.databinding.FragmentShipmentTrackBinding
import au.edu.unimelb.stockahead.databinding.FragmentShipmentsBinding
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A simple [Fragment] subclass.
 * Use the [ShipmentTrackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShipmentTrackFragment : Fragment() {

    private var _binding: FragmentShipmentTrackBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var shipment: Shipment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShipmentTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shipment = ShipmentTrackFragmentArgs.fromBundle(requireArguments()).shipment
        binding.efabPass.setOnClickListener {
            findNavController().navigate(ShipmentTrackFragmentDirections.passShipment(shipment.id))
        }
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://dakshag.pythonanywhere.com/step/${shipment.id}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                Log.w("Inventory", response.toString())
                val typeToken = object : TypeToken<List<Step>>() {}.type
                val inventory = Gson().fromJson<List<Step>>(
                    response.getJSONArray("shipments").toString(),
                    typeToken
                )
                showData(inventory)
            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }

        )
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            50 * 1000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        queue.add(jsonObjectRequest)
    }

    private fun showData(inventory: List<Step>) {
        val adapter = StepAdapter(inventory, findNavController())
        binding.recyc.layoutManager = LinearLayoutManager(requireContext())
        binding.recyc.adapter = adapter
    }
}