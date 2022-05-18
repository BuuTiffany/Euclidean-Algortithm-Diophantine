import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Main {
  /* Euclidean Algorithm & Diaphantine
   * Coded by Tiffany Buu 
   */

  public static void main(String[] args) {
    //Intro
    System.out.println();

    System.out.println("Euclidean Algorithm & Diophantine");
    System.out.println("Coded by Tiffany Buu");

    //Spacer
    System.out.println();
    System.out.println("===================================");
    System.out.println();

    System.out.println("   --- PART 1 ---   ");
    System.out.println("EUCLIDEAN ALGORITHM");

    //Spacer
    System.out.println();
    System.out.println("===================================");
    System.out.println();

    //Getting the numbers

    Scanner scan = new Scanner(System.in);
    ArrayList <Integer> as = new ArrayList <Integer>();
    ArrayList <Integer> bs = new ArrayList <Integer>();
    ArrayList <Integer> qs = new ArrayList <Integer>();
    ArrayList <Integer> rs = new ArrayList <Integer>();
    int a, b;
    
    System.out.println("Please enter an integer");
    a = scan.nextInt();

    System.out.println("Please enter another integer");
    b = scan.nextInt();

    //Setting x as the larger integer
    if (b > a) {
      int temp = a;
      a = b;
      b = temp;
    }

    System.out.println();
    System.out.println("a: " + a);
    System.out.println("b: " + b);

    int gcf = euclideanAlgorithm(a, b, as, bs, qs, rs);

    //Spacer
    System.out.println();
    System.out.println("===================================");
    System.out.println();

    System.out.println("    --- PART 2 ---    ");
    System.out.println("SOLUTION TO DIOPHANTINE");
      
      System.out.println();
      System.out.println("   ax + by = g");
      System.out.println("   where:");
      System.out.println("      - x, y, a, b, & g are integers");
      System.out.println("      - g = gcf(a,b)");


    //Spacer
    System.out.println();
    System.out.println("===================================");
    System.out.println();

    System.out.println("   * Note we will be using the same a and b integers used perviously in the Euclidean Algorithm");

    //Spacer
    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();

    diophantine (a, b, as, bs, qs, rs, gcf);

    scan.close();

  }

  /** FUNCTIONS **/
  public static int euclideanAlgorithm (int a, int b, ArrayList<Integer> as, ArrayList<Integer> bs, ArrayList<Integer> qs, ArrayList<Integer> rs) {

    //Spacer
    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();

    int quotient = 0;
    int remainder = 1;

    while (remainder > 0) {

        //Calculate remainder and quotiente
        remainder = a % b;
        quotient = a / b;
      
        //Add equation to the ArrayList
        as.add(a);
        bs.add(b);
        qs.add(quotient);
        rs.add(remainder);

        //Output the text
        System.out.println(a + " = " + b + " * " + quotient + " + " + remainder);
      

        //Set the remainder as the new divisor for the previous divisor
        a = b;
        b = remainder;
    }

    //Spacer
    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();

    System.out.println(a + " is the greatest common factor.");
    
    return a;

  }

  public static void diophantine (int a, int b, ArrayList<Integer> as, ArrayList<Integer> bs, ArrayList<Integer> qs, ArrayList<Integer> rs, int gcf) {
    
    

    System.out.println ( a + "x + " + b + "y = " + gcf);

    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();

    //if GCF is 0 there are infinite solutions to the Diophantine
    if (gcf == 0) {
      System.out.println("Infinite Solutions Exists.");
    }

    //Special Case when int b = gcf, and there is only one equation
    if (as.size() > 1) {
        //Remove the last equation from the set
        as.remove(as.size()-1);
        bs.remove(bs.size()-1);
        qs.remove(qs.size()-1);
        rs.remove(rs.size()-1);
    }

    //Get the final remainder and the two quotiants
    int r = rs.get(rs.size()-1);
    int q1 = 1;
    int q2 = qs.get(qs.size()-1);

    // r = a - b * q
    System.out.println(r + " = " + q1 + " * " + as.get(as.size()-1) + " - " + q2 + " * " + bs.get(bs.size()-1));

    int x = q1;
    int y = -q2;

    int count = 1;
    boolean even = false;
    if (as.size() % 2 == 0) even = true;

    while (as.size() > 1) {
        
        //Algorithm alternates between the y variable and the x variable, beginning with the y variable
        //This means if the count is odd, we are focusing on the y variable and if count is even we are focusing on the x variable
        if (count % 2 != 0) {
            System.out.println(r + " = " + q1 + " * " + as.get(as.size()-1) + " - " + q2 + " * (" + as.get(as.size()-2) + " - " + qs.get(bs.size()-2) + " * " + bs.get(qs.size()-2) + ")");

            q1 = q1 + qs.get(bs.size()-2)*q2;
            
            System.out.println(r + " = " + q1 + " * " + as.get(as.size()-1) + " - " + q2 + " * " + as.get(as.size()-2));
        } else {
            System.out.println(r + " = " + q1 + " * (" + as.get(as.size()-2) + " - " + qs.get(bs.size()-2) + " * " + bs.get(qs.size()-2) + ")" + " - " + q2 + " * " + as.get(as.size()-1));

            q2 = q2 + qs.get(bs.size()-2)*q1;

            System.out.println(r + " = " + q1 + " * " + as.get(as.size()-2) + " - " + q2 + " * " + as.get(as.size()-1));
        }

        if (even) {
            y = q1;
            x = -q2;
        } else {
            y = -q2;
            x = q1;
        }
        

        as.remove(as.size()-1);
        bs.remove(bs.size()-1);
        qs.remove(qs.size()-1);
        count++;
        // rs.remove(rs.size()-1);
    }

    //Final Answer

    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();

    System.out.println("x = " + x);
    System.out.println("y = " + y);
    System.out.println();
    System.out.println ( a + "(" + x + ") + " + b + "(" + y + ") = " + gcf);

    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println();
     
  }

}