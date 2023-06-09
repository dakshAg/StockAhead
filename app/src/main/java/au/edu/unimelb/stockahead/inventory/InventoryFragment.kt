package au.edu.unimelb.stockahead.inventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.unimelb.stockahead.PreferenceManager
import au.edu.unimelb.stockahead.databinding.FragmentInventoryBinding
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {

    private var _binding: FragmentInventoryBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(InventoryFragmentDirections.addInventory())
        }
        getData()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(requireContext())
        val companyId = PreferenceManager(requireContext()).getCompanyID()
        val url = "https://dakshag.pythonanywhere.com/inventory/${companyId}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                Log.w("Inventory", response.toString())
                val typeToken = object : TypeToken<List<Inventory>>() {}.type
                val inventory = Gson().fromJson<List<Inventory>>(
                    response.getJSONArray("inventory").toString(),
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

    private fun showData(inventory: List<Inventory>) {
        val adapter = InventoryAdapter(inventory, findNavController())
        binding.recyc.layoutManager = LinearLayoutManager(requireContext())
        binding.recyc.adapter = adapter
    }
}