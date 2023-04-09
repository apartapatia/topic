import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IntervalQuestionThreads implements Callable<Double> {

    private int M ;
    private int nStart ;

    public IntervalQuestionThreads(int M, int nStart) {
        this.M = M;
        this.nStart = nStart;
    }

    @Override
    public Double call() {
        double sumN = 0;

        for (int step = 0; step < M; step++) {
            int n = nStart;
            double[] points = new double[n + 1];
            // формируем массив: n случайных чисел в порядке возрастания
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                points[i] = random.nextDouble();
            }
            Arrays.sort(points); // сортируем массив
            points[n] = 1; // добавляем последний элемент равный 1

            int N = 0;
            double minBound = 0;
            double maxBound = 1;
            double h = 0;
            int i = 0;
            // задаем вопросы до тех пор, пока не будет ровно одной точки левее порога h
            while (i != 1) {
                // Шаг 1. Формируем новый интервал [minBound maxBound] c n точками
                if (i == 0) {
                    minBound = h;
                } else {
                    maxBound = h;
                    n = i;
                }
                // Шаг 2. Определяем значение порога h
                h = (maxBound - minBound) / n + minBound;
                // Шаг 3. Находим число точек на интервале [minBound, h]
                i = 0;
                while (points[i + 1] < h) {
                    i++;
                }
                // Получен ответ: левее порога h расположено i точек
                N++;
            }
            sumN += N;
        }
        // Получаем оценку для среднего числа вопросов
        return sumN / M;
    }
        public static void main(String[] args) {
            long startTime = System.nanoTime();
            int numThreads = 6;
            int M = 1000000;
            int nStart = 1000;
            double sumN = 0;

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            Future<Double>[] futures = new Future[numThreads];

            for (int i = 0; i < numThreads; i++) {
                Callable<Double> task = new IntervalQuestionThreads(M / numThreads, nStart);
                futures[i] = executor.submit(task);
            }

            for (int i = 0; i < numThreads; i++) {
                try {
                    sumN += futures[i].get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            executor.shutdown();

            double avgNumOfQuestions = sumN / numThreads;
            System.out.println("Average number of questions: " + avgNumOfQuestions);
            long endTime = System.nanoTime();
            long durationInMillis = (endTime - startTime) / 1_000_000; // разница в миллисекундах
            System.out.println("Программа выполнилась за " + durationInMillis + " мс");
        }
    }