What is SAGA?
The Saga architecture pattern provides transaction management using a sequence of local transactions. A local transaction is the unit of work performed by a saga participant. Every operation that is part of the Saga can be rolled back by a compensating transaction. Further, the Saga pattern guarantees that either all operations are complete successfully or the corresponding compensation transactions are run to undo the work previously completed.

The SAGA Design Pattern is also a way to manage data consistency across microservices in distributed transaction scenarios.