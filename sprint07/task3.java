import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;


class Interactor {
    int x;

    private boolean serveSetKey = false;
    private boolean consumeEndedWork = false;

    public synchronized void serve(UnaryOperator<Integer> uo, int initializer) throws InterruptedException {
        System.out.println("Serving thread running");
        System.out.println("Serving thread initializes the key");
        x = uo.apply(initializer);
        System.out.println("key = " + x);
        serveSetKey = true;
        notify();
        while (!consumeEndedWork) {
            wait();
        }
        System.out.println("Serving thread resumed");
    }

    public synchronized void consume(BinaryOperator<Integer> bo, int operand2) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (!serveSetKey && (System.currentTimeMillis() - startTime) < 3000) {
            wait(3000);
        }
        System.out.println("Consuming thread received the key. key = " + x);
        x = bo.apply(x, operand2);
        System.out.println("Consuming thread changed the key. key = " + x);
        consumeEndedWork = true;
        notify();
    }
}
