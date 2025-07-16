# Transcript-Analyzer
A desktop application to automate academic transcript audits for the Bachelor of Computer Science (BCS) program at the University of New Brunswick (UNB). The application parses pdf transcripts and categorizes the courses into the core courses and the breadth, technical, and free electives. It tracks credit hours with a substantial writing component (W) and with a substantial programming component (P). Finally it displays all of this information with a projected time to graduate. 
## Features
- **Import pdf Transcripts**: Extracts text from a text based pdf of a students transcript using Apache PDFBox.
- **Course Categorization**: Recognizes core courses in the transcript and categorize the rest.
- **Maintains P/W Course List**: Saves a list of courses that has the P or W requirement met while allowing the user to add/remove courses from the list.
- **Maintain Excluded Course List**; Saves a list of courses that are not allowed to be counted towards the graduation of BCS students.
- **Progress Dashboard**: Allows the user to view a completed vs. remaining courses and credit hours.
- **Graduation Projection**: Makes an estimated as to what term the student will graduate. Assumes 5 courses per term unless otherwise specified.
- **Export Reports**: Exports the results generated within the application to an excel or pdf file.
## Getting Started
###Prerequisites
- Java 17 SDK
- Maven 3.6+
- Git
##Project Structure
src/
|-main/
|---java/com/yourorg/transcriptanalyzer
│   │   |--- io/             # IO Layer (PDFImporter, TextPreprocessor, ExcelWriter)
│   │   |--- parser/         # Parsing & Categorization (TranscriptParser, CoreMatcher...)
│   │   |--- logic/          # Business Logic (ProgressCalculator, ProjectionEngine)
│   │   |--- ui/             # Presentation Layer (DashboardView, AdminView)
│   |---|resources/
│       |--- config.json     # Default P/W lists, Excluded list
└── test/
