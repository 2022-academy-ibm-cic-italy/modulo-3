public class Counter implements CounterInterface {

    private int value;

    public Counter(int value) {
        this.value = value;
    }

    public void increase() {
        value++;
    }

    public void decrease() {
        if (value > 0) {
            value--;
        }
    }

    public int getValue() {
        return value;
    }

}
