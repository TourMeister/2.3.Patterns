//package ru.netology.data;
//import java.io.File;
//import java.util.List;
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//
///** ПОКА НЕ РАЗОБРАЛСЯ В ЭТОМ */
//
//
///**
// * Helper class to wrap a list of persons. This is used for saving the
// * list of persons to XML.
// *
// * @author Marco Jakob
// */
//@XmlRootElement(name = "persons")
//public class PersonListWrapper {
//
//    private List<Person> persons;
//
//    @XmlElement(name = "person")
//    public List<Person> getPersons() {
//        return persons;
//    }
//
//    public void setPersons(List<Person> persons) {
//        this.persons = persons;
//    }
//
//  /*  Обратите внимание на две заметки:
//
//    @XmlRootElementОпределите имя корневого элемента.
//    @XmlElementНеобязательное имя, используемое для указания элемента.
//    Чтение и запись данных с использованием JAXB
//
//    Мы позволяемMainAppКласс отвечает за чтение и запись данных персонала. Добавьте следующие два метода вMainApp.javaПоследний:
//*/
//    /**
//     * Loads person data from the specified file. The current person data will
//     * be replaced.
//     *
//     * @param file
//     */
//    public void loadPersonDataFromFile(File file) {
//        try {
//            JAXBContext context = JAXBContext
//                    .newInstance(PersonListWrapper.class);
//            Unmarshaller um = context.createUnmarshaller();
//
//            // Reading XML from the file and unmarshalling.
//            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
//
//            personData.clear();
//            personData.addAll(wrapper.getPersons());
//
//            // Save the file path to the registry.
//            setPersonFilePath(file);
//
//        } catch (Exception e) { // catches ANY exception
//            Dialogs.create()
//                    .title("Error")
//                    .masthead("Could not load data from file:\n" + file.getPath())
//                    .showException(e);
//        }
//    }
//
//    /**
//     * Saves the current person data to the specified file.
//     *
//     * @param file
//     */
//    public void savePersonDataToFile(File file) {
//        try {
//            JAXBContext context = JAXBContext
//                    .newInstance(PersonListWrapper.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            // Wrapping our person data.
//            PersonListWrapper wrapper = new PersonListWrapper();
//            wrapper.setPersons(personData);
//
//            // Marshalling and saving XML to the file.
//            m.marshal(wrapper, file);
//
//            // Save the file path to the registry.
//            setPersonFilePath(file);
//        } catch (Exception e) { // catches ANY exception
//            Dialogs.create().title("Error")
//                    .masthead("Could not save data to file:\n" + file.getPath())
//                    .showException(e);
//        }
//    }
//}