package com.nexsoft.exam.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam.KelilingKolam;

public class KelilingKolamTest {
	
	@DisplayName("Keliling Kolam")
	@ParameterizedTest
	@CsvFileSource(resources = "/data_keliling.csv",delimiter = ',',numLinesToSkip = 1 )
	public void keliling_kolam(int nomor, double panjang, double lebar, double tinggi, double expected) {
		//arrange
		KelilingKolam kelilingKolam = new KelilingKolam();
		//act
		double result = kelilingKolam.keliling(panjang,lebar,tinggi);
		//assert
		assertEquals(expected,result);
	}
	
	@DisplayName("Tidak dimasukkan angka")
	@ParameterizedTest
    @CsvSource(value = {",,,0",",,,0",",,,0"},delimiter = ';')
	
	public void tidak_dimasukkan_angka(String panjang) {
		
		KelilingKolam kelilingKolam = new KelilingKolam();
     
        String arrInt[] = panjang.split(",");
        int data = arrInt.length;
        int arrResult[] = new int[data];
           
        for (int i = 0; i < data; i++) {
        	if (arrInt[i].isEmpty())
        	{
        		arrResult[i] = 0;
        	}
        	else {
        		arrResult[i] = Integer.parseInt(arrInt[i]);
        	}
        }
        double result = kelilingKolam.keliling(arrResult[0], arrResult[1], arrResult[2]);
		assertEquals(arrResult[3], result);
    }
	
	@ParameterizedTest
	@DisplayName("Bukan angka")
	@CsvSource("Test bukan angka")
//	@CsvFileSource(resources = "/data_keliling.csv", delimiter = ',', numLinesToSkip = 1)
	public void testKelilingKolam_BukanAngka(String str) {
		KelilingKolam kelilingKolam = new KelilingKolam();
		assertThrows(IllegalArgumentException.class, ()-> {
			kelilingKolam.keliling(3, 5, Double.parseDouble(str));
		});
	}
	
	@DisplayName("Test Absolut Positif")
    @ParameterizedTest
    @CsvSource(value = {"-8;-5;-1;56",  "-7;-12;-1;80","-5;-4;-1;40"},delimiter = ';')
    public void testKelilingAbsolut(double panjang, double lebar, double tinggi, double expected) {
        KelilingKolam kelilingKolam = new KelilingKolam();
        double result = kelilingKolam.keliling(panjang, lebar, tinggi);
        assertEquals(expected, result);

    }
}
