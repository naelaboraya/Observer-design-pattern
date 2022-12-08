package observer;

/**
 * This class represents the "observer" that gets notified when a change happens to the
 * {@code GroupAdmin} {@code state} ({@code UndoableStringBuilder})
 *
 *  @author Nael Aboraya , Marwan Hresh
 */
public class ConcreteMember implements Member{
    private String name;//the observer name
    private UndoableStringBuilder observer_state;
    private GroupAdmin sender;

    /*
    public UndoableStringBuilder getstate(){
        return this.observer_state;
    }
   */


    public ConcreteMember(GroupAdmin sender , String name ){
        this.sender = sender;
        this.name = name;
    }
    //stamm
    @Override
    public void update(UndoableStringBuilder usb) {
        observer_state = this.sender.getState();
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name='" + name + '\'' +
                ", observer_state=" + observer_state +
                ", sender=" + sender +
                '}';
    }
}



/// shallow copy State !!!!! ????