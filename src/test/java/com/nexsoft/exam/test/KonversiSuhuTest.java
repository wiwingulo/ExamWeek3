package com.nexsoft.exam.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam.KonversiSuhu;


public class KonversiSuhuTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/data_suhu.csv",delimiter = ',',numLinesToSkip = 1 )
	public void konversi_suhu(int nomor, float fahrenheit, float expected) {
		//arrange
		KonversiSuhu konversiSuhu = new KonversiSuhu();
		//act
		float result = konversiSuhu.konversi(fahrenheit);
		//assert
		assertEquals(expected,result);
	}
	
	@ParameterizedTest
	@DisplayName("Bukan angka")
	@CsvSource("Test bukan angka")
//	@CsvFileSource(resources = "/data_keliling.csv", delimiter = ',', numLinesToSkip = 1)
	public void testKelilingKolam_BukanAngka(String str) {
		KonversiSuhu konversiSuhu = new KonversiSuhu();
		assertThrows(IllegalArgumentException.class, ()-> {
			konversiSuhu.konversi(Float.parseFloat(str));
		});
	}
	@DisplayName("Test Absolut Positif")
    @ParameterizedTest
    @CsvSource(value = {"-1;-23;-5",  "-2;-32;0","-3;-65;18.33333"},delimiter = ';')
    public void testKelilingAbsolut(int nomor, float fahrenheit, float expected) {
        KonversiSuhu konversiSuhu = new KonversiSuhu();
        float result = konversiSuhu.konversi(fahrenheit);
        assertEquals(expected, result);

    }
	@DisplayName("Tidak dimasukkan angka")
	@ParameterizedTest
    @CsvSource(value = {",,,0",",,,0",",,,0"},delimiter = ';')
	
	public void tidak_dimasukkan_angka(String panjang) {
		
		KonversiSuhu konversiSuhu = new KonversiSuhu();
     
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
        double result = konversiSuhu.konversi(arrResult[0]);
		assertEquals(arrResult[3], result);
    }
	
}
