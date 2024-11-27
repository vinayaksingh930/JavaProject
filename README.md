# **Simple Java Text Editor**

A Java text editor written using the Java Swing framework for the user interface and Java Collections Framework for implementing the logic. This project combines the basic functionalities of a text editor with advanced features like shape drawing and resizing.

---

## **Project Overview**

The **Simple Java Text Editor** is a lightweight text editing application with a graphical interface. It allows users to perform basic text operations and integrates a feature for inserting and resizing geometric shapes. This project is designed to demonstrate the use of Java Swing for UI development and Java's event-handling capabilities.

---

## **Features**

### **Text Editing**
- **Basic Operations**:
  - Cut, Copy, Paste text.
  - Save typed text to a file.
  - Load a previously saved file for editing.

- **Formatting**:
  - Change the font type and size.
  - Adjust the case of selected text (uppercase, lowercase).

- **Find and Replace**:
  - Search for specific words in the document.
  - Replace a word or all occurrences with another word.

- **Word and Character Count**:
  - Display the total number of words and characters in the selected text.

---

### **Shape Drawing and Resizing**
- Insert basic geometric shapes:
  - Rectangle
  - Circle
  - Line
  - Triangle
  - Polygon

- Resize shapes dynamically by dragging the mouse.

- Highlight shapes when hovered for better interaction.

---

## **Technologies Used**

- **Java Swing**: For creating the graphical user interface.
- **Java AWT**: For handling shapes and mouse events.
- **Java Collections Framework**: To manage data structures for text and shape handling.

---

## **How to Run the Project**

### **Requirements**
- Java Development Kit (JDK) 8 or later.
- IDE (e.g., IntelliJ IDEA, Eclipse) or a terminal for running Java programs.

---

### **Steps to Run**

1. **Download the Code**:
   - Clone or download the repository and extract it into a local directory.

2. **Compile the Project**:
   - Open a terminal and navigate to the project folder:
     ```bash
     cd <project_directory>
     ```
   - Compile the source files:
     ```bash
     javac -d bin src/*.java
     ```

3. **Run the Project**:
   - Execute the compiled files:
     ```bash
     java -cp bin Main
     ```

4. **Navigate the Application**:
   - Use the menu options for text operations.
   - Switch to the "Shape Drawer" section to draw and resize shapes interactively.

---

## **Usage**

### **Text Editing**
- Type text in the editor.
- Use the menu or toolbar to cut, copy, paste, and format text.
- Save and load files via the "File" menu.

### **Shape Drawing**
- Select a shape from the toolbar.
- Click and drag on the canvas to draw the shape.
- Hover over a shape to highlight it.
- Drag to resize the shape dynamically.

---

## **Key Functionalities**

- **Text Operations**:
  - Rich editing features for basic and advanced text manipulation.

- **Shape Drawing**:
  - Interactive drawing and resizing of shapes to enhance usability.

- **File Handling**:
  - Save and reload your text documents seamlessly.

---

This project demonstrates the use of Java Swing for GUI development and provides a foundation for building more complex applications. Enjoy experimenting and building upon this project! 😊
