import java.util.*;

class Student{
    public void setName(String _name){
        this.name = _name;
    }
    public void setGrade(int _grade){
        this.grade = _grade;
    }

    public String getName(){
        return this.name;
    }
    public int getGrade(){
        return this.grade;
    }

    public String toString(){
        return "Name:" + this.name + " Score:" + this.grade;
    }

    private String name;
    private int grade;
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
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        ArrayList<Student> s = new ArrayList<Student>(size);
        s = init(size);

        Collections.sort(s, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return Integer.compare(s2.getGrade(), s1.getGrade());
            }
        });

        for(Student stu : s){
            System.out.println(stu.toString());
        }
    }

    public static ArrayList<Student> init(int n){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> s = new ArrayList<Student>(n);
        for(int i = 0; i < n; ++ i){
            Student tmp = new Student();
            tmp.setName(scanner.next());
            tmp.setGrade(scanner.nextInt());
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