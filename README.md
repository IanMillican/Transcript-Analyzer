# Transcript-Analyzer
A desktop application to automate academic transcript audits for the Bachelor of Computer Science (BCS) program at the University of New Brunswick (UNB). The application parses PDF transcripts and categorizes the courses into the core courses and the breadth, technical, and free electives. It tracks credit hours with a substantial writing component (W) and with a significant programming component (P). Finally, it displays all of this information with a projected time to graduate. 

In addition to the above-mentioned features, it also provides a summary of the students' progress in their degree. This summary shows the number of completed courses in each category, as well as total credit hours with a W component and total courses with a P component.
## Features
- **Import pdf Transcripts**: Extracts text from a text based pdf of a students transcript using Apache PDFBox.
- **Course Categorization**: Recognizes core courses in the transcript and categorize the rest.
- **Maintains P/W Course List**: Saves a list of courses that has the P or W requirement met while allowing the user to add/remove courses from the list.
- **Maintain Excluded Course List**: Saves a list of courses that are not allowed to be counted towards the graduation of BCS students.
- **Progress Dashboard**: Allows the user to view a completed vs. remaining courses and credit hours.
- **Graduation Projection**: Makes an estimated as to what term the student will graduate. Assumes 5 courses per term unless otherwise specified.
- **Export Reports**: Exports the results generated within the application to an excel or pdf file.
## Getting Started
### Prerequisites
- Java 17 SDK
- Maven 3.6+
- Git
## Project Structure
```
src/
└── main/
    ├── java/
    │   └──com.ianmillican.transcriptanalyzer/
    │       ├── app/
    │       ├── config/
    │       ├── dataaccess/
    │       │   ├── export/
    │       │   └── parser/
    │       ├── domain/
    │       │   ├── dao/
    │       │   ├── interfaces/
    │       │   ├── model/
    │       │   ├── rules/
    │       │   └── service/
    │       └── ui/
    │           ├── builder/
    │           ├── components/
    │           └── viewmodel/
    └── resources/
            ├── catalogs/
            ├── exporters/templates/
            ├── lib/
            ├── lists/
            ├── requirements/
            └── ui/
                ├── css/
                └── icons/
```
### Packages
- **app** 
- **config** 
- **dataaccess** 
    - **dao** contains data transfer objects responsible for passing data back and forth between the domain and dataaccess layers.
    - **export** contains classes for writing summaries to PDFs and Excel files.
    - **parser** contains classes for parsing the transcript. For example it would contain a class for breaking the transcript into terms while another class would take the term and break it into the individual courses.
- **domain** contains business logic for ui and io layer interaction
    - **dao** contains data transfer objects responsible for passing data back and forth between the domain and dataaccess layers.
    - **interfaces** a package for all of the interfaces.
    - **model** contains data models (pojos).
    - **rules** a package for implementing rules from the app configuration. An example would be in the core courses students must take exactly one of CS1303 and MATH2203. This package will contain classes for validating that these rules are followed.
    - **service** contains all the services coordinating the data transfer from the ui and domain layers
- **ui**
    - **viewmodel** responsible for methods that GUI components use to ask the domain layer for information.
    - **components** contains resources for creating similar ui components such as buttons and text fields.
    - **builder** responsible for handling purely UI component creation.

### Resources
- **catalogs** Contains the catalog of all courses. Currently the plan for this is to have a large initial set that may not be complete, and allow it to be extended. This could be done manually by the user or automatically when a course not in the catalog is read in.
- **exporters/templates** Excel templates for exporting transcript information.
- **lib** contains the libraries for JavaFX and the Apache POI.
- **lists** contains lists of P/W courses and excluded courses.
- **requirements**
- **ui**
    - **css** contains and style sheets used within the application.
    - **icons** contains any icons such as the icon displayed for the app.
