/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;

public class ETLPipeline {
	
	public static void main(String[] args) {
		
		// Define file paths
		String inputFile = "data/products.csv";
		String outputFile = "data/transformed_products.csv";
		
		// Check if the input file exists
		if (!Files.exists(Paths.get(inputFile))) {
			System.err.println("Error: Input file" + inputFile + " not found.");
			System.exit(1);
		}
		
		// Counters for summary
		int rowsRead = 0;
		int rowsTransformed = 0;
		int rowsSkipped = 0;
		
		// Core file processing behavior
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			
			String line;
			//boolean isFirstLine = true;
			int lineNumber = 0;
			
			// Always write header to output file
			writer.write("ProductID,Name,Price,Category,PriceRange");
			writer.newLine();

			
			// Read each line
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				
				if (lineNumber == 1) {
					continue;
				}
				
				// Skip blank lines
				if (line.trim().isEmpty()) {
					continue;
				}
				
				rowsRead++; 
				
				String[] rawFields = line.split(",", -1); // Split by comma
				String[] fields = new String[rawFields.length];
				for (int i = 0; i < rawFields.length; i++) {
				    fields[i] = rawFields[i]
				        .replace("\u00A0", "")   // remove non-breaking spaces
				        .strip();              
				}
				
				java.util.List<String> nonEmptyFields = new java.util.ArrayList<>();
				for (String field : fields) {
					if (!field.isEmpty()) {
						nonEmptyFields.add(field);
					}
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
				
				// DEBUG: show raw values with markers
//				if (productIdStr.equals("8")) {
//				    System.out.println("DEBUG LINE FOR PRODUCT 8:");
//				    System.out.println("ProductID = >" + productIdStr + "<");
//				    System.out.println("Name      = >" + name + "<");
//				    System.out.println("Price     = >" + priceStr + "<");
//				    System.out.println("Category  = >" + category + "<");
//				}

				// Parse Price and ProductID
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
				
				// Transformations
				 
				// 1. UPPERCASE Product names
				String newName = name.toUpperCase();
				
				// 2. Apply 10% discount to Electronics
				BigDecimal newPrice = price;
				if (category.equals("Electronics")) {
					newPrice = price.multiply(new BigDecimal("0.90"));
				}
				newPrice = newPrice.setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
				
				// 3. Change category to Premium Electronics if needed
				String newCategory = category;
				if (category.equals("Electronics") && newPrice.compareTo(new BigDecimal("500.00")) > 0) {
					newCategory = "Premium Electronics";
				}
				
				// 4. Determine price range
				String priceRange;
				if (newPrice.compareTo(new BigDecimal("10.00")) <= 0) {
					priceRange = "Low";
				} else if (newPrice.compareTo(new BigDecimal("100.00")) <= 0) {
					priceRange = "Medium";
				} else if (newPrice.compareTo(new BigDecimal("500.00")) <= 0) {
					priceRange = "High";
				} else {
					priceRange = "Premium";
				}
				
				if (productId == 8) {
				    System.out.println("DEBUG AFTER TRANSFORM:");
				    System.out.println("NewName = >" + newName + "<");
				}

				writer.write(productId + "," + newName + "," + newPrice.toPlainString() + "," + newCategory + "," + priceRange);
				writer.newLine();
				
				rowsTransformed++;
				
				
			}
			
			// Summary
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
 