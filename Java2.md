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

      public static void writeTableCover(String filename, String table){
         try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))){
            writer.write(table);
         }catch(IOException e){
            e.printStackTrace();
         }
      }

      public static void writeTableAppend(String filename, String table){
         try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            writer.write(table);
         }catch(IOException e){
            e.printStackTrace();
         }
      }

      public static String generateTable(){
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
1. FlowLayoutDemo
   ```java
   import java.awt.*;
   import javax.swing.*;

   public class FlowLayoutDemo extends JFrame{
      public FlowLayoutDemo(){
         super("FlowLayout");

         // 设置面板布局为流式布局，左对齐，水平间距10px，垂直间距15px
         m_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));

         m_button1 = new JButton("Button 1");
         m_button2 = new JButton("Button 2");
         m_button3 = new JButton("Button 3");

         m_panel.add(m_button1);
         m_panel.add(m_button2);
         m_panel.add(m_button3);

         // 将面板添加进入窗体
         // add this panel into the frame
         this.add(m_panel);
         // set window size with x = 200px, y = 200px
         this.setSize(200, 200);
         // set left top position at (200px, 100px)
         this.setLocation(200, 100);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
      }

      public static void main(String[] args){
         new FlowLayoutDemo();
      }

      private JPanel m_panel;
      private JButton m_button1, m_button2, m_button3;
   }
   ```
2. GridLayoutDemo
   ```java
   import java.awt.*;
   import javax.swing.*;

   public class GridLayoutDemo extends JFrame{
      public GridLayoutDemo(){
         super("Grid Layout");

         // 建立一个2*3的网格
         m_Panel = new JPanel(new GridLayout(2, 3));
         m_Buttons = new JButton[6];
         for(int i = 0; i < 6; ++ i){
            m_Buttons[i] = new JButton("Button" + (i + 1));
         }
         for(int i = 0; i < 6; ++ i){
            m_Panel.add(m_Buttons[i]);
         }

         this.add(m_Panel);
         this.setSize(200, 200);
         this.setLocation(200, 100);
         this.setDefaultExitOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
      }

      public static void main(String[] args){
         new GridLayoutDemo();
      }

      private JPanel m_Panel;
      private JButton[] m_Buttons;
   }
   ```
3. JLabelDemo
   ```java
   import java.awt.*;
   import javax.swing.*;

   public class JLabelDemo extends JFrame{
      public JLabelDemo(){
         super("JLabel");

         m_Panel = new JPanel(new GridLayout(3, 1));

         labelTxt    = new JLabel("Text Label");
         labelImg    = new JLabel(new ImageIcon("<img-path>"));
         labelTxtImg = new JLabel("Text and Image Label", new ImageIcon("<img-path>"));

         p.add(labelTxt);
         p.add(labelImg);
         p.add(labelTxtImg);

         this.add(p);
         this.setSize(200, 200);
         this.setLocation(200, 100);
         this.setDefaultExitOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
      }

      public static void main(String[] args){
         new JLabelDemo();
      }

      private JPanel m_Panel;
      private JLabel labelTxt, labelImg, labelTxtImg;
   }
   ```
4. JListDemo
   ```java
   import java.awt.*;
   import javax.swing.*;

   public class JListDemo extends JFrame{
      public JListDemo(){
         super("JList");
         this.setLayout(new GridLayout(1, 3));

         m_Panel = new JPanel(new GridLayout(2, 1));

         m_ListL = new JList(new String[]("看书", "写字", "画画"));
         m_ListL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

         m_ListR = new JList();
         model = new DefaultListModel();
         m_ListR.setModel(model);

         m_OK = new JButton("--->");
         m_Cancel = new JButton("<--");

         m_OK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String str = m_ListL.getSelectedValue().toString();
               model.addElement(str);
            }
         });
         m_Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               model.remove(m_ListR.getSelectedValue());
            }
         });

         m_Panel.add(m_Ok);
         m_Panel.add(m_Cancel);

         this.add(m_ListL);
         this.add(m_Panel);
         this.add(m_ListR);

         this.setSize(200, 200);
         this.setLocation(200, 100);
         this.setDefaultExitOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
      }

      public static void main(String[] args){
         new JListDemo();
      }

      private JPanel m_Panel;
      private JList m_ListL, m_ListR;
      private JButton m_OK, m_Cancel;
      private DefaultListModel model;
   }
   ```

### Programming
计算器 登录 网络聊天室 记事本

## Multi-threads
### Analysis
1. ThreadDemo
   ```java
   import java.util.*;
   
   public class ThreadDemo extends Thread{
      public static void main(String[] args){
         ThreadDemo td = new ThreadDemo();

         td.start();
         for(int i = 0; i < 100; ++ i){
            System.out.println(Thread.currentThread().getName() + ": " + i);
         }
      } 

      public void run(){
         for(int i = 0; i < 100; ++ i){
            System.out.println(this.getName() + ": " + i);
         }
      }
   }
   ```  
2. ThreadLifeDemo
   ```java
   class MyThread extends Thread{
      public void run(){
         int sum = 0;
         for(int i = 0; i < 100; ++ i){
            sum += i;
         }
         System.out.println("子线程求和: " + sum);
      }
   }

   public class ThreadLifeDemo{
      public static void main(String[] args) throws Exception{
         MyThread thread1 = new MyThread();
         System.out.println("new state[is alive: " + thread1.isAlive() + "]");
         thread1.start();
         System.out.println("new state[is alive: " + thread1.isAlive() + "]");
         Thread.sleep(1000);
         System.out.println("new state[is alive: " + thread1.isAlive() + "]");
      }
   }
   ```
3. PriorityDemo
   ```java
   class MyThread extends Thread{
      public MyThread(String name){
         super(name);
      }

      public void run(){
         for(int i = 0; i < 100; ++ i){
            System.out.println(this.getName() + "the priority is: " + this.getPriority() + " times: " + i);
         }
      }
   }

   public class PriorityDemo{
      public static void main(String[] args){
         System.out.println(Thread.currentThread().getPriority());
         MyThread t1 = new MyThread("top");
         t1.setPriority(Thread.MAX_PRIORITY);

         MyThread t2 = new MyThread("simple");
         MyThread t3 = new MyThread("low");
         t2.setPriority(Thread.MIN_PRIORITY);

         MyThread t4 = new MyThread("target");
         t4.setPriority(8);

         t1.start();
         t2.start();
         t3.start();
         t4.start();
      }
   }
   ```

### Programming
满天星 

## Socket
### Analysis
1. ServerSocketDemo
   ```java
   import java.io.*;
   import java.net.*;

   public class ServerSocketDemo extends Thread{
      public ServerSocketDemo(){
         try{
            // 监听端口28888
            server = new ServerSocket(28888);
         }catch(IOException e){
            e.printStackTrace();
         }

         this.start();
      }

      public static void main(String[] args){
         new ServerSocketDemo();
      }

      public void run(){
         while(this.isAlive()){
            try{
               Socket socket = server.accept();
               BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               String line = br.readLine();
               System.out.println(line);

               PrintStream ps = new PrintStream(socket.getOutputStream());
               ps.println();
               ps.flush();
   
               br.close();
               ps.close();
               socket.close();
            }catch(IOException e){
               e.printStackTrace();
            }
         }
      }

      private ServerSocket server;
      private int num = 0;
   }
   ```
2. URLConnectionDemo
   ```java
   import java.io.*;
   import java.net.*;

   public class URLConnectionDemo{
      public static void main(String[] args){
         try{
            URL mybook = new URL("http://book.moocollege.cn/java-book1.html");
            URLConnection urlConnection = mybook.openConnection();
            urlConnection.setRequestProperty("Charset", "UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String input;
            while((input = br.nextLine()) != null){
               system.out.println(input);
            }
            br.close();
         }catch(MalformedURLException e){
            e.printStackTrace();
         }catch(IOException e){
            e.printStackTrace();
         }
      }
   }
   ``` 
3. ChatServer
   ```java
   import java.io.*;
   import java.net.*;
   import java.util.*;
   import java.text.*;

   public class ChatServer extends Thread{
      public ChatServer(){
         try{
            m_Socket = new ServerSocket(28888);
         }catch(IOException e){
            e.printStackTrace();
         }

         new AcceptSocketThread().start();
         new SendMsg2Client().start();
      }

      public static void main(String[] args){
         new ChatServer();
      }

      class AcceptSocketThread extends Thread{
         public void run(){
            while(this.isAlive()){
               try{
                  Socket socket = m_Socket.accept();
                  if(socket != null){
                     BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     m_Readers.add(br);
                     new GetMsgFromClient(br).start();
                     m_Writers.add(new PrintWriter(socket.getOutputStream()));
                  }
               }catch(IOException e){
                  e.printStackTrace();
               }
            }
         } 
      }

      class GetMshFromClient extends Thread{
         public GetMshFromClient(BufferedReader _bufferReader){
            this.m_Reader = _bufferReader;
         }

         public void run(){
            while(this.isAlive()){
               try{
                  String msg = m_Reader.readLine();
                  if(msg != null){
                     SimpleDateFormat date = new SimpleDateFormat("yyyy - MM - dd HH:mm:ss");
                     String time = date.format(new Date());
                     m_Msg.addFirst("<==" + time + "==>\n" + msg);
                  }
               }catch(IOException e){
                  e.printStackTrace();
               }
            }
         }

         private BufferedReader m_Reader;
      }

      class SendMsg2Client extends Thread{
         public void run(){
            while(this.isAlive()){
               try{
                  if(!m_Msg.isEmpty()){
                     String msg = m_Msg.removeLast();
                     int n = m_Writers.size();

                     for(int i = 0; i < n; ++ i){
                        m_Writers.get(i).println(msg);
                        m_Writers.get(i).flush();
                     }
                  }
               }catch(IOException e){
                  e.printStackTrace();
               }
            }
         }
      }

      private ServerSocket m_Socket;
      private ArrayList<BufferedReader> m_Readers = new ArrayList<BufferedReader>();
      private ArrayList<PrintWriter> m_Writers = new ArrayList<PrintWriter>();
      private LinkedList<String> m_Msg = new LinkedList<String>();
   }
   ```
4. ChatClient
   ```java
   import java.awt.*;
   import java.io.*;
   import java.net.*;
   import javax.swing.*;

   public class ChatClient extends JFrame{
      private Socket m_Socket;
      private PrintWriter m_PrintWriter;
      private BufferedReader m_BufferedReader;
      private JPanel m_Panel;
      private JScrollPane m_ScrollPane;
      private JTextArea m_Content;
      private JLabel m_LabelName, m_LabelSend;
      private JTextField m_TextName, m_TextSend;
      private JButton m_Button;

      public ChatClient(){
         super("Chat Client");

         m_Content = new JTextArea();
         m_Content.setEditable(false);

         m_ScrollPane = new JScrollPane(m_Content);

         m_LabelName = new JLabel("username: ");
         m_TextName = new JTextField(5);
         m_LabelSend = new JLabel("Body: ");
         m_TextSend = new JTextField(20);
         m_Button =  new JButton("Send");

         m_Panel = new JPanel();
         m_Panel.add(m_LabelName);
         m_Panel.add(m_TextName);
         m_Panel.add(m_LabelSend);
         m_Panel.add(m_TextSend);
         m_Panel.add(m_Button);
         this.add(m_Panel, BorderLayout.SOUTH);

         this.add(m_ScrollPane);
         this.setSize(800, 600);
         this.setDefaultExitOperation(JFrame.EXIT_ON_CLOSE);
         
         try{
            m_Socket = new Socket("127.0.0.1", 28888);
            m_PrintWriter = new PrintWriter(m_Socket.getOutputStream());
            m_BufferedReader = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));
         }catch(IOException e){
            e.printStackTrace();
         }catch(UnknownHostException e){
            e.printStackTrace();
         }

         m_Button.addListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String strName = m_TextName.getText();
               String strMsg = m_TextSend.getText();

               if(!strMsg.equals("")){
                  m_PrintWriter.println(strName + "say: " + strMsg);
                  m_PrintWriter.flush();
                  // 清空
                  m_PrintWriter.setText("");
               }
            }
         });

         new GetMsgFromServer().start();
      }

      class GetMshFromServer extends Thread{
         public void run(){
            while(this.isAlive()){
               try{
                  String strMsg = m_BufferedReader.readLine();
                  if(strMsg != null){
                     m_TextContent.append(strMsg + "\n");
                  }
                  Thread.sleep(50);
               }catch(IOException e){
                  e.printStackTrace();
               }
            }
         }
      }

      public static void main(String[] args){
         new CharClient().setVisible(true);
      }
   }
   ```

### Programming
聊天室

## Super Program
### Analysis
1. EnumMethodDemo
   ```java
   public class EnumMethodDemo{
      public static void main(String[] args){
         for(SeasonEnum s : SeasonEnum.values()){
            System.out.println(s + "--" + s.ordinal());
         }

         SeasonEnum s1, s2, s3, s4;
         s1 = SeasonEnum.SPRING;
         s2 = SeasonEnum.SUMMER;
         s3 = SeasonEnum.FALL;
         s4 = Enum.valueOf(SeasonEnum.class, "FALL");
         if(s1.compareTo(s2) < 0){
            System.out.println(s1 + "在" + s2 + "之前");
         }
         if(s3.equals(s4)){
            System.out.println(s3 + "=" + s4);
         }
         if(s3 == s4){
            System.out.println(s3 + "=" + s4);
         } 

         // 后面没啥区别不写了
      }
   }
   ```

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