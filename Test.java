/**
 * Created by zunwang on 2017/3/25.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static HashMap<String, Integer> map = new HashMap<>();
    public static HuffmanTree tree = null;

    public static void readInputFile (String inputPath) {
        // read input file to get key and frequency map
        try {
            String encoding = "GBK";
            File file = new File(inputPath);
            if (file.isFile() && file.exists()) { //if the file exists
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//judge coding type
                BufferedReader br = new BufferedReader(read);
                String str;
                while ((str = br.readLine()) != null) {
                    if (map.containsKey(str)) {
                        map.put(str, map.get(str) + 1);
                    } else {
                        map.put(str, 1);
                    }
                }
                read.close();
            } else {
                System.out.println("Cannot find the file...");
            }
        } catch (Exception e) {
            System.out.println("Reading Error...");
            e.printStackTrace();
        }
    }

    public static void writeFreqTable (String inputPath, String outputPath) {
        // read map to generate the frequency table file
        try {
            readInputFile(inputPath);
            FileWriter writer = new FileWriter(outputPath);
            BufferedWriter bw = new BufferedWriter(writer);
            String str1;
            for(Map.Entry<String, Integer> entry : map.entrySet()){
//                    System.out.println(entry.getKey()+","+entry.getValue());
                str1 = entry.getKey()+"==>"+entry.getValue();
                bw.write(str1);
                bw.newLine();
            }
            bw.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HuffmanTree buildTree (PriorityQueue<HuffmanTree> h) throws IOException {
        //Build the tree now

        PriorityQueue<HuffmanTree> heap = h;


        HuffmanTree left_child;
        HuffmanTree right_child;
        HuffmanTree result;

        for (String c : map.keySet()) {
            if (c != null)
            heap.insert(new HuffmanTree(c, map.get(c)));
        }

        while (heap.getSize() > 1) {
            left_child = heap.removeMin();
            right_child = heap.removeMin();
            result = new HuffmanTree("-->", left_child.frequency + right_child.frequency);
            result.leftChild = left_child;
            result.rightChild = right_child;
            heap.insert(result);
        }
        HuffmanTree hTree = heap.removeMin();
        tree = hTree;
        return tree;
//        hTree.printTreeNode();
    }

//    public static void main(String args[]) throws IOException {
//        String inputPath = "sample_input_large.txt";
//        String freqoutPath = "freq_table_large.txt";
//        String codeTablePath = "code_table_large.txt";
//
//
////        String inputPath = "sample_input_small.txt";
////        String freqoutPath = "freq_table_small.txt";
////        String codeTablePath = "code_table_small.txt";
//
//        PriorityQueue<HuffmanTree> binaryHeap = new BinaryHeap<>();  // 158s
//        PriorityQueue<HuffmanTree> pairingHeap = new PairingHeap<>(); //3-4s
//        PriorityQueue<HuffmanTree> fourWayHeap = new DAryHeap<>();
//
//
//        readInputFile(inputPath);
//
//        long startMili=System.currentTimeMillis();// Time Start
//        System.out.println("Input File: sample_input_large.txt ");
//        System.out.println("Start to build Huffman Tree 10 times:");
//        System.out.println("Building... ");
//        for (int i = 1; i <= 10; i++){
//            System.out.print(i+"... ");
//            buildTree(pairingHeap);
//        }
//        System.out.println();
//        long endMili=System.currentTimeMillis();
//        System.out.println("Runtime of building HuffmanTree by Paring Heap is: "+(endMili-startMili)+" ms");
//
//        System.out.println();
//        System.out.println("Start to encode...");
//        encoder.encode(tree);
//        long endMili1=System.currentTimeMillis();
//        System.out.println("Runtime of encoding is: "+(endMili1-endMili)+" ms");
//
//        System.out.println();
//        System.out.println("Start to generate binary file...");
//        encoder.genBin(inputPath);
//        long endMili2=System.currentTimeMillis();
//        System.out.println("Runtime of generating binary file is: "+(endMili2-endMili1)+" ms");
//
//        System.out.println();
//        System.out.println("Start to decode...");
//        decoder.decode(codeTablePath);
//        long endMili3=System.currentTimeMillis();
//        System.out.println("Runtime of decoding binary file is: "+(endMili3-endMili2)+" ms");
//
//        System.out.println();
//        long endMili4=System.currentTimeMillis();
//        System.out.println("Total RUNTIME is: "+(endMili4-startMili)+" ms");
//    }

}


