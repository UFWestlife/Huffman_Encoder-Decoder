/**
 * Created by zunwang on 2017/4/1.
 */

import java.util.*;
public class DAryHeap<T extends Comparable<T>> implements PriorityQueue<T> {

    public ArrayList<T>  heap;
    public DAryHeap(){
    heap = new ArrayList<>();
    heap.add(null);
    }


    @Override
    public int getSize(){
        return (heap.size()-1);
    }

    @Override
    public void insert(T node){
        heap.add(node);
        int i = heap.size()-1;
        int p = (int)Math.ceil((double)(i-1)/4);
        while (i > 1  && heap.get(i).compareTo(heap.get(p)) < 0){
            T temp = heap.get(i);
            heap.set(p, heap.get(i));
            heap.set(i, temp);
            i = p;
            p = (int)Math.ceil((double)(i-1)/4);
        }
    }

    public T removeMin(){
        T min = null;
        if (heap.size() > 1) {
            min = heap.remove(1);
            if (heap.size() > 2) {
                heap.add(1, heap.remove(heap.size() - 1));
                percolateDown(1);
            }
        }
        return min;
    }


    private void percolateDown(int hole){
        int firstChild = 4*(hole -1 ) + 2;
        T temp = heap.get(hole);
        int min = hole;
        int secondChild =firstChild +1;
        int thirdChild = secondChild +1;
        int fourthChild = thirdChild + 1;
        if(firstChild < heap.size() && heap.get(firstChild).compareTo(heap.get(hole))<0){
            min = firstChild;
        }
        if (secondChild < heap.size()){
            if (heap.get(secondChild).compareTo(heap.get(min)) < 0) {
                min = secondChild;
            }
        } if (thirdChild < heap.size()){
            if (heap.get(thirdChild).compareTo(heap.get(min))<0){
                min = thirdChild;
            }
        }if(fourthChild < heap.size()){
            if(heap.get(fourthChild).compareTo(heap.get(min)) < 0){
                min = fourthChild;
            }
        }

        if (min != hole) {
            T temp1 = heap.get(hole);
            heap.set(hole, heap.get(min));
            heap.set(min, temp);
            percolateDown(min);
        }
    }

}
