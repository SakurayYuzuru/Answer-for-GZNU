import java.util.*;

class Human{
    private int age;
    private String name;

    public Human() { }
    public Human(int _age, String _name){
        this.age = _age;
        this.name = _name;
    }

    protected void sleep(){
        System.out.println("8 h / d");
    }

    protected void eat(){
        System.out.println("In canteen");
    }

    public void setName(String _name){
        this.name = _name;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return " [age=" + age + ", name=" + name + "]";
    }
}

class Student extends Human{
    public Student() {}
    public Student(int _age, String _name){
        super(_age, _name);
    }

    public void setGrade(int _grade){
        this.grade = _grade;
    }

    public int getGrade(){
        return this.grade;
    }

    private int grade;
}

class Teacher extends Human{
    private double salary;

    public Teacher(int _age, String _name, double _salary){
        super(_age, _name);
        this.salary = _salary;
    }

    private void teaching(){
        System.out.println("Teacher is teaching!");
    }

    @Override
    public String toString(){
        return "Teacher [salary=" + salary + "]"+super.toString();
    }
}

class Manager extends Teacher{
    public Manager(int _age, String _name, double _salary){
        super(_age, _name, _salary);
    }

    public void manager(){
        System.out.println("He is a manager!");
    }
}

class Rectangle{
    int a, b;
    Rectangle(int _a, int _b){
        a = _a;
        b = _b;
    }
    
    public int area(){
        return a * b;
    }
}

public class Solve{
    public static void main(String[] args){
        showClass();
    }

    public static void showClass(){
        Student student = new Student(20,"BoyuZhang");
		student.eat();
		student.sleep();
		System.out.println(student);

		Teacher teacher=new Teacher(40,"SiLi",100);
		teacher.sleep();
		teacher.eat();
		System.out.println(teacher);

		Manager mt = new Manager(35,"WuWang",200);
		mt.sleep();
		mt.eat();
		mt.manager();
		System.out.println(mt);
    }

    public static int statistics(String s){
        return s.length();
    }

    public static String sort(String s){
        String[] str = s.split(" ");
        Arrays.sort(str);
        String sorted = String.join(" ", str);
        return sorted;
    }

    public static String addString(String s){
        Scanner in = new Scanner(System.in);
        String tmp = in.nextLine();
        return s + tmp;
    }
    
    public static void deleteString(String s){
        s.replace(s, "");
    }

    public static boolean findString(String s, String find){
        return s.contains(find);
    }

    public static String reverseString(String s){
        String r = new StringBuilder(s).reverse().toString();
        return r;
    }

    public int alphaCount(String s){
        int res = 0;
        for(int i = 0; i < s.length(); ++ i){
            if(Character.isLetter(s.charAt(i))){
                res ++;
            }
        }

        return res;
    }

    public int digitCount(String s){
        int res = 0;
        for(int i = 0; i < s.length(); ++ i){
            if(Character.isDigit(s.charAt(i))){
                res ++;
            }
        }

        return res;
    }

    public int spaceCount(String s){
        int res = 0;
        for(int i = 0; i < s.length(); ++ i){
            if(Character.isSpaceChar(s.charAt(i))){
                res ++;
            }
        }

        return res;
    }

    public static ArrayList<Student> init(int n){
        Scanner cin = new Scanner(System.in);
        ArrayList<Student> s = new ArrayList<Student>(n);
        for(int i = 0; i < n; ++ i){
            Student tmp = new Student();
            tmp.setName(cin.next());
            tmp.setGrade(cin.nextInt());
            s.add(tmp);
        }

        return s;
    }

    private static int getAvg(ArrayList<Integer> a){
        int avg = 0;
        for(int i : a){
            avg += i;
        }
        return avg / a.size();
    }

    private static int getUpper(ArrayList<Integer> a){
        int res = 0;
        int avg = getAvg(a);
        for(int i : a){
            if(i > avg){
                res ++;
            }
        }

        return res;
    }

    private static int getLower(ArrayList<Integer> a){
        int res = 0;
        int avg = getAvg(a);
        for(int i : a){
            if(i < avg){
                res ++;
            }
        }

        return res;
    }

    private static void insertNewElem(int num, ArrayList<Integer> a){
        a.add(num);
    }

    private static void printArray(ArrayList<Integer> a){
        Collections.sort(a);
        for(int i : a){
            System.out.print(i + " ");
        }
    }

    // private static char[] verification(){
    //     Random random1 = new Random(10);
    //     Random random2 = new Random(26);
    //     Random random3 = new Random(26);
    //     Random pos = new Random(2);
    //     char[] s = "     ";
    //     int index = 5;

    //     while(index -- != 0){
    //         int p = pos.nextInt();
    //         if(p == 1){
    //             int value = random1.nextInt();
    //             s[index - 1] = value + '0';
    //         }else if(p == 2){
    //             int value = random2.nextInt();
    //             s[index - 1] = value + 'a';
    //         }else{
    //             int value = random3.nextInt();
    //             s[index - 1] = value + 'A';
    //         }
    //     }

    //     return s;
    // }

    private static int Score(int[] a){
        Arrays.sort(a);
        int res = 0;
        for(int i = 1; i < 9; ++ i){
            res += a[i];
        }

        return res;
    }

    private static float InfixExpressCalculation(float x, char op, float y){
        switch(op){
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
            default: return x / y;
        }
    }

    private static int maxDivisor(int m, int n){
        int res = 1;
        for(int i = 1; i <= Math.min(m, n); ++ i){
            if(m % i == 0 && n % i == 0){
                res = i;
            }
        }
        return res;
    }

    private static int minMultiple(int m, int n){
        return m * n / maxDivisor(m, n);
    }

    static int input_times = 3;
    private static void getInput(String user_name, String user_password){
        String _name = "admin";
        String _password = "123";

        if(user_name.equals(_name)){
            //System.out.println("1");
            if(user_password.equals(_password)){
                //System.out.println("2");
                System.out.println("Accessed");
            }
        }else{
            input_times --;
            System.out.println("You just have " + input_times + " times to try");
        }
    }

    private static void isPerfectNumber(int a){
        int cpy = a;
        int[] factor = new int[10];
        int index = 0;
        for(int i = 1; i <= a / 2; ++ i){
            if(a % i == 0){
                factor[index ++] = i;
            }
        }

        for(int i = 0; i < index; ++ i){
            a -= factor[i];
        }

        if(a == 0){
            System.out.println("The number " + cpy + " is a perfect number.");
        }else{
            System.out.println("The number " + cpy + " isn't a perfect number.");
        }
    }

    private static void CheckDay(String s){
        String res = new String("Today is ");
        switch(s){
            case"Mon":
                res += "Monday";
                break;
            case"Tue":
                res += "Tuesday";
                break;
            case"Wed":
                res += "Wednesday";
                break;
            case"Thur":
                res += "Thursday";
                break;
            case"Fri":
                res += "Friday";
                break;
            case"Sat":
                res += "Saturday";
                break;
            case"Sun":
                res += "Sunday";
                break;
            default:
                System.out.println("Please input a valid value.");
                Scanner cin = new Scanner(System.in);
                s = cin.next();
                CheckDay(s);
        }

        System.out.println(res);
    }

}