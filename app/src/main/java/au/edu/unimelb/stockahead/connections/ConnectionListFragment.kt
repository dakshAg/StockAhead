package au.edu.unimelb.stockahead.connections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.edu.unimelb.stockahead.databinding.FragmentConnectionListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectionListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectionListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentConnectionListBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConnectionListBinding.inflate(inflater, container, false)
        return binding.root
    }
}