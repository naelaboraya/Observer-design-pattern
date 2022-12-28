import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Sender;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        //String usb = "Alice";
        //String usb2 = "Bob";
        UndoableStringBuilder usb = new UndoableStringBuilder();
        UndoableStringBuilder usb2 = new UndoableStringBuilder();
       for (int i=0 ; i<1000 ; i++){
           usb.append("1");
           usb2.append("2");
       }
        GroupAdmin GA = new GroupAdmin();
        GA.register(new ConcreteMember("aa"));
        //GA.append("String_state_1");
        GroupAdmin GA2 = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("bb");
        GA2.register(cm);
        GA2.append("zzz");
        GA2.delete(0,1);

        GroupAdmin GAA = new GroupAdmin();
        ConcreteMember cmm = new ConcreteMember("tal");
        ConcreteMember cm1 = new ConcreteMember("asaf");

        GAA.register(cmm);
        GAA.register(cm1);

        String uusb = "211640925aksdfjlkajfa";
        int a = 211640925;
        System.out.println(JvmUtilities.objectTotalSize(uusb));
        System.out.println(JvmUtilities.objectFootprint(uusb));

        System.out.println(JvmUtilities.objectTotalSize(a));
        System.out.println(JvmUtilities.objectFootprint(a));
       // System.out.println(JvmUtilities.objectTotalSize(GA));

//        logger.info(()->JvmUtilities.objectFootprint(usb));
//
//        logger.info(()->JvmUtilities.objectTotalSize(usb));
//
//        //logger.info(()->JvmUtilities.memoryStats(GA));
//        System.out.println("---------------------------------------------------------------------------------------");
//        logger.info(()->JvmUtilities.objectFootprint(usb2));
//
//        //logger.info(()->JvmUtilities.objectFootprint(GA2));
//
//        logger.info(()->JvmUtilities.objectTotalSize(usb2));
//
//       // logger.info(()->JvmUtilities.memoryStats(GA2));
//
//        logger.info(() -> JvmUtilities.jvmInfo());


    }
}
