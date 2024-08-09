# Makefile for Java project

# Variables
SRC_DIR = src
BUILD_DIR = build
MAIN_CLASS = Main
JAR_NAME = Sudoku.jar

# Compilation flags
JAVAC_FLAGS = -d $(BUILD_DIR)

# Default target
all: compile jar

# Target to create the build directory
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Target to compile the Java source files
compile: $(BUILD_DIR)
	javac $(JAVAC_FLAGS) $(SRC_DIR)/*.java

# Target to create the JAR file
jar: compile
	jar cfe $(JAR_NAME) $(MAIN_CLASS) -C $(BUILD_DIR) .

# Target to run the Java program
run: compile
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

# Target to clean the project
clean:
	rm -rf $(BUILD_DIR) $(JAR_NAME)

# Phony targets
.PHONY: all compile jar run clean
