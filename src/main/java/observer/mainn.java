package observer;

public class mainn {
    public static void main(String[] args) {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("aa");
        ConcreteMember CM2 = new ConcreteMember("bb");
        ConcreteMember CM3 = new ConcreteMember("CC");

        GA.register(CM);
        GA.register(CM2);
        GA.register(CM3);
        GA.append("TEST");
        System.out.println(CM+","+CM2+","+CM3);
        GA.delete(1,2);
        System.out.println(CM+","+CM2+","+CM3);
        GA.unregister(CM3);
        GA.unregister(CM2);
        GA.append("QQQ");

        System.out.println(CM+","+CM2+","+CM3);
        System.out.println("All ovbservers ----------------");
        GA.printallobervers();
        System.out.println("------------------");
        System.out.println(CM3.getstate());

    }
}
