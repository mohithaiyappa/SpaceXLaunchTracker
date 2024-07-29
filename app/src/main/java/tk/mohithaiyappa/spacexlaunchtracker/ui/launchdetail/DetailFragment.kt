package tk.mohithaiyappa.spacexlaunchtracker.ui.launchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tk.mohithaiyappa.spacexlaunchtracker.databinding.FragmentDetailBinding
import tk.mohithaiyappa.spacexlaunchtracker.ui.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    private var flightNumber: Int? = null
    private var binding: FragmentDetailBinding? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flightNumber = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root?.apply {
            setOnClickListener {
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            flightNumber?.let {
                viewModel.getLaunch(it)
                    .collectLatest { launchEntity ->
                        binding?.apply {
                            ivMissionPatch.load(launchEntity.missionPatch)
                            tvLaunchDate.text = launchEntity.launchYear
                            tvMissionName.text = launchEntity.missionName
                            tvRocketName.text = launchEntity.rocketName
                            tvRocketType.text = launchEntity.rocketType
                            tvPayloadDetails.text = launchEntity.payloadDetails
                            tvLaunchSite.text = launchEntity.launchSite
                        }
                    }
            } ?: run {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(flightNumber: Int) =
            DetailFragment().apply {
                arguments =
                    Bundle().apply {
                        putInt(ARG_PARAM1, flightNumber)
                    }
            }
    }
}
