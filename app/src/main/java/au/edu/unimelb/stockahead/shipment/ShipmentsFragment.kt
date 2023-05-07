package au.edu.unimelb.stockahead.shipment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.unimelb.stockahead.PreferenceManager
import au.edu.unimelb.stockahead.R
import au.edu.unimelb.stockahead.databinding.FragmentInventoryBinding
import au.edu.unimelb.stockahead.databinding.FragmentShipmentsBinding
import au.edu.unimelb.stockahead.inventory.Inventory
import au.edu.unimelb.stockahead.inventory.InventoryAdapter
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ShipmentsFragment : Fragment() {

    private var _binding: FragmentShipmentsBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShipmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabNewShipment.setOnClickListener {
            findNavController().navigate(ShipmentsFragmentDirections.newShipment())
        }

        getData()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(requireContext())
        val companyId = PreferenceManager(requireContext()).getCompanyID()
        val url = "https://dakshag.pythonanywhere.com/shipment/supplier/${companyId}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                Log.w("Inventory", response.toString())
                val typeToken = object : TypeToken<List<Shipment>>() {}.type
                val inventory = Gson().fromJson<List<Shipment>>(
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

    private fun showData(inventory: List<Shipment>) {
        val adapter = ShipmentAdapter(inventory, findNavController())
        binding.recyc.layoutManager = LinearLayoutManager(requireContext())
        binding.recyc.adapter = adapter
    }
}