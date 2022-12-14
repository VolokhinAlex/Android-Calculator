package com.example.java.android1.javaandroid1calculator;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ButtonClickListener implements View.OnClickListener {

    private final MainActivity mainActivity;
    private MaterialButton mButtonOne;
    private MaterialButton mButtonTwo;
    private MaterialButton mButtonThree;
    private MaterialButton mButtonFour;
    private MaterialButton mButtonFive;
    private MaterialButton mButtonSix;
    private MaterialButton mButtonSeven;
    private MaterialButton mButtonEight;
    private MaterialButton mButtonNine;
    private MaterialButton mButtonZero;
    private MaterialButton mButtonPoint;
    private MaterialButton mButtonMultiplication;
    private MaterialButton mButtonDivision;
    private MaterialButton mButtonMinus;
    private MaterialButton mButtonPlus;
    private MaterialButton mButtonEqual;
    private MaterialButton mButtonPercent;
    private MaterialButton mButtonDelete;
    private MaterialButton mButtonClear;

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
        if (isMoreOperator(mOperator, display.getText().toString())) return;
        setTextDisplay(display, operator);
    }

    private void deleteChars(TextView display, String text) {
        if (text.length() <= 0) return;
        display.setText(text.substring(0, text.length() - 1));
        if (mOperator != null && isNumber(text.substring(text.lastIndexOf(mOperator) + 1))) return;
        mOperator = null;
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
        if (isAllowedNumber(display, oldNumber, newNumber)) return;
        switch (operators) {
            case "*":
                mResult = oldNumber * newNumber;
                display.setText(stringFormatter(mResult));
                break;
            case "/":
                if (newNumber == 0) {
                    display.setText(R.string.division_by_zero);
                    mResult = 0d;
                    return;
                }
                mResult = oldNumber / newNumber;
                display.setText(stringFormatter(mResult));
                break;
            case "+":
                mResult = oldNumber + newNumber;
                display.setText(stringFormatter(mResult));
                break;
            case "-":
                mResult = oldNumber - newNumber;
                display.setText(stringFormatter(mResult));
                break;
            case "%":
                mResult = oldNumber / 100;
                display.setText(stringFormatter(mResult));
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
        if (number == null || operator == null || number.equals(".")) return true;
        if (number.contains(operator)) {
            if (isNumber(number.substring(0, number.lastIndexOf(operator)))) {
                return false;
            }
            return !isNumber(number.substring(number.lastIndexOf(operator) + 1));
        }
        return false;
    }

    private boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAllowedNumber(TextView display, Double oldNumber, Double newNumber) {
        if (oldNumber > 99999999 || newNumber > 99999999) {
            display.setText(R.string.not_allowed_values);
            return true;
        }
        return false;
    }

    private boolean isMoreOperator(String operator, String oldNumber) {
        String[] operators = new String[]{"+", "-", "*", "/"};
        for (String s : operators) {
            if (operator != null && oldNumber.contains(s)) {
                if (isNumber(oldNumber.substring(oldNumber.lastIndexOf(s) + 1))) {
                    return true;
                } else {
                    deleteChars(mDisplayView, oldNumber);
                }
            }
        }
        return false;
    }

    private String stringFormatter(double result) {
        return new DecimalFormat("#.######").format(result);
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
                equalValue(mDisplayView, mOldNumber, mOldNumber, mOperator);
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
