package com.alam.quizapplication;





import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;
    private TextView scoreTextView;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?"
    };

    private String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"Mars", "Jupiter", "Saturn", "Venus"},
            {"William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"}
    };

    private int[] answers = {0, 0, 0}; // Index of correct answer for each question
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);
        scoreTextView = findViewById(R.id.scoreTextView);

        showQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    showQuestion();
                } else {
                    showFinalScore();
                }
            }
        });
    }

    private void showQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < optionsRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
            radioButton.setText(options[currentQuestionIndex][i]);
        }
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));
        if (selectedOptionIndex == answers[currentQuestionIndex]) {
            score++;
        }
        updateScore();
        optionsRadioGroup.clearCheck();
    }

    private void updateScore() {
        scoreTextView.setText("Score: " + score);
    }

    private void showFinalScore() {
        questionTextView.setText("Quiz completed!");
        optionsRadioGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        scoreTextView.setText("Final Score: " + score);
    }
}
