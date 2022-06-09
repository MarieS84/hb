Systemmap is used to document systems and their runtime, service and system integration.

The program is Maven-based and built using the Spring framework. It uses an in-memory h2 database which is reached at http://localhost:8080/h2.
The program is run from Windows command prompt by typing java -jar target/systemmap-1.0-0.0.1-SNAPSHOT.jar
When launched, the user is presented with a menu with 10 options. It is preferable to start by creating a new Business System.
When a task is completed the user is informed and has the choice of returning to the menu or ending the program.

The entities are:
BusinessSystem
Runtime
Service
SystemIntegration

BusinessSystem has a OneToOne relationship with Runtime, Service and SystemIntegration. All 4 entities can be created and deleted.
If BusinessSystem is deleted, the related entities are deleted as well, but Runtime, Service and SystemIntegration can also be deleted 
separately without deleting their related BusinessSystem.


Still to do:

Relationship between Business system and Runtime should be OneToMany

Relationship between Runtime and Service should be OneToMany

Relationship between Business system and System Integration should be ManyToMany

Improve error and exception handling to better guide the user and make sure program doesn't crash in case of
erroneous input.

Understand the batch update possibility in order to update several Business Systems, and be able to update 
all variables (not just name and description as is currently the case with updateBusinessSystems());



