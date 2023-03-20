import java.util.*;

public class Interval {
    private int n;
    private int min;
    private int max;

    private int sc;
    private Map<Integer, Integer> interval;

    public Interval(int n, int min_bound, int max_bound) {
        this.max = max_bound;
        this.min = min_bound;
        this.n = n;
        this.interval = new HashMap<>();
        for (int i = 0; i < n ; i++) {
            int index = (int) (Math.random() * (max_bound - min_bound + 1)) + min_bound;
            if(interval.containsKey(index)){
                i--;
            } else {
                interval.put(index, 1);
            }
        }
        //System.out.println(interval.toString());
    }

    public int solution(int sc, int min_bound, int max_bound) {
        int flag = 0;
        int index = 0;
        for(int i = min_bound; i <= max_bound; i++){
            if(interval.containsKey(i)){
                flag++;
                index = i;
            }
        }

        //System.out.println("FLAG: " + flag);
        sc++;
        int count = Math.abs(max_bound - min_bound);
        //int remax = max;
        if(flag == 1) {
            //System.out.println("\n" + "kolvo sh: "+ sc);
            return index;
        } else if (flag > 1) {
            //this.n = flag;
            index = solution(sc,min_bound, min_bound + count / flag);
            return index;
        } else {
            index = solution(sc,max_bound, max_bound + count);
            return index;
        }
    }
}