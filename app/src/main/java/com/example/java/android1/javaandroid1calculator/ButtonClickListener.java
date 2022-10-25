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
    private Button mButtonEquals;
    private Button mButtonPercent;
    private Button mButtonDelete;
    private Button mButtonClear;
    private TextView mDisplayLabel;
    private String mOperator;
    private String mOldNumber;
    private Double mResult;
    private boolean mIsDot = true;
    private boolean mIsFirstValue;

    public ButtonClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        initializationWidgets();
        initializationButtonsClickListener();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int widgetId = view.getId();
        switch (widgetId) {
            case R.id.button_one:
                setValueOnDisplay((String) mButtonOne.getText());
                break;
            case R.id.button_two:
                setValueOnDisplay((String) mButtonTwo.getText());
                break;
            case R.id.button_three:
                setValueOnDisplay((String) mButtonThree.getText());
                break;
            case R.id.button_four:
                setValueOnDisplay((String) mButtonFour.getText());
                break;
            case R.id.button_five:
                setValueOnDisplay((String) mButtonFive.getText());
                break;
            case R.id.button_six:
                setValueOnDisplay((String) mButtonSix.getText());
                break;
            case R.id.button_seven:
                setValueOnDisplay((String) mButtonSeven.getText());
                break;
            case R.id.button_eight:
                setValueOnDisplay((String) mButtonEight.getText());
                break;
            case R.id.button_nine:
                setValueOnDisplay((String) mButtonNine.getText());
                break;
            case R.id.button_zero:
                setValueOnDisplay((String) mButtonZero.getText());
                break;
            case R.id.button_point:
                isPressDot(mOldNumber);
                break;
            case R.id.button_multiplication:
                isNotEmptyOperator("*");
                break;
            case R.id.button_division:
                isNotEmptyOperator("/");
                break;
            case R.id.button_plus:
                isNotEmptyOperator("+");
                break;
            case R.id.button_minus:
                isNotEmptyOperator("-");
                break;
            case R.id.button_result:
                equalsValue();
                break;
            case R.id.button_percent:
                isNotEmptyOperator("%");
                break;
            case R.id.button_clear:
                mDisplayLabel.setText("");
                break;
            case R.id.button_delete:
                deleteValueOnDisplay();
                break;
            default:
                throw new RuntimeException("Undefined widget id: " + widgetId);
        }
    }

    public void initializationWidgets() {
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
        mButtonEquals = mainActivity.findViewById(R.id.button_result);
        mButtonPercent = mainActivity.findViewById(R.id.button_percent);
        mButtonDelete = mainActivity.findViewById(R.id.button_delete);
        mButtonClear = mainActivity.findViewById(R.id.button_clear);
        mDisplayLabel = mainActivity.findViewById(R.id.display);
    }

    public void initializationButtonsClickListener() {
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
        mButtonEquals.setOnClickListener(this);
        mButtonPercent.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
    }

    private void setValueOnDisplay(String text) {
        if (!mIsFirstValue) {
            mDisplayLabel.setText("");
        }
        isNotEmptyResult();
        mDisplayLabel.append(text);
        mOldNumber = mDisplayLabel.getText().toString();
        mIsFirstValue = true;
    }

    private void equalsValue() {
        String lastNumber = mDisplayLabel.getText().toString();
        if (lastNumber.length() > 1) {
            try {
                lastNumber = lastNumber.substring(lastNumber.lastIndexOf(mOperator) + 1);
                if (lastNumber.length() > 0) {
                    operations(Double.parseDouble(lastNumber));
                } else {
                    if (mOperator.equals("*") || mOperator.equals("/")) {
                        operations(1);
                    } else {
                        operations(0);
                    }
                }
                mIsFirstValue = true;
            } catch (NumberFormatException e) {
                mDisplayLabel.setText("");
            }
        }
    }

    private void operations(double number) {
        double oldNumber = Double.parseDouble(mOldNumber.substring(0, mOldNumber.lastIndexOf(mOperator)));
        switch (mOperator) {
            case "*":
                mResult = oldNumber * number;
                mDisplayLabel.setText(String.format(Locale.getDefault(),"%.1f", mResult));
                break;
            case "/":
                if (number == 0) {
                    mDisplayLabel.setText(R.string.division_by_zero);
                    mResult = 0d;
                    return;
                }
                mResult = oldNumber / number;
                mDisplayLabel.setText(String.format(Locale.getDefault(),"%.1f", mResult));
                break;
            case "+":
                mResult = oldNumber + number;
                mDisplayLabel.setText(String.format(Locale.getDefault(),"%.1f", mResult));
                break;
            case "-":
                mResult = oldNumber - number;
                mDisplayLabel.setText(String.format(Locale.getDefault(),"%.1f", mResult));
                break;
            case "%":
                mResult = oldNumber / 100d;
                mDisplayLabel.setText(String.format(Locale.getDefault(),"%.1f", mResult));
                break;
        }
        mOperator = null;
    }

    private void isNotEmptyResult() {
        if (mResult != null) {
            mDisplayLabel.setText("");
            if (mOperator != null) {
                mOldNumber = String.valueOf(mResult);
                mDisplayLabel.append(mOldNumber);
            }
            mResult = null;
            mIsDot = false;
        }
    }

    private void isNotEmptyOperator(String operator) {
        if (mOperator != null && mDisplayLabel.getText().toString().endsWith(mOperator)) {
            deleteValueOnDisplay();
        }
        mOperator = operator;
        setValueOnDisplay(operator);
    }

    private void deleteValueOnDisplay() {
        String text = mDisplayLabel.getText().toString();
        if (text.length() <= 0) return;
        mDisplayLabel.setText(text.substring(0, text.length() - 1));
    }

    private void isPressDot(String number) {
        if (!number.contains(".")) {
            setValueOnDisplay((String) mButtonPoint.getText());
            mIsDot = false;
        } else if (number.contains(".") && !mIsDot) {
            setValueOnDisplay((String) mButtonPoint.getText());
            mIsDot = true;
        }
    }

    public void setOldNumber(String mOldNumber) {
        this.mOldNumber = mOldNumber;
    }

    public void setOperator(String mOperator) {
        this.mOperator = mOperator;
    }

    public void setFlagIfValueFirst(boolean mIsFirstValue) {
        this.mIsFirstValue = mIsFirstValue;
    }
}
