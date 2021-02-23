package org.soen6441.controller;

import java.util.ArrayList;						import java.util.HashMap;						
import org.soen6441.model.Continent;			import org.soen6441.model.Country;

/**
 * This class checks the validity of the map by converting into a graph object. 
 */
public class ValidateMap {
	int d_VertexCount;
	ArrayList<ArrayList<Integer>> d_VertexList;
	
	/**
	 * This constructor of a class initialize the adjacency list representation of graph. 
	 * 
	 * @param p_VertexCount This is the size of which adjacency list will be created. 
	 */
	public ValidateMap(int p_VertexCount){
		d_VertexCount = p_VertexCount;
		d_VertexList = new ArrayList<>(d_VertexCount);
		for(int l_I=0;l_I<d_VertexCount;l_I++) {
			d_VertexList.add(new ArrayList<Integer>());
		}
	}
	
	/**
	 * This constructor receives country and continent objects and use them to add into adjacency list of graph. 
	 * 
	 * @param p_CountryObjects List of country objects stored in the map model. 
	 * @param d_ContinentObjects List of continent objects stored in the map model. 
	 * @throws Exception If any continent doesn't have a country, it notifies the user. 
	 */
	public ValidateMap(ArrayList<Country> p_CountryObjects,ArrayList<Continent> d_ContinentObjects) throws Exception{
		if(checkCountryAndContinent(p_CountryObjects,d_ContinentObjects)){
			HashMap<Integer,ArrayList<Integer>> p_HMap;
			p_HMap = updateCount(p_CountryObjects);
			System.out.println("printing hashmap"+p_HMap);
			d_VertexCount = p_HMap.size();
			d_VertexList = new ArrayList<>(d_VertexCount);
			for(int i=0;i<d_VertexCount;i++) {
				d_VertexList.add(new ArrayList<Integer>());
			}
			assignBorders(p_HMap);
		}
		else{
			throw new Exception("There should be atleast one country for a continent");
		}
	}
	
	/**
	 * This method checks if every continent has at least one country
	 *  
	 * @param p_CountryObjects List of country objects 
	 * @param p_ContinentObjects List of continent objects
	 * @return true if all continents have at least one country. false otherwise. 
	 */
	public boolean checkCountryAndContinent(ArrayList<Country> p_CountryObjects,ArrayList<Continent> p_ContinentObjects) {
		for(Continent l_C1:p_ContinentObjects){
			if(l_C1.getCountryList().size()<1){
				return false;
			}
		}
		return true;
	}
	
	public HashMap<Integer,ArrayList<Integer>> updateCount(ArrayList<Country> p_CountryObjects ){
		int l_Sequence=0,l_ID;
		ArrayList<String>d_UpdatedNeighbors=new ArrayList<String>();
		ArrayList<Country> d_NCountryObjects=p_CountryObjects;
		HashMap<Integer,Integer> d_UpdatedIDCount=new HashMap<Integer,Integer>();
		HashMap<Integer,ArrayList<Integer>> d_UpdatedMap=new HashMap<Integer,ArrayList<Integer>>();
		for(Country l_C: d_NCountryObjects){
			l_Sequence++;
			d_UpdatedIDCount.put(l_C.getCountryID(),l_Sequence);
		}
		for(Country l_C: d_NCountryObjects){
			ArrayList<Integer>d_StoreNeighbors=new ArrayList<Integer>();
			l_ID=d_UpdatedIDCount.get(l_C.getCountryID());
			d_UpdatedNeighbors=l_C.getBorder();
			for(String l_S: d_UpdatedNeighbors){
				for(Country l_C2: d_NCountryObjects){
					if(l_C2.getCountryName().equals(l_S)){
						int l_NewNeighborID=d_UpdatedIDCount.get(l_C2.getCountryID());
						d_StoreNeighbors.add(l_NewNeighborID);	
					}
				}
			}
			d_UpdatedMap.put(l_ID,d_StoreNeighbors);
		}	
		return d_UpdatedMap;	
	}

	/**
	 * This method fill all the values in the adjacency graph representation (i.e. d_VertexList).
	 * 
	 * @param p_HMap This parameter is the updated hash map created in updateCount method
	 */
	public void assignBorders(HashMap<Integer,ArrayList<Integer>> p_HMap) {
		System.out.println("Printing Map " + p_HMap);
		try {
			for(int l_I=1;l_I<p_HMap.size()+1;l_I++) {
				if(p_HMap.get(l_I)!=null) {
					ArrayList<Integer> l_TempBorderList = p_HMap.get(l_I);
					for(Integer l_TempBorderId:l_TempBorderList) {
						addBorder(l_I-1, l_TempBorderId-1);
					}
				}
			}
		}catch(Exception l_E) {
			System.out.println("Exception while assigning borders in validate map : " + l_E.getMessage());
		}
	}
	
	/**
	 * This method add the border 'p_V' at the index position 'p_U'
	 * 
	 * @param p_U index of the arraylist where border has to be added.
	 * @param p_V value to be added in the arraylist
	 */
	public void addBorder(int p_U, int p_V) {
		d_VertexList.get(p_U).add(p_V);
	}
	
	/**
	 * This method returns true if graph is connected. 
	 * DFS starts from one index and try to reach every other index from there. 
	 * This is checked for all the index present in the graph. 
	 * 
	 * @param p_Start starting point of the DFS traversal.
	 * @return returns true if all countries are traversed from the starting point. false otherwise. 
	 */
	public boolean runDFS(int p_Start) {
		boolean[] l_NodeVisited = new boolean[d_VertexCount];
		System.out.print("DFS of Graph : ");
		markVisited(p_Start, l_NodeVisited);
		System.out.println();
		for(boolean l_B: l_NodeVisited) {
			if(!l_B)
				return false;
		}
		return true;
	}
	
	/**
	 * This method is called from DFS function which sets the visited flag to true.
	 * Also this method is called recursively for all the countries that are connected from the starting point. 
	 * 
	 * @param p_Start Starting point of the DFS function. 
	 * @param p_NodeVisited boolean array which keeps track of which countries are visited and which are not visited. 
	 */
	private void markVisited(int p_Start, boolean[] p_NodeVisited) {
		p_NodeVisited[p_Start] = true;
		System.out.print(p_Start + " ");
		for(int l_I: d_VertexList.get(p_Start)) {
			if(!p_NodeVisited[l_I]){
				markVisited(l_I, p_NodeVisited);
			}
		}
	}
	
	/**
	 * This method takes the transpose of the graph. 
	 * For Example : If there is a border between country 1 --> 2, it will become 2 ---> 1 
	 * 
	 * @param p_VertexList list of vertices in the graph. (list of countries and their borders)
	 * @return new validate map object which holds the transposed vertext list. 
	 */
	public ValidateMap getTranspose(ArrayList<ArrayList<Integer>> p_VertexList) {
		ValidateMap l_TempMap = new ValidateMap(p_VertexList.size());
		for(int l_I=0;l_I<p_VertexList.size();l_I++) {
			for(int l_J : p_VertexList.get(l_I)) {
				l_TempMap.addBorder(l_J, l_I);
			}
		}
		return l_TempMap;
	}
	
	/**
	 * This method calls DFS for original graph and transposed graph. 
	 * 
	 * @return true if both DFS are passed, false otherwise. 
	 */
	public String isValid() {
		boolean l_B1 = runDFS(0);
		ValidateMap l_TempMap = getTranspose(this.d_VertexList);
		boolean l_B2 = l_TempMap.runDFS(0);
		if(l_B1 && l_B2) {
			return "Map is Valid";
		}
		return "Map is not Valid";
	}
}