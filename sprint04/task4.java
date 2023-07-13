class ArrayUtil {
    
    // Write static method setAndReturn(...) here
    public static <T> T setAndReturn(T[] array, T value, int position){
        array[position] = value;
        return value;
    }
}
