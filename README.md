# ProgrammingAssignment4
  In this task, you are supposed to implement a Java program for the scheduling problem of a famous online order delivery company by using heap-based priority queue. The manager is trying to find how many couriers work in the order delivery part. For each courier in the order delivery, the expense of the company increases; but according to the standards of this company, the average waiting time for all customers should not exceed a given amount of time. So, the manager needs to optimize this number and calls for your help in this task. This company has the data of predict delivery time of customers. Your program should use these data to calculate average waiting times and find the minimum number of couriers needs to meet the average waiting time requirement.

  • The customer with the highest priority should be examined first.
  
  • In case of having two customers with the same highest priority, the customer who has
  waited longer should be selected first.
  
  • If more than one couriers is available at a given time; the customer is assigned to the
  courier with a lower id.
  
  • When a courier starts giving a service to a customer, the courier should finish his service
  with this customer even though another customer with a higher priority gives order to
  company.
  
  • Once a customer is assigned to a courier, the courier immediately starts delivering order
  of that customer and is not available during the service time given for that
  customer. After the service of that customer carries out, the courier becomes available
  immediately.
  
  • The waiting time of a customer is the duration (difference) between the given order time
  of the customer and the time he is assigned to a courier.

  Our code follows a path starting from the class main to the class heapPQ, throughout its path it uses objects of customer and couriers classes. After gathering our txt file and the desired maximum waiting time, we create an array of customers using txtToArray() method. We give this array and the desired waiting time to simulateTest(). simulateTest() runs continuous simulations with increasing courier counts to find the minimum couriers required. After that it runs simulate(), which is identical to simulateTest() except for the outer loop which tries increasing courier counts as we already have the correct courier count which outputs the order of customers being taken care of, the couriers that took care of them, their order times, the current time and the time they waited thus our algorithm is complete after simulate. These simulations run based on current time, they enlist the new coming customers, update the couriers to see if their availability has changed, assign the available couriers to the top priority customers. While doing all of these they store the waiting time of each customer and calculate an average time. These simulations use the methods required for heapPQ such as enlist() and delMax() and also a crucial method called blockAvailableCouriers() which checks each courier, if available give them the top customer and print the situation if not in test mode. To update the couriers availability, we use two methods of the class couriers; blockCourier() and renewAvailability(). blockCourier() assigns false to availability status of the courier and stores the time they’ll be available. renewAvailability compares the time they’ll be available to the current time and if their time has come return them to available.