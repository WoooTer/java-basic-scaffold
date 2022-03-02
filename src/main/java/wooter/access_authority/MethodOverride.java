package wooter.access_authority;

public class MethodOverride {
    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

class Father {

    public void show() {
        showName();
        System.out.println(getClass().getSimpleName());
    }

    public void showName() {
        System.out.println("father");
    }
}

class Son extends Father {
    public void showName() {
        System.out.println("son");
    }
}
