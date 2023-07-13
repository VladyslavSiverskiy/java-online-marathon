class ArrayUtil {
    // Write static method averageValue(...) here
    public static double averageValue(Array<? extends Number> array) {
        double sum = 0;
        int arrayLength = array.length();
        for (int i = 0; i < arrayLength; i++){
            sum += array.get(i).doubleValue();
        }
        return sum / arrayLength;
    }
}
