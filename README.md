# springbootmanytomany
Demo of many to many mapping using Hibernate. 

The relationship shown is of student entity and course enitity. A student can take many courses and a course can be taken by many students.
Implemented lazy fetch. Dealt with Hibernate proxy objects. Hibernate proxy objects cannot be directly used after the session is closed.
You need to convert the proxy Hibernate objects to real objects or to unproxy it by loading them to the memory. 
You can load them to the memory by calling the getter methods on the fields and for a collection you can call size() method to load the 
child objects of the entity to the memory. You may also alternatively print the entity that implements the toString() method and accesses the fields. 
This unproxying of entity objects is necessary to pass them around the application and use them outside the session like a normal object.
If you dont unproxy it before use out session you get lazyinitializationexception.
You can also use JOIN FETCH as another solution to this.

Another thing we need to take care of is during deleting a course or a student.
For a student to be deleted it must be deleted from the collection of all courses the student has taken. This results in Hibernate deleting entries of the student from the Join Table (student_course). The student id is a foreign key to this table so if we dont remove the student_id values before deleting the student we get a Foreign key constraint error that its being referenced.

Operations to entity objects are mapped to operations in the database tables by Hibernate. We only need to deal / operate with objects. We are abstracted from the use of tables.



