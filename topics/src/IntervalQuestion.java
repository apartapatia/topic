import java.util.Arrays;
import java.util.Random;

public class IntervalQuestion {

    private int M = 1000000;
    private int nStart = 500;

    double getAverageNumberOfQuestions() {
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


        IntervalQuestion intervalQuestion = new IntervalQuestion();
        double avgNumOfQuestions = intervalQuestion.getAverageNumberOfQuestions();
        System.out.println("Average number of questions: " + avgNumOfQuestions);

        long endTime = System.nanoTime();
        long durationInMillis = (endTime - startTime) / 1_000_000; // разница в миллисекундах
        System.out.println("Программа выполнилась за " + durationInMillis + " мс");
    }
}