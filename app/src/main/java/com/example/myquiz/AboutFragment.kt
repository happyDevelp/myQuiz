
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myquiz.R
import com.example.myquiz.databinding.FragmentAboutBinding
import com.example.myquiz.databinding.FragmentGameOverBinding

class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentAboutBinding = FragmentAboutBinding.inflate(inflater, container, false)

        return binding.root
    }
}