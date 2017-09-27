/**
 * Created by zunwang on 2017/3/25.
 */

import java.util.LinkedList;
import java.util.Queue;

public class HuffmanTree implements Comparable<HuffmanTree> {
	
	public String key;
	public int frequency;
	public HuffmanTree leftChild;
	public HuffmanTree rightChild;
	public boolean hasKey;

    public HuffmanTree(int frequency) {
        // TODO Auto-generated constructor stub
        this.frequency = frequency;
        this.key = String.valueOf(-1);
        this.hasKey = false;
    }
	
	public HuffmanTree(String key, int frequency) {
        // TODO Auto-generated constructor stub
        this.key = key;
        this.frequency = frequency;
        this.hasKey = true;
	}



    public String getKey() {
        return key;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void printTreeNode()
	{
		Queue<HuffmanTree> q = new LinkedList<>();
		q.add(this);
		while(!q.isEmpty()){
			HuffmanTree node = q.poll();
			System.out.println(node.key + "\t" + node.frequency);// get the key and frequency of this node
			if(node.leftChild != null) q.add(node.leftChild);
			if(node.rightChild != null) q.add(node.rightChild);

		}
	}
    @Override
    public int compareTo(HuffmanTree other)
    {
        return getFrequency().compareTo(other.getFrequency());
    }


    public boolean isLeaf()
    {
        return (leftChild == null && rightChild == null);
    }
}
