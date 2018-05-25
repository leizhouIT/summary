package datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoulei8 on 2017/6/24.
 * 图结构
 */
public class Chart {

    public static void main(String[] args) {
        /*new Random().nextInt();
        int count = 0;
        for (int i = 0; i <3 ; i++) {
            try {
                System.out.println("第 "+count +" 次执行");
                int j = 1/0;
                break;
            }catch (Exception e){
                count++;
            }
        }*/
    }

    /**
     * 表示图数据顶点类
     */
    class Vertex{
        public char lable;//节点数据
        public boolean wasVisited;//搜索过的标识，true：搜索过，false:未搜索过

        public Vertex(char lab){
            lable = lab;
            wasVisited = false;
        }
    }

    /**
     * 创建邻接表和邻接矩阵，并添加边
     */
    class Graph{
        private final int MAX_VERTS = 20;
        private Vertex vertexList[];//存放节点的数据数组
        private int adjMat[][];//关联的节点边，相邻为1，不相邻为0
        private int nVerts;//数据中的元素个数
        private Stack theStack;//用于存放搜索过程中的数据，深度优先搜索用到

        private QueueDemo queue;//用于存放搜索过程中的数据，广度优先搜索用到
        private char sortedArray[];//
        public Graph(){
            vertexList = new Vertex[MAX_VERTS];
            adjMat=new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for (int j=0;j<MAX_VERTS;j++){
                for (int k=0;k<MAX_VERTS;k++){
                    adjMat[j][k]=0;
                }
            }
            theStack = new Stack(0);
            queue = new QueueDemo(MAX_VERTS);
            sortedArray = new char[MAX_VERTS];

        }
        /**
         * 添加一个顶点数据
         * @param lab
         */
        public void addVertex(char lab){
            vertexList[nVerts++]=new Vertex(lab);
        }
        /**
         * 添加一条边，用矩阵记录两个顶点的关系
         * @param start 开始顶点下标
         * @param end//链接顶点的下标
         */
        public void addEdge(int start,int end){
            adjMat[start][end]=1;
            adjMat[end][start]=1;
        }
        public void displayVertex(int v){
            System.out.println(vertexList[v].lable);
        }
        /**
         * 查找两个节点是否相连接
         * @param v
         * @return
         */
        public int getAdjUnvisitedVertex(int v){
            for (int j=0;j<nVerts;j++){
                //如果记录节点是否相连的二维数组中数据的值是1，那么表明两个节点是邻接节点
                if (adjMat[v][j]==1 && vertexList[j].wasVisited == false){
                    return j;//返回与查找的节点邻接节点的下标
                }
            }
            //没有找到返回-1
            return -1;
        }
        /**
         * 搜索，从顶点开始，深度优先搜索
         */
        public List<String> dfsNode(Node node){
            List list = new ArrayList();
            if (null == node)
                return list;
            theStack.push(node.getNumItems());
            while (!theStack.isEmpty()){
                long pop = theStack.pop();
                list.add(pop);
                //获取所有子节点
                Node child = node.getChild(0);
                if (child != null){
                    theStack.push(child.getNumItems());
                }

            }
            return list;
        }


        public void dfs(){
            vertexList[0].wasVisited = true;//记录顶点搜索过，将标识变为true
            displayVertex(0);
            theStack.push(0);//将顶点放入栈中
            while (!theStack.isEmpty()){//如果栈的数据不为空
                //从栈中弹出一个元素，查找与该元素的邻接节点元素的下标
                int v = getAdjUnvisitedVertex((int) theStack.peek());
                if (v==-1){//如果没有找到，从栈中弹出该元素
                    theStack.pop();
                }else {//如果找到将标识更改为搜索过，并且放入栈中
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    theStack.push(v);
                }
            }
            //还原数组中的元素未搜索过
            for (int j=0;j<nVerts;j++){
                vertexList[j].wasVisited = false;
            }
        }
        /**
         * 搜索，从顶点开始，广度优先
         */
        public void bfs(){
            vertexList[0].wasVisited = true;
            displayVertex(0);
            queue.insert(0);
            int v2;
            while (!queue.isEmpty()){//如果队列不为空
                int v1 = (int) queue.remove();
                while ((v2 = getAdjUnvisitedVertex(v1))!=-1){
                    vertexList[v2].wasVisited=true;
                    displayVertex(v2);
                    queue.insert(v2);
                }
            }
            for (int j=0; j<nVerts;j++){
                vertexList[j].wasVisited = false;
            }
        }
        /**
         * 最小生产树
         */
        public void mst(){
            vertexList[0].wasVisited = true;//记录顶点搜索过，将标识变为true
            theStack.push(0);
            while (!theStack.isEmpty()){
                int currentVertex = (int) theStack.peek();//获取栈顶元素
                //从栈中弹出一个元素，查找与该元素的邻接节点元素的下标
                int v = getAdjUnvisitedVertex(currentVertex);
                if (v == -1){//表示没有邻接节点
                    theStack.pop();//弹出栈顶元素
                }else {
                    vertexList[v].wasVisited = true;
                    theStack.push(v);
                    displayVertex(currentVertex);
                    displayVertex(v);
                    System.out.println(" ");
                }
            }
            for (int j = 0; j<nVerts;j++){
                vertexList[j].wasVisited=false;
            }
        }
        /**
         * 图的拓扑排序
         */
        public void topo(){
            int orig_nVerts = nVerts;//记录所有的顶点个数
            while (nVerts >0){
                //查找没有后继节点的顶点
                int currentVertex = noSuccessous();
                //如果所有元素都循环过没有找到，那么直接返回
                if (currentVertex == -1){
                    System.out.println("erroe");
                    return;
                }
                //记录找到的顶点，将他放入排序数组中，从最后一个元素开始放入
                sortedArray[nVerts-1]=vertexList[currentVertex].lable;
                //删除找到的顶点
                deleteVertex(currentVertex);
            }
            System.out.println(" ");
            for (int j=0;j<orig_nVerts;j++){
                System.out.println(sortedArray[j]);
            }
            System.out.println(" ");
        }

        /**
         * 查找一个没有后继节点的顶点
         * @return
         */
        public int noSuccessous(){
            boolean isEdge;
            //循环二维数组
            for (int row=0;row<nVerts;row++){
                isEdge=false;//记录是否有后继节点
                for (int col=0;col<nVerts;col++){
                    if (adjMat[row][col] > 0) {//表明该节点有后继节点，因为大于0表明有节点相连接
                        isEdge=true;//修改标记
                        break;//跳出内层循环
                    }
                }
                //如果没有后继节点，返回该节点的下标
                if (!isEdge)
                    return row;
            }
            return -1;//没有返回-1
        }

        /**
         * 删除找到没有后继节点的节点
         * @param delVert
         */
        public void deleteVertex(int delVert){
            if (delVert != nVerts-1){
                //循环存放数据的数组,从将要删除的位置开始向后
                for (int j=delVert;j<nVerts-1;j++){
                    //将删除后的空位补上，向前移动数据
                    vertexList[j]= vertexList[j+1];
                }

                //按照行移动记录邻接节点的数组
                for (int row=delVert;row<nVerts-1;row++){
                    moveRowUp(row,nVerts);
                }
                //按照列移动记录邻接节点的数组
                for (int col=delVert;col<nVerts-1;col++){
                    moveColLeft(col,nVerts-1);
                }
                nVerts--;
            }
        }
        //将行向上移动填补空缺
        private void moveRowUp(int row,int length){
            //将该节点对应的行向上移动
            for (int col=0;col<length;col++){
                adjMat[row][col]=adjMat[row+1][col];
            }

        }
        //移动列和行之间的关系，将列向左移动填补空缺
        private void moveColLeft(int col,int length){
            //将该节点对应的列向左移动
            for (int row=0;row<length;row++){
                adjMat[row][col]=adjMat[row][col+1];
            }
        }
    }
}
