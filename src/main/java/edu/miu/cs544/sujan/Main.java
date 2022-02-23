package edu.miu.cs544.sujan;

import edu.miu.cs544.sujan.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JOB_PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        Job job = new Job("Developer", 200000);
        Skill skill1 = new Skill("Coding", "4 years", "Backend expertise", "Java");
        Skill skill2 = new Skill("Management", "10 years", "Managing skill", "English");
        job.setSkills(Arrays.asList(skill1, skill2));

        Company company = new Company("Google Technology LLC", new Address("1000 N 4th street", "Fairfield", "52557", "IA"));

        Client client1 = new Client("Renegrade Insurance LLC", new Address("57 kilvert", "Rhode Island", "34211", "MA"), "To revolutionize insurance", "For no reason", "www.rengadeinsurance.com");
        em.persist(client1);

        Client client2 = new Client("Facebook LLC", new Address("123 New Road", "Palo Alto", "32900", "CA"), "Social network", "Marketing and Advertisement", "www.fb.com");
        em.persist(client2);

        Recruiter recruiter = new Recruiter("Alpha Beta Recruiter", new Address("123 New Street", "New York", "23000", "NY"));
        recruiter.setClients(Arrays.asList(client1, client2));
        em.persist(recruiter);

        job.setCompany(company);
        Application application = new Application(new Date(), "2.0", job);
        em.persist(application);


        ScreeningInterview screeningInterview =
                new ScreeningInterview(new Date(), "6418191456", "smaka@miu.edu", "sujan", "passed");
        em.persist(screeningInterview);

        TechnicalInterview technicalInterview =
                new TechnicalInterview(new Date(), "6418191456", "smaka@miu.edu", 20, Location.IN_PERSON, Arrays.asList(new Question("What is your weakness?"), new Question("What is your strength?")));
        em.persist(technicalInterview);

        HiringManagerInterview hiringManagerInterview =
                new HiringManagerInterview(new Date(), "6418191456", "smaka@miu.edu", 10, new Date());
        em.persist(hiringManagerInterview);
        tx.commit();
        em.close();
        emf.close();
    }
}
