# OrganiZer
Application built to mimic github style Issues management. OrganiZe your life, events, non-software projects and more with this easy to use application.

# How it works?
The application is built using functionalities from Spring Boot. 

# Heirarchy
There 4 main services:
- Users
- Companies
- Projects
- Tasks

Each user can belong to multiple companies and projects at once.
User autentication feature is present but doesn't serve any purpose as of now (05/01/2023 UPDATE)

A company holds users and projects. Only users that belong to the company can also be registered to the company's projects.

A project keeps track of assigned users and the tasks that belong to it.

Tasks are the fundamental structure of this application. Multiple users can be assigned to a task.

# Planned Features
1) Authentication & Deployment to proper server so multiple users can perform actions simultaneously.
2) User roles: Based on the roles, grant more permissions to the user.
3) Mark tasks as completed or incomplete. Change the deadlines. Add sub-tasks to tasks. Add tasks related to each other. 
