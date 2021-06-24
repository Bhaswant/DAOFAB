**Transaction Tracker Service**
1. As the name suggests, this service provides APIs to get the list of transactions based on the seed data provided.
2. It also provides the installements paid for each transaction occurred.
3. These APIs are meant for the use of UI, however are user friendly as well since they output data in json files.
4. pom file is within the above directory which acts as a starting point for the service.


**How to Run**

1. Compile the code with the command "mvn clean install" 
2. Execute the following command: java -jar <jar file> <parent json> <child json>
3. Trigger the following


**Following APIs are supported**

To fetch parent transactions:
1. /v1/transactions - Fetches the 1st page with default page size of 2
2. /v1/transactions?pageNo=1&pageSize=2 - Fetches the transactions information with provided page no and page size without any sorting.
3. /v1/transactions?pageNo=1&pageSize=2&sortCol=parentId - Fetches the transactions information with provided page no, page size and sorting on parent id
4. /v1/transactions?sortCol=parentId - Fetches 1st page with default page size and sorting on parent id.

To fetch child Transactions:
5. /v1/transactions/child?parentId= - Returns the sorted list of children along with their parent attributes. Additional attributes like parent Id are sent for clarity. UI can filter them out.

**Anatomy of the service**:
This service is divided into 4 layers:
    1. Controller - First point of contact for the REST API
    2. Service - Layer that performs required business logic and interacts with both DB and controller.
    3. Repository - Layer that sits nearer to the business logic but that contains CRUD of entities.
    4. Database - Actual persistence of the data.
