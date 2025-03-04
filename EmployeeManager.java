
//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options:");
            System.out.println("  line - List all employees");
            System.out.println("  s - Show a random employee");
            System.out.println("  +<name> - Add a new employee");
            System.out.println("  ?<name> - Search for an employee");
            System.out.println("  c - Count the number of words in the file");
            System.out.println("  u<name> - Update an employee's name to 'Updated'");
            System.out.println("  d<name> - Delete an employee");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readEmployeesFromFile()) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEES_FILE_PATH, true));
                bufferedWriter.write(", " + args[0].substring(1));
                bufferedWriter.close();
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String searchEmployee = args[0].substring(1);
                boolean found = Arrays.asList(employees).contains(searchEmployee);
                if (found) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found.");
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                int wordCount = 0;
                for (String employee : readEmployeesFromFile()) {
                    wordCount += employee.split(" ").length;
                }
                System.out.println(wordCount + " word(s) found");
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } catch (Exception e) {
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
                employeeList.remove(args[0].substring(1));
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception e) {
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line.split(",");
    }

    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEES_FILE_PATH));
        bufferedWriter.write(String.join(",", employees));
        bufferedWriter.close();
    }
}