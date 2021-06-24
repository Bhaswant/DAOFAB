How to Run:&nbsp;

1. Compile the code with the command "mvn clean install" \
2. Execute the following command: java -jar <jar file> <parent json> <child json>\
3. Trigger the following


Following APIs are supported

To fetch parent transactions:\
1. /v1/transactions - Fetches the 1st page with default page size of 2\
2. /v1/transactions?pageNo=1&pageSize=2 - Fetches the transactions information with provided page no and page size without any sorting.\
3. /v1/transactions?pageNo=1&pageSize=2&sortCol=parentId - Fetches the transactions information with provided page no, page size and sorting on parent id\
4. /v1/transactions?sortCol=parentId - Fetches 1st page with default page size and sorting on parent id.

To fetch child Transactions:
5. /v1/transactions/child?parentId= - Returns the sorted list of children along with their parent attributes. Additional attributes like parent Id are sent for clarity. UI can filter them out.

