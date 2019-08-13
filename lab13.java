/*************************************/
// Yamel Hernandez
//80590552
//CS1101 MW7:30
/***********************************/

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class lab13 {
	//static int tries =2;
    
    /* Get user’s basic info: (get info and create a file)
     * First and last names: the first initial and last name will be used to create the user’s expenses file.
     * • Takes nothing as input
     * • Return a string: the name of the file to create
     ************************************************************************************/
    public static String GetUserInfo(/* complete signature here */) throws FileNotFoundException, IOException{
        // complete with your code of the method GetUserInfo
	  Scanner s = new Scanner (System.in);
	  System.out.println("What is your full name? (first & last only)");
	  String name = s.nextLine();
	  String filename = "";
	  char fn = name.charAt(0); //first letter initial
	  for (int i=0; i<name.length(); i++) {
	   if (name.charAt(i) == ' '){
	    filename = name.substring(i+1); //last name
	  break;
	   }
	  }
	  filename = fn + filename + ".txt"; //creates file with the first initial of their name and their last name combined
	  return filename;
	    }
 
     /* Get information about the client’s expenses: (get info and write in a file): GetUserInfo
     * Ask the user for his/her expenses.
     * For each expense, the user is expected to provide:   
     *    o The expense category (you are given an array of these categories), and
     *    o The expense amount
     * While the user still wants to enter spending info, keep getting this information. reading it and write it into a file.
     * 
     * For each piece of information entered by the user:
     *    o If the information is incorrect (e.g., unknown category or amount that is negative or not a number), give a second 
     *      chance to the user to enter it correctly, and then stop acquiring information from the client.
     *    o If the information is correct, write it in the user’s expenses file: one line per expense. Each line should read: an 
     *      expense category followed by a number (which is a dollar amount)
     * This method WriteExpenses: 
     * • Takes a string: 
     *      o a String: the name of the file to write in, which is the result of the method GetUserInfo
     *      o a 1D array of strings: the array of categories of expenses, to be able to check if a category is correct
     * • Returns nothing: the file that is written in this method will still exist as a result of executing this method, even after *       closing the method
     *************************************************************************************/
    public static void WriteExpenses(String filename, String [] A) throws FileNotFoundException, IOException {
			// complete with your code of the method WriteExpenses
		PrintWriter w = new PrintWriter(filename);
		System.out.println("Valid expense categories are: Lodging, Utilities, Groceries, EatingOut, Vacation");
		Scanner s = new Scanner (System.in);
		System.out.println("How many times would you like to input your expenses?");
		int counter = s.nextInt(); //says how many times to run the loop depending on how many times the user wants to input an expense
		boolean lies = false; //will check in the loop whether the user entered a proper category
		
		while(counter!=0) {
			System.out.println("Enter an expense category:");
			String cat = s.next();
			for(int j=0; j<A.length; j++) {
				if(A[j].equals(cat)){
					System.out.println("What is your expense in this category?(make sure you include the first decimal)");
					double ex = s.nextDouble();
					w.println(cat + ": " + ex);
					w.print("");
					lies = false;
					break;
				}
			}
			if (lies){ //if the user inputted a wrong category, allows them to enter it once more
				System.out.println("Error in expense category, enter an expense category:");
				String cato = s.next();
				for(int j=0; j<A.length; j++) {
					if(A[j].equals(cato)) {
						System.out.println("What is your expense in this category?(make sure you include the first decimal)");
						double ex = s.nextDouble();
						w.println(cato + " " + ex);
						lies = false;
					}
				}
			}
	  counter--;
	  }
	  w.close();
    }
	
    
    /* Once the user is done entering expenses, read the file and create an array: (read file, create 1 array)
     * Create and fill one array (2D) that contains  for each type of expense (in the same order as the array of expenses that is 
     * given to you):
     *      o The minimum amount spent in this category, 
     *      o The maximum amount spent in this category, and 
     *      o The total amount spent in this category (each of these amounts should be of type double).
     * This method ExpensesStats: 
     * • Takes: 
     *      o A string: the name of the file to read to acquire the info that goes in the array
     *      o A 1D array: the array of expense categories
     * • Returns a 2D array of expenses per category with, for each category, the min, max, and total amounts spent in this 
     *      category
     * • Note: the resulting array should be of size n x 3, where n is the size of the array that contains the categories of 
     *      expenses
     *************************************************************************************/
    public static double[][] ExpensesStats(String filename, String[] A) throws IOException {
        // complete with your code of the method ExpensesStats
	  String line;
	  BufferedReader b = new BufferedReader(new FileReader(filename));
	  double [][] expenses = new double [A.length][3];
	  double temp;
	  
	  for (int c =0; c <expenses.length; c++) {
		  for(int r =0; r<expenses[c].length-1; r++) {
			  expenses[c][r] = -1;
		  }
	  }  
	  while ((line = b.readLine())!= null) {
		  String[] x = line.split(": ");  //tells user what to use to split 
		  for(int i =0; i<x.length; i+=2) {
			  temp =  Double.parseDouble(x[i+1]);
			  if (x[i].equals("Lodging")) {
				 if(temp<expenses[0][0] || expenses [0][0] == -1) {
					 expenses [0][0] = temp;
				 }
				 if(temp>expenses[0][1] || expenses [0][1] == -1) {
					 expenses [0][1] = temp;
				 }
				 expenses [0][2] += temp;	 
			  }
			  
			  else if (x[i].equals("Utilities")) {
				 if(temp<expenses[1][0] || expenses [1][0] == -1) {
					 expenses [1][0] = temp;
				 }
				 if(temp>expenses[1][1] || expenses [1][1] == -1) {
					 expenses [1][1] = temp;
				 }
				 expenses [1][2] += temp;	 
			  }
			  else if (x[i].equals("Groceries")) {
				 if(temp<expenses[2][0] || expenses [2][0] == -1) {
					 expenses [2][0] = temp;
				 }
				 if(temp>expenses[2][1] || expenses [2][1] == -1) {
					 expenses [2][1] = temp;
				 }
				 expenses [2][2] += temp;	 
			  }
			  else if (x[i].equals("EatingOut")) {
				 if(temp<expenses[3][0] || expenses [3][0] == -1) {
					 expenses [3][0] = temp;
				 }
				 if(temp>expenses[3][1] || expenses [3][1] == -1) {
					 expenses [3][1] = temp;
				 }
				 expenses [3][2] += temp;	 
			  }
			  else {
				 if(temp<expenses[4][0] || expenses [4][0] == -1) {
					 expenses [2][0] = temp;
				 }
				 if(temp>expenses[4][1] || expenses [4][1] == -1) {
					 expenses [4][1] = temp;
				 }
				 expenses [4][2] += temp;	 
			  }
		  }  
	  }
	  b.close();
	  return expenses; 
    }

// x[i].equals("Vacation")
	public static int count (String filename) throws IOException {
		BufferedReader b = new BufferedReader(new FileReader(filename)); //need to use bufferedreader again to read/count the lines in the file
		int count = 0;
		while (b.ready()) {
			b.readLine();
			count++;
		}
		b.close();
		return count;
	}
	
	public static int find (String e, String [] E) {
		int in = -1;
		int i;
		for (i=0; i <E.length; i++) {
			if (e==E[i]) {
				return i;
			}
		}
		return in;
	}
	
     /* You can then allow the user to query his/her spending by category: (access arrays)
     * • Ask the user for the category of interest.
     * • Provide the minimum, maximum, and total amounts spent in this category.
     * • However, if the chosen category was not valid, allow the user two more tries and then shut down the system.
     * This method CategoryQuery: 
     * • Takes:
     *      o A 1D array: the array of expense categories
     *      o A 2D array: the array of stats on the user’s spending in each category (resulting from method ExpensesStats)
     * • Returns nothing: it only prints out the queried information, which is the min, max, and total amount of the queried 
     *      category
     * • Note: The user is asked which category is queried within method CategoryQuery. The user is also given two more chances 
     *      to enter a correct category if he/she enters an incorrect one.
     *************************************************************************************/
    public static void CategoryQuery(String[] Categories, double[][] Expenses) {
       System.out.println("Which category would you like printed?"); // asks user for which category they want to print
    	Scanner s = new Scanner(System.in);
    	int i;
		int col = 0;
    	String cate = s.next();
    	for(i =0; i<Categories.length;i++) { //takes user input of wished category to print
    		if(cate.equals(Categories[i])) {
    			col=i;
    		}
    	}
    	for(i=0;i<Expenses[col].length;i++) {
    		System.out.println(Expenses[col][i]);
    	}
		return;    	
    }
    
    /* Recursively compute the total amount spent by the user: (recursively access array)
     * • Recursively access the 2D array of expenses
     * • To compute the total amount of expenses (which is the sum of all totals)
     * Note: the partial code of this method is given to you. You just have to complete it.
     * 
     * This method TotalSpending RECURSIVELY computes the total spending:
     * • Takes a 2D array: the 2D array of expenses per category and a start index
     * • Returns a double value: the total amount spent over all categories
     * • Note: This method is expected to be implemented recursively.
     *************************************************************************************/
    public static double TotalSpending(double[][] Expenses, int startIndex) {
//         Note: The 2D array of expenses is expected to have only 3 columns (one for min expense, one for max, and one for total)
//        
//         If the startIndex is beyond the range of the 2D array of expenses, it means that all expenses have been taken into account 
        if (startIndex >= Expenses.length)
        	return 0;
	startIndex++; //increments the index
	return Expenses[startIndex][2] + TotalSpending(Expenses,startIndex); 
    }
    /* Here is a method given to you: Please DO NOT MODIFY it
     *************************************************************************************/
    public static void printout(double[][] Expenses, String[] category) {
        for (int i=0; i<Expenses.length; i++) {
            System.out.println(category[i] + "-- Min = " + Expenses[i][0] + ", Max = " + Expenses[i][1] + ", Total = " + Expenses[i][2]);   
        }
		return;
    }
    
    /* Here you are given the main method: Please DO NOT MODIFY it
     *************************************************************************************/
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // Here we are providing to you the array of types of expenses for your use in the subsequent tasks
        String[] TypesOfExpenses = {"Lodging", "Utilities", "Groceries", "EatingOut", "Vacation"};
        
        // Get user’s basic info: (get info and create a file)
        // First and last names: the first initial and last name will be used to create the user’s expenses file. 
        String filename = GetUserInfo();
        
        // Get information about the client’s expenses: (get info and write in a file)
        WriteExpenses(filename,TypesOfExpenses);
        
        // Once the user is done entering expenses, read the file and create an array: (read file, create 1 array)
        double[][] ExpensesByCategory = ExpensesStats(filename,TypesOfExpenses);
        
        // Let's print the double array Expenses to check if it was done properly
        printout(ExpensesByCategory,TypesOfExpenses);
        
        // You can then allow the user to query his/her spending by category: (access arrays)
        CategoryQuery(TypesOfExpenses,ExpensesByCategory);
        
        // Recursively compute the total amount spent by the user: (recursively access array)
        double totalSpent = TotalSpending(ExpensesByCategory,0);
        System.out.println("The total amount of expenses is: " + totalSpent);
    }    
}