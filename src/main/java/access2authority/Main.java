package access2authority;

import access2authority.package1.Animal;
import access2authority.package1.Person;
import access2authority.package2.Person2;
import access2authority.package2.Person3;

/**
 * 类在继承结构中的同名属性是共存的，不会覆盖。
 * 变量声明的类型是什么，就会访问与该类型最近的属性值
 */
class Main1{
    public static void main(String[] args) {
        Animal a = new Person();
        System.out.println(a.name);   //打印"animalName"
        Person p = (Person)a;
        System.out.println(p.name);   //打印"personName"
    }
}

/**
 * 类在继承结构中的同名方法是不会共存的，会覆盖。
 * 变量的实际指向的类型是什么，就会访问该类型的方法
 */
class Main2{
    public static void main(String[] args) {
        Animal a = new Person();
        a.howl();     //打印"Hi"
        Animal a2 = (Animal)a;
        a2.howl();    //还是打印"Hi"
    }
}

/**
 * 父类的 protected字段，可以被他所有子类访问，无论这个子类是继承了多少层
 */
class Main3{
    public static void main(String[] args) {
        Person2 p2 = new Person2();
        p2.callFatherProtectedAttr();
        Person3 p3 = new Person3();
        p3.callFathersFatherProtectedAttr();
    }
}