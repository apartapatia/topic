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
        System.out.println(interval.toString());
    }

    public int solution(int min_bound, int max_bound) {
        int m = 0; // переменная количества значений в интервале
        int index = 0; // переменная искомого значения
        for(int i = min_bound; i <= max_bound; i++){ // проходимся по всем значениям интервала
            if(interval.containsKey(i)){    // если значение содержится в интервале
                m++; // количество значений в интервале увеличиваем на 1
                index = i; // запоминаем искомое значение
            }
        }
        System.out.println("min_bound: " + min_bound + " max_bound: " + max_bound + " index: " + index + " m: " + m);
        int range = Math.abs(max_bound - min_bound); // расчитываем расстояние между правой и левой границей интервала
        if(m == 1) { // если количество значений в интервале равно 1, то возвращаем искомое значение
            return index;
        } else if (m > 1) { // если количество значений в интервале больше 1, то выполняем функцию заново, сдвигая правую границу интервала влево
            // range / m - разделение интервала, при котором вероятность попадания одного значения в интервал высокое
            index = solution(min_bound, min_bound + range / m);
            return index;
        } else { // если нет значений на данном интервале, то сдвигаем левую границу интервала вправо
            // прибавляя range к max_bound достигаем наименьшего количества шагов для достижения результата
            index = solution(max_bound, max_bound + range);
            return index;
        }
    }
}