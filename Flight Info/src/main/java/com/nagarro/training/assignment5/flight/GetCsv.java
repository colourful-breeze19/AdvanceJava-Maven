/**
 * @class GetCsv
 * @author chahat
 * @description GetCsv class get the path for each of the airline csv files present in resource folder.
 *
 */
package com.nagarro.training.assignment5.flight;

import java.io.File;
class GetCsv {
    /**
     * Method to get a list of paths for each airline's csv file.
     *
     * @return
     */
    public static File[] getFiles() {
        File csvfile = new File("src/main/resources");
        File[] filesList = csvfile.listFiles();
        return filesList;
    }
}
