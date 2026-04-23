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

How AI Helped (2–3 sentences):
AI helped 
Reflection (1–2 sentences):
