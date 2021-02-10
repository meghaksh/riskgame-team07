package org.soen6441.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ValidateMap {
	static Scanner scan;
	public static void main(String[] args) {
		DummyMap d_dmap = openFile();
		closeFile();
		/*Not strongly connected graph example*/
//				d_dmap.addBorder(0, 1);
//				d_dmap.addBorder(1, 2);
//				d_dmap.addBorder(2, 3);
		
		/*strongly connected graph example*/
		//		d_dmap.addBorder(0, 4);
		//		d_dmap.addBorder(1, 0);
		//		d_dmap.addBorder(1, 2);
		//		d_dmap.addBorder(2, 1);
		//		d_dmap.addBorder(2, 4);
		//		d_dmap.addBorder(3, 1);
		//		d_dmap.addBorder(3, 2);
		//		d_dmap.addBorder(4, 3);
		d_dmap.showMap();
		boolean l_isValidMap = d_dmap.runDFS(0);
		DummyMap l_tempMap = d_dmap.getTranspose(d_dmap.d_vertexList);
		//l_tempMap.showMap();
		boolean l_isValidTransposeMap = l_tempMap.runDFS(0);
		if(l_isValidMap && l_isValidTransposeMap) {
			System.out.println("Strongly Connected Graph");
		}else {
			System.out.println("Not A Strongly Connected Graph");
		}
	}
	public static DummyMap openFile() {
		DummyMap l_dmap = new DummyMap(180);
		try {
			scan = new Scanner(new File("src\\main\\java\\org\\soen6441\\controller\\borders.txt"));
			l_dmap = readFile(l_dmap);	
		}catch(Exception e) {
			System.out.println("Exception while opening the file" + e);
		}
		return l_dmap;
	}
	public static DummyMap readFile(DummyMap p_dmap) {
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

class DummyMap {
	int d_vertexCount;
	ArrayList<ArrayList<Integer>> d_vertexList;
	DummyMap(int p_vertexCount){
		d_vertexCount = p_vertexCount;
		d_vertexList = new ArrayList<>(d_vertexCount);
		for(int i=0;i<d_vertexCount;i++) {
			d_vertexList.add(new ArrayList<Integer>());
		}
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
	public DummyMap getTranspose(ArrayList<ArrayList<Integer>> p_vertexList) {
		DummyMap l_tempMap = new DummyMap(p_vertexList.size());
		for(int i=0;i<p_vertexList.size();i++) {
			for(int j : p_vertexList.get(i)) {
				l_tempMap.addBorder(j, i);
			}
		}
		return l_tempMap;
	}
}