class Accountant {
    public static int sum(int x, int y) {
        ParallelCalculator parallelCalculator = new ParallelCalculator((first, second) -> first + second, x, y);
        Thread t = new Thread(parallelCalculator);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            return -1;
        }
        return parallelCalculator.result;
    }
}
