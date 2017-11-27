import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Wykreslanka {
	public static void main(String[] args)throws FileNotFoundException
	{
		System.out.println("Tworzê wykreœlanki");
	/*	System.out.println("Podaj nazwê pliku");
	Scanner wejscie = new Scanner(System.in);
	String nazwa = wejscie.nextLine();*/
	File plik = new File("wyrazy.txt");
	Scanner odczyt = new Scanner(plik);
	int i=0;
	String jeden_wyraz;
	int[] wyrazy_rozmiar=new int[11];
	List<String> wyrazy = new ArrayList<String>();
	PrintWriter zapis = new PrintWriter("wyrazy_wynik.txt");
	while (odczyt.hasNextLine()){
		jeden_wyraz=odczyt.nextLine();
		wyrazy_rozmiar[i]=jeden_wyraz.length();
		wyrazy.add(jeden_wyraz);
		System.out.println((String)wyrazy.get(i));
		zapis.println((String)wyrazy.get(i));
		i++;
	}//while
	//Sortowanie wyrazów:
	int min,j;
	for(i=1;i<11;i++){
		min=i;
		for(j=min;j<11;j++){
			if(wyrazy_rozmiar[j]>wyrazy_rozmiar[min])
				min=j;
		}
		int temp;

		temp=wyrazy_rozmiar[min];
		wyrazy_rozmiar[min]=wyrazy_rozmiar[i];
		wyrazy_rozmiar[i]=temp;
		String stemp;
		stemp=wyrazy.get(min);
		wyrazy.set(min, wyrazy.get(i));
		wyrazy.set(i, stemp);
	}
	/*sprawdzenie czy dzia³a i dzia³a
	for(i=1;i<11;i++){
		System.out.println(wyrazy_rozmiar[i]+" ");
		System.out.println((String)wyrazy.get(i));
	}
	*/
	/*Rozmieszczamy 4 pierwsze wyrazy na brzegach ramki (1,2,3,4).
	  Bierzemy najd³u¿szy i sprawdzamy czy ma wspóln¹ pierwsza literê
	  Jeœli nie ma rozmiar ramki = rozmiar najd³u¿szego +1.
	  Potem sprawdzamy czy ostatni wyraz najd³u¿szego jest wspólny dla innych.
	  Jeœli nie to rozmiar ramki++;
	  */
	//Bierzemy pierwsze 4 wyrazy;
	char[][] znaki = new char [11][];
	boolean[] czy_uzyty=new boolean[11];
	
	for(i=0;i<11;i++){
	znaki[i]=wyrazy.get(i).toCharArray();
	czy_uzyty[i]=false;
	}
	czy_uzyty[1]=true;
	//Szukamy wspólny liter z wyrazami 2, 3 i 4
	int dl_poziom=wyrazy.get(1).length();
	int dl_pion=wyrazy.get(2).length();
	int nr=0; // numer wyrazu pasuj¹cego do 1
    for(i=2;i<=4;i++){
    	if(znaki[1][0]==znaki[i][0]){
    		nr=i;
    		break;
    	}
    }
    if (nr==0) {
    	dl_poziom++; 
    	dl_pion++;
    }
    dl_poziom++;
    dl_pion++;
    char[][] wykreslanka = new char[dl_pion][dl_poziom];
    //wype³niamy spacjami
    for(i=0;i<dl_pion;i++){
    	for(j=0;j<dl_poziom;j++){
    		wykreslanka[i][j]=' ';
    	}
    }
    //wpisujemy wyraz 1
    if (nr==0) {
    	for(j=1;j<dl_poziom-1;j++){
    		wykreslanka[0][j]=znaki[1][j-1];
    }
    }else{
    	for(j=0;j<dl_poziom;j++){
    		wykreslanka[0][j]=znaki[1][j];
    }
    }
    czy_uzyty[1]=true;
    //wpisujemy wyraz 2
    if(nr==0){
    	nr=2;
    	for(j=1;j<dl_pion-1;j++){
    		wykreslanka[j][0]=znaki[nr][j-1];
    	}
    	czy_uzyty[2]=true;
    }else{
    	for(j=0;j<dl_pion;j++){
    		wykreslanka[j][0]=znaki[nr][j];
    	}
    	czy_uzyty[nr]=true;
    }
    for(i=2;i<11;i++){
    	if(czy_uzyty[i]==false)
    		break;
    }
    //mo¿na u¿yæ i wpisujemy trzeci wyraz
 if(znaki[i][0]==znaki[1][znaki[1].length-1]){
	 for(j=0;j<znaki[i].length;j++){
		 wykreslanka[j][dl_poziom-1]=znaki[i][j];
	 }
	 czy_uzyty[i]=true;
 }else{
	 for(j=0;j<znaki[i].length;j++){
		 wykreslanka[j][dl_poziom-1]=znaki[i][j];
	 }
	 czy_uzyty[i]=true;
 }
 for(i=2;i<11;i++){
 	if(czy_uzyty[i]==false)
 		break;
 }
// Wpisujemy czwarty wyraz
for(j=0;j<znaki[i].length;j++){
	wykreslanka[dl_poziom-1][j]=znaki[i][j];
}
czy_uzyty[i]=true;   

//a teraz naprzemiennie wpisujemy pozosta³e wyrazy
int m=1,n=dl_poziom-2;
for(i=5;i<11;i++){
	if((i%2)==0){
		for(j=0;j<znaki[i].length;j++){
			wykreslanka[m][j+1]=znaki[i][j];			
		}
		m++;
	}else{
		for(j=0;j<znaki[i].length;j++){
			wykreslanka[m][n-j]=znaki[i][j];
		}
			n--;
			m++;
	 }
	czy_uzyty[i]=true;
}
//losowanie przypadkowych znaków

Random znak = new Random();
for(i=0;i<dl_pion;i++){
	for(j=0;j<dl_poziom;j++){
		if(wykreslanka[i][j]==' '){
			int x=znak.nextInt(25)+97;
			wykreslanka[i][j]=(char)x;
		}			
	}
}

   //zawartoœc tablicy wykreslanki
    for(i=0;i<dl_pion;i++){
    	for(j=0;j<dl_poziom;j++){
    		System.out.print(wykreslanka[i][j]+" ");
    		zapis.print(wykreslanka[i][j]+" ");

    	}
	    zapis.println();
    	System.out.println();
    }


	odczyt.close();
	zapis.close();
    }
}