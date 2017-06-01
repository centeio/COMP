public class MyPattern{
    void P1(){
	if(a0 == a1) {
            a0--; a0++;
        }
    }
    void P2(){
	for(int i = 0; i < a0; i++) {
            a1[i] = a0 + 1;
            ignore();
        }
    }
    void P3(){
	while(a0 < a1) {
            a2[a0]--;
            a0++;
        }
    }
    void P4(){
	while(a0 < a1) {
            ignore();
        }
    }
    void P5(){
	a0+a0;
    }
}
