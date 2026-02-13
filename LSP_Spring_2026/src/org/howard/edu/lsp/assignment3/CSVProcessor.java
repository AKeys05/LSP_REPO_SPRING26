/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;

public class CSVProcessor {

    private String inputFile;
    private String outputFile;

    public CSVProcessor(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

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
