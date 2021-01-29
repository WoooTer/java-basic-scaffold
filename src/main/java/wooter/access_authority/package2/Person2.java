package wooter.access_authority.package2;

import wooter.access_authority.package1.Person;

/**
 * Created by taota on 2019/3/27.
 */
public class Person2 extends Person {
    public void callFatherProtectedAttr(){
        System.out.println(size);
    }
}
