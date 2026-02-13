/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;

/**
 * Handles CSV reading, validation, and writing of transformed product data.
 * 
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Read input CSV file line by line</li>
 *   <li>Trim and clean whitespace (including non-breaking spaces)</li>
 *   <li>Validate fields (skip lines with missing or invalid data)</li>
 *   <li>Create Product objects and transform them using ProductTransformer</li>
 *   <li>Write output CSV file including header</li>
 *   <li>Print summary of rows read, transformed, and skipped</li>
 * </ul>
 * </p>
 * 
 */
public class CSVProcessor {

    private String inputFile;
    private String outputFile;

    /**
     * Constructs a CSVProcessor with the given input and output file paths.
     * 
     * @param inputFile  path to the input CSV file
     * @param outputFile path to the output CSV file
     */
    public CSVProcessor(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    
    /**
     * Processes the input CSV file: reads, validates, transforms, and writes the
     * output CSV file.
     * 
     * <p>
     * Skips invalid rows and prints messages to the console for:
     * <ul>
     *   <li>Missing or invalid ProductID</li>
     *   <li>Missing or invalid Price</li>
     *   <li>Incorrect number of fields</li>
     * </ul>
     * </p>
     */
    public void process() {

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            int lineNumber = 0;

            // Always write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) continue;
                if (line.trim().isEmpty()) continue;

                rowsRead++;

                String[] rawFields = line.split(",", -1);
                String[] fields = new String[rawFields.length];

                for (int i = 0; i < rawFields.length; i++) {
                    fields[i] = rawFields[i]
                            .replace("\u00A0", "")
                            .strip();
                }

                java.util.List<String> nonEmptyFields = new java.util.ArrayList<>();
                for (String field : fields) {
                    if (!field.isEmpty()) nonEmptyFields.add(field);
                }

                if (nonEmptyFields.size() != 4) {
                    System.out.println("Skipping line " + lineNumber + " - does not have exactly 4 valid fields");
                    rowsSkipped++;
                    continue;
                }

                String productIdStr = fields[0];
                String name = fields[1];
                String priceStr = fields[2];
                String category = fields[3];

                int productId;
                try {
                    productId = Integer.parseInt(productIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + " - invalid ProductId");
                    rowsSkipped++;
                    continue;
                }

                BigDecimal price;
                try {
                    price = new BigDecimal(priceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + " - invalid Price");
                    rowsSkipped++;
                    continue;
                }

                Product product = new Product(productId, name, price, category);

                String outputRow = ProductTransformer.transform(product);
                writer.write(outputRow);
                writer.newLine();

                rowsTransformed++;
            }

            System.out.println("Transformation Summary:");
            System.out.println("Rows read = " + rowsRead);
            System.out.println("Rows transformed = " + rowsTransformed);
            System.out.println("Rows skipped = " + rowsSkipped);
            System.out.println("Output file = " + outputFile);

        } catch (IOException e) {
            System.err.println("Error: Failed to process files - " + e.getMessage());
            System.exit(1);
        }
    }
}
