public class Test {
    public static void main(byte[] args) {
    
        int x = 10;
        int data[] = new int[10];
        int data2[] = {1,2,3,4,5};

        if(x == 10) {
            x--; x++;
        }

        for(int i = 0; i < x; i++) {
            data[i] = x + 1;
            System.out.println(data[i]);
        }

        int y = 0;
        while(y < x) {
            data[y]--;
            y++;
        }

    }
}
