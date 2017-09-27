/**
 * Created by zunwang on 2017/3/25.
 */

import java.util.ArrayList;

public class PairingHeap<T extends Comparable<T>> implements PriorityQueue<T>
{

	public T root;
	public ArrayList<PairingHeap<T>> heap;

	//Constructor
	public PairingHeap()
	{
        // TODO Auto-generated constructor stub
        root = null;
        heap = new ArrayList<>();
	}

	public PairingHeap(T root)
	{
		this.root = root;
		this.heap = new ArrayList<>();
	}

    @Override
    public int getSize()
    {
        int size = 1;
        if(root == null) return 0;
        if(heap.size() == 0) return size;
        for(PairingHeap<T> child : heap)
        {
            size++;
        }
        return size;
    }


	@Override
	public void insert(T node)
	{

		if(root == null) root = node;
		else {
			PairingHeap<T> newNode = new PairingHeap<T>(node);//initialize a node
			if(newNode.root.compareTo(root) > 0)// compare with root, if bigger, be a child
                heap.add(newNode);
			else {
                newNode.root = root; newNode.heap = heap; root = node;
                heap = new ArrayList<>(); heap.add(newNode);
			}
		}
	}

    @Override
    public T removeMin()
    {
        T min = root;
        if(heap.isEmpty())
        {
            root = null;
            return min;
        }
        ArrayList<PairingHeap<T>> Melded = heap;
        while(Melded.size() > 1)
        {
            int n = (int) Math.ceil(Melded.size()/2);// after the removal, using n-pass to merge all child
            ArrayList<PairingHeap<T>> pass = new ArrayList<>(n);

            //int cnt = (int) Math.ceil(Melded.size()/2);
            // what's the difference here
            int count = (int) Math.ceil((float)Melded.size()/2);// number of passes

            if(Melded.size()%2 == 1) count--;
            for(int i=0; i<count; i++)
            {
                pass.add(meld(Melded.get(i*2), Melded.get((2*i) + 1)));
            }
            if(Melded.size()%2 == 1) pass.add(Melded.get(Melded.size()-1));
            Melded = pass;
        }
        root = Melded.get(0).root;// get the new root
        heap = Melded.get(0).heap;
        return min;
    }

    @Override
    public String toString()
    {
        return heap.toString();
    }

	private PairingHeap<T> meld(PairingHeap<T> ph1, PairingHeap<T> ph2)
	{
		if(ph1.root.compareTo(ph2.root) < 0)	//t1 is smaller
		{
			ph1.heap.add(ph2);
			return ph1;
		}
		else
		{
			ph2.heap.add(ph1);
			return ph2;
		}
	}
}
