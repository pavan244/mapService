package com.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.map.service.MapService;


@RestController
@RequestMapping("/connected")
public class MapController {
	
	@Autowired
	private MapService mapservice;
	
	@GetMapping
	public String isConnected(@RequestParam String origin, @RequestParam String destination)
	{
		if(mapservice.isConnected(origin, destination))
		{
			return "yes";
		}
		return "no";
	}
	
	
	
	

}
