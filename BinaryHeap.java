/**
 * Created by zunwang on 2017/3/25.
 */


import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T>{

    //Constructor
    public ArrayList<T> heap;

    public BinaryHeap()
    {
        // TODO Auto-generated constructor stub
        this.heap=new ArrayList<T>();
    }

    @Override
    public int getSize(){
        return heap.size();
    }

    @Override
    public void insert(T node)
    {
        heap.add(node);
        int i=heap.size()-1;
        int p=((i-1)/2); // find the index of parent node

        while(heap.get(p).compareTo(heap.get(i)) > 0 && i > 0){
            Collections.swap(heap,i,p);
            i=p;
            p=((i-1)/2);
        }
    }
    @Override
    public T removeMin()
    {
        T min=null;
        if(heap.size()>0)
        {
            min=heap.remove(0);
            if(heap.size()>1)
            {
//                heap.add(0, heap.remove(heap.size()-1));
                heap.add(heap.remove(heap.size()-1));
                percolateDown(0);
            }
        }
        return min;

    }

    public void percolateDown(int index)
    {
        int i = index;
        int left = (2 * index + 1);//find the index of left and right child
        int right = (2 * index + 2);

        //        if (leftChild < heap.size() && heap.get(i).compareTo(heap.get(leftChild)) > 0) i = leftChild;
//        if (rightChild < heap.size() &&  heap.get(i).compareTo(heap.get(rightChild)) > 0 ) i = leftChild;
//        if (i != index){
//            Collections.swap(heap, i, index);
//            percolateDown(i);
//        }


        if(left<heap.size() && heap.get(i).compareTo(heap.get(left))>0) i=left;
        if(right<heap.size() && heap.get(i).compareTo(heap.get(right))>(-1)) i=right;
        if(i!=index)
        {
            Collections.swap(heap,i,index);
            percolateDown(i);
        }
    }
}