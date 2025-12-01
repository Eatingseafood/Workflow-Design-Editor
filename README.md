# Workflow Design Editor

This is the final project of the course "OOAD" in NCU. It is a UML/Workflow design editor built with Java Swing, demonstrating various Object-Oriented Design patterns.

## Features

The editor provides a graphical interface to create structural diagrams with the following elements:

### Basic Objects

- **Rect:** Represents classes or entities.
- **Oval:** Represents use cases or process nodes.

### Connections (Lines)

- **Association:** Standard link between objects.
- **Generalization:** Inheritance relationship.
- **Composition:** Strong "whole-part" relationship.

### Editing Capabilities

- **Select Tool:** Click to select, drag to move objects.
- **Grouping:** Combine multiple objects into a single `CompositeObject`.
- **Dynamic Labeling:** (If implemented) Edit names of objects.
- **Connection Ports:** Automatically detects attachment points on objects.

## Architecture & Design Patterns

The project is structured using standard software engineering principles:

- **MVC (Model-View-Controller):** Separation of concerns between data (`src/model`), interface (`src/view`), and logic (`src/controller`).
- **State Pattern:** Used to manage different editor modes (e.g., `SelectMode`, `RectMode`, `AssociationMode`) in `src/controller/mode`.
- **Composite Pattern:** Implemented in `CompositeObject` to handle groups of objects uniformly.
- **Factory/Strategy Patterns:** (Implied) For creating different types of shapes and links.

## Project Structure

```text
src/
├── Main.java            # Entry point
├── controller/          # Logic for handling user inputs
│   ├── listener/        # Event listeners
│   └── mode/            # State machine for editor tools
├── model/               # Data structures for shapes and lines
│   ├── basic/           # Basic shapes (Rect, Oval)
│   └── link/            # Connection lines
└── view/                # GUI components (MainFrame, Canvas, Toolbar)
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### How to Run

1. **Compile:**
    Navigate to the `src` folder and compile the sources.

    ```bash
    cd src
    javac Main.java
    ```

    *Note: Ensure your classpath includes the current directory.*

2. **Run:**

    ```bash
    java Main
    ```

Alternatively, import the project into **IntelliJ IDEA** using the provided `.iml` file or open as a standard Java project in Eclipse/VS Code.

