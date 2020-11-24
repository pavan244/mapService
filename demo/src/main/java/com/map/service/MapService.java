package com.map.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.map.exceptions.TextFormatException;

@Component
public class MapService {

	@Value("${filepath}")
	public String filePath;
	
	private HashMap<String,HashSet<String>> hm;
	
	public HashMap<String,HashSet<String>> loadFile()
	{
		hm = new HashMap<String,HashSet<String>>();
		BufferedReader reader;
		try {
			System.out.println(filePath);
			Resource resource =  new ClassPathResource(filePath);   
			InputStream input = resource.getInputStream();
			File file = resource.getFile();			
			reader = new BufferedReader(new FileReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				try
				{
					if(!line.contains(","))
					{
						throw new TextFormatException();
					}
					String arr[] = line.split(",");	
					if(arr.length!=2)
					{
						throw new TextFormatException();
					}
					if(!hm.containsKey(arr[0]))
					{
						hm.put(arr[0], new HashSet());
					}
					hm.getOrDefault(arr[0], new HashSet()).add(arr[1]);
				}
				catch(TextFormatException e)
				{
				     e.printException(line);
				}
				
				line = reader.readLine();
			}
			reader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return hm;
		
	}
	
	public boolean isConnected(String source,String dest)
	{
		if(hm==null)
		{
			hm = loadFile();
		}
		if(!hm.containsKey(source))
		{
			return false;
		}
		Queue<String> q = new LinkedList();
		q.add(source);
		HashSet<String> visited = new HashSet();
		while(!q.isEmpty())
		{
			String src = q.poll();
			visited.add(src);
			HashSet<String> destinations  = hm.getOrDefault(src, null);
			if(destinations == null)
			{
				return false;
			}
			if(destinations.contains(dest))
			{
				return true;
			}
			for(String s:destinations)
			{
				if(!visited.contains(s))
				{
					q.add(s);
				}				
			}			
		}
		
		
		return false;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapService ms = new MapService();
		ms.loadFile();
	}

}
