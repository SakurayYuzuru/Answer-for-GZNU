# Data Structure
## 树
```cpp
template <typename T>
struct TreeNode{
    T val;
    TreeNode* left;
    TreeNode* right;

    TreeNode(T& _val) : val(_val), left(nullptr), right(nullptr) { }
};
```
1. 节点度的概念: 某个节点的子树的个数称`结点的度`，树中所有节点的度中最大值为`树的度`
2. n叉树的概念: 是由n($n >= 0$)个结点或元素组成的有限集合
3. 根据树形，求树的高度(树中结点的最大层次)
4. 高度为h，二叉树结点的最大值是$2^{h - 1}$
5. 完全二叉树结点的计算
6. 二叉树的形态判断
7. 树的逻辑表示方法有`顺序存储结构`和`链式存储结构`
8. 分支结点: 树中度不为零的结点， 又叫非终端结点
9. 叶子结点: 度为零的结点
10. 路径长度的概念: 是该路径所通过的结点数目减1
11. 二叉树的概念: 是一个有限的结点集合，这个集合或者为空，或者由一个根结点和两棵互不相交的称为左子树和右子树的二叉树构成
12. 满二叉树的概念: 在一棵二叉树中，如果所有分支结点都有左右孩子结点，并且叶子结点都集中在二叉树的最下一层，这样的二叉树称为满二叉树
13. 满二叉树和完全二叉树的关系: 满二叉树是完全二叉树的一种特例，并且完全二叉树与通告度的满二叉树的对应位置结点的编号相同 
14. 遍历方式
    1. 中序遍历
        ```cpp
        template <typename T>
        std::vector<T> inorderTraversal(TreeNode* root){
            std::vector<T> res;
            inorder(root, res);
            return res;
        }

        template <typename T>
        void inorder(TreeNode* root, std::vector<T>& res){
            if(!root){
                return ;
            }

            inorder(root->left, res);
            res.emplace_back(root->val);
            inorder(root->right, res);
        }
        ```
    2. 先序遍历
        ```cpp
        template <typename T>
        std::vector<T> preorderTraversal(TreeNode* root){
            std::vector<T> res;
            preorder(root, res);
            return res;
        }

        template <typename T>
        void preorder(TreeNode* root, std::vector<T>& res){
            if(!root){
                return ;
            }

            res.emplace_back(root->val);
            preorder(root->left, res);
            preorder(root->right, res);
        }
        ```
    3. 后续遍历
        ```cpp
        template <typename T>
        std::vector<T> postorderTraversal(TreeNode* root){
            std::vector<T> res;
            postorder(root, res);
            return res;
        }

        template <typename T>
        void postorder(TreeNode* root, std::vector<T>& res){
            if(!root){
                return ;
            }

            preorder(root->left, res);
            preorder(root->right, res);
            res.emplace_back(root->val);
        }
        ```

## 图
1. 计算强联通图的边数:最少为$n$，最多为$n(n - 1)$
2. 计算有向完全图的边数: $n(n - 1)$
3. 有向图顶点入度和与出度和之间的关系: 一个顶点的入度与出度的和为该顶点的度
4. 广度优先遍历顺序: 先访问初始点`v`，接着访问顶点`v`的所有未被访问过的临界点$v_{1}, v_{2}, ...$， 然后按照$v_{1}, v_{2}, ...$的次序访问每一个顶点的所有未被访问过的邻接点，直到图中所有和初始点`v`由路径相通的顶点都被访问过为止
5. 无向连通图的最小生成树是否唯一: 不一定唯一
6. 有向图的概念: 在图G中，如果表示边的顶点对是有序的
7. 无向图的概念: 在图G中，若$<i, j> \in E(G)$必有$<j, i> \in E(G)$,即$G$是对称的，则用$(i, j)$代替这两个顶点对，表示顶点i, j的条无向边，称G为无向图
8. 完全图包含的边数是多少:
   1. 无向完全图: $\frac{n(n - 1)}{2}$
   2. 有向完全图: $n(n - 1)$
9.  图的存储结构有哪些:
    1.  邻接矩阵
    2.  邻接表
    3.  边集数组
10. 有向图中，起点的定义: 一条有向边$<i, j>$中i为起点
11. 有向图中，终点的定义: 一条有向边$<i, j>$中j为终点
12. 邻接矩阵的定义: 一种采用邻接矩阵数组表示顶点之间相邻关系的存储结构
13. 深度优先遍历与广度优先遍历的关系:`BFS`借助队列一步一步地“齐头并进”，相对`DFS`，`BFS`找到的路径一定是最短的，但代价是消耗的空间比`DFS`大。`DFS`可能较快地找到目标点，但找到的路不一定是最短的

## 查找
1. 顺序查找法的存储结构: 线性表，链表
2. 平衡二叉树结点的计算: 结点树$N(h) = N(h - 1) + N(h - 2) + 1 = 2^{h} - 1$
3. 顺序查找法的平均查找长度: 
   1. 成功: $ASL = \sum_{i = 1}^{n} p_{i}c_{i} = \frac{1}{n} \sum i = \frac{1}{n} \times \frac{n(n + 1)}{2} = \frac{n + 1}{2}$
   2. 失败: $ASL = n + 1$
4. 二叉排序树的特点: 
   1. 若根结点的左子树非空，则左子树上的所有结点关键字均小于根结点关键字
   2. 若根结点的右子树非空，则右子树上的所有结点关键字均大于根结点关键字
   3. 根结点的左右子树本身又是一棵二叉排序树
5. 解决哈希冲突的方法: 
   1. 开放地址法
      1. 线性探测法
      2. 平方探测法
   2. 拉链法
6. 线性表的查找方法: 顺序查找，二分查找，分块查找
7. 分块查找的基本思想: 分而治之
8. 哈希冲突的定义: 在构建哈希表是可能存在这样的问题，两个关键字$k_{i}$和$k_{j}(i \not = j)$且有$k_{i} \not = k_{j}$，但会出现$hash(k_{i}) = hash(k_{j})$的情况

## 排序
1. 直接插入排序的排序过程: 将当前无序区的开头元素插入到有序区的合适位置
    ```cpp
    void DirectInsertSort(std::vector<int>& arr){
        int n = arr.size();
    
        for (int i = 1; i < n; ++i) {
            int key = arr[i]; 
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; 
                j--;
            }
            arr[j + 1] = key; 
        }
    }
    ```
2. 冒泡排序关键字的比较次数计算: 理论$O(n^{2})$
3. 简单选择排序是否稳定: 不稳定
4. 排序算法的时间复杂度:
   | 排序方式 | 最好时间 | 最坏时间 | 平均时间 | 稳定性 | 
   | --- | --- | --- | --- | --- |
   | 冒泡排序 | $O(n)$ | $O(n^{2})$ | $O(n^{2})$ | 稳定 | 
   | 直接插入排序 | $O(n)$ | $O{n^{2}}$ | $O(n^{2})$ | 稳定 | 
   | 简单选择排序 | $O(n)$ | $O{n^{2}}$ | $O{n^{2}}$ | 不稳定 |
   | 希尔排序 | $O(\log n)$ ~ $O(n^{2})$ | $O{n^{2}}$ | 不确定 | 不稳定 | 
   | 归并排序 | $O(n\log n)$ | $O(n\log n)$ | $O(n\log n)$ | 稳定 |
   | 快速排序 | $O(n\log n)$ | $O(n^{2})$ | $O(n\log n)$ | 不稳定 |
   | 堆排序 | $O(n\log n)$ | $O(n\log n)$ | $O(n\log n)$ | 不稳定 |
   | 计数排序 | $O(n + k)$ | $O(n + k)$ | $O(n + k)$ | 稳定 |
   | 基数排序 | $O(n \times k)$ | $O(n \times k)$ | $O(n \times k)$ | 稳定 |
   | 桶排序 | $O(n + k)$ | $O(n^{2})$ | $O(n)$ | 稳定 | 
5. 直接插入排序的时间复杂度$O(n^{2})$和空间复杂度$O(1)$
6. 内排序的概念: 若整个排序表都放在内存中处理，排序时不涉及数据的内、外存交换
7. 外排序的概念: 若在排序过程中要进行数据的内、外存交换
8. 冒泡排序的执行过程: 从最下面的元素开始，对每两个相邻的关键字进行比较，且使关键字较小的元素换至关键字较大的元素之上，使得经过一趟冒泡排序后关键字最小的元素到达最上端
9.  二叉排序树的特点:

## 练习
1. 已知中序序列为`DGBAECF`，后序序列为`GDBEFCA`，构造对应的二叉树，并给出先序序列
   ```mermaid
    graph TB
        A --- B
        A --- C
        B --- D
        D --- G
        C --- E
        C --- F
   ```
    先序序列： `ABDGCEF`
2. 已知先序序列为`ABDGCEF`，中序序列为`DGBAECF`，构造对应的二叉树，并给出后序序列  
    后序序列为`GDBEFCA`
3. 给定一个无向图，利用`Prim`算法和`Kruskal`算法构造最小生成树  
   1. Prim
        ```cpp
        void Prim(const std::vector<std::vector<int>>& G, int start) {
            int n = G.size();
            std::vector<int> lowCost(n);
            std::vector<int> closest(n);
            std::vector<bool> visited(n, false);
            
            for (int i = 0; i < n; ++i) {
                lowCost[i] = G[start][i];
                closest[i] = start;
            }
            visited[start] = true;

            for (int i = 1; i < n; ++i) {
                int minDist = std::numeric_limits<int>::max();
                int k = -1;

                for (int j = 0; j < n; ++j) {
                    if (!visited[j] && lowCost[j] < minDist) {
                        minDist = lowCost[j];
                        k = j;
                    }
                }

                if (k == -1) {
                    break;
                }

                visited[k] = true;
                for (int j = 0; j < n; ++j) {
                    if (!visited[j] && G[k][j] < lowCost[j]) {
                        lowCost[j] = G[k][j];
                        closest[j] = k;
                    }
                }
            }
        }
        ```
   2. Kruskal: 对边排序，从小到大选取，出现回路舍弃当前最小值选择下一个
4. 给定一组权值，构造哈夫曼树
   ```cpp
    struct TreeNode{
        int w;
        TreeNode* left;
        TreeNode* right;

        TreeNode(int _w) : w(_w), left(nullptr), right(nullptr) { }
    };

    struct Compare{
        bool operator()(TreeNode* a, TreeNode* b){
            return a->w > b->w;
        }
    };

    void buildHuffmanTree(const std::vector<int>& weights){
        std::priority_queue<TreeNode*, std::vector<TreeNode*>, Compare> pq;

        for(int w : weights){
            pq.push(new TreeNode(w));
        }

        while(pq.size() > 1){
            TreeNode* left = pq.top();
            pq.pop();
            TreeNode* right = pq.top();
            pq.pop();

            TreeNode* parent = new TreeNode(left->w + right->w);
            parent->left = left;
            parent->right = right;

            pq.push(parent);
        }

        return pq.top();
    }
   ```
5. 给定一组关键字，分别利用二路归并排序算法和快速排序算法进行排序，要求给出过程
   1. 二路归并排序
        ```cpp
        void merge(std::vector<int>& arr, int left, int right, int mid){
            std::vector<int> tmp(right - left + 1);
            int i = left, j = mid + 1, k = 0;

            while(i <= mid && j <= right){
                tmp[k ++] = (arr[i] <= arr[j]) ? arr[i ++] : arr[j ++];
            }
            while(i <= mid){
                tmp[k ++] = arr[i ++];
            }
            while(j <= right){
                tmp[k ++] = arr[j ++];
            }

            for(int m = 0; m < tmp.size(); ++ m){
                arr[left + m] = tmp[m];
            }
        }

        void mergeSort(std::vector<int>& arr, int left, int right){
            if(left >= right){
                return ;
            }

            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        ```
   2. 快速排序
        ```cpp
        int partition(std::vector<int>& arr, int left, int right){
            int pivot = arr[left];

            int i = left, j = right;
            while(i < j){
                while(i < j && arr[j] >= pivot){
                    j --;
                }
                if(i < j){
                    arr[i ++] = arr[j];
                }
                while(i < j && arr[i] <= pivot){
                    i ++;
                }
                if(i < j){
                    arr[j --] = arr[i];
                }
            }
            arr[i] = pivot;

            return i;
        }

        void qsort(std::vector<int>& arr, int left, int right){
            if(left >= right){
                return ;
            }

            int pivot = partition(arr, left, right);
            qsort(arr, left, pivot - 1);
            qsort(arr, pivot + 1, right);
        }
        ```
6. 给定关键字集合，利用二分查找算法查找时，针对具体的元素，依次与哪些元素比较？计算成功和不成功时的平均查找长度
   1. 成功: $ASL = \sum 比较次数 / 元素个数$
   2. 不成功: $ASL = \sum 比较次数 / 元素个数$
   ```cpp
   int divideSearch(const std::vector<int>& arr, int target){
        int n = arr.size();
        int l = 0, r = n - 1;

        while(l < r){
            int mid = (l + r) >> 1;
            if(target < arr[mid]){
                r = mid - 1;
            }else if(target > arr[mid]){
                l = mid + 1;
            }else{
                return mid;
            }
        }

        return -1;
   }
   ```
7. 有关键字集合，采用散列存取，散列表为`HT[0, ..., 14]`，设散列函数`H(k) = k % 13`，解决冲突采用开放定址法中的平方探测法。试将k值填入`HT`表，并把查找每个关键字探测的比较次数`M`填入表中，并计算出查找成功时的平均查找长度
   若发生冲突，$hash(x) = (H(x) + k^{2}) \mod size$，其中k为本次查询的冲突次数