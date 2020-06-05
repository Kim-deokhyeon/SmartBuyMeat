package navigation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.smartbuymeat2.MainActivity

import com.example.smartbuymeat2.R
import kotlinx.android.synthetic.*

class NearsFragment : Fragment() {

    var fragmentView : View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_nears,container,false)


        return fragmentView
    }



}
