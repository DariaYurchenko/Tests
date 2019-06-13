insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 1, 3, 4, 'int []a = {5,5};
int b = 1;
a[b] = b = 0;
System.out.println(Arrays.toString(a));', '5{0}', 'Ошибка компиляции', '[0,5]', '[5,0]', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 1, 2, 5, 'class Main {
    public static void main(String[] args) {
        long[] array2 = {3, 4};
        for (int x : array2) {
            System.out.println(x);
        }
    }
}', '3 4', 'Ошибка времени выполнения', '4 3', 'Ошибка компиляции', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 1, 2, 5, 'import java.util.ArrayList;
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<Integer>(2);
        array.add(5);
        array.add(6);
        array.add(1, 7);
        System.out.print(array.indexOf(6));
        array.remove(1);
        System.out.println(array.indexOf(6));
    }
}', '22', '-1-1', 'Ошибка времени выполнения', '21', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 1, 3, 4, 'Какие из следующих интерфейсов содержат Iterator?', 'SortedMap<K,V>', null , null , 'List<E>', 'Queue<E>' , 'Set<E>');
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 1, 2, 6, 'Какие из следующих структур данных, которые наследуют интерфейс Map, используют оператор ==, вместо equals, для сравнения объектов?', 'LinkedHashMap', 'WeakHashMap' , 'HashMap' , 'IdentityHashMap', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 1, 103, 120, 'Какие из следующих строчек кода скомпиллируются без ошибок?', 'float[] f = {0.7, 0.4}', null , null , 'int[ ][ ] scores = {{1, 2}, {1, 2, 3, 4}, {1, 2, 3}};', 'String[ ] names[ ];', 'float[ ] f1[ ], f2;');
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 1, 37, 45, 'Выберите правильные варианты объявления переменной.', 'short [5] x2;', 'short z2 [5];', null , 'short x [];', 'short [] z [] [];', null);

insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 2, 2, 5, 'public class Main {
    public static void main(String args[]) {
        byte b = 0;
        while (++b > 0);
        System.out.println(b);
    }
}', '255', '0', '-256', '-128', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 2, 4, 5, 'int i = 0, j = 5;
tp: for (;;) {
    i++;
    for (;;) {
        if (i > --j) {
            break tp;
        }
    }
    System.out.println("i =" + i + ", j = " + j);
}', 'i = 1, j = 4', 'i = 1, j = 0', 'i = 3, j = 0', 'Ошибка компиляции', null , null );
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 2, 4, 7, 'class Main {
    public static void main(String[] args) {
        switch (new Integer(4)) {
            case 4:
                System.out.println("4");
                break;
            default:
                System.out.println("default");
        }
    }
}', 'Ошибка времени выполнения', 'default', 'Ошибка компиляции', '4', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 2, 20, 52, 'Какие типы циклов есть в Java?', 'break', ' vicious', null , 'do...while', 'for', null);

insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 3, 9, 14, 'public class Parent {
    protected String value = "parent";
    public static void main(String[] args) {
        Parent parent = new Child();
        Child child = new Child();
        System.out.println(parent.value);
        System.out.println(child.value);
    }
}
class Child extends Parent {
    protected String value = "child";
}', 'child child', 'parent parent', 'Ошибка времени выполнения', 'parent child', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 3, 9, 10, 'class A {
    public void m(int k) {
        System.out.println("class A, method m : " + ++k);
    }
}
class B extends A {
    public int m(int k) {
        System.out.println("class B, method m : " + k++);
        return k;
    }
}
public class MainClass {
    public static void main(String args[]) {
        A a = new B();
        a.m(34);
    }
}', 'class A, method m : 34', 'class B, method m : 34', 'class A, method m : 35', 'Ошибка компиляции', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 3, 10, 14, 'public class Z {
    public void print( Object o ) {
        System.out.println( "Object" );
    }
    public void print( String str ) {
        System.out.println( "String" );
    }
    public void print( Integer i ) {
        System.out.println( "Integer" );
    }
    public static void main(String[] args) {
        Z z = new Z();
        z.print( null );
    }
}', 'Object', 'String', 'Integer', 'Ошибка компиляции', null , null);

insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 4, 13, 25, 'public class Starter extends Thread {
    private int x = 2;
    public static void main(String[] args) throws Exception {
        new Starter().makeItSo();
    }
    public Starter() {
         x = 5;
         start();
    }
    public void makeItSo() throws Exception {
        join();
        x = x - 1;
        System.out.println(x);
    }
    public void run() { x *= 2; }
 }', 'Ошибка времени выполнения', '4', '5', '9', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 4, 17, 28, 'Какие значения может принимать приоритет потока?', 'от 1 до 5', 'от 5 до 10', 'от 1 до 100', 'от 1 до 10', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 4, 13, 25, 'Какие могут быть состояния потока?', 'Working', null , null , 'Waiting', 'New', 'Blocked');
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (3, 4, 13, 25, 'import java.util.Map;
import java.util.concurrent.__________;
public class ThreadSafe6Quiz {
    public static class ThreadSafe6 {
        private final Map< String, String > data = new ConcurrentHashMap< >();
        public void putIfAbsent(final String key, final String value) {
            data.putIfAbsent(key, value);
        }
    }
}', null , null , null , 'ConcurrentHashMap', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (3, 4, 33, 55, 'import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExitMain1 {
    private static final int POOL_SIZE = 50;
    public static void main(final String[] args) throws Exception {
        final ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            executor.submit(() -> System.out.printf("Running %s%n", Thread.currentThread().getName()));
        }
        executor.__________;
    }
}', null , null , null , 'shutdown()', null , null);

insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 5, 12, 18, 'byte b1 = 127;
b1 += 1;', 'b1 = 128', 'b1 = 0', 'Ошибка времени выполнения', 'b1 = -128', null , null );
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 5, 1, 3, 'public class MyFirst {
    public static void main(String[] args){
        int i, j;
        i = 10;
        j = 20;
        while(++i < --j);
        System.out.println(i);
    }
}', '10', '20', 'Ошибка компиляции', '15', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 5, 8, 18, 'System.out.println(Math.sqrt(-2.0));
System.out.println(1.0/0.0);', 'Infinity NaN', 'NaN NaN', 'Infinity Infinity', 'NaN Infinity', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 5, 9, 14, 'Операнды каких операторов всегда являются числами', '||', '?: (тернарный оператор)', '&& (2 амперсанда)', '%', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 5, 19, 37, 'Вставьте такой оператор, чтоб данный код выводил каждый пятый символ.', '++a', 'a++', null , 'a+b', 'a+=1', null);


insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 6, 12, 18, 'public class PrintTest {
    public static void main(String[] args) {
        System.out.println("1" + 2 + 3);
    }
}', 'Ошибка компиляции', '15', 'ClassCastException', '123', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 6, 19, 24, 'public class Quizful {
    public static void main(String[] args) {
        byte b = 127;
        b += 129;
        System.out.println(b);
    }
}', '128', '256', 'Ошибка компиляции', '0', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (1, 6, 12, 16, 'public class ByteTest {
    public static void main(String[] s) {
        byte b = 8;
         m(b);
         m(7);
    }
    static void m(byte b) {
        System.out.print("byte");
    }
}', '"bytebyte" без кавычек', 'ClassCastException', 'Ошибка времени выполнения', 'Ошибка компиляции', null , null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 6, 25, 48, 'Какие из указанных записей правильные?', 'boolean b1 = 0;', 'boolean b2 = ‘false’;', null , 'boolean b6 = Boolean.FALSE;', 'boolean b3 = false;', null);
insert into questions_rus (question_type, question_theme_id, right_answers, answers, question, incorrect_option1, incorrect_option2, incorrect_option3, correct_option1, correct_option2, correct_option3) values (2, 6, 49, 52, 'Какие из указанных строчек кода скомпиллируются без ошибок и предупреждений?', 'char c = "a";', 'boolean b = null;', 'float f = 1.3;', 'int i = 10;', null , null);

