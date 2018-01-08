package com.example.android.classicmusicquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * Created by fanny on 05/01/2018.
 */

public class MainActivity extends AppCompatActivity {

    final int totalQuestions = 6;
    Button submitButton;
    RadioGroup questionOneAnswers, questionTwoAnswers, questionThreeAnswers, questionFourAnswers,
            questionFiveAnswers, questionSixAnswers;
    RadioButton questionOneCorrectAnswer, questionTwoCorrectAnswer, questionThreeCorrectAnswer,
            questionFourCorrectAnswer, questionFiveCorrectAnswer, questionSixCorrectAnswer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (findViewById(R.id.submit_button));


        questionOneAnswers = findViewById(R.id.radio_group_question_1);
        questionTwoAnswers = findViewById(R.id.radio_group_question_2);
        questionThreeAnswers = findViewById(R.id.radio_group_question_3);
        questionFourAnswers = findViewById(R.id.radio_group_question_4);
        questionFiveAnswers = findViewById(R.id.radio_group_question_5);
        questionSixAnswers = findViewById(R.id.radio_group_question_6);

        questionOneCorrectAnswer = findViewById(R.id.question_1_answer_3_correct);
        questionTwoCorrectAnswer = findViewById(R.id.question_2_answer_2_correct);
        questionThreeCorrectAnswer = findViewById(R.id.question_3_answer_2_correct);
        questionFourCorrectAnswer = findViewById(R.id.question_4_answer_4_correct);
        questionFiveCorrectAnswer = findViewById(R.id.question_5_answer_3_correct);
        questionSixCorrectAnswer = findViewById(R.id.question_6_answer_3_correct);

        Intent userNameIntent = getIntent();
        final String userName = userNameIntent.getStringExtra("userName");


        submitButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                int[] results = checkAnswers();
                String message = createMessage(results, totalQuestions, userName);
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.alertDialog);
                builder.setIcon(R.drawable.trompet);
                builder.setMessage(message);


                // Button in AlertDialog to finish, try again or show good answers
                builder.setPositiveButton(R.string.finish_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // finish MainActivity
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });
                builder.setNegativeButton(R.string.view_answers_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // See good answers
                                highlightCorrectAnswers();
                            }
                        });

                builder.setNeutralButton(R.string.try_again_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // re start the quiz without cancelling answers
                                dialog.cancel();
                            }
                        });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }

    /**
     * This method checks if any answer in any question was selected and then checks
     * if the correct answer was selected. It also calculates the score of correct answers and
     * how many questions was not answered.
     *
     * @return an array of two values [correct answers, questions not selected by the user]
     */
    public int[] checkAnswers() {
        int correctAnswers = 0;
        int questionsNotChecked = 0;


        if (questionOneAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionOneCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionTwoAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionTwoCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionThreeAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionThreeCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionFourAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionFourCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionFiveAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionFiveCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionSixAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionSixCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }
        return new int[]{correctAnswers, questionsNotChecked};
    }

    /**
     * This method creates the score message and checks if the user answered to all questions.
     *
     * @param results        an array of two values [correct answers, questions not selected by the user]
     * @param totalQuestions the total number of questions in the quiz
     * @param userName       the user's name
     * @return final score message
     */
    public String createMessage(int[] results, int totalQuestions, String userName) {
        String message = "";
        message += getString(R.string.userGreetings) + " " + userName + "!\n";
        if (results[1] > 0) {
            message += getString(R.string.question_not_answered) + " " + results[1] + " " + getString(R.string.score_message_3);
            return message;
        } else if (results[0] == totalQuestions) {
            message += getString(R.string.perfect_score);
            return message;
        } else if (results[0] >= 3) {
            message += getString(R.string.score_message_1) + " " + results[0] + " " + getString(R.string.correct_answers);
            message += " " + getString(R.string.total_question) + " "+ totalQuestions;
            return message;
        } else {
            message += getString(R.string.score_message_2) + " " + results[0] + " " + getString(R.string.correct_answers);
            message += " " + getString(R.string.total_question) +" "+  totalQuestions;
            return message;
        }
    }

    /**
     * This method changes the text color of correct answers.
     */
    public void highlightCorrectAnswers() {
        questionOneCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionTwoCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionThreeCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionFourCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionFiveCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionSixCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
    }
}
