package net.lax1dude.eaglercraft.v1_8.internal.teavm;

public class ForceReflection {
    public static Object myObject;

    public static Object forceInit(Class iClass) {
        myObject = new ReflectiveClass();
        try {
            myObject = iClass.newInstance();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return myObject;
    }

    public static class ReflectiveClass {
    }
}