package com.example.myquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myquiz.databinding.FragmentWonBinding

class FragmentWon : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentWonBinding = FragmentWonBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "myQuiz"
        binding.btnTryAgain.setOnClickListener {view: View ->
            view.findNavController().navigate(FragmentWonDirections.actionFragmentWonToQuiz())
        }

        //Receive data from Quiz class
        var args = FragmentWonArgs.fromBundle(requireArguments())
        Toast.makeText(context,
            "NumCorrect: ${args.numCorrect}, numQuestions: ${args.numQuestions}",
            Toast.LENGTH_LONG
        ).show()

        return binding.root
    }


}