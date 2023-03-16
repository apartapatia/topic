import java.util.Arrays;

public class Interval {
    int [] interval;
    int min;
    int max;

    int n;

    public Interval(int n, int min_bound, int max_bound){
        this.max = max_bound;
        this.min = min_bound;
        this.n = n;
        this.interval =  new int[max_bound + 1];
        max -= min;
        for (int i = 1; i < n + 1; i++) {
            int index = (int) (Math.random() * ++max) + min;
            if(interval[index] > 0){
                i--;
            }
            else{
                interval[index]++;
            }
        }
    }

    public int solution(int n, int min_bound, int max_bound) {
        max /= n;
        int flag = 0;
        for(int i = min; i < max; i++){
            if(interval[i] > 0){
                flag++;
                System.out.println("FLAG" + flag);
            }
        }
        if(flag == 1){
            for(int i = min; i < max; i++){
                if(interval[i] > 0){
                    return i;
                }
            }
        }
        else if (flag > 1){
            solution(n,min,max);
        }
        else {
            solution(n,max,max);
        }
        return 0;
    }
}