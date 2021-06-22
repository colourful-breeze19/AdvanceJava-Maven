/**
 * @class  InputValidator
 * @author chahat
 * @description InputValidator class provides user input and validation of those inputs.
 *
 */
package com.nagarro.training.assignment5.flight;

import com.nagarro.training.assignment5.constant.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputValidator {
    static Scanner scan = new Scanner(System.in);

    /**
     * Method to check if the country code given by user exists in the csv file or not.
     *
     * @param loc
     * @return
     * @throws IOException
     * @throws CsvValidationException
     */
    public static boolean validLoc(String loc) throws IOException, CsvValidationException {
        File[] filesLists = GetCsv.getFiles();
        boolean check = false;
        for (File file : filesLists) {
            if (check) break;
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            ArrayList<String> arr;
            while (true) {
                /**
                 * Base Condition: If the file has no more line then loop ends.
                 */
                if (!((nextLine = reader.readNext()) != null)) break;

                /**
                 * Generate token out of each line and use them to search the required data.
                 */
                for (String line : nextLine) {
                    StringTokenizer tokenList = new StringTokenizer(line, Constants.SEPARTATOR);
                    arr = new ArrayList<String>(line.length());
                    while (tokenList.hasMoreTokens()) {
                        arr.add(tokenList.nextToken());
                    }
                    if (arr.get(1).equals(loc) || arr.get(2).equals(loc)) {
                        check = true;
                        break;
                    }
                }
                if (check) break;
            }
        }
        return check;
    }

    /**
     * Takes input from user and validates it for the Code.
     *
     * @return
     */
    public static String depLocationInput() throws IOException, CsvValidationException {
        String loc = "";
        while (true) {
            System.out.print("Enter departure location -> ");
            loc = scan.nextLine().toUpperCase();
            boolean check = validLoc(loc);
            try {
                if (loc == null || loc.isEmpty()) {
                    throw new Exception(Constants.EMPTY_LOC);
                } 
                else if (loc.length() != 3) {
                    throw new Exception(Constants.LOC_CODE_ERROR);
                } 
                else if (!check) {
                    throw new Exception(Constants.NO_FLIGHT);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return loc;
    }
    public static String arrlocationInput() throws IOException, CsvValidationException {
        String loc = "";
        while (true) {
            System.out.print("Enter arrival location -> ");
            loc = scan.nextLine().toUpperCase();
            boolean check = validLoc(loc);
            try {
                if (loc == null || loc.isEmpty()) {
                    throw new Exception(Constants.EMPTY_LOC);
                } 
                else if (loc.length() != 3) {
                    throw new Exception(Constants.LOC_CODE_ERROR);
                } 
                else if (!check) {
                    throw new Exception(Constants.NO_FLIGHT);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return loc;
    }


    /**
     * Takes input from user and validates it for the Date.
     *
     * @return
     */
    public static String enterDate() {
        String date;
        while (true) {
            System.out.print("Enter travelling date -> ");
            try {
                date = scan.nextLine().toUpperCase();
                if (!date.matches(Constants.DATE))
                    throw new Exception("Date not in appropriate(dd-MM-yyyy) format, Enter Again: ");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    /**
     * Takes input from user and validates it for the Flight class.
     *
     * @return
     */
    public static String flightClassInput() {
        String flightClass;
        while (true) {
            System.out.println("Enter the flight class \nEB for Business \nE for Economic ");
            try {
                flightClass = scan.nextLine().toUpperCase();
                if (!flightClass.equals(Constants.FLIGHT_BUSINESS) && !flightClass.equals(Constants.FLIGHT_ECONOMY)) {
                    throw new Exception(Constants.INVALID_CHOICE);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return flightClass;
    }

    /**
     * Takes input from user and validates it for the choice of output preference.
     *
     * @return
     */
    public static int output() {
        int preferredOutput;
        while (true) {
            System.out.println("Enter the Output Preference ->\n 1. To sort by Fare "
            		+ "\n 2. To sort by Flight Duration ");
            try {
                preferredOutput = scan.nextInt();
                if (preferredOutput != 1 && preferredOutput != 2) {
                    throw new Exception(Constants.INVALID_CHOICE);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return preferredOutput;
    }
}
