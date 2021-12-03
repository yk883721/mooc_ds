package unionfind;

public class QuickFind implements UF{

    private int[] id;

    public QuickFind(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId){
            return ;
        }
        for (int i = 0; i < id.length; i++) {
             if (id[i] == pId){
                 id[i] = qId;
             }
        }
    }

    // 查找元素p所对应的集合编号
    // O(1)复杂度
    private int find(int index){
        if(index < 0 || index >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[index];
    }

}
