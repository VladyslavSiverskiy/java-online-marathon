import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;


class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class Student extends Person {
    private String studyPlace;
    private int studyYears;

    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studyYears == student.studyYears && Objects.equals(studyPlace, student.studyPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studyPlace, studyYears);
    }
}
class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return experienceYears == worker.experienceYears && Objects.equals(workPosition, worker.workPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workPosition, experienceYears);
    }
}
public class MyUtils {
    public List<Person> maxDuration(List<Person> persons) {
        Set<Person> personsWithMaxYears = new HashSet<>();
        int maxStudyYears = 0;
        int maxExperienceYears = 0;

        for (Person person : persons) { //find max values
            if (person instanceof Student) {
                int studyYears = ((Student) person).getStudyYears();
                if (studyYears > maxStudyYears) {
                    maxStudyYears = studyYears;
                }
            } else if (person instanceof Worker) {
                int experienceYears = ((Worker) person).getExperienceYears();
                if (experienceYears > maxExperienceYears) {
                    maxExperienceYears = experienceYears;
                }
            }
        }

        for (Person p : persons) { //select objects with max values
            if (p instanceof Student && ((Student) p).getStudyYears() == maxStudyYears
                || p instanceof Worker && ((Worker) p).getExperienceYears() == maxExperienceYears) {
                personsWithMaxYears.add(p);
            }
        }
        return new ArrayList<>(personsWithMaxYears);
    }
}
