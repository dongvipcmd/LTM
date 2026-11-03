public class Test{

    public static void main(String[] args){
        int num = 1341;
        int divisor = 1;

        while (num / divisor >= 10) {
            divisor *= 10;
        }

        while (divisor > 0) {
            int result = (num / divisor) * divisor;
            System.out.println(result);

            num %= divisor;
            divisor /= 10;
        }

    }

}