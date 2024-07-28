package tk.mohithaiyappa.spacexlaunchtracker.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import tk.mohithaiyappa.spacexlaunchtracker.R
import tk.mohithaiyappa.spacexlaunchtracker.databinding.ActivityMainBinding
import tk.mohithaiyappa.spacexlaunchtracker.ui.home.HomeFragment
import tk.mohithaiyappa.spacexlaunchtracker.ui.search.SearchFragment
import tk.mohithaiyappa.spacexlaunchtracker.ui.store.StoreFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    lateinit var homeFragment: HomeFragment
    lateinit var searchFragment: SearchFragment
    lateinit var storeFragment: StoreFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
                navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            )
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            homeFragment = HomeFragment()
            searchFragment = SearchFragment()
            storeFragment = StoreFragment()

            binding.loadFragment(homeFragment, "homeFragment")
            binding.bnvMainActivity.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.bnmHome -> {
                        binding.loadFragment(homeFragment, "homeFragment")
                        return@setOnItemSelectedListener true
                    }
                    R.id.bnmSearch -> {
                        binding.loadFragment(searchFragment, "searchFragment")
                        return@setOnItemSelectedListener true
                    }
                    R.id.bnmStore -> {
                        binding.loadFragment(storeFragment, "storeFragment")
                        return@setOnItemSelectedListener true
                    }
                    else -> return@setOnItemSelectedListener false
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.syncLaunches()
    }

    fun ActivityMainBinding.loadFragment(
        fragment: Fragment,
        tag: String,
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flMainFragmentContainer.id, fragment, tag)
            commit()
        }
    }
}
