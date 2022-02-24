let users_json = require('./users.json');
let books_json = require('./books.json');
let loans_json = require('./loans.json');

const usr = () => 
{
    let insert = users_json.map(user => 
    {
        let tmp = `INSERT INTO USER(first_name,last_name, email) VALUES ('${user.firstname}', '${user.lastname}', '${user.email}');`;
        console.log(tmp);
    });
}


const bks = () => 
{
    let insert = books_json.map(book => 
    {
        let tmp = `INSERT INTO BOOK(title,stock) VALUES ('${book.title}', '${book.stock}');`;
        console.log(tmp);
    });
}

const lns = () => 
{
    let insert = loans_json.map(loan => 
    {
        let tmp = `INSERT INTO LOAN(fk_id_user,fk_id_book,start_date,type,status) VALUES (${loan.fk_id_user},${loan.fk_id_book},'${loan.start_date}','${loan.type}','${loan.status}');`;
        console.log(tmp);
    });
}

//usr();
//bks();
//lns();