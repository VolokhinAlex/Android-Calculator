package com.example.java.android1.javaandroid1calculator;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ButtonClickListener implements View.OnClickListener {

    private final MainActivity mainActivity;
    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;
    private Button mButtonFour;
    private Button mButtonFive;
    private Button mButtonSix;
    private Button mButtonSeven;
    private Button mButtonEight;
    private Button mButtonNine;
    private Button mButtonZero;
    private Button mButtonPoint;
    private Button mButtonMultiplication;
    private Button mButtonDivision;
    private Button mButtonMinus;
    private Button mButtonPlus;
    private Button mButtonEqual;
    private Button mButtonPercent;
    private Button mButtonDelete;
    private Button mButtonClear;

    private TextView mDisplayView;
    private String mOperator;
    private String mOldNumber;
    private Double mResult;
    private boolean mIsPoint = false;
    private boolean mIsFirstValue = true;

    protected ButtonClickListener(MainActivity activity) {
        this.mainActivity = activity;
        initializationWidgets();
        initializationButtonClickListener();
    }

    private void initializationWidgets() {
        mButtonOne = mainActivity.findViewById(R.id.button_one);
        mButtonTwo = mainActivity.findViewById(R.id.button_two);
        mButtonThree = mainActivity.findViewById(R.id.button_three);
        mButtonFour = mainActivity.findViewById(R.id.button_four);
        mButtonFive = mainActivity.findViewById(R.id.button_five);
        mButtonSix = mainActivity.findViewById(R.id.button_six);
        mButtonSeven = mainActivity.findViewById(R.id.button_seven);
        mButtonEight = mainActivity.findViewById(R.id.button_eight);
        mButtonNine = mainActivity.findViewById(R.id.button_nine);
        mButtonZero = mainActivity.findViewById(R.id.button_zero);
        mButtonPoint = mainActivity.findViewById(R.id.button_point);

        mButtonMultiplication = mainActivity.findViewById(R.id.button_multiplication);
        mButtonDivision = mainActivity.findViewById(R.id.button_division);
        mButtonMinus = mainActivity.findViewById(R.id.button_minus);
        mButtonPlus = mainActivity.findViewById(R.id.button_plus);
        mButtonEqual = mainActivity.findViewById(R.id.button_result);
        mButtonPercent = mainActivity.findViewById(R.id.button_percent);
        mButtonDelete = mainActivity.findViewById(R.id.button_delete);
        mButtonClear = mainActivity.findViewById(R.id.button_clear);
        mDisplayView = mainActivity.findViewById(R.id.display);
    }

    private void initializationButtonClickListener() {
        mButtonOne.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonThree.setOnClickListener(this);
        mButtonFour.setOnClickListener(this);
        mButtonFive.setOnClickListener(this);
        mButtonSix.setOnClickListener(this);
        mButtonSeven.setOnClickListener(this);
        mButtonEight.setOnClickListener(this);
        mButtonNine.setOnClickListener(this);
        mButtonZero.setOnClickListener(this);
        mButtonPoint.setOnClickListener(this);
        mButtonMultiplication.setOnClickListener(this);
        mButtonDivision.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
        mButtonEqual.setOnClickListener(this);
        mButtonPercent.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
    }

    private void setTextDisplay(TextView display, String text) {
        if (mIsFirstValue) {
            display.setText("");
        }
        isNotEmptyResult(display);
        display.append(text);
        mOldNumber = display.getText().toString();
        mIsFirstValue = false;
    }

    private void isNotEmptyResult(TextView display) {
        if (mResult != null) {
            display.setText("");
            if (mOperator != null) {
                mOldNumber = String.valueOf(mResult);
                display.append(mOldNumber);
            }
            mResult = null;
            mIsPoint = false;
        }
    }

    private void isNotEmptyOperator(TextView display, String operator) {
        if (mOperator != null && display.getText().toString().endsWith(mOperator)) {
            deleteChars(display, display.getText().toString());
        }
        mOperator = operator;
        setTextDisplay(display, operator);
    }

    private void deleteChars(TextView display, String text) {
        if (text.length() <= 0) return;
        display.setText(text.substring(0, text.length() - 1));
    }

    private void isPressDot(TextView display, String number, String widgetText, String operator) {
        if (!number.contains(".")) {
            setTextDisplay(display, widgetText);
            mIsPoint = false;
        } else if (number.contains(".") && !mIsPoint && operator != null) {
            setTextDisplay(display, widgetText);
            mIsPoint = true;
        }
    }

    private void equalValue(TextView display, String oldNumber, String lastNumber, String operator) {
        if (isEmptyNumber(oldNumber, operator)) return;
        try {
            oldNumber = oldNumber.substring(0, oldNumber.lastIndexOf(operator)).replace(",", ".");
            lastNumber = lastNumber.substring(lastNumber.lastIndexOf(operator) + 1);
            if (lastNumber.length() > 0 && !oldNumber.equals("")) {
                operations(display, Double.parseDouble(oldNumber), Double.parseDouble(lastNumber), operator);
            } else if (oldNumber.equals("")) {
                operations(display, 0, Double.parseDouble(lastNumber), operator);
            } else {
                if (operator.equals("*") || operator.equals("/")) {
                    operations(display, Double.parseDouble(oldNumber), 1, operator);
                } else {
                    operations(display, Double.parseDouble(oldNumber), 0, operator);
                }
            }
            mIsFirstValue = true;
        } catch (NumberFormatException exception) {
            display.setText("");
        }
    }

    private void operations(TextView display, double oldNumber, double newNumber, String operators) {
        switch (operators) {
            case "*":
                mResult = oldNumber * newNumber;
                display.setText(String.format(Locale.getDefault(), "%.1f", mResult));
                break;
            case "/":
                if (newNumber == 0) {
                    display.setText(R.string.division_by_zero);
                    mResult = 0d;
                    return;
                }
                mResult = oldNumber / newNumber;
                display.setText(String.format(Locale.getDefault(), "%.1f", mResult));
                break;
            case "+":
                mResult = oldNumber + newNumber;
                display.setText(String.format(Locale.getDefault(), "%.1f", mResult));
                break;
            case "-":
                mResult = oldNumber - newNumber;
                display.setText(String.format(Locale.getDefault(), "%.1f", mResult));
                break;
            case "%":
                mResult = oldNumber / 100;
                display.setText(String.format(Locale.getDefault(), "%.1f", mResult));
                break;
            default:
                mResult = 0d;
                throw new RuntimeException("Undefined arithmetic operator: " + operators);
        }
        mOperator = null;
    }

    private void clearDisplay(TextView display) {
        display.setText("");
        mOperator = null;
        mIsPoint = false;
        mResult = null;
    }

    private boolean isEmptyNumber(String number, String operator) {
        if (number == null || operator == null) return true;
        return false;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int widgetId = view.getId();
        switch (widgetId) {
            case R.id.button_one:
                setTextDisplay(mDisplayView, (String) mButtonOne.getText());
                break;
            case R.id.button_two:
                setTextDisplay(mDisplayView, (String) mButtonTwo.getText());
                break;
            case R.id.button_three:
                setTextDisplay(mDisplayView, (String) mButtonThree.getText());
                break;
            case R.id.button_four:
                setTextDisplay(mDisplayView, (String) mButtonFour.getText());
                break;
            case R.id.button_five:
                setTextDisplay(mDisplayView, (String) mButtonFive.getText());
                break;
            case R.id.button_six:
                setTextDisplay(mDisplayView, (String) mButtonSix.getText());
                break;
            case R.id.button_seven:
                setTextDisplay(mDisplayView, (String) mButtonSeven.getText());
                break;
            case R.id.button_eight:
                setTextDisplay(mDisplayView, (String) mButtonEight.getText());
                break;
            case R.id.button_nine:
                setTextDisplay(mDisplayView, (String) mButtonNine.getText());
                break;
            case R.id.button_zero:
                setTextDisplay(mDisplayView, (String) mButtonZero.getText());
                break;
            case R.id.button_point:
                isPressDot(mDisplayView, mDisplayView.getText().toString(),
                        (String) mButtonPoint.getText(), mOperator);
                break;
            case R.id.button_percent:
                isNotEmptyOperator(mDisplayView, (String) mButtonPercent.getText());
                break;
            case R.id.button_delete:
                deleteChars(mDisplayView, mDisplayView.getText().toString());
                break;
            case R.id.button_clear:
                clearDisplay(mDisplayView);
                break;
            case R.id.button_division:
                isNotEmptyOperator(mDisplayView, (String) mButtonDivision.getText());
                break;
            case R.id.button_multiplication:
                isNotEmptyOperator(mDisplayView, (String) mButtonMultiplication.getText());
                break;
            case R.id.button_minus:
                isNotEmptyOperator(mDisplayView, (String) mButtonMinus.getText());
                break;
            case R.id.button_plus:
                isNotEmptyOperator(mDisplayView, (String) mButtonPlus.getText());
                break;
            case R.id.button_result:
                equalValue(mDisplayView, mOldNumber, mOldNumber, mOperator);
                break;
            default:
                throw new RuntimeException("Undefined widget id: " + widgetId);
        }
    }

    public void setOldNumber(String mOldNumber) {
        this.mOldNumber = mOldNumber;
    }

    public void setOperator(String mOperator) {
        this.mOperator = mOperator;
    }

    public void setIsFirstValue(boolean mIsFirstValue) {
        this.mIsFirstValue = mIsFirstValue;
    }

}
