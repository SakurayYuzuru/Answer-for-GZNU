# JAVA
## MySQL
### Analysis
1. ExecuteDemo
   ```java
   import java.sql.*;

   public class ExecuteDemo{
      public static void main(String[] args) throws Exception{
         ExecuteDemo executeObj = new ExecuteDemo();
         System.out.println("执行建表的DDL语句");
         executeObj.executeSQL("CREATE TABLE m_test" + 
            "(test_id int PRIMARY KEY," + 
            "test_name VARCHAR(255))");
         System.out.println("执行插入数据的DML语句");
         executeObj.executeSQL("INSERT INTO m_test(test_id, test_name)" + 
            "SELECT id, username FROM userdetails");
         System.out.println("执行查询的查询语句");
         executeObj.executeSQL("SELECT test_name FROM m_test");
         System.out.println("执行删除表的DDL语句");
         executeObj.executeSQL("DROP TABLE m_test");
      }

      public void executeSQL(String sql) throws Exception{
         Class.forName(driver);

         try(Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement state = connection.createStatement()){
            boolean resultFeedback = state.execute(sql);
            if(resultFeedback){
               try(ResultSet rs = state.getResultSet()){
                  while(rs.next()){
                     System.out.print(rs.getString(1) + "\t");
                  }
                  System.out.println();
               }
            }else{
               System.out.println("该SQL语句影响的记录有" + state.getUpdateCount() + "条");
            }
         }
      }

      private String driver = "oracle.jdbc.driver.OracleDriver";
      private String url    = "jdbc:oracle:thin:@localhost:1521:orcl";
      private String user   = "scott";
      private String pwd    = "zkl123"; 
   }
   ```
2. ExecuteUpdateDemo
   ```java
   import java.sql.*;

   public class ExecuteUpdateDemo{
      public static void main(String[] args){
         ExecuteUpdateDemo execute = new ExecuteUpdateDemo();
         execute.createTable("CREATE TABLE m_test" + 
            "(test_id INT PRIMARY KEY, test_name, VARCHAR(255))");
         long result = execute.insertData("INSERT INTO m_test(test_id, test_name)" + "SELECT id, username FROM userdetails");
      }

      public void createTable(String sql) throws Exception{
         Class.forName(driver);
         
         try(Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement state = connection.createStatement()){
            state.executeUpdate(sql);
         }
      }

      public int insertData(String sql) throws Exception{
         Class.forName(driver);

         try(Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement state = connection.createStatement()){
            return state.executeUpdate(sql);
         }
      }

      private String driver = "oracle.jdbc.driver.OracleDriver";
      private String url    = "jdbc:oracle:thin:@localhost:1521:orcl";
      private String user   = "scott";
      private String pwd    = "zkl123"; 
   }
   ```

### Programming
增删查改
```java

```

## File Stream
### Analysis
1. FileInputStreamDemo
   ```java
   import java.io.*;

   public class FileInputStreamDemo{
      public static void main(String[] args){
         FileInputStream file = null;

         try{
            file = new FileInputStream("<file-path>");
            byte[] buf = new byte[1024];
            int hasRead = 0;
            while((hasRead = file.read(buf)) > 0){
               System.out.print(new String(buf, 0, hasRead));
            }
         }catch(IOException e){
            e.printStackTrace();
         }finally{
            try{
               file.close();
            }catch(IOException e){
               e.printStackTrace();
            }
         }
      }
   }
   ```
2. FileOutputStreamDemo
   ```java
   import java.io.*;
   import java.util.*;

   public class FileOutputStreamDemo{
      public static void main(String[] args){
         FileOutputStream file = null;
         Scanner scanner = new Scanner(System.in);

         try{
            file = new FileOutputStream("../build/output.txt");
            System.out.println("请输入内容");
            String str = scanner.nextLine();
            file.write(str.getBytes());
         }catch(IOException e){
            e.printStackTrace();
         }finally{
            try{
               file.close();
               scanner.close();
            }catch(IOException e){
               e.printStackTrace();
            }
         }
      }
   }
   ```
3. WriterDemo
   ```java
   import java.io.*;
   import java.util.*;

   public class WriterDemo{
      public static void main(String[] args){
         Scanner scanner = new Scanner(System.in);
         FileWriter fw = null;

         try{
            fw = new FileWriter("<file-path>");
            String str = scanner.nextLine();
            fw.write(str);
         }catch(IOException e){
            e.printStackTrace();
         }finally{
            try{
               fw.close();
               scanner.close();
            }catch(IOException e){
               e.printStackTrace();
            }
         }
      }
   }
   ```
4. ReaderDemo
   ```java
   import java.io.*;

   public class ReaderDemo{
      public static void main(String[] args){
         BufferedReader buf = null;

         try{
            buf = new FileReader("<file-path>");
            String result = null;
            while((result = buf.readLine()) != null){
               System.out.println(result);
            }
         }catch(IOException e){
            e.printStackTrace();
         }finally{
            try{
               buf.close();
            }catch(IOException e){
               e.printStackTrace();
            }
         }
      }
   }
   ```

### Programming
1. 读写文件：统计字符 
   ```java
    import java.io.*;

    public class Assignment{
        public static void main(String[] args) {
            String inputFile = "input.txt";
            String outputFile = "output.txt";

            int wordCount = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;

                while ((line = reader.readLine()) != null){
                    if (!line.trim().isEmpty()){
                        wordCount += line.length();
                    }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    writer.write("Words: " + wordCount);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
   ```
2. 生成乘法表（覆盖&不覆盖）
   ```java
   import java.io.*;

   public class MultiplePlot{
      public static void main(String[] args){
         String table = generateTable();
         String filename = "result.txt";

         // 覆盖
         writeTableCover(filename, table);

         // 不覆盖
         writeTableAppend(filename, table);
      }

      private static void writeTableCover(String filename, String table){
         try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))){
            writer.write(table);
         }catch(IOException e){
            e.printStackTrace();
         }
      }

      private static void writeTableAppend(String filename, String table){
         try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            writer.write(table);
         }catch(IOException e){
            e.printStackTrace();
         }
      }

      private static String generateTable(){
         StringBuffer buf = new StringBuffer();
         for(int i = 1; i <= 9; ++ i){
            for(int j = 1; j <= i; ++ j){
               buf.append(j).append(" * ").append(i).append(" = ").append(i * j).append("\t");
            }
            buf.append("\n");
         }

         return buf.toString();
      }
   }
   ```

## Gui
### Analysis
[FlowLayoutDemo](../java2/chapter03/src/com/qst/chapter03/FlowLayoutDemo.java)  
[GridLayoutDemo](../java2/chapter03/src/com/qst/chapter03/GridLayoutDemo.java)
[JLabelDemo](../java2/chapter03/src/com/qst/chapter03/JLabelDemo.java)  
[JListDemo](../java2/chapter03/src/com/qst/chapter03/JListDemo.java)

### Programming
计算器 登录 网络聊天室 记事本

## Multi-threads
### Analysis
ThreadTask.java  
ThreadDemo.java  
[ThreadLifeDemo](../java2/chapter05/src/com/qst/chapter05/ThreadLifeDemo.java)  
5-9  

### Programming
满天星 

## Socket
### Analysis
[ServerSocketDemo](../java2/chapter06/src/com/qst/chapter06/ServerSocketDemo.java)  
[URLConnectionDemo](../java2/chapter06/src/com/qst/chapter06/URLConnectionDemo.java)  
[ChatServer](../java2/chapter06/src/com/qst/chapter06/ChatServer.java)  
[ChatClient](../java2/chapter06/src/com/qst/chapter06/ChatClient.java)

### Programming
聊天室

## Super Program
### Analysis
7-11  

### Programming
函数式编程&枚举
```java
import java.util.*;

interface ICalculator{
   int Add(int a, int b);
}

public class Main{
   public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      ICalculator adder = (a, b)->{a + b};
      int result = adder.Add(5, 6);
      System.out.println("5 + 6 = " + result);
   }
}
```

## 基础
1. `Reader` `Writer`的API
2. Inheritance 1-6图
3. 包: 
   1. I/O: `import java.IO.*;`  
      NIO: `import java.nio.*;`
   2. MySQL: `import java.sql.*;`
   3. Socket: `import java.net.*;` 
4. Swing: 
   1. 事件: 高级事件，低级事件
   2. 布局: GridLayout, FlowLayout
   3. AWT比较
   4. `JOPtionPane`的const value, `JFileChooser`如何使用
5. MySQL:
   1. 驱动程序: `JDBC`，`ODBC`, 本地
6. Multi-threads:
   1. 定义
   2. 实现方式：inherit the parent `thread`, or implement the interface
   3. 生命周期
   4. 优先级
7. Socket:
   1. 定义
   2. 不能同时支持TCP和UDP
   3. `inetAddress`的API
8. 高级编程：
   1. 反射定义
   2. `Class`对象定义
   3. 函数式接口定义
   4. Lambda表达式定义