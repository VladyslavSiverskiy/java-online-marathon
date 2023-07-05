import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;


class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience && Objects.equals(name, employee.name) && Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

}
class Manager extends Employee {
     private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(BigDecimal.valueOf(coefficient));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }


}
public class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if (workers.isEmpty()) {
            return new ArrayList<>();
        }
        Set<Employee> largestEmployees = new HashSet<>();
        int maxExperienceEmployees = 0;
        int maxExperienceManagers = 0;
        BigDecimal maxPaymentEmployees = BigDecimal.ZERO;
        BigDecimal maxPaymentManagers = BigDecimal.ZERO;
        for (Employee worker : workers) {
            if (worker instanceof Employee && !(worker instanceof Manager)) {
                maxExperienceEmployees = Math.max(maxExperienceEmployees, worker.getExperience());
                maxPaymentEmployees = maxPaymentEmployees.max(worker.getPayment());
            } else if (worker instanceof Manager) {
                maxExperienceManagers = Math.max(maxExperienceManagers, worker.getExperience());
                maxPaymentManagers = maxPaymentManagers.max(worker.getPayment());
            }
        }
        for (Employee employee : workers) {
             if ((employee instanceof Employee && !(employee instanceof Manager) &&
                    (employee.getExperience() == maxExperienceEmployees ||
                     employee.getPayment().stripTrailingZeros().toPlainString().equals(maxPaymentEmployees.stripTrailingZeros().toPlainString())))
                || (employee instanceof Manager &&
                    (employee.getExperience() == maxExperienceManagers ||
                     employee.getPayment().stripTrailingZeros().equals(maxPaymentManagers.stripTrailingZeros().toPlainString())))
            ) {
                largestEmployees.add(employee);
            }
        }
        return new ArrayList<>(largestEmployees);
    }
}
