
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
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx]);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = args[0].substring(1);
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                bufferedWriter.write(", " + newEmployee);
                bufferedWriter.close();
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                boolean found = false;
                String searchEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                int wordCount = 0;
                for (String employee : employees) {
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
                String employeeToUpdate = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeToUpdate)) {
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
                String[] employees = readEmployeesFromFile();
                String employeeToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception e) {
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("employees.txt")));
        String line = bufferedReader.readLine();
        bufferedReader.close();
        return line.split(",");
    }

    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("employees.txt"));
        bufferedWriter.write(String.join(",", employees));
        bufferedWriter.close();
    }
}