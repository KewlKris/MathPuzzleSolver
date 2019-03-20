package mathpuzzlesolver;

import java.util.Arrays;
import java.time.Instant;
import java.util.ArrayList;

public class MathPuzzleSolver {
    public static void main(String[] args) {
        /*
        abs(a)
        acos(a)
        asin(a)
        atan(a)
        cbrt(a)
        ceil(a)
        cos(a)
        cosh(a)
        exp(a)
        expml(a)
        floor(a)
        getExponent(a)
        incrementExact(a)
        log(a)
        log(10)
        log1p(a)
        negateExact(a)
        signum(a)
        sin(a)
        sinh(a)
        sqrt(a)
        tan(a)
        tanh(a)
        toDegrees(a)
        toRadians(a)
        ulp(a)
        
        Combinators 14
        + - * /
        atan2(a, b)
        floorDiv(a, b)
        floorMod(a, b)
        hypot(a, b)
        IEEEremainder(a, b)
        max(a, b)
        min(a, b)
        nextAfter(a, b)
        pow(a, b)
        scalb(a, b)
        
        +++2019
        ++2+019
        ++20+19
        +2+0+19
        */
        
        char[][] solvingForms = new char[][]
        {
            "xyzabcd".toCharArray(),
            "xyazbcd".toCharArray(),
            "xyabzcd".toCharArray(),
            "xaybzcd".toCharArray()
        };
        
        char[][] valueForms = new char[][]
        {
            "2019".toCharArray(),
            "0219".toCharArray(),
            "1209".toCharArray(),
            "2109".toCharArray(),
            "0129".toCharArray(),
            "1029".toCharArray(),
            "9021".toCharArray(),
            "0921".toCharArray(),
            "2901".toCharArray(),
            "9201".toCharArray(),
            "0291".toCharArray(),
            "2091".toCharArray(),
            "2190".toCharArray(),
            "1290".toCharArray(),
            "9210".toCharArray(),
            "2910".toCharArray(),
            "1920".toCharArray(),
            "9120".toCharArray(),
            "9102".toCharArray(),
            "1902".toCharArray(),
            "0912".toCharArray(),
            "9012".toCharArray(),
            "1092".toCharArray(),
            "0192".toCharArray()
        };
        
        //System.exit(0);
        //String[][] set_solutions = new String[101][];
        ArrayList<ArrayList<String>> set_solutions = new ArrayList<ArrayList<String>>();
        for(int x=0; x<101; x++) {
            set_solutions.add(new ArrayList<String>());
        }
        
        for (int formID = 0; formID < solvingForms.length; formID++) {
            long startTime = Instant.now().toEpochMilli();
            for (int valueID = 0; valueID < valueForms.length; valueID++) {
                char[] form = solvingForms[formID];
                //Easy access to values
                int[] values = new int[4];
                for (int x=0; x<valueForms[valueID].length; x++) {
                    values[x] = Integer.parseInt(String.valueOf(valueForms[valueID][x]));
                }
                Combination combs = new Combination(COMB_OPERATION_COUNT, 3);

                while (combs.canIncrement()) {
                    try {
                        int returnValue = (int)solveForm(form, values, combs);
                        if (returnValue >= 0 && returnValue <= 100) {
                            //System.out.println("Solution found! | " + toPrefix(form, values, combs));
                            set_solutions.get(returnValue).add(toPrefix(form, values, combs));
                        }
                    } catch (ArithmeticException e) {

                    }
                    combs.nextState();
                }
            }
            long stopTime = Instant.now().toEpochMilli();
            double formsPerSecond = (double)valueForms.length / (double)((stopTime - startTime) / 1000d);
            //System.out.println(String.format("Running at %s valueForms/sec", String.valueOf(formsPerSecond)));    
        }
        
        int solvedCount = 0;
        for (ArrayList<String> s : set_solutions) {
            if (s.size() > 0) {
                solvedCount++;
            }
        }
        System.out.println(String.format("Solved %d/%d", solvedCount, 101));
    }
    
    public static int TARGET;
    public static double solveForm(char[] form, int[] values, Combination combs) throws ArithmeticException {
        INDEX = -1;
        return evaluate(form, values, combs);
    }
    
    private static int INDEX = -1;
    public static double evaluate(char[] form, int[] values, Combination combs) throws ArithmeticException {
        INDEX++;
        char c = form[INDEX];
        double value = 0;
        if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
            double valueA = evaluate(form, values, combs);
            double valueB = evaluate(form, values, combs);
            
            value = performOperation(valueA, valueB, combs.getState()[String.valueOf(c).compareTo("x")]);
        } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
            value = values[String.valueOf(c).compareTo("a")];
        }
        
        return value;
    }
    
    public static int COMB_OPERATION_COUNT = 14;
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
    
    public static String toPrefix(char[] form, int[] values, Combination combs) {
        
        String newForm = "";
        for (int x=0; x<form.length; x++) {
            char c = form[x];
            if (c == 'x' || c == 'y' || c == 'z') { //If it's a combination
                newForm += "c" + String.valueOf(combs.getState()[String.valueOf(c).compareTo("x")]);
            } else if (c == 'a' || c == 'b' || c == 'c' || c == 'd') { //If it's a constant
                newForm += String.valueOf(values[String.valueOf(c).compareTo("a")]).charAt(0);
            }
        }
        
        return newForm;
    }
    
}

class Combination {
    private int numStates, dimension;
    
    private int[] state;
    
    private boolean canIncrement = true;
    
    public Combination(int numStates, int dimension) {
        this.numStates = numStates;
        this.dimension = dimension;
        
        state = new int[dimension];
    }
    
    public int[] getState()
    {
        return state;
    }
    
    public int[] nextState() {
        incrementState();
        return state;
    }
    
    public boolean canIncrement() {
        return canIncrement;
    }
    
    public void reset() {
        state = new int[dimension];
    }
    
    public int getDimension() {
        return dimension;
    }
    
    public int getNumStates() {
        return numStates;
    }
    
    private void incrementState() {
        state[0]++;
        for (int x=0; x<state.length; x++) {
            if (state[x] == numStates) {
                if (x == state.length-1) {
                    state[x]--;
                    canIncrement = false;
                    return;
                }
                state[x] = 0;
                state[x+1]++;
            }
        }
    }
}