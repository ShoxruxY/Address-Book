package com.example.addressbook;
import java.util.List;
import java.util.ArrayList;

public class MockContactDAO implements IContactDAO{
    public static final ArrayList<Contact> contacts = new ArrayList<>();
    private static int autoIncremented = 0;
    public MockContactDAO(){
        addContact(new Contact("John","Doe","johndoed@example.com","0423423423"));
        addContact(new Contact("Jane","Doe","janedoe@example.com", "0423423424"));
        addContact(new Contact("Jay", "Doe","jaydoe@exmaple.com","0423423425"));
        addContact(new Contact("Jerry","Doe","jerrydoe@example.com", "0423423426"));

    }

    @Override
    public void addContact(Contact contact){
        contact.setId(autoIncremented);
        autoIncremented++;
        contacts.add(contact);
    }

    @Override
    public void updateContact(Contact contact){
        for (int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getId() == contact.getId()){
                contacts.set(i, contact);
            }
        }
    }

    @Override
    public void deleteContact(Contact contact){
        contacts.remove(contact);
    }

    @Override
    public Contact getContact(int id){
        for(Contact contact : contacts){
            if(contact.getId() == id){
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getAllContacts(){
        return new ArrayList<Contact>(contacts);
    }

}
