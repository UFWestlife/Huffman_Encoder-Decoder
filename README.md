## Huffman Encoder and Decoder<br>
This project implemented an encoder and a decoder of Huffman code. 
<br><br>
It implemented the priority queue by three kinds of data structures, binary heap, pairing heap, and 4-way heap. Then, selected 4-way heap as the base to build Huffman tree. Because by testing an input file with one million entries, 4-way heap acted more efficiently.
<br><br>
You can just use make command to compile them with makefile. Then, run encoder in command line and the file path is the input parameter. After the encoding operations, you may get a encoded output file. Using this as the input parameter of decoder.java, and finally you will get the original file.  
