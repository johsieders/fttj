package de.qaware.fasttrack.warmingup;

public class OuterClass {

    private  static final String staticOuterString = "static outer string";
    private String dynamicOuterString;
    private final InnerClass dynamicInnerObject;
    private final StaticInnerClass staticInnerObject;

    public OuterClass(String dynamicOuterString) {
        this.dynamicOuterString = dynamicOuterString;
        dynamicInnerObject = new InnerClass("dynamic inner");
        staticInnerObject = new StaticInnerClass(this,"static dynamic inner");
    }

    public String toString() {
        return "outer class\n" +
                staticOuterString + "\n" +
                dynamicOuterString + "\n" +
                dynamicInnerObject + "\n" +
                staticInnerObject + "\n";
    }

    class InnerClass {
        private static final String staticInnerString = "static inner string";
        private final String dynamicInnerString;

        public InnerClass(String dynamicInnerString) {
            this.dynamicInnerString = dynamicInnerString;
            dynamicOuterString += "\nnew inner object added";       // OuterClass.this.dyn.. += ...
        }

        public String toString() {
            return "dynamic inner class\n" +
                    dynamicOuterString + "\n" +
                    staticOuterString + "\n" +
                    dynamicInnerString + "\n";
        }
    }

    static class StaticInnerClass {
        private static final String staticInnerString = "static inner string";
        private final String dynamicInnerString;
        private final OuterClass parent;

        public StaticInnerClass(OuterClass parent, String dynamicInnerString) {
            this.parent = parent;
            this.dynamicInnerString = dynamicInnerString;
            parent.dynamicOuterString += "\nnew static inner class added";
        }

        public String toString() {
            return "static inner class\n" +
                    parent.dynamicOuterString + "\n" +
                    staticOuterString + "\n" +
                    dynamicInnerString + "\n" +
                    staticInnerString + "\n";
        }
    }


    public static void main(String[] args) {
        OuterClass outer = new OuterClass("dynamic outer string");
        System.out.println(outer);
    }
}
