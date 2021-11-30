package segment_tree;

public class SegmentTree<E> {

    private final E[] data;
    private final E[] tree;

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr, Merger<E> merger){

        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1, merger);
    }

    private void buildSegmentTree(int treeIndex, int l, int r, Merger<E> merger){
        if (l >= r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild, l, mid, merger);
        buildSegmentTree(rightChild, mid + 1, r, merger);

        tree[treeIndex] = merger.merger(tree[leftChild], tree[rightChild]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    public int leftChild(int index){
        return 2 * index + 1;
    }

    public int rightChild(int index){
        return 2 * index + 2;
    }

}
