package edu.miu.cs544.sujan;

import edu.miu.cs544.sujan.entity.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JOB_PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /*
        * Native query to return job with application
        * application has unidirectional oneToOne relation to job
        * */
        String nativeQuery = "SELECT j.* FROM application as a inner join job as j on a.job_id=j.id";
        Query query1 = em.createNativeQuery(nativeQuery, Job.class);
        List<Job> result = query1.getResultList();
        System.out.println(result.toString());
        System.out.println();
        /*
        * Dynamic query to return all interviews within a week
        * */
        String jpqlQuery = "select i from Interview as i where i.date between :date1 and :date2";
        TypedQuery<Interview> query2 = em.createQuery(jpqlQuery, Interview.class);
        query2.setParameter("date1", LocalDate.now().minusDays(7));
        query2.setParameter("date2", LocalDate.now());
        System.out.println(query2.getResultList());
        System.out.println();

        /*
        * Named query to return all Jobs with Companies in a certain state
         * */
        TypedQuery<Job> query3 =
                em.createNamedQuery("Job.findCompanies", Job.class);
        query3.setParameter("state", "IA");
        System.out.println(query3.getResultList());
        System.out.println();


        /*
        * Criteria API query to return all Skills for Jobs with salary > certain amount
        *  and with a company in a certain state
        * */
        CriteriaBuilder criBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Job> criQuery = criBuilder.createQuery(Job.class);
        Root<Job> rootJob = criQuery.from(Job.class);
        criQuery.select(rootJob.get("skills"));
        Predicate salaryPredicate =
                criBuilder.greaterThan(rootJob.get("salary"),
                        5000);
        Join<Job, Company> joinCompany =
                rootJob.join("company");
        Predicate statePredicate =
                criBuilder.equal(joinCompany.get("address").get("state"),
                        "IA");
        Predicate andPredicate=
                criBuilder.and(salaryPredicate,
                        statePredicate);
        criQuery.where(andPredicate);
        TypedQuery<Job>
                query = em.createQuery(criQuery);

        System.out.println(query.getResultList());
        System.out.println();



        /*native query to return all Jobs with Companies in a certain state
         * */
//        String query5 = "select * from company as c inner join job as j on c.id = j.company_id where c.type='Company' and j.salary>=:salary";
        String query5 = "select * from company as c inner join job as j on c.id = j.company_id where c.type=?type and j.salary>=?salary";
        Query nativeQuery2 = em.createNativeQuery(query5, Company.class);
        nativeQuery2.setParameter("type", "Recruiter");
        nativeQuery2.setParameter("salary", 10000);
        System.out.println(nativeQuery2.getResultList());

        /*
        * jpql query to return all jobs with at least 2 interview
        * */
        String query6 = "select j from Job as j where size(j.interviews) > 2 ";
        Query jpqlQuery2 = em.createQuery(query6, Job.class);
        System.out.println(jpqlQuery2.getResultList());

//        setData(em);
        tx.commit();
        em.close();
        emf.close();
    }

    private static void setData(EntityManager em) {
        ScreeningInterview screeningInterview =
                new ScreeningInterview(LocalDate.now(), "6418191456", "smaka@miu.edu", "sujan", "passed");
        em.persist(screeningInterview);

        TechnicalInterview technicalInterview =
                new TechnicalInterview(LocalDate.now(), "6517892354", "jenny@miu.edu", 20, Location.IN_PERSON, Arrays.asList(new Question("What is your weakness?"), new Question("What is your strength?")));
        em.persist(technicalInterview);

        HiringManagerInterview hiringManagerInterview =
                new HiringManagerInterview(LocalDate.now(), "2341222334", "srk@miu.edu", 10, LocalDate.now());
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
        Application application1 = new Application(LocalDate.now(), "2.0", job1);
        Application application2 = new Application(LocalDate.now(), "3.0", job2);
        em.persist(application1);
        em.persist(application2);



    }
}
