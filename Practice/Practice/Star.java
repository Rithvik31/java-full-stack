package Practice;

public class Star {
    public void pyramidPattern(int rows) {
        for (int i=0; i < rows; i++) {
            for (int j= i+1 ; j > 0; j--) {
                System.out.println("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Star program = new Star();
        program.pyramidPattern(5);
    }
    
}
