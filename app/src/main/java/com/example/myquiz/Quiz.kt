package com.example.myquiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        QuizQuestions(
            "How many states are there in the USA?",
            listOf("49", "50", "51"),
            1),
        QuizQuestions(
            "What country has the highest life expectancy?",
            listOf("Hong Kong", "Japan", "China"),
            0
        ),
        QuizQuestions(
            "What game studio makes the Red Dead Redemption series?",
            listOf("Microsoft", "GSC Game World", "Rockstar Games"),
            2
        ),


        QuizQuestions(
            "What character have both Robert Downey Jr. and Benedict Cumberbatch played? Sherlock Holmes?",
            listOf("Iron man", "Sherlock Holmes", "Mamma Mia"),
            1
        ),
        QuizQuestions(
            "What country drinks the most coffee per capita? ",
            listOf("Finland", "England", "Norway"),
            0
        ),
        QuizQuestions(
            "Which planet has the most moons?",
            listOf("Jupiter", "Earth", "Saturn"),
            2
        ),
        QuizQuestions(
            "\n" +
                    "Kratos is the main character of what video game series?",
            listOf("God of War", "Red Dead Redemtion 2", "Gta 5"),
            0
        ),
        QuizQuestions(
            "How many bones do we have in an ear?",
            listOf("1", "2", "3"),
            2
        ),
        QuizQuestions(
            "What software company is headquartered in Redmond, Washington?",
            listOf("Google", "Microsoft", "Rockstar Games"),
            1
        ),
        QuizQuestions(
            "In what country is the Chernobyl nuclear plant located?",
            listOf("Ukraine", "Belarus", "Lithuania"),
            0
        ),
        QuizQuestions(
            "What was the old name for a Snickers bar before it changed in 1990?",
            listOf("Sneakses", "The same", "Marathon"),
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
        /*
                binding.tvToolBar.text = "Answer (${currentRightAnswer + 1}/3)"
        */

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
                /*
                                binding.tvToolBar.text = "Answer (${currentRightAnswer + 1}/3)"
                */
            } else {
                view.findNavController().navigate(QuizDirections.actionQuizToFragmentGameOver())
                return@setOnClickListener
            }
            currentQuestionIndex++

            if (currentRightAnswer < COUNT_ANSWERS) {
                loadQuestion()
            } else {
                view.findNavController().navigate(
                    QuizDirections.actionQuizToFragmentWon(
                        COUNT_ANSWERS,
                        currentQuestionIndex
                    )
                )
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
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(
                R.string.title_android_trivia_question,
                currentQuestionIndex + 1,
                COUNT_ANSWERS
            )
    }


}