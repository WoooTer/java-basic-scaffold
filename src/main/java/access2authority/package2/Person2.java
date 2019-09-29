package access2authority.package2;

import access2authority.package1.Person;

/**
 * Created by taota on 2019/3/27.
 */
public class Person2 extends Person {
    public void callFatherProtectedAttr(){
        System.out.println(size);
    }
}
