public class Main {
    
    public static void main(String[] args) {
        Counter c = new Counter(10);
        c.increase();
        System.out.println("Value of Counter: " + c.getValue());
    }
}
