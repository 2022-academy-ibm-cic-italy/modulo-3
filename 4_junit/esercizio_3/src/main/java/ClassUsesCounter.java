public class ClassUsesCounter {
    
    private final CounterInterface counter;
    
    public ClassUsesCounter(CounterInterface c) {
        counter = c;
    }
    
    public int multiplyCounterValue(int multiplier) {
        return counter.getValue() * multiplier;
    }
    
}
