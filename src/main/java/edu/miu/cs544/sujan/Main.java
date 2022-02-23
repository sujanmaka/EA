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


//        setData(em);
        tx.commit();
        em.close();
        emf.close();
    }

    private static void setData(EntityManager em) {
        ScreeningInterview screeningInterview =
                new ScreeningInterview(new Date(), "6418191456", "smaka@miu.edu", "sujan", "passed");
        em.persist(screeningInterview);

        TechnicalInterview technicalInterview =
                new TechnicalInterview(new Date(), "6517892354", "jenny@miu.edu", 20, Location.IN_PERSON, Arrays.asList(new Question("What is your weakness?"), new Question("What is your strength?")));
        em.persist(technicalInterview);

        HiringManagerInterview hiringManagerInterview =
                new HiringManagerInterview(new Date(), "2341222334", "srk@miu.edu", 10, new Date());
        em.persist(hiringManagerInterview);


        Job job1 = new Job("Developer", 200000);
        Job job2 = new Job("Manager", 200000);
        job1.setInterviews(Arrays.asList(screeningInterview, technicalInterview, hiringManagerInterview));
        job2.setInterviews(Arrays.asList(screeningInterview));
        Skill skill1 = new Skill("Coding", "4 years", "Backend expertise", "Java");
        Skill skill2 = new Skill("Management", "10 years", "Managing skill", "English");
        Skill skill3 = new Skill("Designing", "5 years", "Designing skill", "CSS/HTML");
        Skill skill4 = new Skill("Quality Assurance", "2 years", "QC skill", "English");
        job1.setSkills(Arrays.asList(skill1, skill3));
        job2.setSkills(Arrays.asList(skill2, skill4));

        Company company = new Company("Google Technology LLC", new Address("1000 N 4th street", "Fairfield", "52557", "IA"));

        Client client1 = new Client("Renegrade Insurance LLC", new Address("57 kilvert", "Rhode Island", "34211", "MA"), "To revolutionize insurance", "For no reason", "www.rengadeinsurance.com");
        em.persist(client1);

        Client client2 = new Client("Facebook LLC", new Address("123 New Road", "Palo Alto", "32900", "CA"), "Social network", "Marketing and Advertisement", "www.fb.com");
        em.persist(client2);

        Recruiter recruiter = new Recruiter("Alpha Beta Recruiter", new Address("123 New Street", "New York", "23000", "NY"));
        recruiter.setClients(Arrays.asList(client1, client2));
        em.persist(recruiter);

        job1.setCompany(company);
        Application application1 = new Application(new Date(), "2.0", job1);
        Application application2 = new Application(new Date(), "3.0", job2);
        em.persist(application1);
        em.persist(application2);



    }
}
