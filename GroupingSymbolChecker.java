// Fady Zaki SDEV200 2/8/2024

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class GroupingSymbolChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <source-file>");
            return;
        }

        String fileName = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 1;

            Stack<Character> stack = new Stack<>();

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (c == '(' || c == '[' || c == '{') {
                        stack.push(c);
                    } else if (c == ')' || c == ']' || c == '}') {
                        if (stack.isEmpty()) {
                            System.out.println("Error: Extra closing symbol " + c + " at line " + lineNumber);
                            return;
                        }

                        char top = stack.pop();
                        if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                            System.out.println("Error: Mismatched symbol " + top + " and " + c + " at line " + lineNumber);
                            return;
                        }
                    }
                }
                lineNumber++;
            }

            if (!stack.isEmpty()) {
                System.out.println("Error: Unclosed symbol " + stack.pop());
            } else {
                System.out.println("All grouping symbols are correct.");
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
