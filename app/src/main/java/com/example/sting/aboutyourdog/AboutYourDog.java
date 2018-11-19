package com.example.sting.aboutyourdog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class AboutYourDog extends AppCompatActivity {

    boolean[] isQuestion = new boolean[6]; // stores correct answer

    // Color set for to mark answers as incorrect

    ColorStateList colorStateListIncorrect = new ColorStateList(
            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{
                    Color.rgb(117, 117, 117),
                    Color.rgb(244, 67, 54)
            }
    );

    // Color set to return color to primary

    ColorStateList colorStateListNeutral = new ColorStateList(
            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{
                    Color.rgb(117, 117, 117),
                    Color.rgb(103, 58, 183)
            }
    );

    // Color set to mark correct answers

    ColorStateList colorStateListCorrect = new ColorStateList(

            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{
                    Color.rgb(117, 117, 117),
                    Color.rgb(76, 175, 80)
            }
    );

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_your_dog);
        final TextInputEditText answer_EditText = findViewById(R.id.answer_edit_Text);

        //Sets and removes the focus of the answer EditText
        answer_EditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(answer_EditText.getWindowToken(), 0);
                    answer_EditText.setFocusable(false);
                    answer_EditText.setFocusableInTouchMode(true);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Set the color of all radio button as primary, this to prevent errors from happening
        // in the UI during execution time
        AppCompatRadioButton[] radioAnswersGroup = new AppCompatRadioButton[9];
        radioAnswersGroup[0] = findViewById(R.id.question_2_answer_a_radio);
        radioAnswersGroup[1] = findViewById(R.id.question_2_answer_b_radio);
        radioAnswersGroup[2] = findViewById(R.id.question_2_answer_c_radio);
        radioAnswersGroup[3] = findViewById(R.id.question_3_answer_a_radio);
        radioAnswersGroup[4] = findViewById(R.id.question_3_answer_b_radio);
        radioAnswersGroup[5] = findViewById(R.id.question_4_answer_a_radio);
        radioAnswersGroup[6] = findViewById(R.id.question_4_answer_b_radio);
        radioAnswersGroup[7] = findViewById(R.id.question_6_answer_a_radio);
        radioAnswersGroup[8] = findViewById(R.id.question_6_answer_b_radio);

        for (int i = 0; i < radioAnswersGroup.length; i++) {
            radioAnswersGroup[i].setSupportButtonTintList(colorStateListNeutral);
        }
    }

    /**
     * This method checks for correct answers in questions 2,3,4 and 6. Returning a true or false
     * value every time a radio button is clicked.
     **/
    public void questionRadioCheck(View view) {
        // Is the button now checked?
        boolean checked = ((AppCompatRadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.question_2_answer_a_radio:
                if (checked)
                    isQuestion[1] = false;
                break;
            case R.id.question_2_answer_b_radio:
                if (checked)
                    isQuestion[1] = false;
                break;
            case R.id.question_2_answer_c_radio:
                if (checked)
                    isQuestion[1] = true;
                break;
            case R.id.question_3_answer_a_radio:
                if (checked)
                    isQuestion[2] = true;
                break;
            case R.id.question_3_answer_b_radio:
                if (checked)
                    isQuestion[2] = false;
                break;
            case R.id.question_4_answer_a_radio:
                if (checked)
                    isQuestion[3] = true;
                break;
            case R.id.question_4_answer_b_radio:
                if (checked)
                    isQuestion[3] = false;
                break;
            case R.id.question_6_answer_a_radio:
                if (checked)
                    isQuestion[5] = false;
                break;
            case R.id.question_6_answer_b_radio:
                if (checked)
                    isQuestion[5] = true;
                break;
        }
    }

    /**
     * This method checks for the number of correct answers while setting up the color for
     * correct an incorrect answers. A dialog is shown after execution with the proper output.
     **/
    @SuppressLint("RestrictedApi")
    public void submitAnswer(View view) {
        int correctAnswers = 0;
        int numberQuestion1;
        boolean[] question5answers = new boolean[4];
        boolean[] radioAnswers = new boolean[9];
        String answerQuestion1;
        String message = " ";
        String dismiss = " ";
        String action = " ";
        String facts = getString(R.string.dog_facts);

        // Checking correct answer in question number 1
        // If answer is correct, 250, text color changes to green
        // If not text color changes to red

        TextInputEditText answer_EditText = findViewById(R.id.answer_edit_Text);
        answerQuestion1 = answer_EditText.getText().toString();

        if (TextUtils.isEmpty(answerQuestion1)) {
            numberQuestion1 = 0;
        } else {
            numberQuestion1 = Integer.parseInt(answerQuestion1);
        }

        if (numberQuestion1 == 250) {
            isQuestion[0] = true;
            answer_EditText.setTextColor(getResources().getColor(R.color.colorRightAnswer));
        } else if (numberQuestion1 == 0) {
            answer_EditText.setTextColor(Color.BLACK);
        } else {
            isQuestion[0] = false;
            answer_EditText.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }

        // Setting up correct and incorrect colors for questions 2, 3, 4 and 6

        AppCompatRadioButton[] radioAnswersGroup = new AppCompatRadioButton[9];
        radioAnswersGroup[0] = findViewById(R.id.question_2_answer_a_radio);
        radioAnswersGroup[1] = findViewById(R.id.question_2_answer_b_radio);
        radioAnswersGroup[2] = findViewById(R.id.question_2_answer_c_radio);
        radioAnswersGroup[3] = findViewById(R.id.question_3_answer_a_radio);
        radioAnswersGroup[4] = findViewById(R.id.question_3_answer_b_radio);
        radioAnswersGroup[5] = findViewById(R.id.question_4_answer_a_radio);
        radioAnswersGroup[6] = findViewById(R.id.question_4_answer_b_radio);
        radioAnswersGroup[7] = findViewById(R.id.question_6_answer_a_radio);
        radioAnswersGroup[8] = findViewById(R.id.question_6_answer_b_radio);

        for (int i = 0; i < radioAnswersGroup.length; i++) {
            radioAnswers[i] = radioAnswersGroup[i].isChecked();
        }

        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 1 || i == 4 || i == 6 || i == 7) {
                if (radioAnswers[i]) {
                    radioAnswersGroup[i].setSupportButtonTintList(colorStateListIncorrect);
                    radioAnswersGroup[i].setChecked(true);
                } else {
                    radioAnswersGroup[i].setSupportButtonTintList(colorStateListNeutral);
                }
            } else {
                if (radioAnswers[i]) {
                    radioAnswersGroup[i].setSupportButtonTintList(colorStateListCorrect);
                    radioAnswersGroup[i].setChecked(true);
                } else {
                    radioAnswersGroup[i].setSupportButtonTintList(colorStateListNeutral);
                }
            }
        }

        // Checking correct answer in question number 5
        // If either answers b or d are marked the answer is incorrect
        // If either answers a or c are marked the answer is correct only if neither answer b nor d
        // are marked

        AppCompatCheckBox[] question5answerBox = new AppCompatCheckBox[4];
        question5answerBox[0] = findViewById(R.id.question_5_answer_a);
        question5answerBox[1] = findViewById(R.id.question_5_answer_b);
        question5answerBox[2] = findViewById(R.id.question_5_answer_c);
        question5answerBox[3] = findViewById(R.id.question_5_answer_d);

        for (int i = 0; i < question5answerBox.length; i++) {
            question5answers[i] = question5answerBox[i].isChecked();
        }

        if ((!question5answers[1] && !question5answers[3]) && (question5answers[0] ||
                question5answers[2])) {
            isQuestion[4] = true;
        } else {
            isQuestion[4] = false;
        }

        for (int i = 0; i < 4; i++) {
            if (i == 1 || i == 3) {
                if (question5answers[i]) {
                    question5answerBox[i].setSupportButtonTintList(colorStateListIncorrect);
                    if (question5answers[0])
                        question5answerBox[0].setSupportButtonTintList(colorStateListNeutral);
                    if (question5answers[2])
                        question5answerBox[2].setSupportButtonTintList(colorStateListNeutral);
                } else {
                    question5answerBox[i].setSupportButtonTintList(colorStateListNeutral);
                }
            } else {
                if (question5answers[i] && (!question5answers[1] && !question5answers[3])) {
                    question5answerBox[i].setSupportButtonTintList(colorStateListCorrect);
                } else {
                    question5answerBox[i].setSupportButtonTintList(colorStateListNeutral);
                }
            }
        }


        // Establishing number of correct answer to display in dialog box

        for (int i = 0; i < isQuestion.length; i++) {
            if (isQuestion[i]) correctAnswers += 1;
        }

        switch (correctAnswers) {
            case 1:
                message = getString(R.string.message_1_answer);
                dismiss = getString(R.string.try_again);
                action = getString(R.string.answers);
                break;
            case 6:
                message = getString(R.string.message_awesome);
                dismiss = getString(R.string.nice);
                action = getString(R.string.facts);
                break;
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                message = getString(R.string.message_complete, correctAnswers);
                dismiss = getString(R.string.try_again);
                action = getString(R.string.answers);
                break;
        }

        FragmentManager fm = getSupportFragmentManager();
        DialogFragment finalMessage = MessagesIncomplete.newInstance(message, dismiss, action, facts);
        finalMessage.show(fm, "Message");
    }

}
