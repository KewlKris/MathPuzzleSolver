package mathpuzzlesolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class SolvingThread extends Thread {
    private char[] solvingForm;
    private char[] valueForm;
    
    private int COMB_OPERATION_COUNT, MUT_OPERATION_COUNT, MUTATOR_DEPTH;
    
    private int threadID;
    
    public SolvingThread(char[] solvingForm, char[] valueForm, int combOpCount,
            int mutOpCount, int mutatorCount, int threadID) {
        this.solvingForm = solvingForm;
        this.valueForm = valueForm;
        COMB_OPERATION_COUNT = combOpCount;
        MUT_OPERATION_COUNT = mutOpCount;
        MUTATOR_DEPTH = mutatorCount;
        this.threadID = threadID;
    }
    
    public void run() {
        //Easy access to values
        int[] values = new int[4];
        for (int x=0; x<valueForm.length; x++) {
            values[x] = Integer.parseInt(String.valueOf(valueForm[x]));
        }
        
        Combination combs = new Combination(COMB_OPERATION_COUNT, 3);
        
        ArrayList<ArrayList<String>> solutionList = new ArrayList<ArrayList<String>>();
        for (int x=0; x<=100; x++) {
            solutionList.add(new ArrayList<String>());
        }

        while (combs.canIncrement()) {
            Combination muts = new Combination(MUT_OPERATION_COUNT, solvingForm.length*MUTATOR_DEPTH);
            while (muts.canIncrement()) {
                try {
                    int returnValue = (int)solveForm(solvingForm, values, combs, muts);
                    if (returnValue >= 0 && returnValue <= 100) {
                        int score = scoreEquation(solvingForm, values, combs, muts);
                        if (MathPuzzleSolver.scores[returnValue] == 0 || (MathPuzzleSolver.scores[returnValue] >= score)) {
                            if (score < MathPuzzleSolver.scores[returnValue]) {
                                synchronized (MathPuzzleSolver.OUTPUT) {
                                    try {
                                        MathPuzzleSolver.OUTPUT[returnValue] = new PrintWriter(new BufferedWriter(new FileWriter(new File(String.format("./output/%d.txt", returnValue)))));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                solutionList.set(returnValue, new ArrayList<String>());
                            }
                            solutionList.get(returnValue).add(toStandard(solvingForm, values, combs, muts));
                            MathPuzzleSolver.solutionFoundList[returnValue] = true;
                            MathPuzzleSolver.scores[returnValue] = score;
                        }
                    }
                } catch (ArithmeticException e) {
                    
                }
                muts.nextState();
            }
            synchronized(MathPuzzleSolver.OUTPUT) {
                for (int x=0; x<solutionList.size(); x++) {
                    for (String s : solutionList.get(x)) {
                        MathPuzzleSolver.OUTPUT[x].println(s);
                    }
                }
                for (int x=0; x< MathPuzzleSolver.OUTPUT.length; x++) {
                     MathPuzzleSolver.OUTPUT[x].flush();
                }
                
            }
            
            solutionList = new ArrayList<ArrayList<String>>();
            for (int x=0; x<=100; x++) {
                solutionList.add(new ArrayList<String>());
            }
            combs.nextState();
        }
        MathPuzzleSolver.threadBusy[threadID] = false;
    }
    
    public double solveForm(char[] form, int[] values, Combination combs, Combination muts) throws ArithmeticException {
        INDEX = 0;
        return evaluate(form, values, combs, muts);
    }
    
    private int INDEX = 0;
    private boolean eIsMutator = true;
    public double evaluate(char[] form, int[] values, Combination combs, Combination muts) throws ArithmeticException {
        double value = 0;
        char c = form[INDEX];
        int baseIndex = INDEX;
        if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
            INDEX++;
            double valueA = evaluate(form, values, combs, muts);
            INDEX++;
            double valueB = evaluate(form, values, combs, muts);

            value = performOperation(valueA, valueB, combs.getState()[String.valueOf(c).compareTo("x")]);
        } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
            value = values[String.valueOf(c).compareTo("a")];
        }
        
        value = performMutation(value, muts.getState()[baseIndex]);
        
        return value;
    }
    
    private static int[] MUTATOR_SCORES = new int[] {
        0, 1, 10, 2, 15, 15, 2, 15, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
    };
    public static double performMutation(double valueA, int operationID) throws ArithmeticException {
        switch (operationID) {
            case 0:
                return valueA;
            case 1:
                return Math.abs(valueA);
            case 2:
                return Math.cbrt(valueA);
            case 3:
                return Math.ceil(valueA);
            case 4:
                return Math.cos(valueA);
            case 5:
                return Math.exp(valueA);
            case 6:
                return Math.floor(valueA);
            case 7:
                return Math.sin(valueA);
            case 8:
                return Math.sqrt(valueA);
            case 9:
                return Math.tan(valueA);
        }
        System.out.println("Invalid mutation!");
        return 0;
    }
    
    public static int[] OPERATION_SCORES = new int[] {
        1, 3, 5, 8, 10, 5, 3, 3, 3, 3, 3, 3, 3, 3
    };
    public static double performOperation(double valueA, double valueB, int operationID) throws ArithmeticException {
        switch (operationID) {
            case 0:
                return valueA + valueB;
            case 1:
                return valueA - valueB;
            case 2:
                return valueA * valueB;
            case 3:
                return valueA / valueB;
            case 4:
                return Math.hypot(valueA, valueB);
            case 5:
                return Math.pow(valueA, valueB);
        }
        System.out.println("Invalid operation!");
        return 0;
    }
    
    public String toPrefix(char[] form, int[] values, Combination combs, Combination muts) {
        
        String newForm = "";
        for (int x=0; x<form.length; x++) {
            for (int y=MUTATOR_DEPTH-1; y>=0; y--) {
                newForm += 'm' + String.valueOf(muts.getState()[x+y]) + 'm';
            }
            
            char c = form[x];
            if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
                newForm += 'c' + String.valueOf(combs.getState()[String.valueOf(c).compareTo("x")]) + 'c';
            } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
                newForm += String.valueOf(values[String.valueOf(c).compareTo("a")]).charAt(0);
            }
        }
        
        return newForm;
    }
    
    private int standardIndex = 0;
    public String toStandard(char[] form, int[] values, Combination combs, Combination muts) {
        standardIndex = 0;
        isMutator = true;
        return toStandardIteration(form, values, combs, muts);
    }
    
    private final String[] mutatorStrings = new String[] {"", "abs", "cbrt", "ceil", "cos", "exp", "floor", "sin", "sqrt", "tan"};
    private final String[] combinationStrings = new String[] {"", "", "", "", "hypot", "pow"};
    private boolean isMutator = true;
    private String toStandardIteration(char[] form, int[] values, Combination combs, Combination muts) {
        int tempIndex = standardIndex;
        String statement = "";
        if (isMutator) {
            isMutator = false;
            if (muts.getState()[standardIndex] == 0) {
                statement += toStandardIteration(form, values, combs, muts);
                return statement;
            }
            statement += "Math." + mutatorStrings[muts.getState()[standardIndex]] + "(";
            statement += toStandardIteration(form, values, combs, muts);
            statement += ")";
            return statement;
        }
        isMutator = true;
        
        char c = form[standardIndex];
        if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
            standardIndex++;
            String valueA = toStandardIteration(form, values, combs, muts);
            standardIndex++;
            String valueB = toStandardIteration(form, values, combs, muts);
            
            if (combs.getState()[String.valueOf(c).compareTo("x")] <= 3) {
                char symbol = 'N';
                switch (combs.getState()[String.valueOf(c).compareTo("x")]) {
                    case 0:
                        symbol = '+';
                        break;
                    case 1:
                        symbol = '-';
                        break;
                    case 2:
                        symbol = '*';
                        break;
                    case 3:
                        symbol = '/';
                        break;
                }
                
                statement += "(" + valueA + " " + symbol + " " + valueB + ")";
                return statement;
            }
            
            statement += "Math." + combinationStrings[combs.getState()[tempIndex]] + "(";
            statement += valueA + ", " + valueB;
            statement += ")";
            return statement;
        } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
            statement += String.valueOf(values[String.valueOf(c).compareTo("a")]);
            return statement;
        }
        
        System.out.println("Standard not caught! " + c + " " + (c == 'x'));
        return statement;
    }
    
    public int scoreEquation(char[] form, int[] values, Combination combs, Combination muts) {
        int[] mutScores = MUTATOR_SCORES.clone();
        int[] comScores = OPERATION_SCORES.clone();
        
        int score = 0;
        
        //Score operations
        for (int x=0; x<combs.getDimension(); x++) {
            score += comScores[combs.getState()[x]];
            comScores[combs.getState()[x]]++;
        }
        
        //Score mutations
        for (int x=0; x<muts.getDimension(); x++) {
            if (muts.getState()[x] == 0) {
                continue;
            }
            score += mutScores[muts.getState()[x]];
            mutScores[muts.getState()[x]]++;
        }
        
        return score;
    }
}
