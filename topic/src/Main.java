public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Interval a = new Interval(50,1,100);
            System.out.println(a.solution(-1,1,100));
        }
        

    }
}