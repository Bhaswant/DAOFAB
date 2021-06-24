How to Run:

Compile the code with the command "mvn clean install"
Execute the following command: java -jar
Trigger the following APIs


Following APIs are supported

To fetch parent transactions:
/v1/transactions - Fetches the 1st page with default page size of 2
/v1/transactions?pageNo=1&pageSize=2 - Fetches the transactions information with provided page no and page size without any sorting.
/v1/transactions?pageNo=1&pageSize=2&sortCol=parentId - Fetches the transactions information with provided page no, page size and sorting on parent id
/v1/transactions?sortCol=parentId - Fetches 1st page with default page size and sorting on parent id.

To fetch child Transactions:
/v1/transactions/child?parentId= - Returns the sorted list of children along with their parent attributes. Additional attributes like parent Id are sent for clarity. UI can filter them out.

