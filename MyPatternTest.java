public class MyPattern{
	void P1(String Exhaustive){
		if(a0 == a1) {
			a0--; a0++;
		}
	}
	void P2(String Exhaustive){
		for(int i = 0; i < a0; i++) {
			a1[i] = a0 + 1;
			ignore();
		}
	}
	void P3(String Exhaustive){
		while(a0 < a1) {
			a2[a0]--;
			a0++;
		}
	}
	void P4(String Exhaustive){
		while(a0 < a1) {
			ignore();
		}
	}
	void P5(String Exhaustive){
		a0+a0;
	}
	void P6(String Exhaustive){
		if(a0 == a1) {
			a1--; a0++;
		}
	}
	void P7(String Exhaustive){
		if(a1) {
			a0--; a0++;
		}
	}
	void P8(String Exhaustive){
		do {
			ignore();
		} while (a0);
	}
	void P9(String Exhaustive){
		do {
			ignore();
		} while (a0 < a1);
	}
	void P10(String Exhaustive){
		for(int i = 0; i < a0; i++) {
			f1(a0);
		}
	}
	void P11(String Parcial){
		{
			if(a0 == a1) {
				a0--; a0++;
			}

			for(int i = 0; i < a0; i++) {
				a2[i] = a0 + 1;
				ignore();
			}
		}
	}
}
