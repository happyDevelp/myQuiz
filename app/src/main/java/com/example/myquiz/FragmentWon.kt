package com.example.myquiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.navigation.findNavController
import com.example.myquiz.databinding.FragmentWonBinding

class FragmentWon : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentWonBinding = FragmentWonBinding.inflate(inflater, container, false)
        binding.btnTryAgain.setOnClickListener { view: View ->
            view.findNavController().navigate(FragmentWonDirections.actionFragmentWonToQuiz())
        }

        //Receive data from Quiz class

        /*  Toast.makeText(context,
              "NumCorrect: ${args.numCorrect}, numQuestions: ${args.numQuestions}",
              Toast.LENGTH_LONG
          ).show()*/

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        //if we can`t share then we have this logic for hide menu icon
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager))
            menu.findItem(R.id.share).isVisible = false
    }

    private fun getShareIntent(): Intent {
        var args = FragmentWonArgs.fromBundle(requireArguments())
        /*        val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
                return shareIntent*/

        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }

        return super.onOptionsItemSelected(item)
    }
}