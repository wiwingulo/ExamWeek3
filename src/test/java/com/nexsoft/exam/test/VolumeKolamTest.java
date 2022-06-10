package com.nexsoft.exam.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.nexsoft.exam.VolumeKolam;

public class VolumeKolamTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/data_volume.csv",delimiter = ',',numLinesToSkip = 1 )
	public void volume_kolam(int nomor, double panjang, double lebar, double tinggi, double expected) {
		//arrange
		VolumeKolam volumeKolam = new VolumeKolam();
		//act
		double result = volumeKolam.volume(panjang,lebar,tinggi);
		//assert
		assertEquals(expected,result);
	}
	
	@DisplayName("Tidak dimasukkan angka")
	@ParameterizedTest
    @CsvSource(value = {",,,0",",,,0",",,,0"},delimiter = ';')
	
	public void tidak_dimasukkan_angka(String panjang) {
		
		VolumeKolam volumeKolam = new VolumeKolam();
     
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
        double result = volumeKolam.volume(arrResult[0], arrResult[1], arrResult[2]);
		assertEquals(arrResult[3], result);
    }
	
	@ParameterizedTest
	@DisplayName("Bukan angka")
	@CsvSource("Test bukan angka")
//	@CsvFileSource(resources = "/data_keliling.csv", delimiter = ',', numLinesToSkip = 1)
	public void testKelilingKolam_BukanAngka(String str) {
		VolumeKolam volumeKolam = new VolumeKolam();
		assertThrows(IllegalArgumentException.class, ()-> {
			volumeKolam.volume(3, 5, Double.parseDouble(str));
		});
	}
	
	@DisplayName("Test Absolut Positif")
    @ParameterizedTest
    @CsvSource(value = {"-6;-18;-1;108",  "-4;-16;-3;192","-3;-14;-1;42"},delimiter = ';')
    public void testVolumeAbsolut(double panjang, double lebar, double tinggi, double expected) {
        VolumeKolam volumeKolam = new VolumeKolam();
        double result = volumeKolam.volume(panjang, lebar, tinggi);
        assertEquals(expected, result);

    }
}
