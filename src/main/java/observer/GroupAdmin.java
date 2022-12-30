package observer;

import java.util.*;

/**
 * This class represents the "observable" that sends notification to its observers when a change happen to the
 * state ({@code UndoableStringBuilder})
 * In this class , the state's type is UndoableStringBuilder , the observers are stored in a Hashmap , Members as keys
 * and their names as values.
 * <p>This class has 3 main methods : {@code register} which registers a Member to this GroupAdmin , {@code unregister}
 * which unregisters a Member and {@code notifyAllMembers} which notifies/updates the registered Members that a change
 * happened to the state of this GroupAdmin.
 * @author Nael Aboraya , Marwan Hresh
 */
public class GroupAdmin implements Sender{


    private UndoableStringBuilder USB_state;//the state..

    //A Map of observers that should be notified in case of change to the state. (The key is the Member itself , and the
    // value is the name of the Member)
    private Map<Member,String> observers ;


    /**
     * Constructor that initializes the state (UndoableStringBuilder) of the GroupAdmin that has been constructed ,
     * as well as initializing the data structure that stores the observers/members that observe the GroupAdmin
     */
    public GroupAdmin(){
        this.USB_state = new UndoableStringBuilder();//the subject state
        this.observers = new HashMap<>();//initializing the map of all observers that observe this group admin
    }


    //getter and setter :
    /**
     * getter
     * <p>gets the current state/UndoableStringBuilder of this observable.
     * @return {@code USB_state} (the state of this observable).
     */
    public UndoableStringBuilder getState(){
        return this.USB_state;
    }

    /**
     * setter
     * <p>
     * sets the current state as the given state (new_state) and notifies the observers that a change  has happened
     * @param new_state
     */
    public void setState(UndoableStringBuilder new_state){
        this.USB_state = new_state;
        notifyAllObservers();
    }

    /**
     * This method registers an observer/member to this observable by adding the member to the observers/members list.
     * <p>
     * After registering the member , the method calls the update method that updates the member state/UndoableStrongBuilder.
     * @param obj
     */
    @Override
    public void register(Member obj) {
        //checking if the member is registered to this GroupAdmin
        if(this.observers.containsKey(obj))
            throw new RuntimeException("The member is already registered to this GroupAdmin !");
        //checking if the member is registered to another GroupAdmin
        if(!this.observers.containsKey(obj) && ((ConcreteMember)obj).isRegistered==true)
            throw new RuntimeException("The member is registered to another GroupAdmin !");
        //else (the member is not registered to any GroupAdmin)
        this.observers.put(obj,((ConcreteMember)obj).getName());
        obj.update(this.getState());
        ((ConcreteMember) obj).isRegistered = true;

    }


    /**
     * This method unregisters an observer/member to this observable by removing the given observer from the list of observers.
     *<p>After removing the observer , the observer is updated to be null.
     * @param obj
     * @throws NoSuchElementException in case of trying to unregister a member that is not registered (not observing this observable).
     */
    @Override
    public void unregister(Member obj) throws NoSuchElementException {
        if(!this.observers.containsKey(obj))
            throw new NoSuchElementException("Observer is not registered !");
        this.observers.remove(obj);
        obj.update(null);
        ((ConcreteMember) obj).isRegistered = false;
    }


    /**
     * Applies insertion to the state of this observable  (calls insert() function of class UndoableStringBuilder).
     * <p>After the insertion , it calls notifyAllObservers() method and updates all the observer of this
     * observable that a change has happened to the state/UndoableStringBuilder.
     * @param offset
     * @param obj
     * @throws StringIndexOutOfBoundsException  if the offset is invalid
     */
    @Override
    public void insert(int offset, String obj) throws StringIndexOutOfBoundsException{
        this.USB_state.insert(offset,obj);
        notifyAllObservers();
    }


    /**
     * Applies appending to the state of this observable  (calls append() function of class UndoableStringBuilder).
     * <p>After the appending , it calls notifyAllObservers() method and updates all the observer of this
     * observable that a change has happened to the state/UndoableStringBuilder.
     * @param obj
     */
    @Override
    public void append(String obj) {
        this.USB_state.append(obj);
        notifyAllObservers();
    }


    /**
     * Applies deletion to the state of this observable  (calls delete() function of class UndoableStringBuilder).
     * <p>After the deletion , it calls notifyAllObservers() method and updates all the observer of this
     * observable that a change has happened to the state/UndoableStringBuilder.
     * @param start
     * @param end
     * @throws StringIndexOutOfBoundsException if {@code start}
     * is negative, greater than {@code length()}, or
     * greater than {@code end}.
     */
    @Override
    public void delete(int start, int end) throws StringIndexOutOfBoundsException{
        this.USB_state.delete(start,end);
        notifyAllObservers();
    }


    /**
     * Applies an undo operation to the state of this observable  (calls undo() function of class UndoableStringBuilder).
     * <p>After the undo operation , it calls notifyAllObservers() method and updates all the observer of this
     * observable that a change has happened to the state/UndoableStringBuilder.
     */
    @Override
    public void undo() {
        this.USB_state.undo();
        notifyAllObservers();
    }


    /**
     * updates all the registered observers with the current state/UndoableStringBuilder
     * <p>This method is used in every method that changes/modifies the State/UndoableStringBuilder of this observable
     * to notify the observers that the state has changed.
     */
    private void notifyAllObservers(){
        for (Member observer : observers.keySet()) {
            observer.update(this.getState());
        }
    }

    //A method that prints all the observers that are observing this observable.
//    public void printallobervers(){
//        for (Map.Entry<Member, String> entry : observers.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }

    public Map<Member,String> getallobervers(){
        return this.observers;
    }


    //toString : describes this observable
    @Override
    public String toString() {
        return "state : "+this.USB_state+" , observers : "+this.observers.toString();
    }
}
