package com.example.addressbook;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.awt.event.MouseEvent;
import java.util.List;

public class MainController {
    @FXML
    private ListView<Contact> contactsListView;
    private IContactDAO contactDAO;
    @FXML
    private VBox contactContainer;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    public MainController(){
        contactDAO = new MockContactDAO();
    }

    private void selectContact(Contact contact){
        contactsListView.getSelectionModel().select(contact);
        firstNameTextField.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        emailTextField.setText(contact.getEmail());
        phoneTextField.setText(contact.getPhone());

    }

    @FXML
    private void onEditConfirm(){
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            selectedContact.setFirstName(firstNameTextField.getText());
            selectedContact.setLastName(lastNameTextField.getText());
            selectedContact.setEmail(emailTextField.getText());
            selectedContact.setPhone(phoneTextField.getText());
            syncContacts();
        }
    }

    @FXML
    private void onDelete(){
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            contactDAO.deleteContact(selectedContact);
            syncContacts();
        }

    }

    @FXML
    private void onAdd(){
        final String DEFAULT_FIRST_NAME = "New";
        final String DEFAULT_LAST_NAME = "Contact";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PHONE = "";
        Contact newContact = new Contact(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PHONE);

        contactDAO.addContact(newContact);
        syncContacts();

        selectContact(newContact);
        firstNameTextField.requestFocus();
    }

    @FXML
    private void onCancel(){
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            selectContact(selectedContact);
        }
    }

    private ListCell<Contact> renderCell(ListView<Contact> contactListView){
        return new ListCell<>(){
            @Override
            protected void updateItem(Contact contact, boolean empty){
                super.updateItem(contact, empty);
                if (empty || contact == null || contact.getFullName() == null){
                    setText(null);
                    super.setOnMouseClicked(this::onContactSelected);
                } else {
                    setText(contact.getFullName());
                }
            }

            private void onContactSelected(javafx.scene.input.MouseEvent mouseEvent) {
                ListCell<Contact> clickedCell = (ListCell<Contact>) mouseEvent.getSource();
                Contact selectedContact = clickedCell.getItem();
                if(selectedContact != null) selectContact(selectedContact);
            }
        };
    }

    private void syncContacts(){
        Contact currentContact = contactsListView.getSelectionModel().getSelectedItem();
        contactsListView.getItems().clear();
        List<Contact> contacts = contactDAO.getAllContacts();
        boolean hasContact = !contacts.isEmpty();
        if (hasContact){
            contactsListView.getItems().addAll(contacts);
        }
        contactContainer.setVisible(hasContact);
        Contact nextContact = contacts.contains(currentContact) ? currentContact : contacts.get(0);
        contactsListView.getSelectionModel().select(nextContact);
        selectContact(nextContact);
    }

    @FXML
    public void initialize(){
        contactsListView.setCellFactory(this::renderCell);
        syncContacts();
        contactsListView.getSelectionModel().selectFirst();
        Contact firstContact = contactsListView.getSelectionModel().getSelectedItem();
        if (firstContact != null){
            selectContact(firstContact);
        }
    }

}
