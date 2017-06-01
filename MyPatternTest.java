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
    void P6(){
	if(a0 == a1) {
            a1--; a0++;
        }
    }
    void P7(){
	if(a1) {
            a0--; a0++;
        }
    }
    void P8(){
	do {
     	    ignore();
	} while (a0);
    }
    void P9(){
	do {
     	    ignore();
	} while (a0 < a1);
    }
    void P10(){
	for(int i = 0; i < a0; i++) {
            f1(a0);
        }
    }
}
