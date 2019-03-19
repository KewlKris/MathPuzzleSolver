package mathpuzzlesolver;

import java.util.Arrays;

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
            "+++abcd".toCharArray(),
            "++a+bcd".toCharArray(),
            "++ab+cd".toCharArray(),
            "+a+b+cd".toCharArray()
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
        
        int target = 6;
        
        System.exit(0);
        
        for (int formID = 0; formID < solvingForms.length; formID++) {
            for (int valueID = 0; valueID < valueForms.length; valueID++) {
                char[] form = solvingForms[formID];
                //Easy access to values
                int[] values = new int[4];
                
            }
        }
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
                }
                state[x] = 0;
                state[x+1]++;
            }
        }
    }
}