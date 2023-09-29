package com.example.myquiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompatSideChannelService
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
    var currentRightAnswer = 0
    var currentQuestionIndex = 0
    val COUNT_ANSWERS = 3

    var listOfQuestions = listOf<QuizQuestions>(
        QuizQuestions("How many states are there in the USA?", listOf("49", "50", "51"), 1),
        QuizQuestions(
            "What country has the highest life expectancy?",
            listOf("Hong Kong", "Japan", "China"),
            0
        ),
        QuizQuestions(
            "What game studio makes the Red Dead Redemption series?",
            listOf("Microsoft", "GSC Game World", "Rockstar Games"),
            2
        )

    )

    fun randomizeQuestions(): List<QuizQuestions> {
        listOfQuestions = listOfQuestions.shuffled()
        return listOfQuestions
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.tvToolBar.text = "Answer (${currentRightAnswer + 1}/3)"

        var idElem: Int = 0
        randomizeQuestions()
        loadQuestion()



        binding.btnAnswerSend.setOnClickListener { view: View ->
            var currentQuestion = listOfQuestions[currentQuestionIndex]
            var checkedId = binding.rbGroup.checkedRadioButtonId
            var idElem: Int = 0
            when (checkedId) {
                binding.rb0.id -> idElem = 0
                binding.rb1.id -> idElem = 1
                binding.rb2.id -> idElem = 2
            }

            if (currentQuestion.correctAnswer == idElem) {
                currentRightAnswer++
                binding.tvToolBar.text = "Answer (${currentRightAnswer + 1}/3)"
            }
            else {
                view.findNavController().navigate(R.id.action_quiz_to_fragmentGameOver)
                return@setOnClickListener //What if delete?
            }
            currentQuestionIndex++

            if (currentRightAnswer < COUNT_ANSWERS){
                loadQuestion()
            }

            else {
                view.findNavController().navigate(R.id.action_quiz_to_fragmentWon)
                currentRightAnswer = 0
                currentQuestionIndex = 0
                randomizeQuestions()
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