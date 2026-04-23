AI Tools Used:
Claude

Prompts Used (2–5 max):
1. Can you help me design a Java report system using Template Method with a fixed workflow and variable steps matching the following: loadData → formatHeader → formatBody → formatFooter → generateReport. Be sure to include clean javadocs to accompany the code.

The data requirements are as follows:
StudentReport: studentName (String), gpa (double)
CourseReport: courseName (String), enrollment (int)
Values must be set in loadData() and used in output.

The driver requirements are as follows: 
- Use List<Report>
- Add StudentReport and CourseReport
- Loop through list
- Call generateReport()
- Demonstrate polymorphism

The output should follow this format:
=== HEADER ===
Student Report

=== BODY ===
Student Name: John Doe
GPA: 3.8

=== FOOTER ===
End of Student Report

=== HEADER ===
Course Report

=== BODY ===
Course: CSCI 363
Enrollment: 45

=== FOOTER ===
End of Course Report

2. Help me understand more in depth the breakdown of each class.

How AI Helped (2–3 sentences):
AI helped me to break down what the base class and subclasses should be for this system, as well as what should be included within each one. It also was helpful with formatting the Driver class properly so that it matched the expected output given in the instructions.

Reflection (1–2 sentences):
I learned how to actually put the Template Method into use and breakdown a design according to a list of given requirements.
