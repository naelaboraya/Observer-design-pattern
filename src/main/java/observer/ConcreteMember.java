package observer;

/**
 * This class represents the "observer" that gets notified when a change happens to the
 * {@code GroupAdmin} {@code state} ({@code UndoableStringBuilder})
 *
 *  @author Nael Aboraya , Marwan Hresh
 */
public class ConcreteMember implements Member{

    private String name;//the name of the observer
    private UndoableStringBuilder observer_state;//the state of this observer (changes when the GroupAdmin notifies it)


    final boolean Default = false;
    public boolean isRegistered = Default;

    //Constructor : takes nothing
    public ConcreteMember(){this.name="Anonymous";}
    //Constructor : takes a name as String and puts it to this observer
    public ConcreteMember(String name){
        this.name = name;
    }

    public UndoableStringBuilder getstate(){return this.observer_state;}


    public String getName(){return this.name;}


    /**
     * Updates the state/UndoableStringBuilder of this observer to {@code usb} (given state.)
     * <p>Makes this state point at usb (shallow copy).
     * <p>This method is called generally in {@code notifyAllObservers} method in GroupAdmin which is called after every
     * change to the state of the GroupAdmin.
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
//        UndoableStringBuilder copy = new UndoableStringBuilder();
//        copy.append(usb.toString());
//        observer_state = copy;
        observer_state=usb;


    }


    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name='" + name + '\'' +
                ", observer_state=" + observer_state +

                '}';
    }
}



