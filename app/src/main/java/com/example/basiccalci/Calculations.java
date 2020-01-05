package com.example.basiccalci;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Calculations {

    final private ArrayList<String> binaryOperators = new ArrayList<>();
    public ArrayList<String> numbers = new ArrayList<>();
    public String answer;
    public String currentStatus = "null";
    private Context ctx;


    public Calculations(Context ct) {
        ctx = ct;

        binaryOperators.add("/");
        binaryOperators.add("*");
        binaryOperators.add("-");
        binaryOperators.add("+");


    }

    public boolean clear() {
        try {
            numbers.clear();
            answer = "0";
            calc(numbers);
        } catch (Exception e) {
            calc(numbers);
            return false;
        }
        currentStatus = "null";
        return true;
    }

    public boolean ce() {
        try {
            switch (currentStatus) {
                case "num":
                    numbers.remove(numbers.size() - 1);
                    break;
                case "binary":
                    numbers.remove(numbers.size() - 1);
                    break;
                case "po":
                    numbers.remove(numbers.size() - 1);
                    break;
                case "pc":
                    numbers.remove(numbers.size() - 1);
                    break;
            }
            if (numbers.size() == 0) {
                currentStatus = "null";
                return true;
            }
            currentStatus = getStatus(numbers.size() - 1);
            calc(numbers);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean numberClicked(String number) {
        switch (currentStatus) {
            case "null":
                numbers.add(number);
                break;
            case "num":
                numbers.set(numbers.size() - 1, formatNumber(numbers.get(numbers.size() - 1) + number));
                break;
            case "binary":
                numbers.add(number);
                break;
            case "po":
                numbers.add(number);
                break;
            case "pc":
                numbers.add("*");
                numbers.add(number);
                break;
            default:
                Toast.makeText(ctx, "Error while numberClicked(), currentStatus = " + currentStatus, Toast.LENGTH_SHORT).show();
                break;
        }
        currentStatus = "num";
        calc(numbers);
        return true;
    }


    public boolean operatorClicked(String operator) {
        Log.d("calc", "Operator : " + operator);
        try {
            if (operator.equals("±")) {
                numbers.set(numbers.size() - 1, formatNumber(String.valueOf((Double.parseDouble(numbers.get(numbers.size() - 1)) * -1))));
                calc(numbers);
                return true;
            }
        } catch (Exception e) {
            calc(numbers);
            return false;
        }

        if (binaryOperators.contains(operator)) {
            switch (currentStatus) {
                case "null":
                    numbers.add("0");
                    numbers.add(operator);
                    currentStatus = "binary";
                    break;
                case "num":
                    numbers.add(operator);
                    currentStatus = "binary";
                    break;
                case "binary":
                    numbers.set(numbers.size() - 1, operator);
                    currentStatus = "binary";
                    break;
                case "po":
                    break;
                case "pc":
                    numbers.add(operator);
                    currentStatus = "binary";
                    break;
                default:
                    Toast.makeText(ctx, "Error while binary, currentStatus = " + currentStatus, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        return false;
    }

    public boolean parent_openClicked() {
        switch (currentStatus) {
            case "null":
                numbers.add("(");
                break;
            case "num":
                numbers.add("*");
                numbers.add("(");
                break;
            case "binary":
                numbers.add("(");
                break;
            case "po":
                numbers.add("(");
                break;
            case "pc":
                numbers.add("*");
                numbers.add("(");
                break;
            default:
                Toast.makeText(ctx, "Error while numberClicked(), currentStatus = " + currentStatus, Toast.LENGTH_SHORT).show();
                break;
        }
        currentStatus = "po";
        calc(numbers);
        return true;
    }

    public boolean parent_closeClicked() {
        if (!canCloseParent()) {
            Toast.makeText(ctx, "No open parenthesis to compensate", Toast.LENGTH_SHORT).show();
            return false;
        }
        switch (currentStatus) {
            case "num":
                numbers.add(")");
                currentStatus = "pc";
                break;
            case "binary":
                break;
            case "po":
                break;
            case "pc":
                numbers.add(")");
                break;
            default:
                Toast.makeText(ctx, "Error while numberClicked(), currentStatus = " + currentStatus, Toast.LENGTH_SHORT).show();
                break;
        }
        calc(numbers);
        return true;
    }

    private boolean canCloseParent() {
        int bracketOffset = 0;
        for (String number : numbers) {
            if (number.equals("(")) {
                bracketOffset++;
            }
            if (number.equals(")")) {
                bracketOffset--;
            }
        }
        return (bracketOffset > 0);
    }


    public String getCurrentNumber() {
        if (!(currentStatus.equals("num"))) {
            Log.d("calc", "current Number returned : 0");
            return "0";
        } else {
            Log.d("calc", "current Number returned : " + numbers.get(numbers.size() - 1));
            return numbers.get(numbers.size() - 1);
        }
    }

    public boolean evaluateAnswer() {
        if (numbers.contains("(")) {
            if (canCloseParent()) {
                int bracketOffset = 0;
                for (String number : numbers) {
                    if (number.equals("(")) {
                        bracketOffset++;
                    }
                    if (number.equals(")")) {
                        bracketOffset--;
                    }
                }
                for (int i = 0; i < bracketOffset; i++) {
                    numbers.add(")");
                }
            }
        }
        answer = doBODMAS(numbers);
        calc(numbers);
        return true;
    }

    public String calc(ArrayList<String> numbers) {
        String num = "";
        for (String number : numbers) {
            num += number + " ";
        }
        Log.d("calc", num);
        return num;
    }

    @NonNull
    private String doBODMAS(ArrayList<String> numbers) {

        while (numbers.size() != 1) {
            Log.d("calc", "doBodmas:");
            calc(numbers);
            Log.d("calc", "contains ( = " + numbers.contains("("));
            if (numbers.contains("(")) {
                int bracketOffset = 0;
                for (int i = numbers.size() - 1; i >= 0; i--) {
                    Log.d("calc", "at i = " + i);
                    if (numbers.get(i).equals(")")) {
                        bracketOffset--;
                        for (int j = i - 1; j >= 0; j--) {
                            Log.d("calc", "at i = " + j);
                            if (numbers.get(j).equals(")")) {
                                bracketOffset--;
                            }
                            if (numbers.get(j).equals("(")) {
                                bracketOffset++;
                                if (bracketOffset == 0) {
                                    Log.d("calc", "satisfied at j = " + j);
                                    ArrayList<String> nums = new ArrayList<>();
                                    for (int k = j + 1; k < i; k++) {
                                        nums.add(numbers.get(k));
                                    }
                                    calc(nums);
                                    for (int k = j; k < i; k++) {
                                        numbers.remove(j);
                                    }
                                    numbers.set(j, doBODMAS(nums));
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            } else {
                if (contains(binaryOperators, numbers)) {
                    for (int i = 0; i < binaryOperators.size(); i++) {
                        if (numbers.contains(binaryOperators.get(i))) {
                            for (int j = 0; j < numbers.size(); j++) {
                                if (numbers.get(j).equals(binaryOperators.get(i))) {
                                    String ans = (evaluateBinary(numbers.get(j - 1), numbers.get(j), numbers.get(j + 1)));
                                    numbers.remove(j - 1);
                                    numbers.remove(j - 1);
                                    numbers.set(j - 1, ans);
                                    i = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        currentStatus = "num";
        return formatNumber(numbers.get(0));
    }

    private boolean contains(ArrayList<String> source, ArrayList<String> target) {
        for (String val : target) {
            if (source.contains(val)) {
                return true;
            }
        }
        return false;
    }

    private String getStatus(int index) {
        if (binaryOperators.contains(numbers.get(index))) {
            return "binary";
        } else if (numbers.get(index).equals("(")) {
            return "po";
        } else if (numbers.get(index).equals(")")) {
            return "pc";
        } else if (numbers.get(index).equals("0")) {
            if (numbers.size() - 1 == index) {
                numbers.remove(index);
            }
            return "null";
        } else {
            return "num";
        }
    }


    private String formatNumber(String number) {
        Log.d("calc", "Format Number : " + number);
        if (number == null || number.equals("") || number.isEmpty()) {
            return "0";
        }
        try {
            if (Double.parseDouble(number) == Long.parseLong(number.split("\\.")[0])) {
                return String.valueOf(Long.parseLong(number.split("\\.")[0]));
            } else {
                return String.valueOf(Double.parseDouble(number));
            }
        } catch (NumberFormatException nfe) {
            try {
                return String.valueOf(Double.parseDouble(number));
            } catch (NumberFormatException nfe2) {
                try {
                    number = number.substring(0, number.length() - 1);
                    return String.valueOf(Double.parseDouble(number));
                } catch (Exception e) {
                    return number;
                }
            }
        }
    }


    private String evaluateBinary(String number1, String operation, String number2) {
        Log.d("calc", "binary : " + number1 + ", " + operation + ", " + number2);
        String answer = "error";
        switch (operation) {
            case "/":
                answer = division(number1, number2);
                break;
            case "*":
                answer = multiplication(number1, number2);
                break;
            case "+":
                answer = addition(number1, number2);
                break;
            case "-":
                answer = subtraction(number1, number2);
                break;
            default:
                Toast.makeText(ctx, "error in evaluating Binary operation : " + operation, Toast.LENGTH_SHORT).show();
                break;
        }
        try {
            return formatNumber(answer);
        } catch (Exception e) {
            return answer;
        }
    }

    @NonNull
    private String division(String num1, String num2) {
        return String.valueOf((Double.parseDouble(num1) / Double.parseDouble(num2)));
    }

    @NonNull
    private String multiplication(String num1, String num2) {
        return String.valueOf((Double.parseDouble(num1) * Double.parseDouble(num2)));
    }

    @NonNull
    private String addition(String num1, String num2) {
        return String.valueOf((Double.parseDouble(num1) + Double.parseDouble(num2)));
    }

    @NonNull
    private String subtraction(String num1, String num2) {
        return String.valueOf((Double.parseDouble(num1) - Double.parseDouble(num2)));
    }
}