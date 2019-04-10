/* Question 1 */

select noOfCopies from tbl_book_copies where bookId In (
select bookId From tbl_book where title Like 'The Shadow of The Wind' ) and branchId in (
select branchId from tbl_library_branch where branchName like 'Sharpstown');

select noOfCopies
from tbl_book_copies 
join tbl_book,tbl_library_branch
Where tbl_book.title like 'The Shadow of The Wind'
and tbl_library_branch.branchName like 'Sharpstown'
and tbl_book_copies.bookId = tbl_book.bookId 
and tbl_book_copies.branchId = tbl_library_branch.branchId;

/* Question 2 */


select branchName,noOfCopies
from tbl_book_copies 
join tbl_book,tbl_library_branch
Where tbl_book.title like 'The Shadow of The Wind'
and tbl_book_copies.bookId = tbl_book.bookId 
and tbl_book_copies.branchId = tbl_library_branch.branchId
group by tbl_library_branch.branchName ;

/* Question 3 */

select name
from tbl_borrower
left join tbl_book_loans
on tbl_borrower.cardNo = tbl_book_loans.cardNo
Where tbl_book_loans.cardNo is null;

select name
from tbl_borrower
join tbl_book_loans
Where tbl_borrower.cardNo = tbl_book_loans.cardNo
and tbl_book_loans.bookId is null
and tbl_book_loans.dateOut is null;

select name 
from tbl_borrower
where not exists
(select cardNo from tbl_book_loans where tbl_book_loans.cardNo is not null);

/* Question 4 */

select title,name,address
from tbl_book
join tbl_book_loans,tbl_library_branch, tbl_borrower
where tbl_library_branch.branchName like 'Sharpstown'
and tbl_book_Loans.bookId = tbl_book.bookId 
and tbl_book_loans.branchId = tbl_library_branch.branchId
and tbl_book_loans.dueDate like '2019-03-27 00:00:00';

select title,name,address
from tbl_book
join tbl_book_loans,tbl_library_branch, tbl_borrower
where tbl_library_branch.branchName like 'Sharpstown'
and tbl_book_Loans.bookId = tbl_book.bookId 
and tbl_book_loans.branchId = tbl_library_branch.branchId
and tbl_book_loans.dueDate like curdate();


/* Question 5 */

select branchName, count(bookId)
from tbl_library_branch
join tbl_book_loans
where tbl_book_loans.dateOut is not null 
and tbl_book_loans.branchId = tbl_library_branch.branchId
group by tbl_library_branch.branchName;


/* Question 6 */


select name,address, count(bookId) as BooksBorrowed
from tbl_borrower
join tbl_book_loans
where tbl_book_loans.dateOut is not null 
and tbl_book_loans.cardNo = tbl_borrower.cardNo
group by tbl_borrower.name;


/* Question 7 */


select title,count(tbl_book_copies.bookId) as NumberOfCopies
from tbl_book
join tbl_book_copies,tbl_library_branch,tbl_author
where tbl_library_branch.branchName like 'Once Upon A Time'
and tbl_author.authorId = tbl_book.authId 
and tbl_book_copies.branchId = tbl_library_branch.branchId
and tbl_author.authorName like 'Carlos Zafone';

