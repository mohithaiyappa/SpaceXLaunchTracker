package tk.mohithaiyappa.spacexlaunchtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tk.mohithaiyappa.spacexlaunchtracker.databinding.FragmentHomeBinding
import tk.mohithaiyappa.spacexlaunchtracker.ui.MainViewModel
import tk.mohithaiyappa.spacexlaunchtracker.util.adapters.LaunchItemAdapter

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: MainViewModel by activityViewModels()
    private var adapter: LaunchItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            adapter = LaunchItemAdapter()
            rvLaunchesList.layoutManager = LinearLayoutManager(requireContext())
            rvLaunchesList.adapter = adapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .getlaunches()
                .collectLatest {
                    adapter?.submitList(it)
                }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
