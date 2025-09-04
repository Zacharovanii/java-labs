public class Task3 {
    public static void main(String[] args) {
        int start = 1;
        int end = 30;
        double step = 0.5;

//        With formula
        double n = ((end - start) / step) + 1;
        double sum = (n / 2) * (start + end);

        System.out.println(sum);

//        With loop
        double loopSum = 0;
        for (double i = start; i != end + step; i += step) {
            loopSum += i;
        }
        System.out.print(loopSum);
    }
}
