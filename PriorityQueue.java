/**
 * Created by zunwang on 2017/3/25.
 */

public interface PriorityQueue<T>
{
    public int getSize();
    public void insert(T t);
    public T removeMin();
}
