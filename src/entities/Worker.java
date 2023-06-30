package entities;

import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private String name;
    private WorkerLevel level;
    private Double baseSalary;
    private List<HourContract> contracts = new ArrayList<>();
    private Department department;

    public Worker() {
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addContract(HourContract contract) {
        this.contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        this.contracts.remove(contract);
    }

    public Double income(Integer year, Integer month) throws ParseException {
        double sum = this.baseSalary;

        for (HourContract contract: this.contracts) {
            LocalDate parsedContractDate = contract.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Integer contractYear = parsedContractDate.getYear();
            Integer contractMonth = parsedContractDate.getMonthValue();

            if (year.equals(contractYear) && month.equals(contractMonth)) {
                sum += contract.totalValue();
            }
        }

        return sum;
    }
}
