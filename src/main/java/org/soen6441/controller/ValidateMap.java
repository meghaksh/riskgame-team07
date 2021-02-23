package org.soen6441.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.soen6441.model.Continent;
import org.soen6441.model.Country;

	public class ValidateMap {
	int d_vertexCount;
	ArrayList<ArrayList<Integer>> d_vertexList;
	ValidateMap(int p_vertexCount){
		d_vertexCount = p_vertexCount;
		d_vertexList = new ArrayList<>(d_vertexCount);
		for(int i=0;i<d_vertexCount;i++) {
			d_vertexList.add(new ArrayList<Integer>());
		}
	}
	public boolean checkCountryAndContinent(ArrayList<Country> p_CountryObjects,ArrayList<Continent> p_ContinentObjects) {
		for(Continent l_C1:p_ContinentObjects)
		{
			if(l_C1.getCountryList().size()<1)
			{
				return false;
			}
		}
		return true;
	}
	public HashMap<Integer,ArrayList<Integer>>  updateCount(ArrayList<Country> p_CountryObjects ){
		//checking if every continent has a country and vice versa
		int l_Sequence=0,l_ID;
		ArrayList<String>d_UpdatedNeighbors=new ArrayList<String>();
		ArrayList<Country> d_NCountryObjects=p_CountryObjects;
		HashMap<Integer,Integer> d_UpdatedIDCount=new HashMap<Integer,Integer>();
		HashMap<Integer,ArrayList<Integer>> d_UpdatedMap=new HashMap<Integer,ArrayList<Integer>>();
		for(Country l_C: d_NCountryObjects)
		{
			l_Sequence++;
			d_UpdatedIDCount.put(l_C.getCountryID(),l_Sequence);
		}
		for(Country l_C: d_NCountryObjects)
		{
			ArrayList<Integer>d_StoreNeighbors=new ArrayList<Integer>();
			l_ID=d_UpdatedIDCount.get(l_C.getCountryID());
			d_UpdatedNeighbors=l_C.getBorder();
			for(String l_S: d_UpdatedNeighbors)
			{
				for(Country l_C2: d_NCountryObjects)
				{
					if(l_C2.getCountryName().equals(l_S))
					{
						int l_NewNeighborID=d_UpdatedIDCount.get(l_C2.getCountryID());
						d_StoreNeighbors.add(l_NewNeighborID);	
					}
				}
			}
			d_UpdatedMap.put(l_ID,d_StoreNeighbors);
		}	
		return d_UpdatedMap;	
	}
	public ValidateMap(ArrayList<Country> p_CountryObjects,ArrayList<Continent> d_ContinentObjects)throws Exception{
		if(checkCountryAndContinent(p_CountryObjects,d_ContinentObjects))
		{
		HashMap<Integer,ArrayList<Integer>> p_HMap;
		p_HMap=updateCount(p_CountryObjects);
		System.out.println("printing hashmap"+p_HMap);
		d_vertexCount = p_HMap.size();
		d_vertexList = new ArrayList<>(d_vertexCount);
		for(int i=0;i<d_vertexCount;i++) {
			d_vertexList.add(new ArrayList<Integer>());
		}
		assignBorders(p_HMap);
	}
		else
		{
			throw new Exception("There should be atleast one country for a continent");
			//System.out.println("There should be atleast one country for a continent");
		}
	}
	public void assignBorders(HashMap<Integer, ArrayList<Integer>> p_HMap) {
		System.out.println("Printing Map " + p_HMap);
		try {
			for(int i=1;i<p_HMap.size()+1;i++) {
				//System.out.print("Vertex Value" + i +  " :::");
				//System.out.println(p_HMap.get(i));
				if(p_HMap.get(i)!=null) {
					ArrayList<Integer> l_TempBorderList = p_HMap.get(i);
					for(Integer l_TempBorderId:l_TempBorderList) {
						addBorder(i-1, l_TempBorderId-1);
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Exception while assigning borders : " + e.getMessage());
		}
		
		//System.out.println("Borders added");
		//printGraph();
		
	}
	public void printGraph() {
		for(int i=0;i<d_vertexList.size();i++) {
			System.out.print(i +" -----> " );
			ArrayList<Integer> newlist = d_vertexList.get(i);
			for(int j=0;j<newlist.size();j++) {
				System.out.print(newlist.get(j) + " ::: ");
			}
			System.out.println();
		}
	}
	public String isValid() {
		boolean b1 = runDFS(0);
		ValidateMap l_tempMap = getTranspose(this.d_vertexList);
		boolean b2 = l_tempMap.runDFS(0);
		if(b1 && b2) {
			return "Map is Valid";
		}
		return "Map is not Valid";
	}
	public void showMap() {
		for(int i=0;i<d_vertexList.size();i++) {
			System.out.print("Node " + (i+1) + " : ");
			for(int j : d_vertexList.get(i)) {
				System.out.print(j+1 + " ");
			}
			System.out.println();
		}
	}
	public void addBorder(int u, int v) {
		d_vertexList.get(u).add(v);
	}
	public boolean runDFS(int p_start) {
		boolean[] l_nodeVisited = new boolean[d_vertexCount];
		System.out.print("DFS of Graph : ");
		markVisited(p_start, l_nodeVisited);
		System.out.println();
		for(boolean b: l_nodeVisited) {
			if(!b)
				return false;
		}
		return true;
	}
	private void markVisited(int p_start, boolean[] p_nodeVisited) {
		p_nodeVisited[p_start] = true;
		System.out.print(p_start + " ");
		for(int i: d_vertexList.get(p_start)) {
			if(!p_nodeVisited[i]){
				markVisited(i, p_nodeVisited);
			}
		}
	}
	public ValidateMap getTranspose(ArrayList<ArrayList<Integer>> p_vertexList) {
		ValidateMap l_tempMap = new ValidateMap(p_vertexList.size());
		for(int i=0;i<p_vertexList.size();i++) {
			for(int j : p_vertexList.get(i)) {
				l_tempMap.addBorder(j, i);
			}
		}
		return l_tempMap;
	}
}

















/**
 * Class which takes map input and validate it.
 * Validation logic: Perform DFS on graph, Take Transpose of graph, Again perform DFS on graph*/
  class D {
	static Scanner scan;
	public static void main(String[] args) {
		ValidateMap d_dmap = openFile();
		closeFile();
		/*Not strongly connected graph example*/
//				d_dmap.addBorder(0, 1);
//				d_dmap.addBorder(1, 2);
//				d_dmap.addBorder(2, 3);
		
		/*strongly connected graph example*/
				d_dmap.addBorder(0, 4);
				d_dmap.addBorder(1, 0);
				d_dmap.addBorder(1, 2);
				d_dmap.addBorder(2, 1);
				d_dmap.addBorder(2, 4);
				d_dmap.addBorder(3, 1);
				d_dmap.addBorder(3, 2);
				d_dmap.addBorder(4, 3);
		d_dmap.showMap();
		boolean l_isValidMap = d_dmap.runDFS(1);
		ValidateMap l_tempMap = d_dmap.getTranspose(d_dmap.d_vertexList);
		//l_tempMap.showMap();
		boolean l_isValidTransposeMap = l_tempMap.runDFS(1);
		if(l_isValidMap && l_isValidTransposeMap) {
			System.out.println("Strongly Connected Graph");
		}else {
			System.out.println("Not A Strongly Connected Graph");
		}
	}
	public static ValidateMap openFile() {
		ValidateMap l_dmap = new ValidateMap(180);
		try {
			scan = new Scanner(new File("src\\main\\java\\org\\soen6441\\controller\\borders.txt"));
			l_dmap = readFile(l_dmap);	
		}catch(Exception e) {
			System.out.println("Exception while opening the file" + e);
		}
		return l_dmap;
	}
	public static ValidateMap readFile(ValidateMap p_dmap) {
		int l_counter=0;
		while(scan.hasNextLine()) {
			String[] l_tempArray = scan.nextLine().split(" ");
			for(int i=1;i<l_tempArray.length;i++) {
				p_dmap.addBorder(l_counter, Integer.parseInt(l_tempArray[i])-1);
			}
			l_counter++;
		}
		return p_dmap;
	}
	public static void closeFile() {
		scan.close();
	}
}

 