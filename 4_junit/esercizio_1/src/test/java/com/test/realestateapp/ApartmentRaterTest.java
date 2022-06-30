package com.test.realestateapp;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import com.realestateapp.Apartment;
import com.realestateapp.ApartmentRater;



class ApartmentRaterTest {
	

	@Test
	void rateApartment() {
		
//		BigDecimal CHEAP_THRESHOLD = new BigDecimal(6000.0);
		
//		fail("Not yet implemented");
		double area = 45.86;
		BigDecimal price = new BigDecimal(350000);
		Apartment a = new Apartment(area, price);
		
		ApartmentRater aR = new ApartmentRater(); 
		
		int t = aR.rateApartment(a);
//		BigDecimal ratio = a.getPrice().divide(new BigDecimal(a.getArea()), RoundingMode.HALF_UP);
//		System.out.println(ratio);
//		System.out.println(ratio.compareTo(CHEAP_THRESHOLD));
		assertEquals("rateApartment success", 1, t);
//		assertEquals("rateApartment fail 0", 0, t);
//		assertEquals("rateApartment fail 2", 2, t);
	}

}
