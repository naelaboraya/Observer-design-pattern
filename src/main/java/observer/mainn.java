package observer;

public class mainn {
    public static void main(String[] args) {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("aa");
        ConcreteMember CM2 = new ConcreteMember("bb");
        ConcreteMember CM3 = new ConcreteMember("CC");
        ConcreteMember CM4 = new ConcreteMember();
        ConcreteMember CM5 = new ConcreteMember();
        System.out.println(CM4.equals(CM5));
        GA.register(CM);
        GA.register(CM2);
        GA.register(CM3);
        GA.register(CM4);
        GA.register(CM5);
        GA.append("TEST");
        System.out.println(CM+","+CM2+","+CM3);
        GA.delete(1,2);
        System.out.println(CM+","+CM2+","+CM3);
        GA.unregister(CM3);
       // GA.unregister(CM2);
        GA.append("QQQ");

        System.out.println(CM+","+CM2+","+CM3);
        System.out.println("All ovbservers :");
        GA.printallobervers();
        System.out.println("------------------");

        System.out.println(CM3.getstate());
        System.out.println(GA.getState());
    }
}
