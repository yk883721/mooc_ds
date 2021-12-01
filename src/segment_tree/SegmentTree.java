package segment_tree;

public class SegmentTree<E> {

    private final E[] data;
    private final E[] tree;
    private final Merger<E> merger;

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr, Merger<E> merger){

        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);
        this.merger = merger;

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l >= r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

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

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    public E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + ( r - l ) / 2;
        if (queryL >= mid + 1){
            return query(rightChild, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftChild, l, mid, queryL, queryR);
        }

        E leftResult = query(leftChild, l, mid, queryL, mid);
        E rightResult = query(rightChild, mid + 1, r, mid + 1, queryR);

        return merger.merger(leftResult, rightResult);
    }
    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                Integer::sum);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }

}
