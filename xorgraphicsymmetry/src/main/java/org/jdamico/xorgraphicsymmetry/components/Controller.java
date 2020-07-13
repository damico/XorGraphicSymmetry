package org.jdamico.xorgraphicsymmetry.components;

import org.jdamico.xorgraphicsymmetry.dataobjects.Binary;

public class Controller {

	private static Controller INSTANCE = null;
	public static Controller getInstance(){
		if(INSTANCE == null) INSTANCE = new Controller();
		return INSTANCE;
	}
	private Controller(){}
	
	public Binary intToBinary(int source){
		StringBuffer binSb = new StringBuffer();
		String binary = Integer.toBinaryString(source);
		Binary bin = new Binary();
		if(binary.length() < 4) for(int k = 0; k < 4-binary.length(); k++) binSb.append(String.valueOf(0*k));
		binSb.append(binary);
		binary  = binSb.toString();			
		int[] bitArray = new int[4];
		for(int j=0; j<4; j++) bitArray[j] = Character.getNumericValue(binary.charAt(j)); 
		bin.setBitArray(bitArray);
		bin.setStrBitArray(binary);
		return bin;
	}
	
	public int xor(int a, int b){
		/* XOR = 0	0	= 0
				 0	1	= 1
				 1	0	= 1
				 1	1	= 0
		 */
		
		int r = 0;
		
		boolean ba=false;
		boolean bb=false;
		
		if(a==1) ba = true;
		if(b==1) bb = true;
		
		boolean res = ba ^ bb;
		
		if(res) r = 1;
		
		return r;
		
	}
	
	public String intArrayToString(int[] intArray){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intArray.length; i++) sb.append(intArray[i]);
		return sb.toString();
	}
	
}
