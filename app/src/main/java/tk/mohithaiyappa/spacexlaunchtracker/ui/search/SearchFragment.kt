package tk.mohithaiyappa.spacexlaunchtracker.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import tk.mohithaiyappa.spacexlaunchtracker.R
import tk.mohithaiyappa.spacexlaunchtracker.databinding.FragmentSearchBinding
import tk.mohithaiyappa.spacexlaunchtracker.ui.MainViewModel
import tk.mohithaiyappa.spacexlaunchtracker.ui.launchdetail.DetailFragment
import tk.mohithaiyappa.spacexlaunchtracker.util.adapters.LaunchItemAdapter
import tk.mohithaiyappa.spacexlaunchtracker.util.textChanges

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
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
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            adapter =
                LaunchItemAdapter { flightNumber ->
                    requireActivity().supportFragmentManager.apply {
                        beginTransaction().apply {
                            add(R.id.clSearchContainer, DetailFragment.newInstance(flightNumber))
                            addToBackStack(null)
                            commit()
                        }
                        executePendingTransactions()
                    }
                }
            rvSearchList.layoutManager = LinearLayoutManager(requireContext())
            rvSearchList.adapter = adapter

            tieSearch.setText(viewModel.searchQuery)

            viewLifecycleOwner.lifecycleScope.launch {
                tieSearch
                    .textChanges()
                    .debounce(300)
                    .collectLatest {
                        viewModel.searchLaunches("%$it%")
                            .collectLatest {
                                adapter?.submitList(it)
                            }
                    }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
