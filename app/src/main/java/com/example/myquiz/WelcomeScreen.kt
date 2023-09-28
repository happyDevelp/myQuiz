package com.example.myquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.myquiz.databinding.FragmentWelcomeScreenBinding


class WelcomeScreen : Fragment() {
    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        binding.btnStartGame.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_quiz)

        }

        return binding.root
    }


}