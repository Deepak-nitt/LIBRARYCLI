# Library CLI Application

A command-line application to manage **Books**, **Members**, and **Loans** in a library. Built using **Java**, **Picocli CLI framework**, and follows **best practices** with layered architecture (Repository → Service → CLI).  

This application supports full in-memory management of books, members, and loan records, including validation, sorting, searching, issuing, returning, overdue calculations, and fine computation.



## Prerequisites

- Java 17+
- Maven
- Git (optional)


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

### Loans
- Issue books to active members
- Return books
- Calculate overdue days and fines (Rs. 10/day)

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
# -------------------------
# Book Commands
# -------------------------

# Add a book
book add --isbn 1234 --title "Java Basics" --author "John Doe" --genre FICTION --year 2023

# Add another book
book add --isbn 5678 --title "Advanced Java" --author "Jane Smith" --genre FANTASY --year 2024

# List all books
book list

# List all books sorted by title
book list --sort title

# List all books sorted by year
book list --sort year

# Find a book by title
book find --title "Java Basics"

# Find a book by author
book find --author "John Doe"

# Find a book by ISBN
book find --isbn 1234

# Find a book by genre
book find --genre FICTION

# Update a book
book update --isbn 1234 --title "Java Basics Updated" --author "John Doe" --year 2025
book update --isbn 1234 --genre MYSTERY

# Delete a book
book delete --isbn 1234

# -------------------------
# Member Commands
# -------------------------

# Add/Register a member
member add --name "Alice" --email "alice@example.com"

# Add another member
member add --name "Bob" --email "bob@example.com"

# List all members
member list

# List active members
member list --status ACTIVE

# List inactive members
member list --status INACTIVE

# Find a member by ID
member find --id M0001

# Find a member by name
member find --name "Alice"

# Find a member by email
member find --email "alice@example.com"

# Update a member
member update --id M0001 --name "Alice Updated" --email "alice.new@example.com"

# Delete a member
member delete --id M0001

# Activate a member
member activate --id M0002

# Deactivate a member
member deactivate --id M0002

# -------------------------
# Loan Commands
# -------------------------

# Issue a book to a member
loan issue --isbn 5678 --member M0002 --date 2025-09-10

# Return a book
loan return --isbn 5678 --member M0002 --date 2025-10-26
