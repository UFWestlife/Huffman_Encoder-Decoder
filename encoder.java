/**
 * Created by zunwang on 2017/3/28.
 */

import java.io.*;
import java.util.HashMap;

public class encoder {
    public static void main (String[] args) throws IOException {
        encoder enc = new encoder();
        Test.readInputFile(args[0]);
        HuffmanTree tree = Test.buildTree(pairingHeap);
        enc.encode(tree);
        enc.genBin(args[0]);
    }
    public encoder(){
        codes = new HashMap<>();
        pairingHeap = new PairingHeap<>();
    }
    String inputPath = "sample_input_large.txt";
    String freqoutPath = "freq_table_large.txt";
    String codeTablePath = "code_table_large.txt";

    public static PriorityQueue<HuffmanTree> pairingHeap; //3-4s
    public static HashMap<String, String> codes;
	// Generates code_table.txt and encoded.bin
	public static void encode(HuffmanTree root) throws IOException
	{
		//Get code in HashMap
        codes = encodeRecursive(root, "", new HashMap<String, String>());
		//Write codes to file
		FileWriter fw = new FileWriter("code_table.txt", true);
		for(String s : codes.keySet())
		{
			fw.append(s + " " + codes.get(s) + "\n");
		}
		fw.close();
	}

	public static void genBin(String inputFile) throws IOException{
        //Test bin
//        String hfm = "10100101";
//        int n = Integer.parseInt(hfm);
//        byte bin = (byte)Integer.parseInt(hfm);
//        System.out.print(bin);


        //Generate binary
        BufferedReader bufferedReader2=new BufferedReader(new FileReader(inputFile));
        DataOutputStream bufferedWriter2 = new DataOutputStream(new FileOutputStream(new File("encoded.bin")));

        String string = "";
        String tempResult = "";
        int currentSize = 0;
        int pos = 0;
//        while((line = bufferedReader2.readLine())!=null) {
//            String str [] = line.split(" ");
//            String hfmcode = str[1];
////            stringBuffer.append(hfmcode);
////            bufferedWriter2.write((byte)Integer.parseInt(hfmcode));
//            tempResult += hfmcode;
//            currentSize += hfmcode.length();
//            while (currentSize >= 8){
//                currentSize -= 8;
//                bufferedWriter2.write((byte) Integer.parseInt(
//                        tempResult.substring(pos, pos + 8), 2));
//                pos += 8;
//            }
//            tempResult = tempResult.substring(pos, tempResult.length());
//            pos = 0;
//            currentSize = tempResult.length();
//        }
//        bufferedReader2.close();
//        bufferedWriter2.close();


        while((string = bufferedReader2.readLine()) != null && string.length() > 0){// string = 2245
            String str = codes.get(string);//get 01010code from hashmap int->String ,map 010101010
            tempResult += str;
            currentSize += str.length();
            while (currentSize >= 8) {
                currentSize -= 8;
                bufferedWriter2.write((byte)Integer.parseInt(
                        tempResult.substring(pos, pos+8), 2));
                pos += 8;
            }
            tempResult = tempResult.substring(pos, tempResult.length());
            pos = 0;
            currentSize = tempResult.length();
        }
        bufferedReader2.close();
        bufferedWriter2.close();
    }

	private static HashMap<String, String> encodeRecursive(HuffmanTree node, String code, HashMap<String, String> encoding)
	{
		//Actually a DFS traversal
		if (node.isLeaf())
			encoding.put(node.key, code);
		else {
			if (node.leftChild != null)
				encodeRecursive(node.leftChild, code + "0", encoding);
			if (node.rightChild != null)
				encodeRecursive(node.rightChild, code + "1", encoding);
		}
		return encoding;
	}
}
