package au.edu.unimelb.stockahead.connections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.unimelb.stockahead.R
import au.edu.unimelb.stockahead.databinding.FragmentAddInventoryItemBinding
import au.edu.unimelb.stockahead.databinding.FragmentConnectionsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectionsFragment : Fragment() {
    private var _binding: FragmentConnectionsBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConnectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = listOf(
            Stakeholder("Suppliers", 4, null, null),
            Stakeholder("Transporters", 5, null, null),
            Stakeholder("Sales Team", 50, null, null),
            Stakeholder("Customers", 12, null, null)
        )
        fillData(data)
    }

    fun fillData(data: List<Stakeholder>) {
        val adapter = StakeholderAdapter(data, findNavController())
        binding.recyc.layoutManager = LinearLayoutManager(requireContext())
        binding.recyc.adapter = adapter
    }
}