// Write PersonComparator, EmployeeComparator and DeveloperComparator here
class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int result = person1.getName().compareTo(person2.getName());
        if(result == 0) result = Integer.compare(person1.getAge(), person2.getAge());
        return result;
    }
}

class EmployeeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee emp1, Employee emp2) {
        int result = emp1.getName().compareTo(emp2.getName());
        if(result == 0) result = Integer.compare(emp1.getAge(), emp2.getAge());
        if(result == 0) result = Double.compare(emp1.getSalary(), emp2.getSalary());
        return result;
    }
}

class DeveloperComparator implements Comparator<Developer> {

    @Override
    public int compare(Developer developer1, Developer developer2) {
        int result = developer1.getName().compareTo(developer2.getName());
        if(result == 0) result = Integer.compare(developer1.getAge(), developer2.getAge());
        if(result == 0) result = Double.compare(developer1.getSalary(), developer2.getSalary());
        if(result == 0) result = developer1.getLevel().compareTo(developer2.getLevel());
        return result;
    }
}



class Utility {
    public static<T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator){
        Arrays.sort(people, comparator);
    }
}
