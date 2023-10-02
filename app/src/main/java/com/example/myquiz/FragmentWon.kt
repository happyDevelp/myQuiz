package com.example.myquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            view.findNavController().navigate(R.id.action_fragmentWon_to_quiz)
        }

        return binding.root
    }


}