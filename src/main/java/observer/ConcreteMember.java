package observer;

/**
 * This class represents the "observer" that gets notified when a change happens to the
 * {@code GroupAdmin} {@code state} ({@code UndoableStringBuilder})
 *
 *  @author Nael Aboraya , Marwan Hresh
 */
public class ConcreteMember implements Member{
    private String name;//the observer name
    private UndoableStringBuilder observer_state;//the state of this observer




    public UndoableStringBuilder getstate(){
        return this.observer_state;
    }


    public ConcreteMember(String name){
        this.name = name;
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        UndoableStringBuilder copy = new UndoableStringBuilder();
        copy.append(usb.toString());
        observer_state = copy;
    }


    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name='" + name + '\'' +
                ", observer_state=" + observer_state +

                '}';
    }
}



