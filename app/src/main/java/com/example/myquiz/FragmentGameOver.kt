package com.example.myquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myquiz.databinding.FragmentGameOverBinding
import com.example.myquiz.databinding.FragmentWonBinding

class FragmentGameOver : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = FragmentGameOverBinding.inflate(inflater, container, false)
        binding.btnTryAgain.setOnClickListener {view: View ->
            view.findNavController().navigate(FragmentGameOverDirections.actionFragmentGameOverToQuiz())
        }


        return binding.root
    }

}