package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;
    InnerObject innerObject;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        TestObject testObj = (TestObject) super.clone();
        testObj.innerObject = innerObject.clone();
        return testObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject testObj1 = new TestObject();
        testObj1.num = 5;
        InnerObject innerObj = new InnerObject();
        innerObj.num = 15;
        testObj1.innerObject = innerObj;

        TestObject testObj2 = testObj1.clone();
        testObj2.num = 25;
        testObj2.innerObject.num = 35;

        System.out.println("testObj1 : " + testObj1);
        System.out.println("testObj1.innerObject : " + testObj1.innerObject);
        System.out.println("testObj2 : " + testObj2);
        System.out.println("testObj2.innerObject : " + testObj2.innerObject);

        System.out.println("Исходный класс: " + testObj1.num);
        System.out.println("Исходный класс: " + testObj1.innerObject.num);
        System.out.println("Исходный класс: " + testObj2.num);
        System.out.println("Исходный класс: " + testObj2.innerObject.num);
    }
}
