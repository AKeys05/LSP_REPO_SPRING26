/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.nio.file.*;

/**
 * Main class for the ETL pipeline assignment.
 * 
 * <p>
 * This class is responsible for starting the ETL process. It checks if the input
 * CSV file exists, and then delegates the processing to CSVProcessor.
 * </p>
 * 
 * <p>
 * Usage: Run this class as a Java Application in Eclipse. Make sure
 * data/products.csv exists in the project root.
 * </p>
 * 
 */
public class ETLPipeline {
	
	/**
     * Main method of the program.
     * 
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";

        // Check input exists
        if (!Files.exists(Paths.get(inputFile))) {
            System.err.println("Error: Input file " + inputFile + " not found.");
            System.exit(1);
        }
        
        // Start ETL process
        CSVProcessor processor = new CSVProcessor(inputFile, outputFile);
        processor.process();

	}

}
