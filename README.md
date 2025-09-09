# Library CLI Application

A command-line application to manage **Books**, **Members**, and **Loans** in a library. Built using **Java**, **Picocli CLI framework**, and follows **best practices** with layered architecture (Repository → Service → CLI).  

This application supports full in-memory management of books, members, and loan records, including validation, sorting, searching, issuing, returning, overdue calculations, and fine computation.

---

## Features

### Books
- Add, update, delete books
- Search books by **title**, **author**, **genre**, or **ISBN**
- List all books with sorting by **title** or **year**
- Validate book data before adding or updating

### Members
- Register, update, delete members
- Activate or deactivate members
- Search members by **ID**, **name**, or **email**
- List members filtered by **active/inactive** status
- Email validation using `Email` record

### Loans
- Issue books to active members
- Return books
- Calculate overdue days and fines (Rs. 10/day)
- List loans by **book**, **member**, or all overdue loans

---

## Folder Structure
```
library-cli/
├── pom.xml
├── README.md
├── src/main/java/com/deepak/librarycli/
│ ├── cli/
│ │ ├── book/
│ │ │ ├── AddBookCommand.java
│ │ │ ├── ListBookCommand.java
│ │ │ ├── FindBookCommand.java
│ │ │ ├── UpdateBookCommand.java
│ │ │ ├── DeleteBookCommand.java
│ │ │ └── BookCommands.java
│ │ ├── member/
│ │ │ ├── AddMemberCommand.java
│ │ │ ├── ListMemberCommand.java
│ │ │ ├── FindMemberCommand.java
│ │ │ ├── UpdateMemberCommand.java
│ │ │ ├── DeleteMemberCommand.java
│ │ │ ├── ActivateMemberCommand.java
│ │ │ ├── DeactivateMemberCommand.java
│ │ │ └── MemberCommands.java
│ │ └── loan/
│ │ ├── IssueLoanCommand.java
│ │ ├── ReturnLoanCommand.java
│ │ └── LoanCommands.java
│ ├── model/
│ │ ├── Book.java
│ │ ├── Member.java
│ │ ├── Loan.java
│ │ ├── Genre.java
│ │ ├── MemberStatus.java
│ │ └── Email.java
│ └── service/
│ ├── BookService.java
│ ├── MemberService.java
│ └── LoanService.java
└── repository/
├── BookRepository.java
├── MemberRepository.java
└── LoanRepository.java
```


---

## Prerequisites

- Java 17+
- Maven
- Git (optional)

---

## Build & Run

1. Clone the repository:

```bash
git clone <your-repo-url>
cd library-cli
```
2. Build the projects
```bash
mvn clean compile
```
3. Run the CLI
```bash
mvn exec:java -Dexec.mainClass="com.deepak.librarycli.Main"
```

## CLI COMMANDS
### Book commands
| Command                                                                                             | Description         |    
| --------------------------------------------------------------------------------------------------- | ------------------- |
| `book add --isbn <isbn> --title <title> --author <author> --genre <genre> --year <year>`            | Add a new book      |   
| `book list [--sort title/year]  `                                                                   | List all books      |
| `book find [--title <title>] [--author <author>] [--isbn <isbn>] [--genre <genre>]`                 | Search books        |                                
| `book update --isbn <isbn> [--title <title>] [--author <author>] [--genre <genre>] [--year <year>]` | Update book details |
| `book delete --isbn <isbn>`                                                                         | Delete a book       |             

### Member Commands
| Command                                                     | Description           |              
| ----------------------------------------------------------- | --------------------- |
| `member add --name <name> --email <email>`                  | Register a new member |              
| `member list [--status ACTIVE/INACTIVE]`                    | List members          |
| `member find [--id <id>] [--name <name>] [--email <email>]` | Search members        |              
| `member update --id <id> [--name <name>] [--email <email>]` | Update member         |              
| `member delete --id <id>`                                   | Delete a member       |              
| `member activate --id <id>`                                 | Activate member       |              
| `member deactivate --id <id>`                               | Deactivate member     |    

### Loan Commands
| Command                                                             | Description                                      |
| ------------------------------------------------------------------- | ------------------------------------------------ |
| `loan issue --isbn <isbn> --member <memberId> [--date yyyy-MM-dd]`  | Issue a book to a member                         |
| `loan return --isbn <isbn> --member <memberId> [--date yyyy-MM-dd]` | Return a book                                    |


## Examples
```bash
# Add a book
book add --isbn 1234 --title "Java Basics" --author "John Doe" --genre FICTION --year 2023

# Add a member
member add --name "Alice" --email "alice@example.com"

# Issue a book
loan issue --isbn 1234 --member M0001 --date 2025-09-09

# Return a book
loan return --isbn 1234 --member M0001 --date 2025-09-20

# List all books sorted by title
book list --sort title

# Search a book by title
book find --title "Java"

# Activate a member
member activate --id M0001

# Deactivate a member
member deactivate --id M0001

```

