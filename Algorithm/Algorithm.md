# 算法设计
## 题型
- 选择（10/10）
- 简答（2/10）
- 算法设计（3/30）
- 程序填空（3/30）
- 算法理解（2/20）

## 总复习题
[总复习](review.md)

## 递归与分治
### 简答题
1. 使用递归函数需要满足什么条件？
   - 存在至少一个不再递归调用自身的基本情形
   - 每次递归调用使问题规模在良序集合上严格减小
   - 原问题的解可由递归子问题的解构造得到

### 程序题
1. 用递归函数写出阶乘函数，此函数的输入和输出是什么？并在主函数对其进行调用
   ```java
   public class Main {
      public static void main(String[] args) {
         System.out.print(fac(5));
      }

      /**
       * @param x 要计算阶乘的非负整数
       * @return x 的阶乘结果
       */
      public static long fac(int x) {
         if(x < 1) {
            return 1;
         }else {
            return x * fac(x - 1);
         }
      }
   }
   ```
2. 用递归函数写出求Fibonacci数列的函数，此函数的输入和输出是什么？并在主函数对其进行调用
   ```java
   public class Main {
      public static void main(String[] args) {
         System.out.print(fibonacci(5));
      }

      /**
       * @param n Fibonacci 数列的项号（n >= 0）
       * @return 第 n 项 Fibonacci 数
       */
      public static int fibonacci(int x) {
         if(x <= 1) {
            return 1;
         }

         return fibonacci(x - 1) + fibonacci(x - 2);
      }
   }
   ```
3. 用递归程序写出求解Ackerman函数，此函数的输入和输出是什么？并在主函数对其进行调用  
   $A(n, m) = \begin{cases}
      A(1, 0) = 2 \\
      A(0, m) = 1 \space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space m >= 0 \\
      A(n, 0) = n + 2 \space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space\space n >= 2 \\
      A(n, m) = A(A(n - 1, m), m - 1) \space\space\space\space n, m >= 1
   \end{cases}$
   ```java
   public class Main {
      public static void main(String[] args) {
         int m = 3;
         int n = 5;
         System.out.println(ackermann(m, n));
      }

      /**
       * @param m 非负整数，Ackermann 函数的第一个参数
       * @param n 非负整数，Ackermann 函数的第二个参数
       * @return Ackermann 函数的计算结果
       */
      public static long ackermann(long m, long n) {
         if (m == 0) {
            return n + 1;
         } else if (m > 0 && n == 0) {
            return ackermann(m - 1, 1);
         } else {
            return ackermann(m - 1, ackermann(m, n - 1));
         }
      }
   }
   ```
4. 用递归算法写出求解全排列问题的程序，输入输出参数分别是什么？程序中两次使用了swap(list,k,i)，它们的作用有什么不同？
   ```java
   public class Main {
      public static void main(String[] args) {
         Integer[] a = { 1, 5, 3, 1, 4, 2 };
         Perm(a, 0, a.length - 1);
      }

      /** 
       * @param a 一个数组
       * @param l 全排列的左端点
       * @param r 全排列的右端点
       * @return 无输出结果，直接打印全排列结果在日志中
       */
      public static <T> void Perm(T[] a, int l, int r) {
         if(l == r) {
            for(int i = 0; i <= r; ++ i) {
               System.out.print(a[i]);
            }
            System.out.println();
         }else {
            for(int i = l; i <= r; ++ i) {
               swap(a, l, i);       //< 做选择，创造下一次递归条件
               Perm(a, l + 1, r);
               swap(a, l, i);       //< 恢复原始状态
            }
         }
      }

      /**
       * @brief 泛式swap函数，因为java自身不提供swap
       */
      private static <T> void swap(T[] a, int i, int j) {
         T tmp = a[i];
         a[i] = a[j];
         a[j] = tmp;
      }
   }
   ```

## 贪心
### 填空题
1. 贪心算法的时间复杂度通常比动态规划**大**， 但不能保证在所有情况下都能得到**最优**解
2. 贪心算法的核心思想是每一步都做出当前状态下**局部最优**的选择，这种策略**不总是**能得到全局最优解
3. 在贪心算法中，背包问题允许物品**分割**，而01背包问题不允许。普通背包问题可以用贪心算法得到**最优**解
4. 在贪心算法中，活动安排问题的目标是选择一组互不重叠的活动，使得活动总数**最多**。该问题的贪心策略是按活动**结束时间**升序排列

### 多选题
1. 下列哪些问题是贪心算法的经典应用场景？
   1. **活动安排问题**
   2. 01背包问题
   3. **最小生成树问题**
   4. 矩阵链乘法问题
   5. **霍夫曼编码问题**
   6. 旅行商问题
2. 下列关于贪心算法与动态规划的区别，哪些说法是正确的？
   1. **贪心算法每一步都做出局部最优选择**
   2. **动态规划从整体考虑最优解**
   3. 贪心算法适用于所有优化问题
   4. 动态规划适用于所有优化问题
   5. **贪心算法的时间复杂度通常低于动态规划**
3. 下列关于贪心算法的说法中，哪些是正确的？
   1. **贪心算法每一步都选择当前最优解**
   2. 贪心算法一定能得到全局最优解
   3. **贪心算法适用于具有贪心选择性质的问题**
   4. 贪心算法适用于所有优化问题
   5. **贪心算法的效率通常较高**
   
### 单选题
1. 在贪心算法中，背包问题允许物品分割，而01背包问题不允许。下列说法正确的是？
   1. 普通背包问题可以用贪心算法得到最优解
2. 贪心算法的核心思想是每一步都做出当前状态下最优的选择，这种策略是否总是能得到全局最优解？
   1. 否，贪心算法不一定能得到全局最优解
3. 在贪心算法中，活动安排问题的目标是选择一组互不重叠的活动，使得活动总数最大。该问题的贪心策略是基于哪种排序方式？
   1. 按活动结束时间升序排列
4. 在活动安排问题中，如果活动集合中有$n$个活动，那么贪心算法的时间复杂度是多少？
   1. $O(nlogn)$

### 判断题
1. 01背包问题可以通过贪心算法得到最优解 $F$
2. 活动安排问题可以通过贪心算法得到最优解 $T$
3. 贪心算法的时间复杂度通常比动态规划低 $T$
4. 贪心算法在每一步选择当前最优解，因此一定可以得到全局最优解 $F$
5. 
