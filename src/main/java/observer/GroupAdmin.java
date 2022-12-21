package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the "observable" that sends notification to its observers when a change happen to the
 * {@code state} ({@code UndoableStringBuilder})
 *
 * @author Nael Aboraya , Marwan Hresh
 */
public class GroupAdmin implements Sender{
    private String name;

    //List of observers that should be notified in case of change..
    private List<Member> observers = new ArrayList<Member>();

    //the state..
    private UndoableStringBuilder USB_state;

    public GroupAdmin(){
        this.name="ZZ";
        this.observers = new ArrayList<>();
        this.USB_state = new UndoableStringBuilder();
    }

    public UndoableStringBuilder getState(){
        return this.USB_state;
    }
    public void setState(UndoableStringBuilder new_state){
        this.USB_state = new_state;
        notifyAllObservers();
    }

    @Override
    public void register(Member obj) {
        this.observers.add(obj);
        obj.update(this.getState());
    }

    @Override
    public void unregister(Member obj) {
        if(!this.observers.contains(obj))
            throw new RuntimeException("Observer is not registered");
        this.observers.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        this.USB_state.insert(offset,obj);
        notifyAllObservers();
    }

    @Override
    public void append(String obj) {
        this.USB_state.append(obj);
        notifyAllObservers();
    }

    @Override
    public void delete(int start, int end) {
        this.USB_state.delete(start,end);
        notifyAllObservers();
    }

    @Override
    public void undo() {
        this.USB_state.undo();
        notifyAllObservers();
    }

    public void notifyAllObservers(){
        for (Member observer : observers){
            observer.update(this.getState());
        }
    }

    public void printallobervers(){
        for (Member observer : observers){
            System.out.println(observer);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
