package au.edu.unimelb.stockahead.inventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.edu.unimelb.stockahead.databinding.FragmentAddInventoryItemBinding
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


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
        val url = "http://128.250.0.196:5000/image_search?url="
        val stringRequest = object : StringRequest(
            Method.POST, url,
            { response ->

            },
            { exception ->
                Log.e("Errr", "That didnt work")
                exception.printStackTrace()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()

                // on below line we are passing our key
                // and value pair to our parameters.
                params["product_name"] = binding.inputProductName.text.toString()
                params["sku"] = binding.inputSku.text.toString()
                params["description"] = binding.inputDescription.text.toString()
                params["price"] = binding.inputPrice.text.toString()
                params["quantity"] = binding.inputQuantity.text.toString()

                // at last we are
                // returning our params.
                return params
            }
        }
        queue.add(stringRequest)
    }
}