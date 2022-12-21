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
        StringBuilder usb = new StringBuilder();
        StringBuilder usb2 = new StringBuilder();
       for (int i=0 ; i<10000 ; i++){
           usb.append("1");
           usb2.append("2");
       }


        logger.info(()->JvmUtilities.objectFootprint(usb));

        logger.info(()->JvmUtilities.objectFootprint(usb,usb2));

        logger.info(()->JvmUtilities.objectTotalSize(usb));

        logger.info(()->JvmUtilities.memoryStats(usb));

        logger.info(() -> JvmUtilities.jvmInfo());


    }
}
