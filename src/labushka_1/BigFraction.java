package labushka_1;
import java.util.Scanner;


/**
* 
* @author Suchok
* @version 1.0

*/

public class BigFraction {

	public int[] temp_mass1 = new int[100]; //массив первого операнда
	public int[] temp_mass2 = new int[100]; //массив второго операнда
	public int[] temp_mass3 = new int[100];  //массив результата
	public int temp1=0;
	public int temp2=0;
	public int temp=0;
	
	
	
	BigFraction (int fir,int sec){
		
	
	
		for(int i = 0; i < 100; i++){  //обнуление массивов дл€ + -
			temp_mass1[i]=0;
			temp_mass2[i]=0;
			temp_mass3[i]=0;
		}
	
		String str1 = Integer.toString(fir);  //перевод введенных чисел в строку
		String str2 = Integer.toString(sec);
		char[] charArray1 = str1.toCharArray(); //перевод строк в массив char
		char[] charArray2 = str2.toCharArray();
		
		//перевод массива charArray1 в int
		temp1=charArray1.length;
		temp=temp1-1;
		for(int i = 0; i < charArray1.length; i++){
		    temp_mass1[temp]=Character.getNumericValue(charArray1[i]);
			temp=temp-1;
			
		}
		
		//перевод массива charArray2 в int
		temp2=charArray2.length;
		temp=temp2-1;
		for(int i = 0; i < charArray2.length; i++){
			temp_mass2[temp]=Character.getNumericValue(charArray2[i]);
			temp=temp-1;
			
		}
		temp=0;
	}
	
	
	
	
	
	public  void Summ() {
		
		/**
		* @param temp_mass1 
		* @param temp_mass2 
		* @return temp_mass3 –езультат
	*/
			
		if (temp2>temp1) temp1=temp2;
         	    for(int i = 0; i <= temp1; i++){  // сложение
		    	temp_mass3[i]=(temp_mass1[i]+temp_mass2[i]+temp)%10;
		    	temp=(temp_mass1[i]+temp_mass2[i]+temp)/10;  // единица переноса
				       	}
            	
	          }
	
	
	
	
	
	public  void Subb() {
		
		/**
		* @param temp_mass1 
		* @param temp_mass2 
		* @return temp_mass3 –езультат
	*/
		
		temp=0;
		if (temp2>temp1) temp1=temp2;
		
	    for(int i = 0; i < temp1; i++){   //вычитание
		if ((temp_mass1[i]-temp)>=temp_mass2[i]){ // вычитание обычное
			temp_mass3[i]=temp_mass1[i]-temp-temp_mass2[i]; 
			temp=0;
		}
		else{ // вычитание с заимствованием
			temp_mass3[i]=temp_mass1[i]-temp+10-temp_mass2[i];
			temp=1;   // зан€та дес€тка у следующего разр€да
		}
		
				}
          }
	


	
	
	public void Multt(){
	
		/**
		* @param temp_mass1 
		* @param temp_mass2 
		* @return temp_mass3 –езультат
	*/
		
		int templ=0; //переменна€ дл€ сдвига нового числа
		//накопление результатов поразр€дного умножени€.перва€ часть умножени€
		
		int[][] temp_mass4 = new int[100][100]; // массив дл€ умножени€
		
		for(int ii = 0; ii < 100; ii++){  // обнуление массива *
			for(int j = 0; j < 100; j++){
				 temp_mass4[ii][j]=0;
			}
		}
		for(int i = 0; i <= temp2; i++){
			temp=0;
			for(int j = 0; j <= temp1; j++){  //поразр€дное умножение
				temp_mass4[i][j+templ]=(temp_mass2[i]*temp_mass1[j]+temp)%10;	
				temp=(temp_mass2[i]*temp_mass1[j]+temp)/10;
			}
			templ=templ+1;  
		}   
 		
		// второй этап умножени€ - сложение строк
		if (temp2>temp1) temp1=temp2;
		
		for(int j = 0; j <=templ; j=j+1){
			temp=0;
			 //сложение
			for(int i = 0; i <= temp1+templ; i++){
			temp_mass3[i]=(temp_mass4[j][i+j]+temp_mass4[j+1][i]+temp)%10; 
    		temp=(temp_mass4[j][i+j]+temp_mass4[j+1][i]+temp)/10;
		       	}
			//перезапись промежуточного результата
				for(int k = 0; k < temp1+templ; k++){
					temp_mass4[j+1][k+j+1]=temp_mass3[k]; 
			}
		}
    	     
	}
	
	

	
	
 public  void Showout(int[] mas) { // выдача результата 
		
		/**
		* @param mas ѕромежуточный массив
		* @return mas ќбновленное значение массива
	*/
	 
		for(int i = 0; i < 100; i++){
			mas[i]=temp_mass3[i];
					}
	}

	
	
	
	
	public static void main(String[] args) {
		
		/**
		* @param first ѕервыйд операнд
		* @param second ¬торой операнд
		* @return mas —трокова€ переменна€ с ответом
		*/
		
		int[] temps = new int[100];
		BigFraction BF;
		String mas="";
		boolean znak=false;
		boolean exitt=false;
		boolean numbers=false;
		while(!exitt){
			numbers=false;
			znak=false;
			mas="";
		while (!numbers){
		try {
		Scanner s = new Scanner(System.in);
		System.out.println("ѕервый операнд");
		int first = s.nextInt(); 
		System.out.println("¬торой операнд");
		int second = s.nextInt(); 
		System.out.println("¬ыберите операцию +:1  -:2  /:3  *:4  Exit:5");
		int select=s.nextInt();
				

		switch (select){ // выбор пункта меню
		case 1:
			//выставление знака операции сложени€
			znak = Logic1(znak, first, second, select);
			//вычитание из большего
			if (Math.abs(first)<Math.abs(second)) {
				first=first+second;
				second=first-second;
				first=first-second;
				}			
			BF=new BigFraction(Math.abs(first),Math.abs(second));
			
			//проверка знаков операндов при сложении
			if (((first>0)&(second<0))|((first<0)&(second>0))){
				BF.Subb();
			}
			else BF.Summ();
			BF.Showout(temps);
			BF = null;
			mas = Pr_znak(temps, mas, znak);
					System.out.println(mas); 
			break;
		case 2:
			//выставление знака операции вычитани€
			znak = Logic2(znak, first, second);
			//вычитание из большего
			if (Math.abs(first)<Math.abs(second)) {
				first=first+second;
				second=first-second;
				first=first-second;
				}
			
			BF=new BigFraction(Math.abs(first),Math.abs(second));
			
			//проверка знаков операндов при вычитании
			if (((first<0)&(second>0))|((first>0)&(second<0))){
				BF.Summ();
			}
			else BF.Subb();			
			BF.Showout(temps);
			BF = null;
			mas = Pr_znak(temps, mas, znak);
					System.out.println(mas); 
			break;
		case 3:
			//выставление знака результата
			if ((first>0)&(second<0)|(first<0)&(second>0)) znak=true;
			
			BF=new BigFraction(Math.abs(first),Math.abs(second));
			BF.Multt();
			BF.Showout(temps);
			BF = null;
			mas = Pr_znak(temps, mas, znak);
					System.out.println(mas); 
			break;
		case 4:
			//выставление знака результата
			if ((first>0)&(second<0)|(first<0)&(second>0)) znak=true;
			
			BF=new BigFraction(Math.abs(first),Math.abs(second));
			BF.Showout(temps);
			BF = null;
			mas = Pr_znak(temps, mas, znak);
					System.out.println(mas); 
			break;
		case 5:
			exitt=true;
			s.close();
			break;
		default: 
			System.out.println("не была введена операци€");
		    break;
		}
		
		numbers=true;
		}
		
		catch (Exception e) {
		System.out.println("ошибка ввода!");
		numbers=false;
		}
		}
		}
	}





	private static String Pr_znak(int[] temps, String mas, boolean znak) {
		int ii;
		boolean bool;
		ii=99;
		bool=false;
		while (ii!=-1){
			if ((!bool)&(temps[ii]!=0)) {
				bool=true;
				if (znak==true) mas=mas+"-";
			}
			if ((bool)){
			mas=mas+Integer.toString(temps[ii]);
			}
			ii--;
		}
		return mas;
	}





	private static boolean Logic1(boolean znak, int first, int second, int select) {
		if ((select==1)&
				(
						(first<0)&(second<0))|
				((first<0)&(Math.abs(first)>second)&(second>0))|
				((first>0)&(first<Math.abs(second))&(second<0))){
			znak=true;
		}
		return znak;
	}





	private static boolean Logic2(boolean znak, int first, int second) {
		boolean bool1 = (first<0)&(second>0)&(Math.abs(first)>second);
		boolean bool2 = (first<0)&(second<0)&(first<second);
		boolean bool3 = (first<0)&(second>0)&(Math.abs(first)<second);
		boolean bool4 = (first>0)&(second>0)&(first<second);
		
		if (bool1|bool2|bool3|bool4){
			znak=true;
		}
		return znak;
	}
}



