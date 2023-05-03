package paquete1;

import java.util.ArrayList;
import java.util.Collections;

public class Truco {
	
	public static void main(String[] args) {
		int[] mano=barajar();
		int[] stats= {0,0,0};
		int intentos=1000000,mayorQue=22;
		for(int i=0;i<intentos;i++) {
			if(contarTantos(mano[0],mano[1],mano[2],stats)>mayorQue) stats[2]++;
			mano=barajar();
		}
		System.out.println("La probabilidad de flor es: "+(double)stats[0]/(intentos/100)+"%");
		System.out.println("La probabilidad de no sumar es: "+(double)stats[1]/(intentos/100)+"%");
		System.out.println("La probabilidad de tener mas que "+mayorQue +" es: "+(double)stats[2]/(intentos/100)+"%");
		
	}
	static int valorTantos(int a) {
		if(a/10<8) return a/10;
		else return 0;
	}
	static int contarTantos(int a,int b,int c,int[]stats){
		if(a==b||b==c||a==c) {System.out.println("Cartas inválidas");return -1;}
		ArrayList<Integer> arr=new ArrayList<>();
		arr.add(valorTantos(a));
		arr.add(valorTantos(b));
		arr.add(valorTantos(c));
		arr.sort(null);
		if(a%10==b%10 &&b%10==c%10) {stats[0]++;return arr.get(1) + arr.get(2)+20;} 
		if(a%10==b%10) return 20+valorTantos(a)+valorTantos(b);
		if(a%10==c%10) return 20+valorTantos(a)+valorTantos(c);
		if(c%10==b%10) return 20+valorTantos(c)+valorTantos(b);
		stats[1]++;
		return arr.get(2);
	}
	static void mostrarPrueba(int a,int b,int c,int[]stats) {
		//System.out.println("Las cartas son: \nEl "+a/10 +" de "+palo(a%10)+",\nEl "+b/10+" de "+palo(b%10)+",\nY el "+c/10+" de "+palo(c%10));
		//System.out.println("Los tantos son "+
		contarTantos(a,b,c,stats);
	}
	static String palo(int a) {
		switch(a) {
		case 1: return "Espada";
		case 2: return "Basto";
		case 3: return "Copa";
		case 4: return "Oro";
		}
		return "0";
	}
	static int[] barajar (){
		int[] mazo= {11,21,31,41,51,61,71,101,111,121,12,22,32,42,52,62,72,102,112,122,13,23,33,43,53,63,73,103,113,123,14,24,34,44,54,64,74,104,114,124};
		int[] barajaAux= {-1,-1,-1};
		int[] baraja= {-1,-1,-1};
		for(int i=0;i<3;i++) {
			int rand=(int) Math.floor(Math.random()*40);
			while(barajaAux[0]==rand||barajaAux[1]==rand) {
				rand=(int) Math.floor(Math.random()*40);
			}
			barajaAux[i]=rand;
		}
		for(int i=0;i<3;i++) baraja[i]=mazo[barajaAux[i]];
		return baraja;
	}
}
