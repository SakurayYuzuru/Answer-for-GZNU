# 数据结构
## 序章
1. 数据的逻辑结构：*图表表示，二元组表示*
2. 数据结构的定义：指所有数据元素以及数据元素之间的关系，可以看作相互之间存在着某种关系的集合。  数据元素：作为数据的基本单位。  数据项：是具有独立含义的数据最小单位。数据对象：指性质相同的数据元素的集合，是数据的一个子集。  
3. 分析时间复杂度：
```c
void fun(int b[], int n){
    int i = 0, j = 0, k = 0, x = 0;
    for(int i = 0; i < n; i ++){
        k = i;
        for(int j = i + 1; j < n; ++ j){
            if(b[k] < b[j]){
                k = j;
            }
        }
        swap(b[i], b[k]);
    }
}
```
最坏时间复杂度为`O(n^2)`
## 线性表
1. 数据的逻辑结构是从数据元素的逻辑关系上描述数据的，是指数据元素之间的逻辑关系的整体，通常是求解问题中提炼出来的
2. 线性表是具有相同特征的数据元素的一个有限序列
3. 线性表的基本运算
```cpp
#define size_t int
class List{
public:
    List();
    ~List();
    
    bool ListEmpty();
    int ListLength();
    void DispList();
    Elem GetElem(size_t i);
    size_t LocateElem(Elem e);
    void ListInsert(size_t i, Elem e);
    Elem ListDelete(size_t i);
}
```
1. 顺序表：线性表的顺序存储结构；链表：线性表的链式存储结构
2. 顺序表具有随机存储的特性，其地址是连续的；链表具有随机存储的特性，其地址是离散的
3. 单/双链表的声明
```c
struct SingleList{
    Node* next;
    Elem value;
};
```
```c
struct DoubleList{
    Node* next;
    Node* prior;
    Elem value;
};
```
1. 顺序表的插入与删除
```c
void insert(List l, int i, Elem e){
    if(i < 1 || i > l-> length + 1 || l-> length == MaxSize){
        // std::cerr << “ERROR::OutOfSpace!” << “\n”;
        return ;
    }
    
    i --;
    for(int j = l-> length; j > i; -- j){
        l-> data[j] = l-> data[j - 1];
    }
    l-> data[i] = e;
    L-> length ++;
}
```
```c
void ListDelete(List l, int i, Elem e){
    if(i < 1 || i > l-> length){
        return ;
    }
    i --;
    e = l-> data[i];
    for(int j = i; j < l-> length; ++ j){
        l-> data[j] = l-> data[j + 1];
    }
    l-> length --;
}
```
1. 单链表的初始化，判断是否为空，插入与删除
```c
void InitList(List l){
    l = (List*)malloc(sizeof(Node));
    l-> next = NULL;
}
```
```c
_Bool ListEmpty(List l){
    return l-> next == NULL;
}
```
```c
void ListInsert(List l, int i, Elem e){
    Node* cur = l-> next;
    if(i < 0){
        return ;
    }
    
    int j = 0;
    while(j ++ < i && cur){
        p = p-> next;
    }
    if(!cur){
        return ;
    }
    
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode-> data = e;
    newNode-> next = cur-> next;
    cur-> next = newNode;
}
```
```c
void ListDelete(List l, int i, Elem e){
    if(i < 0) return ;
    
    Node* cur = l-> next;
    int j = 0;
    while(j ++ < i - 1 && cur){
        p = p-> next;
    }
    if(!cur) return ;
    
    Node* erase = cur-> next;
    if(!erase) return ;
    cur-> next = erase-> next;
    e = erase-> data;
    free(erase);
}
```
1. 双链表的插入与删除
```c
void ListInsert(List l, int i, Elem e){
    if(i < 0) return ;
    
    Node* cur = l-> next;
    int j = 0;
    while(j ++ < i && cur){
        cur = cur-> next;
    }
    if(!cur) return ;
    
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode-> data = e;
    newNode-> next = cur-> next;
    if(cur-> next){
        cur-> next-> prior = newNode;
    }
    newNode-> prior = cur;
    cur-> next = newNode;
}
```
```c
void ListDelete(List l, int i, Elem e){
    if(i < 0) return ;
    
    Node* cur = l-> next;
    int j = 0;
    while(j ++ < i - 1 && cur){
        cur = cur-> next;
    }
    if(!cur) return ;
    
    Node* erase = cur-> next;
    e = erase-> data;
    cur-> next = erase-> next;
    if(erase-> next){
        erase-> next-> prior = cur;
    }
    free(erase);
}
```
## 栈
1. 出栈顺序，反推入栈出栈操作
2. 顺序栈的基本运算，*出栈*
```c
void pop(Stack st, Elem e){
    if(st-> top == -1) return ;
    
    e = st-> data[st-> top];
    st-> top --;
}
```
1. 共享栈：两个栈的栈底在两端，栈顶在中间，类似于C语言的堆栈
2. ***栈的链式存储及其实现***
```c
typedef struct Node{
    Elem data;
    struct Node* next;
} Node;
typedef Stack Node*;

void init(Stack st){
    st = (Stack)malloc(sizeof(Node));
    st-> next = NULL;
}

void destroy(Stack st){
    Node* cur = st-> next, p = st;
    while(cur){
        free(p);
        p = cur;
        cur = cur-> next;
    }
    free(p);
}

_Bool isEmpty(Stack st){
    return st-> next == NULL;
}

void push(Stack st, Elem e){
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode-> data = e;
    newNode-> next = st-> next;
    st-> next = newNode;
}

Elem top(Stack st){
    if(st-> next)
        return st-> next-> data;
    return NAN;
}

// 出栈在下面
```
3. 链栈的基本运算，*出栈*
```c
void pop(Stack st, Elem e){
    if(!st-> next) return ;
    
    Node* cur = st-> next;
    e = cur-> data;
    st-> next = cur-> next;
    free(cur);
}
```
## 队列
1. 队列是一种操作受限的线性表，其限制仅允许在表的一端进行插入操作，而在表的另一端进行删除操作
2. 出队顺序
3. 顺序队的插入与删除
```c
void insert(Queue q, Elem e){
    if(q-> rear == MaxSize - 1) return ;
    
    q-> rear ++;
    q-> data[q-> rear] = e;
}
```
```c
void dequeue(Queue q, Elem e){
    if(isEmpty(q)) return ;
    
    q-> front ++;
    e = q->data[q->front];
}
```
1. 循环队列：把存储队列元素的数组从逻辑上看成一个环。
2. 循环队列判断队列为空，为满，返回当前队列的大小
```c
_Bool isEmpty(Queue q){
    return q-> front == q-> rear;
}
```
```c
_Bool isFull(Queue q){
    return (q->rear + 1) % MaxSize == q-> front;
}
```
```c
int size(Queue q){
    return (q-> rear - q-> front + MaxSize) % MaxSize;
}
```
## 字符串
1. 串是由零个或多个字符组成的有限序列
2. 计算字符串占用的内存，不需要考虑对齐
`char = 1Byte`, `int =  4Byte`
3. 模式匹配：暴力与KMP  
下面是课本给出的暴力解法
```c
int bf(String s, String t){
    int i = 0, j = 0;
    int m = s.length, n = t.length;

    while(i < m && j < n){
        if(s.data[i] == t.data[i]){
            i ++, j ++;
        }else{
            i -= j - 1;
            j = 0;
        }
    }

    if(j >= n){
        return i - n;
    }else{
        return -1;
    }
}
```
然而这种暴力对于初学者有些过于复杂,下面将给出我认为较适合初学者的暴力解法
```c
int bf(String s, String t){
    int m = s.length, n = t.length;
    for(int i = 0; i < m; ++ i){
        for(int j = 0; j < n; ++ j){
            if(s.data[i + j] != t.data[j]){
                break;
            }

            if(s.data[i + j] == t.data[j] && 
                j = n - 1){
                return i;
            }
        }
    }

    return -1;
}
```
下面将展示依据`next`数组的KMP算法
```c
int KMP(String s, String t){
    int next[maxSize], i = 0, j = 0;
    getNext(next, t);

    while(i < s.length && j < t.length){
        if(j == -1 || s.data[i] == t.data[j]){
            i ++;
            j ++;
        }else{
            j = next[j];
        }
    }

    if(j >= t.length){
        return i - t.length;
    }else{
        return -1;
    }
}

void getNext(int* next, String t){
    int i = 0, j = -1;
    next[0] = -1;

    while(i < t.length - 1){
        if(j == -1 || t.data[i] == t.data[j]){
            i ++, j ++;
            next[i] = j;
        }else{
            j = next[j];
        }
    }
}
```
下面将给出改进后的KMP算法
```c
int KMP(String s, String t){
    int next[maxSize], i = 0, j = 0;
    getNext(t, next);

    while(i < s.length && j < t.length){
        if(s.data[i] == t.data[j] || j == -1){
            i ++, j ++;
        }else{
            j = next[j];
        }
    }
    
    if(j >= t.length){
        return i - t.length;
    }else{
        return -1;
    }
}

void getNext(String t, int* next){
    int i = 0, j = -1;
    next[0] = -1;

    while(j < t.length - 1){
        if(j == -1 || t.data[i] == t.data[j]){
            i ++, j ++;
            if(t.data[i] != t.data[j]){
                next[i] = j;
            }else{
                next[i] = next[j];
            }
        }else{
            j = next[j];
        }
    }
}
```
1. 实现`c`的`strcmp()`
```c
int strcmp(char* s1, char* s2){
    int m = strlen(s1), n = strlen(s2);
    printf("%d, %d ", m, n);
    for(int i = 0; i < fmin(m, n); ++ i){
        if(s1[i] < s2[i]){
            return -1;
        }else if(s1[i] > s2[i]){
            return 1;
        }
    }

    if(m > n){
        return 1;
    }else if(m < n){
        return -1;
    }

    return 0;
}
```