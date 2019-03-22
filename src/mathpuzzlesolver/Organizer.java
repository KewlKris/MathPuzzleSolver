package mathpuzzlesolver;

import java.io.*;
import java.util.Arrays;

public class Organizer {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("solved_equations.txt"))));
        for (int x=1; x<=100; x++) {
            String content = "";
            String[] equations;
            
            BufferedReader in =
                    new BufferedReader(
                            new FileReader(
                                    new File(String.format("./output/%d.txt", x))));
            String line;
            while ((line = in.readLine()) != null) {
                content += line + "\n";
            }
            
            equations = content.split("\n");
            
            int shortest = equations[0].length();
            //Get shortest equation
            for (int y=1; y<equations.length; y++) {
                if (equations[y].length() < shortest) {
                    shortest = equations[y].length();
                }
            }
            
            String shortestEquation = "";
            //Find first shortest line
            for (int y=0; y<equations.length; y++) {
                if (equations[y].length() == shortest) {
                    shortestEquation = equations[y];
                    break;
                }
            }
            
            out.println("System.out.println((int)(" + shortestEquation + "));");
            in.close();
        }
        out.flush();
    }
}
