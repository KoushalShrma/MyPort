import React from 'react';
import { motion } from 'framer-motion';
import { Github, Linkedin, Mail, Phone, MapPin, Award, BookOpen, Code, User } from 'lucide-react';

const Portfolio = () => {
  const skills = [
    { category: 'Languages', items: ['Java', 'JavaScript', 'SQL'] },
    { category: 'Frameworks', items: ['Spring', 'Hibernate', 'JPA'] },
    { category: 'Database', items: ['MySQL'] },
    { category: 'Tools', items: ['Maven', 'Postman'] },
    { category: 'Other', items: ['REST APIs'] }
  ];

  const education = [
    {
      degree: 'B.Tech in AI & ML',
      institution: 'Acropolis Institute of Technology and Research, Indore',
      period: '2023-2026',
      grade: 'CGPA: 73.7%'
    },
    {
      degree: 'Diploma in Mechanical Engineering',
      institution: 'Prestige Institute of Engineering Management and Research',
      period: '2019-2022',
      grade: 'CGPA: 8.45'
    },
    {
      degree: 'Higher Secondary Certificate',
      institution: 'Shri Maheshwari H. S. School, Mhow',
      period: '2017-2019',
      grade: '76.7%'
    }
  ];

  const projects = [
    {
      title: 'Find-A-Spot',
      description: 'Smart Parking System with real-time slot availability, advance booking, and smart city integration.',
      duration: '11/2024 - 04/2025',
      role: 'Database management and backend development',
      tech: ['Java', 'Spring', 'MySQL', 'REST APIs']
    }
  ];

  const achievements = [
    'Participated in All India Chess Competition (Online) on Chess.com',
    'Solved 100+ DSA Problems on LeetCode Platform',
    'NPTEL: Database Management System 62% (Elite Badge)',
    'NPTEL: Programming in Java 66% (Elite Badge)'
  ];

  return (
    <div className="portfolio">
      {/* Header Section */}
      <motion.header 
        className="hero"
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
      >
        <div className="hero-content">
          <motion.h1 
            className="hero-title"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 0.2 }}
          >
            Koushal Sharma
          </motion.h1>
          <motion.p 
            className="hero-subtitle"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 0.4 }}
          >
            Java Backend Developer | AI & ML Enthusiast
          </motion.p>
          <motion.p 
            className="hero-description"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 0.6 }}
          >
            Aspiring backend developer with a strong foundation in Java and a passion for building scalable server-side applications
          </motion.p>
          
          <motion.div 
            className="hero-contact"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 0.8 }}
          >
            <div className="contact-item">
              <MapPin size={16} />
              <span>Mhow, Madhya Pradesh, India</span>
            </div>
            <div className="contact-item">
              <Phone size={16} />
              <span>+91 810-913-3203</span>
            </div>
            <div className="contact-item">
              <Mail size={16} />
              <span>kousharmaa@gmail.com</span>
            </div>
          </motion.div>

          <motion.div 
            className="hero-links"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, delay: 1 }}
          >
            <a href="https://github.com/KoushalShrma" target="_blank" rel="noopener noreferrer" className="social-link">
              <Github size={20} />
              <span>GitHub</span>
            </a>
            <a href="https://linkedin.com/in/KoushalShrma" target="_blank" rel="noopener noreferrer" className="social-link">
              <Linkedin size={20} />
              <span>LinkedIn</span>
            </a>
          </motion.div>
        </div>
      </motion.header>

      {/* Skills Section */}
      <motion.section 
        className="section skills-section"
        initial={{ opacity: 0, y: 50 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        viewport={{ once: true }}
      >
        <div className="container">
          <h2 className="section-title">
            <Code size={24} />
            Technical Skills
          </h2>
          <div className="skills-grid">
            {skills.map((skillGroup, index) => (
              <motion.div 
                key={skillGroup.category}
                className="skill-group"
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6, delay: index * 0.1 }}
                viewport={{ once: true }}
              >
                <h3 className="skill-category">{skillGroup.category}</h3>
                <div className="skill-items">
                  {skillGroup.items.map((skill) => (
                    <span key={skill} className="skill-item">{skill}</span>
                  ))}
                </div>
              </motion.div>
            ))}
          </div>
        </div>
      </motion.section>

      {/* Education Section */}
      <motion.section 
        className="section education-section"
        initial={{ opacity: 0, y: 50 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        viewport={{ once: true }}
      >
        <div className="container">
          <h2 className="section-title">
            <BookOpen size={24} />
            Education
          </h2>
          <div className="education-timeline">
            {education.map((edu, index) => (
              <motion.div 
                key={index}
                className="education-item"
                initial={{ opacity: 0, x: -20 }}
                whileInView={{ opacity: 1, x: 0 }}
                transition={{ duration: 0.6, delay: index * 0.2 }}
                viewport={{ once: true }}
              >
                <div className="education-content">
                  <h3 className="education-degree">{edu.degree}</h3>
                  <p className="education-institution">{edu.institution}</p>
                  <div className="education-details">
                    <span className="education-period">{edu.period}</span>
                    <span className="education-grade">{edu.grade}</span>
                  </div>
                </div>
              </motion.div>
            ))}
          </div>
        </div>
      </motion.section>

      {/* Projects Section */}
      <motion.section 
        className="section projects-section"
        initial={{ opacity: 0, y: 50 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        viewport={{ once: true }}
      >
        <div className="container">
          <h2 className="section-title">
            <User size={24} />
            Projects
          </h2>
          <div className="projects-grid">
            {projects.map((project, index) => (
              <motion.div 
                key={index}
                className="project-card"
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6, delay: index * 0.2 }}
                viewport={{ once: true }}
                whileHover={{ y: -5 }}
              >
                <h3 className="project-title">{project.title}</h3>
                <p className="project-description">{project.description}</p>
                <div className="project-meta">
                  <span className="project-duration">{project.duration}</span>
                  <span className="project-role">{project.role}</span>
                </div>
                <div className="project-tech">
                  {project.tech.map((tech) => (
                    <span key={tech} className="tech-tag">{tech}</span>
                  ))}
                </div>
              </motion.div>
            ))}
          </div>
        </div>
      </motion.section>

      {/* Achievements Section */}
      <motion.section 
        className="section achievements-section"
        initial={{ opacity: 0, y: 50 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        viewport={{ once: true }}
      >
        <div className="container">
          <h2 className="section-title">
            <Award size={24} />
            Achievements & Certifications
          </h2>
          <div className="achievements-grid">
            {achievements.map((achievement, index) => (
              <motion.div 
                key={index}
                className="achievement-item"
                initial={{ opacity: 0, x: -20 }}
                whileInView={{ opacity: 1, x: 0 }}
                transition={{ duration: 0.6, delay: index * 0.1 }}
                viewport={{ once: true }}
              >
                <div className="achievement-icon">
                  <Award size={20} />
                </div>
                <span className="achievement-text">{achievement}</span>
              </motion.div>
            ))}
          </div>
        </div>
      </motion.section>

      {/* Footer */}
      <motion.footer 
        className="footer"
        initial={{ opacity: 0 }}
        whileInView={{ opacity: 1 }}
        transition={{ duration: 0.8 }}
        viewport={{ once: true }}
      >
        <div className="container">
          <p>&copy; 2024 Koushal Sharma. All rights reserved.</p>
          <p>Built with Java Spring Boot & React</p>
        </div>
      </motion.footer>
    </div>
  );
};

export default Portfolio;