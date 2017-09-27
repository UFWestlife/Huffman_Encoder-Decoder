/**
 * Created by zunwang on 2017/4/1.
 */
import java.io.*;
import java.util.Scanner;

public class decoder {
    public static void main (String args[]) throws IOException{
        decode(args[0], args[1]);
    }


    public static HuffmanTree buildDecodeTree(String codeTablePath) throws FileNotFoundException{
        HuffmanTree result = new HuffmanTree(0);
        File codeTableFile = new File(codeTablePath);// read file of code_table.txt
        Scanner scanner = new Scanner(codeTableFile);

        while(scanner.hasNext()){
            int value = scanner.nextInt();
            String code = scanner.next();
            char[] codeChar = code.toCharArray();
            HuffmanTree current = result;


            for (char c : codeChar){
                if (c == '0'){
                   if (current.leftChild == null){
                       current.leftChild = new HuffmanTree(0);
                   }
                   current = current.leftChild;
                } else {
                    if (current.rightChild == null){
                        current.rightChild = new HuffmanTree(0);
                    }
                    current = current.rightChild;
                }
            }
            current.hasKey = true;
            current.key = String.valueOf(value);
        }
        return result;
    }

    public static void decode(String binPath, String codeTablePath) throws IOException {

        FileWriter out = new FileWriter("decoded.txt");
        HuffmanTree decodeTree = buildDecodeTree(codeTablePath);


        BinaryInputStream bis = new BinaryInputStream(binPath);
        HuffmanTree current = decodeTree;

        int direction;
        while ((direction = bis.nextBit()) != -1) {
            current = (direction == 0) ? current.leftChild : current.rightChild;
            if (current.hasKey) {
                out.write(current.key + "\n");
                current = decodeTree;
            }
        }
        out.close();
    }
}
