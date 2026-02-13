/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.nio.file.*;

public class ETLPipeline {

	public static void main(String[] args) {
		String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";

        // Check input exists
        if (!Files.exists(Paths.get(inputFile))) {
            System.err.println("Error: Input file " + inputFile + " not found.");
            System.exit(1);
        }

        CSVProcessor processor = new CSVProcessor(inputFile, outputFile);
        processor.process();

	}

}
