import com.sun.istack.internal.SAXParseException2;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import model.Cinema;
import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBException;

/**
 * Created by asus-pc on 01.11.2016.
 */
public class MainClass {

    public static void main(String[] args) {

        if (args[0].equals("marshall")) {
            Cinema cinema = new Cinema();

            Cinema.Actor actor1 = new Cinema.Actor();
            actor1.setName("Leo");
            actor1.setNumFilms(678);
            actor1.setBirthday(XMLGregorianCalendarImpl.createDateTime(1974, 11, 11, 4, 4, 4));
            actor1.setSalary(12);

            Cinema.Actor actor2 = new Cinema.Actor();
            actor2.setName("Kate");
            actor2.setNumFilms(678);
            actor2.setBirthday(XMLGregorianCalendarImpl.createDateTime(1974, 11, 11, 4, 4, 4));
            actor2.setSalary(12);

            cinema.getActor().add(actor1);
            cinema.getActor().add(actor2);
            try {
                Singleton.saveToFile(cinema);
            } catch (JAXBException e) {
                SAXParseException exp = (SAXParseException)e.getLinkedException();
                System.out.println("Error in " + exp.getLineNumber() + " line and " + exp.getColumnNumber() + " column." + exp.getLocalizedMessage());
                return;
            }
        } else if (args[0].equals("unmarshall")) {
            System.out.println("<html>");
            System.out.println("<head><head/>");
            System.out.println("<body>");
            System.out.println("      <table>");
            System.out.println("         <thead>");
            System.out.println("            <tr>");
            System.out.println("               <th>Actor</th>");
            System.out.println("               <th>Birthday</th>");
            System.out.println("               <th>NumFilms</th>");
            System.out.println("               <th>Salary</th>");
            System.out.println("               <th>SumSalary</th>");
            System.out.println("            </tr>");
            System.out.println("         </thead>");
            System.out.println("         <tbody>");

            Integer overSum = 0;
            try {
                for (Cinema.Actor actor : Singleton.getCinema().getActor()) {
                    System.out.print("<tr>");

                    System.out.print("<td>");
                    System.out.print(actor.getName());
                    System.out.print("</td>");

                    System.out.print("<td>");
                    System.out.print(actor.getBirthday());
                    System.out.print("</td>");

                    System.out.print("<td>");
                    System.out.print(actor.getNumFilms());
                    System.out.print("</td>");

                    System.out.print("<td>");
                    System.out.print(actor.getSalary());
                    System.out.print("</td>");

                    System.out.print("<td>");
                    System.out.print(actor.getSalary() * actor.getNumFilms());
                    overSum += actor.getSalary() * actor.getNumFilms();
                    System.out.print("</td>");

                    System.out.println("</tr>");
                }
            } catch (JAXBException e) {
                SAXParseException exp = (SAXParseException)e.getLinkedException();
                System.out.println("Error in " + exp.getLineNumber() + " line and " + exp.getColumnNumber() + " column." + exp.getLocalizedMessage());
                return;
            }

            System.out.println("<td></td> <td></td> <td></td> <td></td> <td><b> " + overSum + "</b></td>");

            System.out.println("</tbody>");
            System.out.println("</table>");
            System.out.println("</body>");
            System.out.println("</html>");
        }
    }

}
