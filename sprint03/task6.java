import java.util.*;

enum SortOrder {
    ASC,
    DESC
}

public class AddressBook implements Iterable{
    // Write your code here
    private NameAddressPair[] addressBook;
    private int counter; //кількість об'єктів
    private int capacity;

    @Override
    public Iterator iterator() {
        return new AddressBookIterator();
    }

    private static class NameAddressPair {
        private final Person person;
        private String address;

        private final static class Person {
            private final String firstName;
            private final String lastName;

            private Person(String name, String surname) {
                this.firstName = name;
                this.lastName = surname;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getSurname() {
                return lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lastName);
            }

        }

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        public Person getPerson() {
            return person;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "NameAddressPair{" +
                   "person=" + person +
                   ", address='" + address + '\'' +
                   '}';
        }
    }

    private class AddressBookIterator implements Iterator {
        private int counter;

        public int getCounter() {
            return counter;
        }

        public boolean hasNext(){
            if(this.counter < size()){
                return true;
            }
            return false;
        }

        public String next(){
            if(hasNext()){
                NameAddressPair pair = addressBook[this.counter];
                counter++;
                return String.format(
                        "First name: %s, Last name: %s, Address: %s",
                        pair.getPerson().getFirstName(),
                        pair.getPerson().getSurname(),
                        pair.getAddress()
                );
            }
            throw new NoSuchElementException("End of the list");
        }
    }

    public AddressBook(int capacity) {
        this.capacity = capacity;
        addressBook = new NameAddressPair[capacity];
    }

    public int size() {
        return counter;
    }

    public boolean create(String name, String surname, String address) {
        if (suchKeyAlreadyExist(name, surname)) return false;
        if (counter == capacity) {
            capacity *= 2; //multiply capacity
            addressBook = Arrays.copyOf(addressBook, capacity);
        }
        addressBook[counter] = new NameAddressPair(new NameAddressPair.Person(name, surname), address);
        counter++;
        return true;
    }

    public String read(String name, String surname){
        AddressBookIterator iterator = (AddressBookIterator) iterator();
        while (iterator.hasNext()){
            String[] record = iterator.next().split(",");
            String recordName = record[0].split(":")[1].trim();
            String recordSurname = record[1].split(":")[1].trim();
            if(recordName.equals(name) && recordSurname.equals(surname)){
                return record[2].split(":")[1].trim();
            }
        }
        return null;
    }

    public boolean update(String name, String surname, String address){
        AddressBookIterator iterator = (AddressBookIterator) iterator();
        while (iterator.hasNext()){
            String[] record = iterator.next().split(",");
            String recordName = record[0].split(":")[1].trim();
            String recordSurname = record[1].split(":")[1].trim();
            if(recordName.equals(name) && recordSurname.equals(surname)){
                addressBook[iterator.getCounter() - 1] = new NameAddressPair(new NameAddressPair.Person(name,surname), address);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String name, String surname){
        AddressBookIterator iterator = (AddressBookIterator) iterator();
        while (iterator.hasNext()){
            String[] record = iterator.next().split(",");
            String recordName = record[0].split(":")[1].trim();
            String recordSurname = record[1].split(":")[1].trim();
            if(recordName.equals(name) && recordSurname.equals(surname)){
                int elementIndex = iterator.getCounter() - 1;
                System.arraycopy(addressBook, elementIndex + 1, addressBook,elementIndex,size() - elementIndex - 1);
                counter--;
                return true;
            }
        }
        return false;
    }

    public void sortedBy(SortOrder sortOrder){
        Arrays.sort(addressBook, (o1, o2) -> {
            int result = o1.getPerson().getFirstName().compareTo(o2.getPerson().getFirstName());
            if (result == 0) result = o1.getPerson().getSurname().compareTo(o2.getPerson().getSurname());
            return sortOrder == SortOrder.DESC ? -result : result;
        });
    }


    private boolean suchKeyAlreadyExist(String name, String surname) {
        NameAddressPair.Person person = new NameAddressPair.Person(name, surname);
        for (NameAddressPair pair : addressBook) {
            if(pair == null) return false;
            if (pair.getPerson().equals(person)) {
                return true;
            }
        }
        return false;
    }
}


