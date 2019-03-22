package mathpuzzlesolver;

public class Combination {
    private int numStates, dimension;
    
    private int[] state;
    
    private boolean canIncrement = true;
    
    public Combination(int numStates, int dimension) {
        this.numStates = numStates;
        this.dimension = dimension;
        
        state = new int[dimension];
    }
    
    public Combination(int[] state, int numStates) {
        this.numStates = numStates;
        this.dimension = state.length;
        
        this.state = state;
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