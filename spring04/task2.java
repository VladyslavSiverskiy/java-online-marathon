import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;


public class MyUtils {
    public static class Student {
        private int id;
        private String name;
        // Constructor, metthods, Code
        
         public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id && Objects.equals(name, student.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
    
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        return list1.stream()
            .filter(list2::contains)
            .collect(Collectors.toSet());
    }
}
