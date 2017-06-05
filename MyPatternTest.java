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
			a1(a0);
		}
	}
	void P11(String Partial){
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
	void P12(String Exhaustive){
		{
			ignore();
			int a0 = 10;
			ignore();
			for(int i = 0;i<a0;i++){
				a1(a0);
			}
			ignore();
		}
	}
	void P13(String Parcial){
		{
			for(int i = 0;i<a0;i++){
				a1(a0);
			}
		}
	}

	void P14(String Exhaustive){
		{
			ignore();
			int a0 = a1;
			while(a0 < a2) {
				data[a0]--;
				a0++;
			}
			ignore();
		}
	}


	void P15(String Exhaustive){
		{
			ignore();
			int a0 = 10;
			int a1[] = new int[a2];
			ignore();
			for(int i = 0; i < a0; i++) {
				a1[i] = a0 + 1;
				System.out.println(a1[i]);
			}
			ignore();
		}
	}
	
	void P16(String Partial){
		{
			int a0 = a1;
			while(a0 < a2) {
				data[a0]--;
				a0++;
			}
		}
	}


	void P17(String Partial){
		{
			int a0 = 10;
			int a1[] = new int[a2];
			for(int i = 0; i < a0; i++) {
				a1[i] = a0 + 1;
				System.out.println(a1[i]);
			}
		}
	}
	
	void P18(String Exhaustive){
		while(a0 < a1) {
			a2--;
			a0++;
		}
	}
}
