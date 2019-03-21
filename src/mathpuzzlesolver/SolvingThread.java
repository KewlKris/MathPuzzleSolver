package mathpuzzlesolver;

import java.util.ArrayList;
import java.util.Arrays;

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
        //System.out.println(Arrays.toString(valueForm));
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
                        //System.out.println("Solution found! | " + toPrefix(form, values, combs));
                        //set_solutions.get(returnValue).add(toPrefix(form, values, combs, muts));
                        //System.out.println(returnValue);
                        solutionList.get(returnValue).add(toPrefix(solvingForm, values, combs, muts));
                        MathPuzzleSolver.solutionFoundList[returnValue] = true;
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
        INDEX = -1;
        return evaluate(form, values, combs, muts);
    }
    
    private int INDEX = -1;
    public double evaluate(char[] form, int[] values, Combination combs, Combination muts) throws ArithmeticException {
        INDEX++;
        char c = form[INDEX];
        double value = 0;
        if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
            double valueA = evaluate(form, values, combs, muts);
            double valueB = evaluate(form, values, combs, muts);
            
            value = performOperation(valueA, valueB, combs.getState()[String.valueOf(c).compareTo("x")]);
        } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
            value = values[String.valueOf(c).compareTo("a")];
        }
        
        for (int x = MUTATOR_DEPTH-1; x>=0; x--)
        {
            value = performMutation(value, muts.getState()[INDEX+x]);
        }
        
        return value;
    }
    
    public static double performMutation(double valueA, int operationID) throws ArithmeticException {
        switch (operationID) {
            case 0:
                return valueA;
            case 1:
                return Math.floor(valueA);
            case 2:
                return Math.ceil(valueA);
            case 3:
                return Math.sqrt(valueA);
        }
        System.out.println("Invalid mutation!");
        return 0;
    }
    
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
                return Math.atan2(valueA, valueB);
            case 5:
                return Math.floorDiv((int)valueA, (int)valueB);
            case 6:
                return Math.floorMod((int)valueA, (int)valueB);
            case 7:
                return Math.hypot(valueA, valueB);
            case 8:
                return Math.IEEEremainder(valueA, valueB);
            case 9:
                return Math.max(valueA, valueB);
            case 10:
                return Math.min(valueA, valueB);
            case 11:
                return Math.nextAfter(valueA, valueB);
            case 12:
                return Math.pow(valueA, valueB);
            case 13:
                return Math.scalb(valueA, (int)valueB);
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
}
