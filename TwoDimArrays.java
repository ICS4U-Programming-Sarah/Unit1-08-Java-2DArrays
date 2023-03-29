import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
* This program uses a loop to calculates the
* sum of numbers. It tells the user if input
* is valid or not, by reading file.
*
* @author  Sarah Andrew
* @version 1.0
*
* @since   2023-03-10
*/

public final class TwoDimArrays {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private TwoDimArrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Pass path to file as parameter.
        final File file = new File("assign.txt");

        // Pass path to file as parameter.
        final File students = new File("students.txt");

        // Print to csv file.
        final File fileOut = new File("marks.cvs");

        // Declare lists.
        final List<String> listOfStud =
            new ArrayList<String>();
        final List<String> listOfAssign = 
                new ArrayList<String>();
        
        // Declare variable.
        final StringBuilder builder;
    
        try {
            // Create FileWriter object to write to file.
            final FileWriter fW = new FileWriter(fileOut);
            // Create Scanner object to read from file.
            final Scanner sc = new Scanner(file);
            // Create Scanner object to read from file.
            final Scanner scannerStu = 
                new Scanner(students);
            // Create PrintWriter object to write to file.
            final PrintWriter write = new PrintWriter(fW);

            while (scannerStu.hasNextLine()) {
                // Read line as string.
                // Adding each string to list.
                listOfStud.add(scannerStu.nextLine());
            }
            while (sc.hasNextLine()) {
                // Adding each string to list.
                listOfAssign.add(sc.nextLine());
            }

            // Convert from list of strings to array.
            final String[] arrayOfStu =
                    listOfStud.toArray(new String[0]);
            final String[] arrayOfAssign =
                    listOfAssign.toArray(new String[0]);
            
            // Declare the 2D array.
            final String[][] markArrays;

            // Call the function.
            markArrays = GenMarks(arrayOfStu, arrayOfAssign);

            // Usage of loop to add 2D array into csv file.
            builder = new StringBuilder();
            for (int rows = 0; rows < markArrays[0].length; ++rows) {
                for (int columns = 0; columns < markArrays.length; ++columns) {
                    builder.append(markArrays[columns][rows]);
                    builder.append(", ");
                }
                builder.append("\n");
            }
            
            // Write to file.
            write.print(builder.toString());

            // Display to user in console.
            System.out.print(builder.toString());
                
            // Closes scanner & writer.
            write.close();
            sc.close();
            scannerStu.close();
        } catch (IOException error) {
            System.out.println("An error occurred: "
                    + error.getMessage());
        }
    }

    /**
    * This function generates a random mark,
    * and assign it to each name.
    *
    * @param arrayNums passed
    * @return mean.
    */
    public static String[][] GenMarks(String[] studArray, String[]assignArray) {
        // Declare 2D arrays of strings.
        String[][] array2DMarks = new 
        String[studArray.length + 1][assignArray.length + 1];
        
        // Declare variable.
        final Random random = new Random();

        // Assign name at index 0,0.
        array2DMarks[0][0] = "Name";

        // Initalize counter.
        int counter = 1;

        // Usage of loop to copy each element into the 2D array.
        for (String assignName : assignArray) {
            array2DMarks[0][counter] = assignName;
            counter++;
        }
        // Usage of loop to copy each element into the 2D array.
        int counter2 = 1;
        for (String student : studArray) {
            array2DMarks[counter2][0] = student;
            counter2++;
        }

        // Populate cell of marks into the 2D array.
        for (int counter3 = 1; counter3 <= studArray.length; counter3++) {
            for (int counter4 = 1; counter4 <= assignArray.length; counter4++) {
                // Generate random marks using standard dev.
                double marks = random.nextGaussian() * 10 + 75;
                // Set marks into positions. 
                array2DMarks[counter3][counter4] = Double.toString(marks);
            }
        }
        // Return results back to main.
        return array2DMarks; 
    }
}