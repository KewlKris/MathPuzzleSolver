package mathpuzzlesolver;

public class Math_Puzzle_2019 {
    public static void main(String[] args) {
        /*
        [][][][][][][][][][][][][][][][][]
        []                              []
        []   Explanation of how values  []
        []     were generated below     []
        []                              []
        [][][][][][][][][][][][][][][][][]
         - Chris
        */
        
        System.out.println((int)((((2 + 9) * 0) + 1)));
        System.out.println((int)((((9 + 1) * 0) + 2)));
        System.out.println((int)((((9 * 0) + 2) + 1)));
        System.out.println((int)(((Math.pow(9, 0) + 2) + 1)));
        System.out.println((int)((((0 + 9) / 2) + 1)));
        System.out.println((int)(Math.abs((((2 + 1) + 0) - 9))));
        System.out.println((int)((((0 + 9) - 2) * 1)));
        System.out.println((int)((((1 + 0) - 2) + 9)));
        System.out.println((int)((((2 + 1) * 0) + 9)));
        System.out.println((int)((((2 + 0) + 9) - 1)));
        System.out.println((int)((((0 + 9) + 2) * 1)));
        System.out.println((int)((((0 + 2) + 1) + 9)));
        System.out.println((int)(((Math.sqrt((0 + 2)) * 9) + 1)));
        System.out.println((int)((((9 + 1) + 0) * Math.sqrt(2))));
        System.out.println((int)(((Math.exp((2 + 0)) + 9) - 1)));
        System.out.println((int)((((0 + 9) - 1) * 2)));
        System.out.println((int)((((0 + 2) * 9) - 1)));
        System.out.println((int)(Math.pow(((0 + 2) * 9), 1)));
        System.out.println((int)((((0 + 2) * 9) + 1)));
        System.out.println((int)((((9 + 1) + 0) * 2)));
        System.out.println((int)(((Math.sqrt((0 + 2)) + 1) * 9)));
        System.out.println((int)(Math.sqrt((Math.pow((0 + 2), 9) + 1))));
        System.out.println((int)((Math.sqrt(Math.pow((0 + 2), 9)) + 1)));
        System.out.println((int)(Math.ceil((Math.sqrt(Math.pow((0 + 2), 9)) + 1))));
        System.out.println((int)(Math.pow(((9 + 1) + 0), Math.sqrt(2))));
        System.out.println((int)(Math.pow(Math.cbrt(((1 + 0) + 2)), 9)));
        System.out.println((int)((((0 + 2) + 1) * 9)));
        System.out.println((int)((((Math.cos(0) + 2) * 9) + 1)));
        System.out.println((int)((Math.exp(((0 + 2) + 1)) + 9)));
        System.out.println((int)(Math.ceil((Math.exp(((0 + 2) + 1)) + 9))));
        System.out.println((int)(Math.ceil(Math.pow(((2 + 0) - Math.cos(1)), 9))));
        System.out.println((int)((Math.floor(((0 + 9) / Math.cos(1))) * 2)));
        System.out.println((int)(((Math.exp(Math.pow(2, 0)) + 1) * 9)));
        System.out.println((int)(Math.ceil(((Math.exp(Math.pow(2, 0)) + 1) * 9))));
        System.out.println((int)(((Math.cbrt((0 + 2)) + Math.exp(1)) * 9)));
        System.out.println((int)((((Math.cos(0) + 2) + 1) * 9)));
        System.out.println((int)(Math.sqrt(Math.pow(Math.hypot((0 + 2), 1), 9))));
        System.out.println((int)(Math.ceil(Math.pow(Math.sqrt(Math.hypot((0 + 2), 1)), 9))));
        System.out.println((int)(Math.pow(((0 + 9) - Math.exp(1)), 2)));
        System.out.println((int)(Math.ceil(Math.pow(((0 + 9) - Math.exp(1)), 2))));
        System.out.println((int)(Math.ceil(Math.pow(Math.pow((0 + 9), 2), Math.sin(1)))));
        System.out.println((int)((((0 + 2) + Math.exp(1)) * 9)));
        System.out.println((int)(Math.ceil((((0 + 2) + Math.exp(1)) * 9))));
        System.out.println((int)(Math.ceil((Math.pow((0 + 9), 2) * Math.cos(1)))));
        System.out.println((int)((Math.exp(((1 + 2) + Math.cos(0))) - 9)));
        System.out.println((int)(((Math.exp((0 + Math.sqrt(2))) + 1) * 9)));
        System.out.println((int)((Math.exp((Math.sqrt((9 + 1)) + 0)) * 2)));
        System.out.println((int)(((Math.exp((1 + 0)) * 2) * 9)));
        System.out.println((int)(Math.pow(((1 + Math.cos(0)) - 9), 2)));
        System.out.println((int)((Math.ceil(((0 + 9) * Math.exp(1))) * 2)));
        System.out.println((int)((Math.exp(((2 + Math.cos(0)) + 1)) - Math.sqrt(9))));
        System.out.println((int)(Math.sqrt(Math.pow(((1 + 0) + Math.sqrt(2)), 9))));
        System.out.println((int)((Math.exp((Math.floor((9 / 2)) + 0)) - 1)));
        System.out.println((int)(Math.exp(((Math.pow(9, 0) + 2) + 1))));
        System.out.println((int)((Math.exp((Math.floor((9 / 2)) + 0)) + 1)));
        System.out.println((int)(Math.ceil((Math.exp((Math.floor((9 / 2)) + 0)) + 1))));
        System.out.println((int)(((Math.exp((2 + 0)) - 1) * 9)));
        System.out.println((int)(Math.ceil(((Math.exp((2 + 0)) - 1) * 9))));
        System.out.println((int)((((0 + 9) - 1) * Math.exp(2))));
        System.out.println((int)(Math.ceil((((0 + 9) - 1) * Math.exp(2)))));
        System.out.println((int)(Math.ceil((Math.exp(((2 + 0) + Math.cbrt(9))) + 1))));
        System.out.println((int)(((Math.floor((0 + Math.exp(2))) * 9) - 1)));
        System.out.println((int)((Math.ceil((Math.exp((2 + 0)) - 1)) * 9)));
        System.out.println((int)(Math.pow(((0 + 9) - 1), 2)));
        System.out.println((int)(((Math.exp((0 + 2)) * 9) - 1)));
        System.out.println((int)(Math.pow(((0 + 9) - Math.sin(1)), 2)));
        System.out.println((int)(((Math.exp((0 + 2)) * 9) + 1)));
        System.out.println((int)(Math.ceil(((Math.exp((0 + 2)) * 9) + 1))));
        System.out.println((int)(Math.ceil((Math.pow((0 + 9), 2) * Math.sin(1)))));
        System.out.println((int)((Math.exp((Math.sqrt((9 * 2)) + 0)) + 1)));
        System.out.println((int)(Math.pow(((0 + 9) - Math.cos(1)), 2)));
        System.out.println((int)((Math.floor((Math.exp((0 + 2)) + 1)) * 9)));
        System.out.println((int)((((9 + 1) + 0) * Math.exp(2))));
        System.out.println((int)(Math.ceil((((9 + 1) + 0) * Math.exp(2)))));
        System.out.println((int)(((Math.exp((0 + 2)) + 1) * 9)));
        System.out.println((int)(Math.ceil(((Math.exp((0 + 2)) + 1) * 9))));
        System.out.println((int)(Math.sqrt((Math.exp(((0 + 9) - 1)) * 2))));
        System.out.println((int)((Math.pow((0 + 9), 2) - Math.exp(1))));
        System.out.println((int)(Math.ceil((Math.pow((0 + 9), 2) - Math.exp(1)))));
        System.out.println((int)((Math.pow((0 + 9), 2) - 1)));
        System.out.println((int)((Math.pow((0 + 9), 2) * 1)));
        System.out.println((int)((Math.pow((0 + 9), 2) + 1)));
        System.out.println((int)(Math.ceil(Math.pow(Math.hypot((0 + 9), 1), 2))));
        System.out.println((int)(Math.ceil((Math.pow((0 + 9), 2) + Math.exp(1)))));
        System.out.println((int)(Math.pow(Math.hypot((1 + Math.cos(0)), 9), 2)));
        System.out.println((int)((Math.exp((Math.cbrt((0 + 2)) + 1)) * 9)));
        System.out.println((int)(Math.pow((Math.exp((0 - 1)) + 9), 2)));
        System.out.println((int)(Math.ceil(Math.pow((Math.exp((0 - 1)) + 9), 2))));
        System.out.println((int)(Math.ceil(Math.pow((Math.sqrt((0 - Math.cos(2))) + 1), 9))));
        System.out.println((int)(Math.pow(Math.hypot(Math.cbrt((1 + Math.cbrt(2))), Math.cos(0)), 9)));
        System.out.println((int)(Math.pow(((9 + Math.cos(1)) + 0), 2)));
        System.out.println((int)(Math.ceil(Math.pow(((9 + Math.cos(1)) + 0), 2))));
        System.out.println((int)(((Math.sqrt((0 + Math.exp(9))) + 2) + 1)));
        System.out.println((int)(Math.pow((Math.sqrt((Math.cos(1) + 0)) + 9), 2)));
        System.out.println((int)(Math.ceil(Math.pow((Math.sqrt((Math.cos(1) + 0)) + 9), 2))));
        System.out.println((int)(Math.pow(((9 + Math.sin(1)) + 0), 2)));
        System.out.println((int)(Math.ceil(Math.pow(((9 + Math.sin(1)) + 0), 2))));
        System.out.println((int)(Math.pow((Math.cbrt((Math.sin(1) + 0)) + 9), 2)));
        System.out.println((int)((Math.pow((Math.cos(0) + 9), 2) - 1)));
        System.out.println((int)(Math.pow(((9 + 1) + 0), 2)));
        
        /*
        
            Rather than find all 100 solutions by hand, I wanted to write
        a program that created the equations for me. Knowledge from CS UIL
        helped me figure out how this program woudl work, namely, using prefix
        notation to represent mathematical equations.
        
        Put simply, writing an equation in prefix notation means you write the operator
        first:
        
        1 + 2           becomes         +12
        (1 + 2) + 3     becomes         ++123
        1 + (2 + 3)     becomes         +1+23
        
            This is a more "linear" form of writing equations, where values can easily
        be substituted and added. My program would "brute-force" all possible
        combinations of an equation of given length, and solve. In order to make
        sure my solutions were clean and didn't include too many repetitive
        operations, each "correct" equation would be scored. If an equation did
        not meet or exceed the current best score calculated, it was not recorded.
        I gave different operations different weights based on how diffuclt
        I thought it was to do in your head. In addition to that, the more an
        operation is used in an equation, the "heavier" it becomes so that the
        program favors simple, diverse equations.
        
            After all of this, another program (Organizer.java) sorted through
        all simple, solved values and picked the equation of shortest length.
        This simple, shortest equation becomes the equation in my Excl spreadsheet.
        
            The programs I wrote to generate and sort through the equations were
        written hastily. They are not well documented and many parts of it are
        "hard-coded" or inefficiently written. Regardless, I'm proud to have written it.
        Everyone else found their 100 answers over several days. I found mine
        in several minutes.
        
        - Chris
        
        */
        
        
    }
}
