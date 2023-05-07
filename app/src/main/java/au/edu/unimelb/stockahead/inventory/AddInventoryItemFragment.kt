package au.edu.unimelb.stockahead.inventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import au.edu.unimelb.stockahead.PreferenceManager
import au.edu.unimelb.stockahead.databinding.FragmentAddInventoryItemBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 * Use the [AddInventoryItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddInventoryItemFragment : Fragment() {
    private var _binding: FragmentAddInventoryItemBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddInventoryItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            pushData()
        }
    }

    private fun pushData() {
        val queue = Volley.newRequestQueue(requireContext())
        val params = HashMap<String, String>()
        params["product_name"] = binding.inputProductName.text.toString()
        params["sku"] = binding.inputSku.text.toString()
        params["description"] = binding.inputDescription.text.toString()
        params["price"] = binding.inputPrice.text.toString()
        params["quantity"] = binding.inputQuantity.text.toString()
        params["company_id"] = PreferenceManager(requireContext()).getCompanyID().toString()

        val url = "https://dakshag.pythonanywhere.com/inventory/add"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, JSONObject((params as Map<*, *>?)!!), { response ->
                Log.w("Inventory", response.toString())
                Toast.makeText(requireContext(), "Inventory Added Successfully", Toast.LENGTH_SHORT)
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