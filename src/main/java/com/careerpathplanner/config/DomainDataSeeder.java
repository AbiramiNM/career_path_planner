package com.careerpathplanner.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.careerpathplanner.model.CareerDomain;
import com.careerpathplanner.model.DomainCertification;
import com.careerpathplanner.model.DomainRoadmapStep;
import com.careerpathplanner.model.DomainSkill;
import com.careerpathplanner.repository.CareerDomainRepository;
import com.careerpathplanner.repository.DomainCertificationRepository;
import com.careerpathplanner.repository.DomainRoadmapStepRepository;
import com.careerpathplanner.repository.DomainSkillRepository;

@Component
public class DomainDataSeeder implements ApplicationRunner {

    private final CareerDomainRepository careerDomainRepository;
    private final DomainSkillRepository domainSkillRepository;
    private final DomainRoadmapStepRepository domainRoadmapStepRepository;
    private final DomainCertificationRepository domainCertificationRepository;

    public DomainDataSeeder(CareerDomainRepository careerDomainRepository,
                            DomainSkillRepository domainSkillRepository,
                            DomainRoadmapStepRepository domainRoadmapStepRepository,
                            DomainCertificationRepository domainCertificationRepository) {
        this.careerDomainRepository = careerDomainRepository;
        this.domainSkillRepository = domainSkillRepository;
        this.domainRoadmapStepRepository = domainRoadmapStepRepository;
        this.domainCertificationRepository = domainCertificationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (careerDomainRepository.count() > 0) {
            return;
        }

        seedITSoftware();
        seedCoreEngineering();
        seedGovernmentPublicSector();
        seedBusinessCommerce();
        seedCreativeMedia();
        seedSkilledTradesTechnical();
        seedSocialNgoEducation();
    }

    private void seedITSoftware() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "IT & Software",
                "Building and maintaining software, systems, and digital solutions.",
                "Desk-based; focus on problem-solving, coding, and collaboration; often flexible or remote options; project deadlines.",
                "Tech companies, startups, banks, healthcare IT, e-commerce, government IT, education platforms.",
                "High demand; strong salary growth; remote work; entrepreneurship; global opportunities; continuous learning."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Programming (e.g. Java, Python, JavaScript)", 1));
        domainSkillRepository.save(new DomainSkill(d, "Data structures and algorithms", 2));
        domainSkillRepository.save(new DomainSkill(d, "Version control (Git)", 3));
        domainSkillRepository.save(new DomainSkill(d, "Basic database and SQL", 4));
        domainSkillRepository.save(new DomainSkill(d, "Problem-solving and logical thinking", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Pick one language", "Start with Python or Java; do small exercises daily."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Learn basics of programming", "Variables, loops, functions, basic data structures."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Build small projects", "Console apps, simple web pages, or a small API."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Learn Git and collaborate", "Create a GitHub account; push code and follow a tutorial."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Explore one specialization", "Web dev, mobile, or data; build one portfolio project."));
        domainCertificationRepository.save(new DomainCertification(d, "CS50 (Harvard)", "Intro to computer science and programming", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "FreeCodeCamp / Codecademy", "Web development or Python tracks", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "AWS Cloud Practitioner / Microsoft Azure Fundamentals", "Cloud basics", "Beginner to Intermediate"));
    }

    private void seedCoreEngineering() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Core Engineering",
                "Design, analysis, and implementation of physical systems: mechanical, electrical, civil, and related fields.",
                "Mix of office, lab, and site; calculations and design; teamwork with technicians and other engineers; safety and standards.",
                "Manufacturing, construction, power, automotive, aerospace, infrastructure, R&D, PSUs.",
                "Stable demand; roles in core industries and PSUs; scope for higher studies and research; design and project leadership."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Strong fundamentals in Physics and Mathematics", 1));
        domainSkillRepository.save(new DomainSkill(d, "Technical drawing and CAD basics", 2));
        domainSkillRepository.save(new DomainSkill(d, "Domain-specific basics (e.g. mechanics, circuits)", 3));
        domainSkillRepository.save(new DomainSkill(d, "Problem-solving and analytical thinking", 4));
        domainSkillRepository.save(new DomainSkill(d, "Understanding of standards and safety", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Strengthen PCM", "Focus on class 11–12 or graduation-level fundamentals."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Choose a branch", "Mechanical, Electrical, Civil, etc.; explore via books and videos."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Learn basic CAD", "Try free tools or student versions of AutoCAD/SolidWorks."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Join clubs or projects", "Robotics, formula student, or college mini-projects."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Internships or workshops", "Apply for summer internships or skill workshops."));
        domainCertificationRepository.save(new DomainCertification(d, "NPTEL Engineering courses", "IIT-led courses in core subjects", "Beginner to Advanced"));
        domainCertificationRepository.save(new DomainCertification(d, "AutoCAD / SolidWorks certifications", "Design and drafting", "Beginner to Intermediate"));
        domainCertificationRepository.save(new DomainCertification(d, "Industry workshops (e.g. Siemens, TATA)", "Hands-on and tools", "Beginner"));
    }

    private void seedGovernmentPublicSector() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Government & Public Sector",
                "Jobs in government departments, PSUs, defence, railways, banking, and public services.",
                "Structured roles; fixed timings and benefits; hierarchy and rules; job security; transfer and posting policies.",
                "Central/state government, PSUs, banks, railways, defence, SSC, UPSC, state PSCs, public health and education.",
                "Job security; pensions and benefits; stable income; social respect; scope for internal growth and transfers."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Aptitude and quantitative ability", 1));
        domainSkillRepository.save(new DomainSkill(d, "Reasoning and logical ability", 2));
        domainSkillRepository.save(new DomainSkill(d, "General awareness and current affairs", 3));
        domainSkillRepository.save(new DomainSkill(d, "English or regional language (as per exam)", 4));
        domainSkillRepository.save(new DomainSkill(d, "Domain knowledge for technical posts", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Identify target exams", "UPSC, SSC, Banking, Railway, or state-level; check eligibility."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Build aptitude and reasoning", "Daily practice from standard books or online platforms."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Follow current affairs", "Newspaper, monthly magazines, or dedicated apps."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Take mock tests", "Time-bound tests to improve speed and accuracy."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Apply and revise", "Apply when notifications release; revise syllabus regularly."));
        domainCertificationRepository.save(new DomainCertification(d, "No single certification; exam-specific preparation", "Coaching or self-study for UPSC/SSC/Banking", "N/A"));
        domainCertificationRepository.save(new DomainCertification(d, "Current affairs and GK courses", "Online or offline", "Beginner"));
    }

    private void seedBusinessCommerce() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Business & Commerce",
                "Accounting, finance, marketing, sales, and running or advising businesses.",
                "Office and client-facing; numbers and reports; deadlines; teamwork and networking; often targets and KPIs.",
                "CA firms, banks, corporates, startups, retail, FMCG, consulting, stock markets, insurance.",
                "Growth in finance and consulting; entrepreneurship; diverse roles from analyst to leadership; global exposure."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Accounting and financial basics", 1));
        domainSkillRepository.save(new DomainSkill(d, "Excel and data presentation", 2));
        domainSkillRepository.save(new DomainSkill(d, "Communication and negotiation", 3));
        domainSkillRepository.save(new DomainSkill(d, "Understanding of markets and economy", 4));
        domainSkillRepository.save(new DomainSkill(d, "Analytical and decision-making ability", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Learn accounting basics", "Class 11–12 accountancy or a short course."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Get comfortable with Excel", "Formulas, pivot tables, simple charts."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Read business news", "Economic times, business sections, industry updates."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Choose a path", "CA, MBA, or job in finance/marketing/sales; research eligibility."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Internship or small venture", "Intern in a firm or help in a small business."));
        domainCertificationRepository.save(new DomainCertification(d, "Tally / accounting software", "Basic accounting and GST", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "NSE/BSE certifications", "Markets and investing basics", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "Digital marketing (Google, Meta)", "Marketing fundamentals", "Beginner"));
    }

    private void seedCreativeMedia() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Creative & Media",
                "Design, content, video, writing, and visual or digital storytelling.",
                "Project-based; often flexible; mix of solo and team work; portfolios and feedback; deadlines.",
                "Design studios, agencies, media houses, OTT, brands, freelancing, startups, education content.",
                "Freelancing; remote work; diverse industries needing content; scope to build a personal brand."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Visual design or writing fundamentals", 1));
        domainSkillRepository.save(new DomainSkill(d, "Use of at least one tool (Canva, Figma, editor)", 2));
        domainSkillRepository.save(new DomainSkill(d, "Understanding of audience and briefs", 3));
        domainSkillRepository.save(new DomainSkill(d, "Basic storytelling and composition", 4));
        domainSkillRepository.save(new DomainSkill(d, "Portfolio presentation", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Pick a focus", "Graphic design, UI/UX, video, or writing."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Learn one tool well", "Figma, Canva, Premiere, or a writing platform."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Create 3–5 sample works", "Fake briefs or personal projects."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Build a simple portfolio", "Notion, Behance, or a simple website."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Share and get feedback", "Social media, contests, or small clients."));
        domainCertificationRepository.save(new DomainCertification(d, "Google UX Certificate / Coursera UX", "UX design fundamentals", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "Canva / Adobe tutorials", "Design and visual communication", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "Content writing / SEO short courses", "Writing and content", "Beginner"));
    }

    private void seedSkilledTradesTechnical() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Skilled Trades & Technical",
                "Hands-on technical work: electrical, mechanical repair, installation, field service, and trades.",
                "On-site or workshop; tools and equipment; safety protocols; often shift or field-based; practical problem-solving.",
                "Manufacturing, utilities, automobiles, construction, telecom, HVAC, equipment servicing, PSUs, MSMEs.",
                "Steady demand; skill-based pay; scope for self-employment; government schemes for skill development."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Technical basics (electrical/mechanical as relevant)", 1));
        domainSkillRepository.save(new DomainSkill(d, "Use of tools and equipment", 2));
        domainSkillRepository.save(new DomainSkill(d, "Safety and standards", 3));
        domainSkillRepository.save(new DomainSkill(d, "Reading diagrams and manuals", 4));
        domainSkillRepository.save(new DomainSkill(d, "Troubleshooting and repair logic", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Choose a trade", "Electrical, mechanical, automobile, HVAC, or similar."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Formal or short course", "ITI, polytechnic, or skill-development program."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Safety training", "Get basic safety certification if required."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Apprenticeship or helper role", "Learn on the job under a skilled worker."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Practice and upgrade", "Keep learning new equipment or certifications."));
        domainCertificationRepository.save(new DomainCertification(d, "ITI / NCVT", "Government trade certification", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "Sector Skill Council (SSC) certifications", "Industry-aligned skills", "Beginner to Intermediate"));
        domainCertificationRepository.save(new DomainCertification(d, "Manufacturer-specific training", "e.g. automobile, electrical equipment", "Beginner"));
    }

    private void seedSocialNgoEducation() {
        CareerDomain d = careerDomainRepository.save(new CareerDomain(
                "Social / NGO & Education",
                "Teaching, counselling, community work, NGO coordination, and roles that directly help people or society.",
                "People-facing; often field or community visits; communication and empathy; flexible but emotionally demanding; mission-driven.",
                "Schools, colleges, NGOs, government schemes, health and welfare, CSR, training and development, UN and international NGOs.",
                "Meaningful work; diverse roles from teaching to program management; scope for advocacy and policy; fellowship programs."
        ));
        domainSkillRepository.save(new DomainSkill(d, "Communication and empathy", 1));
        domainSkillRepository.save(new DomainSkill(d, "Basic teaching or facilitation", 2));
        domainSkillRepository.save(new DomainSkill(d, "Organisation and documentation", 3));
        domainSkillRepository.save(new DomainSkill(d, "Awareness of social issues and policies", 4));
        domainSkillRepository.save(new DomainSkill(d, "Patience and adaptability", 5));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 1, "Volunteer locally", "Teaching, community drives, or NGO support."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 2, "Understand one cause", "Education, health, livelihood, or rights; read and follow organisations."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 3, "Build communication skills", "Presentations, writing, or counselling basics."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 4, "Consider formal path", "B.Ed, MSW, or development studies if you want to specialise."));
        domainRoadmapStepRepository.save(new DomainRoadmapStep(d, 5, "Apply for internships or fellowships", "NGO internships, Teach For India, or similar."));
        domainCertificationRepository.save(new DomainCertification(d, "Teaching eligibility (e.g. CTET preparation)", "For school teaching", "Beginner to Intermediate"));
        domainCertificationRepository.save(new DomainCertification(d, "Counselling or soft-skills courses", "Basic counselling and communication", "Beginner"));
        domainCertificationRepository.save(new DomainCertification(d, "NGO management / social work short courses", "Program and project basics", "Beginner"));
    }
}
