package com.map.service.test;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.map.service.MapService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MapServiceTests {

	@Autowired
	private MapService mapservice;
	
	@Test
	public void loadFileTest()
	{
		HashMap<String,HashSet<String>> hm = mapservice.loadFile();
		Assert.assertNotNull(hm);
		Assert.assertEquals(hm.containsKey("Boston"), true);
		HashSet<String> destinations = hm.get("Boston");		
		Assert.assertEquals(destinations.contains("New York"), true);
		
	}
	
	@Test
	public void testIsconnected()
	{
		Assert.assertEquals(mapservice.isConnected("Philadelphia", "Boston"), true);
		Assert.assertEquals(mapservice.isConnected("Philadelphia", "Dallas"), false);
	}
	
	
	

}
