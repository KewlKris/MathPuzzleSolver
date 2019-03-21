package mathpuzzlesolver;

import java.util.Arrays;
import java.time.Instant;
import java.util.ArrayList;
import java.io.*;

public class MathPuzzleSolver {
    public static PrintWriter[] OUTPUT;
    public static boolean[] threadBusy;
    public static boolean[] solutionFoundList;
    public static int[] scores;
    
    public static int threadCount;
    public static void main(String[] args) throws IOException, InterruptedException {
        threadCount = 4;
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
        
        OUTPUT = new PrintWriter[101];
        for (int x=0; x<OUTPUT.length; x++) {
            OUTPUT[x] = new PrintWriter(new BufferedWriter(new FileWriter(new File(String.format("./output/%d.txt", x)))));
        }
        
        solutionFoundList = new boolean[101];
        
        //System.exit(0);
        //String[][] set_solutions = new String[101][];
        
        threadBusy = new boolean[threadCount];
        SolvingThread[] threadList = new SolvingThread[threadCount];
        
        scores = new int[101];
        
        MUTATOR_DEPTH = 1;
        for (int formID = 0; formID < solvingForms.length; formID++) {
            long startTime = Instant.now().toEpochMilli();
            for (int valueID = 0; valueID < valueForms.length; valueID++) {
                while (true) {
                    boolean notReady = false;
                    for (int x=0; x<threadBusy.length; x++) {
                        if (!threadBusy[x]) {
                            System.out.println("Starting thread " + x);
                            notReady = true;
                            threadList[x] = new SolvingThread(
                                    solvingForms[formID], valueForms[valueID],
                                    COMB_OPERATION_COUNT, MUT_OPERATION_COUNT,
                                    MUTATOR_DEPTH, x);
                            threadList[x].start();
                            threadBusy[x] = true;
                            break;
                        }
                    }
                    if (notReady)
                    {
                        break;
                    }
                }
            }
            long stopTime = Instant.now().toEpochMilli();
            double formsPerSecond = (double)valueForms.length / (double)((stopTime - startTime) / 1000d);
            System.out.println(String.format("Running at %s valueForms/sec", String.valueOf(formsPerSecond)));    
        }
        
        for (int x=0; x<threadList.length; x++) {
            threadList[x].join();
        }
        
        int solvedCount = 0;
        for (int x=0; x<solutionFoundList.length; x++) {
            if (solutionFoundList[x]) {
                solvedCount++;
            }
        }
        System.out.println(String.format("Solved %d/%d", solvedCount, 101));
        
        for (int x=0; x<OUTPUT.length; x++) {
            OUTPUT[x].close();
        }
    }
    
    public static int MUTATOR_DEPTH;
    
    public static int MUT_OPERATION_COUNT = /*27*/20;
    
    public static int COMB_OPERATION_COUNT = /*14*/5;
}