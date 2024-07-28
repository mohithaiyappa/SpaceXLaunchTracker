package tk.mohithaiyappa.spacexlaunchtracker.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import tk.mohithaiyappa.spacexlaunchtracker.databinding.FragmentStoreBinding

const val SPACEX_URL = "https://www.spacex.com/vehicles/falcon-9/"

class StoreFragment : Fragment() {
    private var binding: FragmentStoreBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding?.wvStore?.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            }
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        // Restore WebView state or load URL if not restored
        bdl?.let {
            binding?.wvStore?.restoreState(it)
        } ?: run {
            binding?.wvStore?.loadUrl(SPACEX_URL)
        }
    }

    override fun onPause() {
        super.onPause()
        val tempBundle = Bundle()
        binding?.wvStore?.saveState(tempBundle)
        bdl = tempBundle
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        var bdl: Bundle? = null
    }
}
