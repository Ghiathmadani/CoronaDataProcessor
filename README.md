# CoronaDataProcessor
Simple meeting data processor to track contacts &amp; infections

Create a simple meeting data processor to track contacts & infections.
Specification:

1 Create a command line application in Java or C#/.NET that accepts two file paths as
arguments and prints the output to console.

2 The input files should be in CSV format (Use ‘,’ as field delimiter and ‘;’ as delimiter
for list items within one field. Expected date format is ‘yyyy/MM/dd HH:mm’.).

3 The first argument corresponds to the persons CSV file with the following columns:
ID (integer) & date of the last positive disease check.

4 The second argument corresponds to the meetings CSV file with the following
columns: ID (integer), start date, end date & IDs of the participants.

5 After successfully importing all data, the distinct IDs of all persons who had contact
with a diseased person (direct or one level in between) in the past seven days
should be printed to the console (current date should be ‘faked’ to
‘2021/03/21 20:00’ to ensure that the same input always produces the same
output).







Persons.csv example:

1,

2,2021/03/21 14:00

3,2021/03/19 17:00

4,2021/03/24 12:00

5,

6,

7,

8,2021/03/12 17:00

9,2021/03/15 22:00

Meetings.csv example:

1,2021/03/19 12:00,2021/03/19 13:00,2;3;5

2,2021/03/17 12:30,2021/03/17 14:00,1;5

3,2021/03/16 16:00,2021/03/16 16:30,1;5

4,2021/03/14 12:30,2021/03/14 14:00,1;2;3;4;5

5,2021/03/22 09:00,2021/03/22 11:00,1;2;3;4;5

6,2021/03/19 10:00,2021/03/19 11:00,7;8

7,2021/03/21 17:00,2021/03/21 19:00,6;7

8,2021/03/19 13:00,2021/03/19 15:00,8;9
