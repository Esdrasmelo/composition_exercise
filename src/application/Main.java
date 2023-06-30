package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter department's name: ");
        String departmentName = scanner.nextLine();
        Department department = new Department(departmentName);

        System.out.print("\nEnter worker data: ");

        System.out.print("\nName: ");
        String workerName = scanner.nextLine();

        System.out.print("Level: ");
        String workerLevel = scanner.nextLine();
        WorkerLevel convertWorkerLevel = WorkerLevel.valueOf(workerLevel);

        System.out.print("Base salary: ");
        Double baseSalary = scanner.nextDouble();

        System.out.print("How many contracts to this worker?: ");
        Integer contractsQuantity = scanner.nextInt();

        Worker worker = new Worker(workerName, convertWorkerLevel, baseSalary, department);

        for (int i = 0; i < contractsQuantity; i++) {
            System.out.printf("\nEnter contract #%s data: ", i + 1);

            System.out.print("\nDate (DD/MM/YYYY): ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String contractDate = scanner.next();;
            Date convertContractDate = dateFormat.parse(contractDate);

            System.out.print("Value per hour: $");
            Double valuePerHour = scanner.nextDouble();

            System.out.print("Duration (hours): ");
            Integer duration = scanner.nextInt();

            HourContract contract = new HourContract(convertContractDate, valuePerHour, duration);
            worker.addContract(contract);
        }

        System.out.println();

        System.out.print("Enter month and year to calculate income (DD/YYYY): ");
        String monthAndYear = scanner.next();
        Integer month = Integer.parseInt(monthAndYear.substring(0, 2));
        Integer year = Integer.parseInt(monthAndYear.substring(3));

        System.out.printf("\nName: %s", worker.getName());
        System.out.printf("\nDepartment: %s", worker.getDepartment().getName());
        System.out.printf("\nIncome for %s: %.2f", monthAndYear, worker.income(year, month));

        scanner.close();

    }
}