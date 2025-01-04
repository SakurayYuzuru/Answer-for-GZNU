# Java复习
## Graphics
### interface
####
需要用`class`实现`interface`
`class`必须包含`interface`所有方法的复写  
####
`super`可以调用父类中的各种方法  
如`super.function()`
```java
interface Shape{
    public abstract float Area();
    public abstract float C();
    public abstract void printInfo();
}
```
然后逐步实现其他图形
```java
class Triangle implements Shape {
    double a, b, c;
    Triangle(double _a, double _b, double _c){
        this.a = _a;
        this.b = _b;
        this.c = _c;
    }

    @Override
    public double Area(){
        double p = (this.a * this.b * this.c) / 2;
        return Math.pow(p * (p - this.a) * (p - this.b) * (p - this.c), 0.5);
    }
    @Override
    public double C(){
        return this.a + this.b + this.c;
    }
    @Override
    public void printInfo(){

    }
}
```
```java
class Circle implements Shape{
    private float r;

    @Override
    public float Area(){
        return PI * this.r * this.r;
    }
    @Override
    public float C(){
        return 2 * PI * this.r;
    }
    @Override
    public void printInfo(){

    }
}
```
```java
class Matrix implements Shape{
    float x, y;

    @Override
    public float Area(){
        return this.x * this.y;
    }
    @Override
    public float C(){
        return 2 * (this.x + this.y);
    }
    @Override
    public void printInfo(){

    }
}
```

### enum
下面将用枚举类型演示
```java
enum Graphics implements Shape{
    Triangle(a, b, c){
        @Override
        public float Area(){
            // Using Hailun's function
            double p = (this.a * this.b * this.c) / 2;
            return Math.pow(p * (p - this.a) * (p - this.b) * (p - this.c), 0.5);
        }
        @Override
        public float C(){
            return this.a + this.b + this.c;
        }
        @Override
        public void printInfo(){

        }
    },
    Matrix(x, y){
        @Override
        public float Area(){
            return this.x * this.y;
        }
        @Override
        public float C(){
            return 2 * (this.x + this.y);
        }
        @Override
        public void printInfo(){

        }
    },
    Circle(r){
        @Override
        public float Area(){
            return PI * this.r * this.r;
        }
        @Override
        public float C(){
            return 2 * this.r * PI;
        }
        @Override
        public void printInfo(){

        }
    };
}
```

## Loop
### 下面将展示三种循环格式
```java
while(n --){ }
```
```java
for(var i = 0; i < n; ++ i){ }
```
```java
for(var i : Object){ }
```

## Array
### 编程题1: 随机数 + 区间查询
```java
        int[] a = new int[50];
        Random random = new Random();
        for(var i : a){
            i = random.nextInt(101);
        }
        int up = 0, down = 0;
        for(var i : a){
            if(i >= 90){
                up ++;
            }
            if(i <= 60){
                down --;
            }
        }
```

### 矩阵
```java
        int[][] arr = new int[10][10];
        for(var i : arr){
            for(var j : i){
                j = scanner.nextInt();
            }
        }

        int sum = getSum(arr, 10);
        int[] mx = getMax(arr, 10);
```
下面是`getSum()`与`getMax()`的实现
```java
    private static int sum(int[][] arr, int n){
        int sum = 0;
        for(int i = 0; i < n; ++ i){
            sum += a[i][i];
        }

        return sum;
    }

    private static int[] getMax(int[][] arr, int n){
        int[] res = {-1, -1};
        int mx = Integer.MIN_VALUE;
        for(var i = 0; i < n; ++ i){
            for(var j = 0; j < n; ++ j){
                if(arr[i][j] > mx){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }

        return res;
    }
```

## Exception
### try~catch, throw
记住以下`Exception`类，实在不行先用他们的父类`Exception`
以下是`try-catch`的使用示例
```java
        try{
            foo();
            func();
        }catch(InputMismatchException ime){
            // ...
        }catch(ArrayIndexOutOfBoundsException aioobe){
            // ...
        }catch(RuntimeException re){
            // ...
        }catch(NumberFormatException nfe){
            // ...
        }catch(ArithmeticException ae){
            // ...
        }finally{
            // ...
        }
```
如果`foo()`抛出异常，会`goto`到对应异常类的`catch`  
不过也可以用`throw`来实现
```java
        throw new Exception("Error Massage.");
```
### 阶乘
异常会与阶乘一起出现，下面将给出阶乘的计算方式  
第一种是使用递归实现的
```java
    private int fac(int n){
        if(n <= 1){
            return 1;
        }

        return n * fac(n - 1);
    }
```
第二种是基于`loop`来实现的
```java
    private int fac(int n){
        int res = 0;
        for(int i = 1; i < n; ++ i){
            res *= i;
        }

        return res == 0 ? 1 : res;
    }
```

## 适配器设计模式
### 目的：
子类不一定调用父类中所有的方法
#### 抽象接口
```java
interface University{
    final String name = new String();

    public abstract void Study();
    public abstract void Work();
    public abstract void Relax();
}
```
#### 适配器
```java
class Person implements University{
    private String name;
    private Integer age;
    private Boolean sex;

    Person(){
        super.name = "...";
    }

    @Override
    public void Study(){

    }
    @Override
    public void Work(){

    }
    @Override
    public void Relax(){

    }
}
```
#### 实体类
```java
class Student extends Person{
    String major;
    String XueYuan;
    String id;
    String grade;

    // to init
    Student(){

    }

    @Override
    public void Study(){

    }
    @Override
    public void Relax(){

    }
}

class Teacher extends Person{
    String major;
    String XueYuan;
    String id;

    Teacher(){
        
    }

    @Override
    public void Work(){

    }
    @Override
    public void Relax(){

    }
}
```

## 范式
```java
class Student<S, T>{
    S getS(){
        // s is the same type with S
        return s;
    }
}
```

## String
需要记忆以下`API`
```java
        // 等同于c语言的strcmp()
        string1.compareTo(string2);
        // 比较两字符串内容
        string1.equals(string2);
        // 查询内容
        string.contains(" ");
        // 获取子字符串
        string.substring(begin);
        string.substring(begin, end);
```
***特别注意***  
`string1 == string2`比较的是两字符串的地址，不是两字符串的内容

## Compile
1. 源文件是`.java`, 编译链接后生成`.class`文件
2. 文件名必须与主类名相同，主类一定是被`public`修饰的
3. `operator+`有连接与数加两种效果
```java
        string = string1 + string2;
        n = n1 + n2;
```
4. `Class.length` 用于计算`Array` `String`的长度
5. `Class.size` 用于计算`泛型数组`的长度
6. example 
```java
class A{
    public A(){
        // print something
    }
}

class B extends A{
    public B(){
        // 默认子类先调用父类中无参数类型的构造方法
        super();
        // print something
    }
}
```
运行后，优先打印类`A`的消息，再打印类`B`的消息  
可以用*堆栈*的视角来看

## 继承
只能获取未被`private`修饰的属性  
想访问，改成`protected`即可，没必要写`getValue()`  
### 抽象类
子类必须复写父类的所有抽象方法
与`interface`相同