package com.example.myquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.example.myquiz.databinding.FragmentQuizBinding
import com.example.myquiz.databinding.FragmentWelcomeScreenBinding

data class QuizQuestions(
    val text: String,
    val answers: List<String>,
    val correctAnswer: Int
)

class Quiz : Fragment() {
    lateinit var binding: FragmentQuizBinding
    var currentAnswer = 0
    var currentQuestionIndex = 0
    val COUNT_ANSWERS = 3

    var listOfQuestions = listOf<QuizQuestions>(
        QuizQuestions("How many states are there in the USA?", listOf("49", "50", "51"), 1)

    )

    fun randomizeQuestions(): List<QuizQuestions> {
        listOfQuestions = listOfQuestions.shuffled()
        return listOfQuestions
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        loadQuestion()

        binding.btnAnswerSend.setOnClickListener { view: View ->
            if (currentAnswer < COUNT_ANSWERS) {
                var checkedId = binding.rbGroup.checkedRadioButtonId
                val currentQuestion = listOfQuestions[currentQuestionIndex]
                var idElem: Int = 0
                when (checkedId) {
                    binding.rb0.id -> idElem = 0
                    binding.rb1.id -> idElem = 1
                    binding.rb2.id -> idElem = 2
                }

                if (currentQuestion.correctAnswer == idElem){
                    view.findNavController().navigate(R.id.action_quiz_to_fragmentWon)
                }
                else view.findNavController().navigate(R.id.action_quiz_to_fragmentGameOver)



            }


        }

        return binding.root
    }

    fun loadQuestion() {
        val currentQuestion = listOfQuestions[currentQuestionIndex]
        binding.tvAnswer.text = currentQuestion.text

        binding.rb0.text = currentQuestion.answers[0]
        binding.rb1.text = currentQuestion.answers[1]
        binding.rb2.text = currentQuestion.answers[2]
    }


}