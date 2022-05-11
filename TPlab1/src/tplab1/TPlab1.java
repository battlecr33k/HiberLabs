package tplab1;

import entity.Gruppyi;
import entity.Studentyi;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class TPlab1 {

    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Transaction transaction = s.beginTransaction();
        transaction.commit();

        List<Gruppyi> grs = s.createQuery("from Gruppyi s").list();
        List<Studentyi> q = s.createQuery("from Studentyi s").list();
        Transaction t = s.beginTransaction();

        //      1.  Для каждой группы вывести количество отчисленных студентов
        int n = 0;
        
        for (Gruppyi g : grs) {
            for (Studentyi u : q) {
                if (u.getStatus().equals("expelled") & g.getShifr().equals(u.getGruppyi().getShifr())) {
                    n++;
                }
            }
            System.out.println("В группе " + g.getNazvanie() + " кол-во отчисленных студентов: " + n);
            n = 0;
        }
//
//        //      2.  Вывести сведения о студентах, обучающихся более 4 лет.
//        
//        System.out.println("\nСтуденты, обучающиеся больше 4 лет:");
//        Date date = new Date();
//        long difference_In_Years;
//        
//        for (Studentyi u : q) {
//            difference_In_Years = (date.getTime() - u.getStatusDate().getTime()) / (1000l * 60 * 60 * 24 * 365);
//            if (difference_In_Years >= 4) {
//                System.out.println(u.getFamiliya() + " " + u.getImya() + " " + u.getOtchestvo() + " " + u.getGruppyi().getNazvanie());
//            }
//        }


        //        Разделить группу, численностью более 25 человек на 2 отдельные группы
//        List<Studentyi> tempStudents = new ArrayList<>();
//        List<Gruppyi> tempGroups = new ArrayList<>();
//        Gruppyi group = new Gruppyi();
//        
//        String groupName;
//        
//        for (Gruppyi g : grs) {
//            if (g.getStudentyis().size() > 25) {
//                group = g;
//                groupName = g.getNazvanie().split("-")[0];
//                tempStudents.addAll(g.getStudentyis());
//                tempGroups.add(new Gruppyi(groupName + "-" + 1, new Date(), g.getKodPlana(), "created", new Date(), new HashSet<Studentyi>()));
//                tempGroups.add(new Gruppyi(groupName + "-" + 2, new Date(), g.getKodPlana(), "created", new Date(), new HashSet<Studentyi>()));
//                s.save(tempGroups.get(0));
//                s.save(tempGroups.get(1));
//            }
//        }
//        
//        grs = s.createQuery("from Gruppyi s").list();
//        
//        boolean isEven = true;
//        for (Studentyi st : tempStudents) {
//            if (isEven) {
//                st.setGruppyi(grs.get(grs.size() - 2));
//                isEven = false;
//            }
//            else { 
//                st.setGruppyi(grs.get(grs.size() - 1));
//                isEven = true;
//            }
//            s.update(st);
//        }
//        s.delete(group);
        
        s.flush();
        t.commit();
        s.close();
        sf.close();
    }

}
