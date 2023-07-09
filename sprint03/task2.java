import java.util.NoSuchElementException;



class NameList {
    private String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        private int counter = 0;

        private Iterator() {

        }

        public boolean hasNext() {
			return counter <= names.length - 1;
        }

		public String next(){
			if(hasNext()){
				return names[counter++];
			}
			throw new NoSuchElementException("No more elements");
		}
    }

}
