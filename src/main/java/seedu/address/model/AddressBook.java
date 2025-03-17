package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents an address book that stores a list of persons and groups.
 * Ensures that no duplicate persons or groups exist based on identity comparisons.
 */
public class AddressBook implements ReadOnlyAddressBook {

    /** The list of unique persons in the address book. */
    private final UniquePersonList persons;

    /** The list of unique groups in the address book. */
    private final UniqueGroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new UniqueGroupList();
    }

    /**
     * Constructs an empty AddressBook.
     */
    public AddressBook() {}

    /**
     * Constructs an AddressBook using the data from an existing {@code ReadOnlyAddressBook}.
     *
     * @param toBeCopied The address book whose data should be copied.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// List overwrite operations

    /**
     * Replaces the contents of the person list with a new list of persons.
     * Ensures that the new list does not contain duplicate persons.
     *
     * @param persons The new list of persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with new data.
     *
     * @param newData The new data to replace the current address book data.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// Person-level operations

    /**
     * Checks if a person with the same identity as {@code person} exists in the address book.
     *
     * @param person The person to check.
     * @return True if the person exists, false otherwise.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a new person to the address book.
     * Ensures that the person does not already exist in the address book.
     *
     * @param p The person to add.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces a target person with an edited person in the address book.
     * Ensures that the target exists and that the edited person does not duplicate another existing person.
     *
     * @param target The person to be replaced.
     * @param editedPerson The new person data.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes a person from the address book.
     * Ensures that the person exists before removal.
     *
     * @param key The person to remove.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// Group-level operations

    /**
     * Checks if a group with the same identity as {@code group} exists in the address book.
     *
     * @param group The group to check.
     * @return True if the group exists, false otherwise.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Removes a group from the address book.
     * Ensures that the group exists before removal.
     *
     * @param key The group to remove.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Replaces a target group with an edited group in the address book.
     * Ensures that the target exists and the edited group does not duplicate another existing group.
     *
     * @param target The group to be replaced.
     * @param editedGroup The new group data.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);
        groups.setGroup(target, editedGroup);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    //// Utility methods

    /**
     * Returns an unmodifiable view of the list of persons.
     *
     * @return An observable list of persons.
     */
    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the list of groups.
     *
     * @return An observable list of groups.
     */
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    /**
     * Returns a string representation of the AddressBook object.
     *
     * @return A string describing the address book.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    /**
     * Checks if this AddressBook is equal to another object.
     *
     * @param other The other object to compare to.
     * @return True if both objects are AddressBooks with the same persons list.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles null cases
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    /**
     * Returns the hash code of the AddressBook.
     *
     * @return The hash code based on the persons list.
     */
    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
