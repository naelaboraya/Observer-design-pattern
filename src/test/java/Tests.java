import observer.*;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    //This test checks and shows the size of some objects of types GroupAdmin and ConcreteMember in the memory.
    @Test
    public void testObjectSize(){

        UndoableStringBuilder usb = new UndoableStringBuilder();
        System.out.println("UndoableStingBuilder object size =>"+JvmUtilities.objectTotalSize(usb));
        System.out.println(JvmUtilities.objectFootprint(usb));

        usb.append("HI");
        System.out.println("UndoableStingBuilder object size after appending \"HI\" =>"+JvmUtilities.objectTotalSize(usb));
        System.out.println(JvmUtilities.objectFootprint(usb));

        System.out.println("**************************************************************************************");

        GroupAdmin GA = new GroupAdmin();
        System.out.println("GroupAdmin object size =>"+JvmUtilities.objectTotalSize(GA));
        System.out.println(JvmUtilities.objectFootprint(GA));

        System.out.println("**************************************************************************************");

        ConcreteMember CM = new ConcreteMember();
        System.out.println("Concrete Member object size =>"+JvmUtilities.objectTotalSize(CM));
        System.out.println(JvmUtilities.objectFootprint(CM));


        System.out.println("**************************************************************************************");



        GroupAdmin GAA = new GroupAdmin();
        ConcreteMember cmm = new ConcreteMember("nael");
        ConcreteMember cm1 = new ConcreteMember("Marwan");

        GAA.register(cmm);
        GAA.register(cm1);

        GAA.append("zzz");
        GAA.delete(0,1);
        System.out.println("GroupAdmin object size and footprints after some registrations and operations => "+JvmUtilities.memoryStats(GAA));


        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");


        logger.info(() -> JvmUtilities.jvmInfo());

    }

    //This test checks register method.
    @Test
    public void testRegister(){
        System.out.println("---------------register------------------");
        Sender groupAdmin = new GroupAdmin();
        Sender groupAdmin2 = new GroupAdmin();

        Member concreteMember = new ConcreteMember("nael");
        Member concreteMember2 = new ConcreteMember("Marwan");

        groupAdmin.register(new ConcreteMember());
        groupAdmin.register(concreteMember);
        groupAdmin.register(concreteMember2);

        //checking if there is really 3 members in the observable hashmap
        System.out.println(((GroupAdmin)groupAdmin).getallobervers());
        assertEquals(3,((GroupAdmin)groupAdmin).getallobervers().size());
        assertTrue(((GroupAdmin) groupAdmin).getallobervers().containsKey(concreteMember) &&
                ((GroupAdmin) groupAdmin).getallobervers().containsKey(concreteMember2) &&
                ((GroupAdmin) groupAdmin).getallobervers().containsValue("Anonymous"));
        groupAdmin.append("TEST!!!");
        //checking if "TEST!!!"  is added for the 3 (printing)
        System.out.println(((GroupAdmin)groupAdmin).getallobervers());


        //trying to register concreteMember to grouAdmin
        Exception exception = assertThrows(RuntimeException.class, () -> {
            groupAdmin.register(concreteMember);
        });
        assertEquals("The member is already registered to this GroupAdmin !", exception.getMessage());


        //trying to register concreteMember to groupAdmin2
        Exception exception2 = assertThrows(RuntimeException.class, () -> {
            groupAdmin2.register(concreteMember);
        });
        assertEquals("The member is registered to another GroupAdmin !", exception2.getMessage());


    }

    //This test checks unregister method.
    @Test
    public void testUnregister(){
        System.out.println("---------------unregister------------------");
        Sender groupAdmin = new GroupAdmin();
        Sender groupAdmin2 = new GroupAdmin();

        Member concreteMember = new ConcreteMember("nael");
        Member concreteMember2 = new ConcreteMember("Marwan");

        groupAdmin.register(new ConcreteMember());
        groupAdmin.register(concreteMember);
        groupAdmin.register(concreteMember2);


        assertEquals(3,((GroupAdmin)groupAdmin).getallobervers().size()); //should be 3 first
        System.out.println(((GroupAdmin)groupAdmin).getallobervers());
        groupAdmin.unregister(concreteMember);
        assertEquals(2,((GroupAdmin)groupAdmin).getallobervers().size());//should be 2 now
        //checking which members still registered
        System.out.println("After unregistering 'nael': ");

        assertFalse(((GroupAdmin) groupAdmin).getState().equals(((ConcreteMember) concreteMember).getstate()));
        assertFalse(((GroupAdmin) groupAdmin).getallobervers().containsKey(concreteMember));
        assertTrue(((ConcreteMember) concreteMember2).getstate().equals(((GroupAdmin) groupAdmin).getState()));
        System.out.println(((GroupAdmin)groupAdmin).getallobervers());

        groupAdmin.append("TEST!!!");

        //checking if "TEST!!!"  is added for the 2 (printing)
        System.out.println(((GroupAdmin)groupAdmin).getallobervers());
        assertNull(((ConcreteMember)concreteMember).getstate());//we implemented that the member's state turn to null after unregistering (Not necessary)

        assertFalse(((GroupAdmin) groupAdmin).getState().equals(((ConcreteMember) concreteMember).getstate()));
        assertTrue(((ConcreteMember) concreteMember2).getstate().equals(((GroupAdmin) groupAdmin).getState()));


        //trying to unregister a concreteMember that is not registered to a GroupAdmin
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            groupAdmin2.unregister(concreteMember);
        });
        assertEquals("Observer is not registered !", exception.getMessage());


    }


    //This test checks if the operations of the UndoableStringBuilder work well and checks notifyAllObservers
    //method in GroupAdmin , and update method in ConcreteMember as well .
    //In this test we also used register,unregister method to check to which members the change has applied
    @Test
    public void testOperations(){//testing the operations (append,delete,insert,undo) on GroupAdmin and Members
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();
        ga.register(cm1);
        ga.register(cm2);
        ga.setState(new UndoableStringBuilder("Hello"));
        //checking if the registered observers' state equals ga's state
        assertEquals(ga.getState(),cm1.getstate());
        assertEquals(cm2.getstate(),"Hello");
        assertEquals(cm1.getstate(),cm2.getstate());

        //checking append operation :
        ga.append("World");
        assertEquals("HelloWorld",cm1.getstate().toString());
        assertTrue(cm1.getstate().equals(cm2.getstate()));

        //checking insert operation :
        ga.insert(5,"My");
        assertEquals(ga.getState(),("HelloMyWorld"));
        assertEquals("HelloMyWorld",cm1.getstate().toString());
        assertTrue(cm1.getstate().equals(cm2.getstate()));

        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            ga.insert(-4,"testinsert");//offset is invalid
        });

        ga.unregister(cm2);//cm2 stops taking notifications

        //checking delete operation :
        ga.delete(0,1);
        assertTrue(cm1.getstate().equals("elloMyWorld"));
        assertFalse(cm1.getstate().equals(cm2.getstate()));
        assertNull(cm2.getstate());

        assertThrows(StringIndexOutOfBoundsException.class,()->{
            ga.delete(2,1);//start is greater than end
        });

        //checking the undo operation :
        ga.undo();//the last state before "elloMyWorld" was "HelloMyWorld"
        assertTrue(ga.getState().equals("HelloMyWorld"));
        assertEquals(ga.getState(),cm1.getstate());
        assertEquals("HelloMyWorld",cm1.getstate().toString());
        assertNull(cm2.getstate());

        ga.undo();//another undo -> "HelloWorld"
        assertTrue(cm1.getstate().equals("HelloWorld"));
    }
}
