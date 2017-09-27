JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	PriorityQueue.java \
	HuffmanTree.java \
	BinaryInputStream.java \
	BinaryHeap.java \
	PairingHeap.java \
	DAryHeap.java \
	Test.java \
	encoder.java \
	decoder.java \ 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class